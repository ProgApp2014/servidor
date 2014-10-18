
import static junit.framework.Assert.assertTrue;
import org.junit.Test;
import Controlador.Clases.Utils;
import Controlador.Clases.Fabrica;
import Controlador.Clases.IControladorOrdenes;
import Controlador.Clases.IControladorProductos;
import Controlador.Clases.IControladorUsuarios;
import static Controlador.Clases.Utils.md5;
import static java.util.Objects.isNull;
import static junit.framework.Assert.assertFalse;

/**
 *
 * @author mauro
 */
public class GeneralTest{
    
    @Test
    public void utilesTest(){
        
        Integer idOrdenesControlador = Fabrica.getInstance().getControladorOrdenes(null).getId();
        IControladorOrdenes controlarOrden = Fabrica.getInstance().getControladorOrdenes(idOrdenesControlador);
        Integer idUsuariosControlador = Fabrica.getInstance().getControladorUsuarios(null).getId();
        IControladorUsuarios controlarUsuario = Fabrica.getInstance().getControladorUsuarios(idUsuariosControlador);
        Integer idProductosControlador = Fabrica.getInstance().getControladorProductos(null).getId();
        IControladorProductos controlarProducto = Fabrica.getInstance().getControladorProductos(idProductosControlador);
        
        Utils.generarDatosDePrueba();
        
        
        assertTrue(!isNull(controlarOrden.listarOrdenes()));
        assertTrue(!isNull(controlarOrden.listarClientes()));
        assertTrue(!isNull(controlarOrden.listarCategorias()));

        controlarUsuario.elegirCliente("Dan");
        controlarUsuario.mostrarDatosCliente(); 
        controlarUsuario.getErrors();        
        assertTrue (controlarUsuario.esCliente("Dan"));
        assertTrue (!controlarUsuario.esCliente("Tim1"));
        controlarUsuario.elegirProveedor("Tim1");
        controlarUsuario.mostrarDatosProveedor();
        assertTrue (controlarUsuario.esProveedor("Tim1"));
        assertTrue (!controlarUsuario.esProveedor("Dan"));
        assertTrue (!isNull(controlarUsuario.listarProductosProveedor()));
        
        assertTrue (controlarUsuario.login("Dan", md5("password")));
        assertTrue (controlarUsuario.login("Tim1", md5("password")));
        assertFalse (controlarUsuario.login("Dan", md5("otracosa")));
        
//        assertTrue (controlarProducto.categoryAlreadyExist("Xbox"));        
//        assertTrue (!isNull (controlarProducto.mostrarDatosProducto("IPH5")));
        
        //comentarios
        controlarUsuario.elegirCliente("Phil");       
        controlarProducto.agregarComentario("Phil", "CHP", 0, "aca estoy comentando algo");
        controlarProducto.listarComentarios("CHP");
        
        assertFalse (controlarProducto.validarInfo());        
        
        assertTrue(!isNull(controlarProducto.buscarProductos("Apple")));
        assertTrue (!isNull(controlarProducto.buscarProductosSeparados("Apple","nombre")));

        //COMO BORRO LAS ORDENES DE COMPRA???

//        Integer orden1 = controlarOrden.getUltimaOrdenGuardada();
//        controlarOrden.elegirOrden(orden1);
//        controlarOrden.borrarOrdenCompra();
//        Integer orden2 = controlarOrden.getUltimaOrdenGuardada();
//        controlarOrden.elegirOrden(orden2);
//        controlarOrden.borrarOrdenCompra(); 
//        Integer orden3 = controlarOrden.getUltimaOrdenGuardada();
//        controlarOrden.elegirOrden(orden3);
//        controlarOrden.borrarOrdenCompra();
//        Integer orden4 = controlarOrden.getUltimaOrdenGuardada();
//        controlarOrden.elegirOrden(orden4);
//        controlarOrden.borrarOrdenCompra();
//        Integer orden5 = controlarOrden.getUltimaOrdenGuardada();
//        controlarOrden.elegirOrden(orden5);
//        controlarOrden.borrarOrdenCompra();        
        
        //eliminar especificaciones de la base
        controlarProducto.eliminarEspecificacionProducto("IPH5"); 
        controlarProducto.eliminarEspecificacionProducto("IPH4");        
        controlarProducto.eliminarEspecificacionProducto("NEX4");
        controlarProducto.eliminarEspecificacionProducto("GA3");        
        controlarProducto.eliminarEspecificacionProducto("GA4");  
        controlarProducto.eliminarEspecificacionProducto("AS5");        
        controlarProducto.eliminarEspecificacionProducto("PCG");
        controlarProducto.eliminarEspecificacionProducto("PMH");        
        controlarProducto.eliminarEspecificacionProducto("IRD");        
        controlarProducto.eliminarEspecificacionProducto("IM");                
        controlarProducto.eliminarEspecificacionProducto("RIX");                
        controlarProducto.eliminarEspecificacionProducto("CIX");
        controlarProducto.eliminarEspecificacionProducto("CHP");
        controlarProducto.eliminarEspecificacionProducto("CP3");
        
        //eliminar categorias de la base
        controlarProducto.eliminarCategoria("Xbox");  
        controlarProducto.eliminarCategoria("Playstation"); 
        controlarProducto.eliminarCategoria("Videojuegos"); 
        controlarProducto.eliminarCategoria("Apple");  
        controlarProducto.eliminarCategoria("Baterías");
        controlarProducto.eliminarCategoria("Protectores"); 
        controlarProducto.eliminarCategoria("Accesorios");
        controlarProducto.eliminarCategoria("Nokia"); 
        controlarProducto.eliminarCategoria("Blackberry");
        controlarProducto.eliminarCategoria("Galaxy Ace"); 
        controlarProducto.eliminarCategoria("Galaxy S4");
        controlarProducto.eliminarCategoria("Galaxy S3");
        controlarProducto.eliminarCategoria("Samsung"); 
        controlarProducto.eliminarCategoria("Nexus"); 
        controlarProducto.eliminarCategoria("iPhone");
        controlarProducto.eliminarCategoria("Equipos");
        controlarProducto.eliminarCategoria("Blackberry OS");
        controlarProducto.eliminarCategoria("Symbian"); 
        controlarProducto.eliminarCategoria("Windows Phone"); 
        controlarProducto.eliminarCategoria("Android"); 
        controlarProducto.eliminarCategoria("iOS"); 
        controlarProducto.eliminarCategoria("Sistemas Operativos");        
        controlarProducto.eliminarCategoria("Celulares");
//        controlarProducto.eliminarCategoria("Xbox");
//        controlarProducto.eliminarCategoria("VideoJuegos");
//        controlarProducto.eliminarCategoria("VideoJuegos");
        
        //eliminar usuarios de la base 
        controlarUsuario.eliminarUsuario("Tim1");
        controlarUsuario.eliminarUsuario("Eddy");
        controlarUsuario.eliminarUsuario("CraigX");
        controlarUsuario.eliminarUsuario("Johnny");
        controlarUsuario.eliminarUsuario("OpenPeter");
        controlarUsuario.eliminarUsuario("Dan");
        controlarUsuario.eliminarUsuario("Phil");
        controlarUsuario.eliminarUsuario("BruceS");
        controlarUsuario.eliminarUsuario("JeffW");

        
    }

}