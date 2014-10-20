
import Controlador.Clases.Fabrica;
import Controlador.Clases.IControladorProductos;
import Controlador.Clases.ManejadorCategorias;
import Controlador.DataTypes.DataCategoria;
import java.io.PrintWriter;
import java.io.StringWriter;
import static java.util.Objects.isNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author dario
 */
public class ControladorProducto2Test {
    @Test
    public void AltaDeCategoriaTest() throws Exception {
        try {
            Integer idProductosControlador = Fabrica.getInstance().getControladorProductos(null).getId();
            IControladorProductos controlarProducto = Fabrica.getInstance().getControladorProductos(idProductosControlador);

            //Crear categoria sin padre
            DataCategoria categoria = new DataCategoria("cate1", null);
            
            controlarProducto.ingresarDatosCategoria(categoria);
            controlarProducto.guardarCategoria();
            assertTrue(!isNull(ManejadorCategorias.getInstance().getCategoria("cate1")));

            //ManejadorCategorias.getInstance().obtenerCategorias().get("cat1");
            //Crear con padre cat1
            DataCategoria categoria2 = new DataCategoria("cate2", categoria);
            controlarProducto.ingresarDatosCategoria(categoria2);

            controlarProducto.guardarCategoria();

            assertTrue(!isNull(ManejadorCategorias.getInstance().getCategoria("cate2")));
            assertEquals(ManejadorCategorias.getInstance().getCategoria("cate2").getPadre().getNombre(), "cate1");

            //agrego otro padre por las dudas
            DataCategoria categoria3 = new DataCategoria("cate3", categoria2);
            controlarProducto.ingresarDatosCategoria(categoria3);

            controlarProducto.guardarCategoria();

            assertTrue(!isNull(ManejadorCategorias.getInstance().getCategoria("cate3")));
            assertEquals(ManejadorCategorias.getInstance().getCategoria("cate3").getPadre().getNombre(), "cate2");
            
            
            GeneralTest.deleteAllData();
        }catch(Exception e){
            GeneralTest.deleteAllData();
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            throw new Exception(e.getMessage() + " " + errors.toString());
        }
        
    }
}
