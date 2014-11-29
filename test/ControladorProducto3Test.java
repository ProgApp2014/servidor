
import Controlador.Clases.Fabrica;
import Controlador.Clases.IControladorProductos;
import Controlador.Clases.IControladorUsuarios;
import Controlador.Clases.ManejadorEspProductos;
import Controlador.DataTypes.DataCategoria;
import Controlador.DataTypes.DataEspecificacionProducto;
import Controlador.DataTypes.DataProducto;
import Controlador.DataTypes.DataProveedor;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
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
public class ControladorProducto3Test {
    @Test
    public void ModificarProductoTest() throws Exception {
        try{  
            Integer idUsuariosControlador = Fabrica.getInstance().getControladorUsuarios(null).getId();
            IControladorUsuarios controlarUsuario = Fabrica.getInstance().getControladorUsuarios(idUsuariosControlador);
            Integer idProductosControlador = Fabrica.getInstance().getControladorProductos(null).getId();
            IControladorProductos controlarProducto = Fabrica.getInstance().getControladorProductos(idProductosControlador); 

            //crear proveedor
            Calendar cal3 = Calendar.getInstance();
            cal3.set(1938, 5, 13);
            DataProveedor proveedor3 =new DataProveedor("bbunny", "pass3" ,"Bugs", "Bunny", "bbunny@mail.com", cal3, "Zanahorias", "www.quehaydenuevoviejo.com");
            controlarUsuario.ingresarDatosProveedor(proveedor3);
            controlarUsuario.guardarUsuario();     

            //creamos la especificacion de producto
            DataEspecificacionProducto espProducto3 = new DataEspecificacionProducto("nroref3", "nombre_prod3", "mordido", new HashMap <>() , (float) 12.0, proveedor3, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList());  
            //creamos el producto
            Boolean orden3 = false;
            DataProducto producto3 = new DataProducto(1,"idSpec3", orden3, espProducto3);

            controlarProducto.ingresarDatosProductos(espProducto3);
            controlarProducto.elegirProveedor("bbunny");
            controlarProducto.ingresarEspecificacion("estado", "mordido");
            controlarProducto.ingresarEspecificacion("color", "blanco");
            controlarProducto.ingresarEspecificacion("altura", "2 cm");
            DataCategoria categoria3 = new DataCategoria("cate3", null);
            controlarProducto.ingresarDatosCategoria(categoria3);
            controlarProducto.guardarCategoria();
            controlarProducto.agregarCategoriaAEspecificacion("cate3");
            controlarProducto.agregarImagen("img3");
            controlarProducto.ingresarDatosUnidad(producto3);
            if (controlarProducto.controlarErrores()) {
                controlarProducto.guardarProducto();
            }        

            //Modificar Producto
            DataEspecificacionProducto espProducto4 = new DataEspecificacionProducto("nroref3", "nombre_prod_cambiado", "medio mordido", new HashMap<>(), (float) 2500.0, proveedor3, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList());

            controlarProducto.modificarDatosEspecificacionProducto(espProducto4);
            //ManejadorEspProductos.getInstance().modificarProducto(espProducto4);

            if (controlarProducto.validarInfo()) {
                controlarProducto.guardarEspProductoModificado();
            }

            //pruebas
            assertTrue((ManejadorEspProductos.getInstance().getEspecificacionProducto("nroref3").getPrecio() == (float) 2500.0));
            assertEquals(ManejadorEspProductos.getInstance().getEspecificacionProducto("nroref3").getDescripcion(), "medio mordido");
            assertEquals(ManejadorEspProductos.getInstance().getEspecificacionProducto("nroref3").getNombre(), "nombre_prod_cambiado");
            assertEquals(ManejadorEspProductos.getInstance().getEspecificacionProducto("nroref3").getNroReferencia(), "nroref3");

            assertEquals(ManejadorEspProductos.getInstance().getEspecificacionProducto("nroref3").getDescripcion(), "medio mordido");
            GeneralTest.deleteAllData();
        } catch (Exception e) {
            GeneralTest.deleteAllData();
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            throw new Exception(e.getMessage() + " " + errors.toString());
        }
        
    }
}
