import Controlador.Clases.Fabrica;
import Controlador.Clases.IControladorOrdenes;
import Controlador.Clases.IControladorProductos;
import Controlador.Clases.IControladorUsuarios;
import Controlador.Clases.ManejadorOrdenes;
import Controlador.Clases.ManejadorUsuarios;
import Controlador.Clases.OrdenCompra;
import Controlador.Clases.Producto;
import Controlador.DataTypes.DataCategoria;
import Controlador.DataTypes.DataCliente;
import Controlador.DataTypes.DataEspecificacionProducto;
import Controlador.DataTypes.DataOrdenCompra;
import Controlador.DataTypes.DataProducto;
import Controlador.DataTypes.DataProveedor;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import static java.util.Objects.isNull;
import junit.framework.TestCase;

public class ControladorOrdenesTest extends TestCase{
    
    public void testGenerarOrdenTest() {
        Integer idOrdenesControlador = Fabrica.getInstance().getControladorOrdenes(null).getId();
        IControladorOrdenes controlarOrden = Fabrica.getInstance().getControladorOrdenes(idOrdenesControlador);
        Integer idUsuariosControlador = Fabrica.getInstance().getControladorUsuarios(null).getId();
        IControladorUsuarios controlarUsuario = Fabrica.getInstance().getControladorUsuarios(idUsuariosControlador);
        Integer idProductosControlador = Fabrica.getInstance().getControladorProductos(null).getId();
        IControladorProductos controlarProducto = Fabrica.getInstance().getControladorProductos(idProductosControlador);
        
        Calendar cal1 = Calendar.getInstance();
        cal1.set(1960, 11, 1);
        controlarUsuario.ingresarDatosProveedor(new DataProveedor("jram", "password1", "Joey", "Ramone", "jram@gmail.com", cal1,"Juegos", "www.juegos.com"));
        controlarUsuario.guardarUsuario();
        
        Calendar cal2 = Calendar.getInstance();
        cal2.set(1960, 11, 1);
        controlarUsuario.ingresarDatosCliente(new DataCliente("piedra", "password2", "Pedro", "Picapiedra", "ppiedra@gmail.com", cal2));
        controlarUsuario.guardarUsuario();
        
        DataCategoria cat1 = new DataCategoria("cat1", null);
        controlarProducto.ingresarDatosCategoria(cat1);
        controlarProducto.guardarCategoria();
        
        //leer datos de nueva especificacion de IngresarDatosUnidad
        controlarProducto.elegirProveedor("jram");
        DataProveedor proveedor = new DataProveedor(ManejadorUsuarios.getInstance().getProveedor("jram"));
        DataEspecificacionProducto espProducto = new DataEspecificacionProducto("prod1", "Producto 1", "descripcion 1", new HashMap(), (float)12.0, proveedor, new ArrayList<>(), new ArrayList<>(),new ArrayList<>(), new ArrayList<>());
        controlarProducto.ingresarDatosProductos(espProducto);
        controlarProducto.ingresarEspecificacion("Color", "Verde");
        controlarProducto.ingresarEspecificacion("Peso", "1kg");
        Boolean es_bool = false;
        DataProducto prodUnidad = new DataProducto(11, "idesp1",es_bool, espProducto);
        controlarProducto.ingresarDatosUnidad(prodUnidad);
        controlarProducto.agregarCategoriaAEspecificacion("cat1");
        controlarProducto.agregarImagen("peteco");
        controlarProducto.guardarProducto();
        
        //Cliente Seleccionado
        controlarOrden.elegirCliente("piedra");
        //Categoria Seleccionado
        controlarOrden.elegirCategoria("cat1");
        //Especificacion Producto seleccionado
        controlarOrden.elegirEspecificacionProducto("prod1");
        //Producto seleccionado1
        controlarOrden.elegirProducto(controlarOrden.listarProductosEnEspecificacion().get(0).getId());
        //Generar item Orden1
        controlarOrden.generarItemOrden();
        //Guardar Orden1
        DataOrdenCompra dataOrden = new DataOrdenCompra(1);
        controlarOrden.guardarOrden(dataOrden);
        
        Integer ordenId = controlarOrden.getUltimaOrdenGuardada();
        controlarOrden.elegirOrden(ordenId);
        DataOrdenCompra guardada = controlarOrden.mostrarDatosOrden();
        
        assertTrue (!isNull (guardada));
        //prod
        assertEquals (guardada.getClienteCompraProducto().get(0).getProducto().getIdEspecifico(), new Producto(prodUnidad).getIdEspecifico());
 
        controlarOrden.borrarOrdenCompra();
        controlarProducto.eliminarEspecificacionProducto("prod1"); 
        controlarProducto.eliminarCategoria("cat1");        
        controlarUsuario.eliminarUsuario("piedra");
        controlarUsuario.eliminarUsuario("jram");


        
     }
    
