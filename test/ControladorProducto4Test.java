
import Controlador.Clases.Fabrica;
import Controlador.Clases.IControladorProductos;
import Controlador.Clases.IControladorUsuarios;
import Controlador.Clases.ManejadorEspProductos;
import Controlador.Clases.ManejadorProductos;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author dario
 */
public class ControladorProducto4Test {
    @Test
    public void VerInformaciondeProductoTest() throws Exception {
        try {
            Integer idUsuariosControlador = Fabrica.getInstance().getControladorUsuarios(null).getId();
            IControladorUsuarios controlarUsuario = Fabrica.getInstance().getControladorUsuarios(idUsuariosControlador);
            Integer idProductosControlador = Fabrica.getInstance().getControladorProductos(null).getId();
            IControladorProductos controlarProducto = Fabrica.getInstance().getControladorProductos(idProductosControlador);

            Calendar cal2 = Calendar.getInstance();
            cal2.set(1992, 10, 04);
            controlarUsuario.ingresarDatosProveedor(new DataProveedor("gclaud", "pass2", "Gallo", "Claudio", "gclaudio@mail.com", cal2, "Alpiste", "www.alpiste.com"));
            controlarUsuario.guardarUsuario();

            DataCategoria categoria2 = new DataCategoria("cate25", null);
            controlarProducto.ingresarDatosCategoria(categoria2);
            controlarProducto.guardarCategoria();

            //leer datos de nueva especificacion de IngresarDatosUnidad
            controlarProducto.elegirProveedor("gclaud");
            DataProveedor proveedor2 = new DataProveedor(ManejadorUsuarios.getInstance().getProveedor("gclaud"));
            DataEspecificacionProducto espProducto2 = new DataEspecificacionProducto("nroref201", "nombre_prod2", "usado", new HashMap<>(), (float) 12.0, proveedor2, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            controlarProducto.ingresarDatosProductos(espProducto2);
            controlarProducto.ingresarEspecificacion("estado", "nuevo");
            controlarProducto.ingresarEspecificacion("color", "gris");
            controlarProducto.ingresarEspecificacion("altura", "1 metro");
            Boolean es_bool = false;
            DataProducto producto2 = new DataProducto(2, "idSpec2", es_bool, espProducto2);
            controlarProducto.ingresarDatosUnidad(producto2);
            controlarProducto.agregarCategoriaAEspecificacion("cate25");
            controlarProducto.agregarImagen("new_peteco");
            controlarProducto.agregarMultiplesProductosAutogenerados(2);
            if (controlarProducto.controlarErrores()) {
                controlarProducto.guardarProducto();
            }
            controlarProducto.elegirEspProducto("nroref201");
            controlarProducto.elegirCategoria("cate25");

            //pruebas
            assertTrue(!isNull(controlarProducto.controlarErrores()));
            assertTrue(!isNull(ManejadorProductos.getInstance().obtenerProductos()));
            assertEquals(controlarProducto.listarProductosEnEspecificacion().size(), 3);
            assertEquals(controlarProducto.listarProductosCategoria().size(), 1);
            assertEquals(ManejadorEspProductos.getInstance().getEspecificacionProducto("nroref201").getListaProductos().get(0).getIdEspecifico(), "idSpec2");
            assertEquals(ManejadorEspProductos.getInstance().getEspecificacionProducto("nroref201").getListaProductos().get(0).getEspecificacionProducto().getDescripcion(), "usado");
            assertEquals(ManejadorEspProductos.getInstance().getEspecificacionProducto("nroref201").getListaProductos().get(0).getEspecificacionProducto().getNombre(), "nombre_prod2");
            assertEquals(ManejadorEspProductos.getInstance().getEspecificacionProducto("nroref201").getListaProductos().get(0).getEspecificacionProducto().getNroReferencia(), "nroref201");
            assertTrue((ManejadorEspProductos.getInstance().getEspecificacionProducto("nroref201").getListaProductos().get(0).getEspecificacionProducto().getPrecio() == (float) 12.0));
            assertEquals(ManejadorEspProductos.getInstance().getEspecificacionProducto("nroref201").getListaProductos().get(0).getEspecificacionProducto().getDataProveedor().getLinkSitio(), "www.alpiste.com");
            assertEquals(ManejadorEspProductos.getInstance().getEspecificacionProducto("nroref201").getListaProductos().get(0).getEspecificacionProducto().getDataProveedor().getNombreCompania(), "Alpiste");
            assertEquals(ManejadorEspProductos.getInstance().getEspecificacionProducto("nroref201").getListaProductos().get(0).getEspecificacionProducto().getDataProveedor().getNombre(), "Gallo");
            assertEquals(ManejadorEspProductos.getInstance().getEspecificacionProducto("nroref201").getListaProductos().get(0).getEspecificacionProducto().getDataProveedor().getApellido(), "Claudio");
            assertEquals(ManejadorEspProductos.getInstance().getEspecificacionProducto("nroref201").getListaProductos().get(0).getEspecificacionProducto().getDataProveedor().getNickname(), "gclaud");
            assertEquals(ManejadorEspProductos.getInstance().getEspecificacionProducto("nroref201").getListaProductos().get(0).getEspecificacionProducto().getDataProveedor().getFechaNacimiento().compareTo(cal2), 0);
            GeneralTest.deleteAllData();
        } catch (Exception e) {
            GeneralTest.deleteAllData();
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            throw new Exception(e.getMessage() + " " + errors.toString());
        }

    }
}
