
import Controlador.Clases.Fabrica;
import Controlador.Clases.IControladorProductos;
import Controlador.Clases.IControladorUsuarios;
import Controlador.Clases.ManejadorEspProductos;
import Controlador.Clases.ManejadorUsuarios;
import Controlador.DataTypes.DataCategoria;
import Controlador.DataTypes.DataEspecificacionProducto;
import Controlador.DataTypes.DataProducto;
import Controlador.DataTypes.DataProveedor;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import static java.util.Objects.isNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


/**
 *
 * @author mauro
 */
public class ControladorProducto1Test {

    @Test
    public void RegistrarProductoTest() throws Exception {
        try {
            Integer idProductosControlador = Fabrica.getInstance().getControladorProductos(null).getId();
            IControladorProductos controlarProducto = Fabrica.getInstance().getControladorProductos(idProductosControlador);
            Integer idUsuariosControlador = Fabrica.getInstance().getControladorUsuarios(null).getId();
            IControladorUsuarios controlarUsuario = Fabrica.getInstance().getControladorUsuarios(idUsuariosControlador);

            Calendar cal5 = Calendar.getInstance();
            cal5.set(1930, 11, 1);
            controlarUsuario.ingresarDatosProveedor(new DataProveedor("cxav", "pass25", "Charles", "Xavier", "xxavier@gmail.com", cal5, "Xcel", "www.xcel.com"));
            controlarUsuario.guardarUsuario();

            DataCategoria category = new DataCategoria("category", null);
            controlarProducto.ingresarDatosCategoria(category);
            controlarProducto.guardarCategoria();

            //leer datos de nueva especificacion de IngresarDatosUnidad
            controlarProducto.elegirProveedor("pperez");
            DataProveedor proveedor1 = new DataProveedor(ManejadorUsuarios.getInstance().getProveedor("cxav"));
            DataEspecificacionProducto espProducto = new DataEspecificacionProducto("esp10", "nombre_prod", "nuevo", new HashMap<>(), (float) 2500.0, proveedor1, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            controlarProducto.ingresarDatosProductos(espProducto);
            controlarProducto.ingresarEspecificacion("estado", "nuevo");
            controlarProducto.ingresarEspecificacion("color", "gris");
            controlarProducto.ingresarEspecificacion("altura", "1 metro");
            Boolean es_bool = false;
            DataProducto producto1 = new DataProducto(1, "specif", es_bool, espProducto);
            controlarProducto.ingresarDatosUnidad(producto1);
            controlarProducto.agregarCategoriaAEspecificacion("category");
            controlarProducto.agregarImagen("peteco");
            //controlarProducto.guardarProducto();        
            if (controlarProducto.controlarErrores() && controlarProducto.validarInfo()) {
                controlarProducto.guardarProducto();
            }

            assertTrue(!isNull(controlarProducto.getId()));
            assertTrue(!isNull(controlarProducto.elegirTipoProducto()));
            assertEquals(ManejadorEspProductos.getInstance().getEspecificacionProducto("esp10").getListaProductos().get(0).getIdEspecifico(), "specif");
            assertEquals(ManejadorEspProductos.getInstance().getEspecificacionProducto("esp10").getListaProductos().get(0).getEspecificacionProducto().getDescripcion(), "nuevo");
            assertEquals(ManejadorEspProductos.getInstance().getEspecificacionProducto("esp10").getListaProductos().get(0).getEspecificacionProducto().getNombre(), "nombre_prod");
            assertEquals(ManejadorEspProductos.getInstance().getEspecificacionProducto("esp10").getListaProductos().get(0).getEspecificacionProducto().getNroReferencia(), "esp10");
            assertTrue((ManejadorEspProductos.getInstance().getEspecificacionProducto("esp10").getListaProductos().get(0).getEspecificacionProducto().getPrecio() == (float) 2500.0));
            assertEquals(ManejadorEspProductos.getInstance().getEspecificacionProducto("esp10").getListaProductos().get(0).getEspecificacionProducto().getDataProveedor().getLinkSitio(), "www.xcel.com");
            assertEquals(ManejadorEspProductos.getInstance().getEspecificacionProducto("esp10").getListaProductos().get(0).getEspecificacionProducto().getDataProveedor().getNombreCompania(), "Xcel");
            assertEquals(ManejadorEspProductos.getInstance().getEspecificacionProducto("esp10").getListaProductos().get(0).getEspecificacionProducto().getDataProveedor().getNombre(), "Charles");
            assertEquals(ManejadorEspProductos.getInstance().getEspecificacionProducto("esp10").getListaProductos().get(0).getEspecificacionProducto().getDataProveedor().getApellido(), "Xavier");
            assertEquals(ManejadorEspProductos.getInstance().getEspecificacionProducto("esp10").getListaProductos().get(0).getEspecificacionProducto().getDataProveedor().getNickname(), "cxav");

            assertEquals(ManejadorEspProductos.getInstance().getEspecificacionProducto("esp10").getListaProductos().get(0).getEspecificacionProducto().getDataProveedor().getFechaNacimiento().compareTo(cal5), 0);
            GeneralTest.deleteAllData();
        } catch (Exception e) {
            GeneralTest.deleteAllData();
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            throw new Exception(e.getMessage() + " " + errors.toString());
        }
    }
}