    public void testCancelarOrden () {
        Integer idOrdenesControlador = Fabrica.getInstance().getControladorOrdenes(null).getId();
        IControladorOrdenes controlarOrden = Fabrica.getInstance().getControladorOrdenes(idOrdenesControlador);
        Integer idUsuariosControlador = Fabrica.getInstance().getControladorUsuarios(null).getId();
        IControladorUsuarios controlarUsuario = Fabrica.getInstance().getControladorUsuarios(idUsuariosControlador);
        Integer idProductosControlador = Fabrica.getInstance().getControladorProductos(null).getId();
        IControladorProductos controlarProducto = Fabrica.getInstance().getControladorProductos(idProductosControlador);
        
        Calendar cal1 = Calendar.getInstance();
        cal1.set(1960, 11, 1);
        controlarUsuario.ingresarDatosProveedor(new DataProveedor("jrod", "password","Juan", "Rodriguez", "jrod@gmail.com", cal1,"Juegos", "www.juegos.com"));
        controlarUsuario.guardarUsuario();
        
        Calendar cal2 = Calendar.getInstance();
        cal2.set(1960, 11, 1);
        controlarUsuario.ingresarDatosCliente(new DataCliente("piedra", "password", "Pedro", "Picapiedra", "ppiedra@gmail.com", cal2));
        controlarUsuario.guardarUsuario();
        
        DataCategoria cat1 = new DataCategoria("cat1", null);
        controlarProducto.ingresarDatosCategoria(cat1);
        controlarProducto.guardarCategoria();
        
        //leer datos de nueva especificacion de IngresarDatosUnidad
        controlarProducto.elegirProveedor("jrod");
        DataProveedor proveedor = new DataProveedor(ManejadorUsuarios.getInstance().getProveedor("jrod"));
        DataEspecificacionProducto espProducto = new DataEspecificacionProducto("prod1", "Producto 1", "descripcion 1", new HashMap(), (float)12.0, proveedor, new ArrayList<>(), new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
        controlarProducto.ingresarDatosProductos(espProducto);
        controlarProducto.ingresarEspecificacion("Color", "Verde");
        controlarProducto.ingresarEspecificacion("Peso", "1kg");
        DataProducto prodUnidad = new DataProducto(0, "idesp1", true, espProducto);
        controlarProducto.ingresarDatosUnidad(prodUnidad);
        controlarProducto.agregarCategoriaAEspecificacion("cat1");
        controlarProducto.agregarImagen("peteco");
        controlarProducto.guardarProducto();
        
        //Cliente Seleccionado
        controlarOrden.elegirCliente("piedra");
        //Categoria Seleccionado
        controlarOrden.elegirCategoria("cat1");
        //Especificacion Producto seleccionado
        controlarOrden.elegirEspecificacionProducto("prod1");
        //Producto seleccionado1
        controlarOrden.elegirProducto(controlarOrden.listarProductosEnEspecificacion().get(0).getId());
        //Generar item Orden1
        controlarOrden.generarItemOrden();
        //Guardar Orden1
        DataOrdenCompra dataOrden = new DataOrdenCompra(1);
        controlarOrden.guardarOrden(dataOrden);
        Integer ordenGuardada = controlarOrden.getUltimaOrdenGuardada();
        controlarOrden.elegirOrden(ordenGuardada);
        controlarOrden.borrarOrdenCompra();
        
        Map<Integer, OrdenCompra> ord = ManejadorOrdenes.getInstance().obtenerOrdenes();
        assertTrue (isNull (ord.get(ordenGuardada)));
        
        controlarProducto.eliminarEspecificacionProducto("prod1"); 
        controlarProducto.eliminarCategoria("cat1");        
        controlarUsuario.eliminarUsuario("piedra");
        controlarUsuario.eliminarUsuario("jrod");
    }
    
    public void testVerInformacionOrden () {
        Integer idOrdenesControlador = Fabrica.getInstance().getControladorOrdenes(null).getId();
        IControladorOrdenes controlarOrden = Fabrica.getInstance().getControladorOrdenes(idOrdenesControlador);
        Integer idUsuariosControlador = Fabrica.getInstance().getControladorUsuarios(null).getId();
        IControladorUsuarios controlarUsuario = Fabrica.getInstance().getControladorUsuarios(idUsuariosControlador);
        Integer idProductosControlador = Fabrica.getInstance().getControladorProductos(null).getId();
        IControladorProductos controlarProducto = Fabrica.getInstance().getControladorProductos(idProductosControlador);
        
        Calendar cal1 = Calendar.getInstance();
        cal1.set(1960, 11, 1);
        controlarUsuario.ingresarDatosProveedor(new DataProveedor("jrod","password", "Juan", "Rodriguez", "jrod@gmail.com", cal1,"Juegos", "www.juegos.com"));
        controlarUsuario.guardarUsuario();
        
        Calendar cal2 = Calendar.getInstance();
        cal2.set(1960, 11, 1);
        controlarUsuario.ingresarDatosCliente(new DataCliente("piedra","password", "Pedro", "Picapiedra", "ppiedra@gmail.com", cal2));
        controlarUsuario.guardarUsuario();
        
        DataCategoria cat1 = new DataCategoria("cat1", null);
        controlarProducto.ingresarDatosCategoria(cat1);
        controlarProducto.guardarCategoria();
        
        //leer datos de nueva especificacion de IngresarDatosUnidad
        controlarProducto.elegirProveedor("jrod");
        DataProveedor proveedor = new DataProveedor(ManejadorUsuarios.getInstance().getProveedor("jrod"));
        DataEspecificacionProducto espProducto = new DataEspecificacionProducto("prod1", "Producto 1", "descripcion 1", new HashMap(), (float)12.0, proveedor, new ArrayList<>(), new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
        controlarProducto.ingresarDatosProductos(espProducto);
        controlarProducto.ingresarEspecificacion("Color", "Verde");
        controlarProducto.ingresarEspecificacion("Peso", "1kg");
        DataProducto prodUnidad = new DataProducto(11, "idesp1",true, espProducto);
        controlarProducto.ingresarDatosUnidad(prodUnidad);
        controlarProducto.agregarCategoriaAEspecificacion("cat1");
        controlarProducto.agregarImagen("peteco");
        controlarProducto.guardarProducto();
        
        //Cliente Seleccionado
        controlarOrden.elegirCliente("piedra");
        //Categoria Seleccionado
        controlarOrden.elegirCategoria("cat1");
        //Especificacion Producto seleccionado
        controlarOrden.elegirEspecificacionProducto("prod1");
        //Producto seleccionado1
        controlarOrden.elegirProducto(controlarOrden.listarProductosEnEspecificacion().get(0).getId());
        //Generar item Orden1
        controlarOrden.generarItemOrden();
        //Guardar Orden1
        DataOrdenCompra dataOrdenCreada = new DataOrdenCompra(1);
        controlarOrden.guardarOrden(dataOrdenCreada);
        Integer ordenGuardada = controlarOrden.getUltimaOrdenGuardada();
        //Orden Seleccionada
        controlarOrden.elegirOrden(ordenGuardada);
        
        DataOrdenCompra dataOrden = controlarOrden.mostrarDatosOrden();
        
        assertTrue (!isNull (dataOrden));
        //prod
        assertEquals (dataOrden.getClienteCompraProducto().get(0).getProducto().getIdEspecifico(), new Producto(prodUnidad).getIdEspecifico());
        controlarOrden.elegirOrden(ordenGuardada);
        controlarOrden.borrarOrdenCompra();
        
        Map<Integer, OrdenCompra> ord = ManejadorOrdenes.getInstance().obtenerOrdenes();
        assertTrue (isNull (ord.get(ordenGuardada)));
        
        controlarProducto.eliminarEspecificacionProducto("prod1"); 
        controlarProducto.eliminarCategoria("cat1");        
        controlarUsuario.eliminarUsuario("piedra");
        controlarUsuario.eliminarUsuario("jrod");
    }
}
