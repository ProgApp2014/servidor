
import Controlador.Clases.Fabrica;
import Controlador.Clases.IControladorProductos;
import Controlador.Clases.IControladorUsuarios;
import Controlador.Clases.ManejadorCategorias;
import Controlador.Clases.ManejadorEspProductos;
import Controlador.Clases.ManejadorProductos;
import Controlador.Clases.ManejadorUsuarios;
import Controlador.DataTypes.DataCategoria;
import Controlador.DataTypes.DataEspecificacionProducto;
import Controlador.DataTypes.DataProducto;
import Controlador.DataTypes.DataProveedor;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import static java.util.Objects.isNull;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author mauro
 */
public class TestControladorProducto {

    @Test
    public void AltaDeCategoriaTest() {
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

        controlarProducto.eliminarCategoria("cate3");
        controlarProducto.eliminarCategoria("cate2");
        controlarProducto.eliminarCategoria("cate1");

    }

    @Test
    public void TestRegistrarProducto() {

        Integer idProductosControlador = Fabrica.getInstance().getControladorProductos(null).getId();
        IControladorProductos controlarProducto = Fabrica.getInstance().getControladorProductos(idProductosControlador);
        Integer idUsuariosControlador = Fabrica.getInstance().getControladorUsuarios(null).getId();
        IControladorUsuarios controlarUsuario = Fabrica.getInstance().getControladorUsuarios(idUsuariosControlador);

        Calendar cal = Calendar.getInstance();
        cal.set(1960, 11, 1);
        DataProveedor proveedor1 = new DataProveedor("pperez", "pass " ,"Pedro", "Perez", "perez@gmail.com", cal, "Pcel", "www.pcel.com");
        controlarUsuario.ingresarDatosProveedor(proveedor1);
        controlarUsuario.guardarUsuario();
        DataEspecificacionProducto espProducto = new DataEspecificacionProducto("nroref1", "nombre_prod", "nuevo", new HashMap <>() , (float) 2500.0, proveedor1, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
                                                                                                     //descripcion  especificacion   precio                       imagenes         categorias          productos         comentario
        //Crear producto id=1 y especificacion espProducto
        Boolean ordenado = true;
        DataProducto producto1 = new DataProducto(1,"idSpec", ordenado, espProducto);

        controlarProducto.ingresarDatosProductos(espProducto);
        controlarProducto.elegirProveedor("pperez");
        controlarProducto.ingresarEspecificacion("estado", "nuevo");
        controlarProducto.ingresarEspecificacion("color", "gris");
        controlarProducto.ingresarEspecificacion("altura", "1 metro");
        DataCategoria categoria = new DataCategoria("cate", null);
        controlarProducto.ingresarDatosCategoria(categoria);
        controlarProducto.guardarCategoria();
        controlarProducto.agregarCategoriaAEspecificacion("cate");
        controlarProducto.agregarImagen("img1");
        controlarProducto.ingresarDatosUnidad(producto1);
        if (controlarProducto.controlarErrores()) {
            controlarProducto.guardarProducto();
        }

        assertTrue(!isNull(controlarProducto.getId()));
        assertTrue(!isNull(controlarProducto.controlarErrores()));
        assertTrue(!isNull(controlarProducto.elegirTipoProducto()));
        assertEquals(ManejadorEspProductos.getInstance().getEspecificacionProducto("nroref1").getListaProductos().get(0).getIdEspecifico(), "idSpec");
        assertEquals(ManejadorEspProductos.getInstance().getEspecificacionProducto("nroref1").getListaProductos().get(0).getEspecificacionProducto().getDescripcion(), "nuevo");
        assertEquals(ManejadorEspProductos.getInstance().getEspecificacionProducto("nroref1").getListaProductos().get(0).getEspecificacionProducto().getNombre(), "nombre_prod");
        assertEquals(ManejadorEspProductos.getInstance().getEspecificacionProducto("nroref1").getListaProductos().get(0).getEspecificacionProducto().getNroReferencia(), "nroref1");
        assertTrue((ManejadorEspProductos.getInstance().getEspecificacionProducto("nroref1").getListaProductos().get(0).getEspecificacionProducto().getPrecio() == (float) 2500.0));
        assertEquals(ManejadorEspProductos.getInstance().getEspecificacionProducto("nroref1").getListaProductos().get(0).getEspecificacionProducto().getDataProveedor().getLinkSitio(), "www.pcel.com");
        assertEquals(ManejadorEspProductos.getInstance().getEspecificacionProducto("nroref1").getListaProductos().get(0).getEspecificacionProducto().getDataProveedor().getNombreCompania(), "Pcel");
        assertEquals(ManejadorEspProductos.getInstance().getEspecificacionProducto("nroref1").getListaProductos().get(0).getEspecificacionProducto().getDataProveedor().getNombre(), "Pedro");
        assertEquals(ManejadorEspProductos.getInstance().getEspecificacionProducto("nroref1").getListaProductos().get(0).getEspecificacionProducto().getDataProveedor().getApellido(), "Perez");
        assertEquals(ManejadorEspProductos.getInstance().getEspecificacionProducto("nroref1").getListaProductos().get(0).getEspecificacionProducto().getDataProveedor().getNickname(), "pperez");

        assertEquals(ManejadorEspProductos.getInstance().getEspecificacionProducto("nroref1").getListaProductos().get(0).getEspecificacionProducto().getDataProveedor().getFechaNacimiento().compareTo(cal), 0);

        controlarProducto.eliminarEspecificacionProducto("nroref1");        
        controlarProducto.eliminarCategoria("cate");
        controlarUsuario.eliminarUsuario("pperez");        

    }

      @Test
      public void TestVerInformaciondeProducto() {

        Integer idUsuariosControlador = Fabrica.getInstance().getControladorUsuarios(null).getId();
        IControladorUsuarios controlarUsuario = Fabrica.getInstance().getControladorUsuarios(idUsuariosControlador);
        Integer idProductosControlador = Fabrica.getInstance().getControladorProductos(null).getId();
        IControladorProductos controlarProducto = Fabrica.getInstance().getControladorProductos(idProductosControlador);  
        
        //creamos el proveedor
        Calendar cal2 = Calendar.getInstance();
        cal2.set(1992, 10, 04);
        DataProveedor proveedor2 =new DataProveedor("gclaud", "pass2" ,"Gallo", "Claudio", "gclaudio@mail.com", cal2, "Alpiste", "www.alpiste.com");
        controlarUsuario.ingresarDatosProveedor(proveedor2);
        controlarUsuario.guardarUsuario();

        //creamos la especificacion de producto
        DataEspecificacionProducto espProducto2 = new DataEspecificacionProducto("nroref2", "nombre_prod2", "usado", new HashMap <>() , (float) 12.0, proveedor2, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        
        //creamos el producto
        Boolean orden = true;
        DataProducto producto2 = new DataProducto(2,"idSpec2", orden, espProducto2);

        controlarProducto.ingresarDatosProductos(espProducto2);
        controlarProducto.elegirProveedor("gclaud");
        controlarProducto.ingresarEspecificacion("estado", "nuevo");
        controlarProducto.ingresarEspecificacion("color", "gris");
        controlarProducto.ingresarEspecificacion("altura", "1 metro");
        DataCategoria categoria2 = new DataCategoria("cate2", null);
        controlarProducto.ingresarDatosCategoria(categoria2);
        controlarProducto.guardarCategoria();
        controlarProducto.agregarCategoriaAEspecificacion("cate2");
        controlarProducto.agregarImagen("img2");
        controlarProducto.ingresarDatosUnidad(producto2);
        if (controlarProducto.controlarErrores()) {
            controlarProducto.guardarProducto();
        }
        
        //pruebas
        assertTrue(!isNull(controlarProducto.controlarErrores()));
        assertTrue(!isNull(ManejadorProductos.getInstance().obtenerProductos()));
        assertEquals(ManejadorEspProductos.getInstance().getEspecificacionProducto("nroref2").getListaProductos().get(0).getIdEspecifico(), "idSpec2");
        assertEquals(ManejadorEspProductos.getInstance().getEspecificacionProducto("nroref2").getListaProductos().get(0).getEspecificacionProducto().getDescripcion(), "usado");
        assertEquals(ManejadorEspProductos.getInstance().getEspecificacionProducto("nroref2").getListaProductos().get(0).getEspecificacionProducto().getNombre(), "nombre_prod2");
        assertEquals(ManejadorEspProductos.getInstance().getEspecificacionProducto("nroref2").getListaProductos().get(0).getEspecificacionProducto().getNroReferencia(), "nroref2");
        assertTrue((ManejadorEspProductos.getInstance().getEspecificacionProducto("nroref2").getListaProductos().get(0).getEspecificacionProducto().getPrecio() == (float) 12.0));
        assertEquals(ManejadorEspProductos.getInstance().getEspecificacionProducto("nroref2").getListaProductos().get(0).getEspecificacionProducto().getDataProveedor().getLinkSitio(), "www.alpiste.com");
        assertEquals(ManejadorEspProductos.getInstance().getEspecificacionProducto("nroref2").getListaProductos().get(0).getEspecificacionProducto().getDataProveedor().getNombreCompania(), "Alpiste");
        assertEquals(ManejadorEspProductos.getInstance().getEspecificacionProducto("nroref2").getListaProductos().get(0).getEspecificacionProducto().getDataProveedor().getNombre(), "Gallo");
        assertEquals(ManejadorEspProductos.getInstance().getEspecificacionProducto("nroref2").getListaProductos().get(0).getEspecificacionProducto().getDataProveedor().getApellido(), "Claudio");
        assertEquals(ManejadorEspProductos.getInstance().getEspecificacionProducto("nroref2").getListaProductos().get(0).getEspecificacionProducto().getDataProveedor().getNickname(), "gclaud");
        assertEquals(ManejadorEspProductos.getInstance().getEspecificacionProducto("nroref2").getListaProductos().get(0).getEspecificacionProducto().getDataProveedor().getFechaNacimiento().compareTo(cal2), 0);

        controlarProducto.eliminarEspecificacionProducto("nroref2");
        controlarProducto.eliminarCategoria("cate2");
        controlarUsuario.eliminarUsuario("gclaud");
      }

      @Test
      public void TestModificarProducto() {
          
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
        DataEspecificacionProducto espProducto3 = new DataEspecificacionProducto("nroref3", "nombre_prod3", "mordido", new HashMap <>() , (float) 12.0, proveedor3, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());  
        //creamos el producto
        Boolean orden3 = true;
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
        DataEspecificacionProducto espProducto4 = new DataEspecificacionProducto("nroref4", "nombre_prod_cambiado", "medio mordido", new HashMap<>(), (float) 2500.0, proveedor3, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        controlarProducto.modificarDatosEspecificacionProducto(espProducto3);
        //ManejadorEspProductos.getInstance().modificarProducto(espProducto4);

        if (controlarProducto.validarInfo()) {
            controlarProducto.guardarEspProductoModificado();
        }
        
        //YA DESDE EL PRIMER ASSERT NO ANDA, LOS PRODUCTOS SE ESTAN MODIFICANDO DE OTRA FORMA???
        //pruebas
        assertTrue((ManejadorEspProductos.getInstance().getEspecificacionProducto("nroref4").getListaProductos().get(0).getEspecificacionProducto().getPrecio() == (float) 2500.0));
        assertEquals(ManejadorEspProductos.getInstance().getEspecificacionProducto("nroref4").getListaProductos().get(0).getEspecificacionProducto().getDescripcion(), "medio mordido");
        assertEquals(ManejadorEspProductos.getInstance().getEspecificacionProducto("nroref4").getListaProductos().get(0).getEspecificacionProducto().getNombre(), "nombre_prod_cambiado");
        assertEquals(ManejadorEspProductos.getInstance().getEspecificacionProducto("nroref4").getListaProductos().get(0).getEspecificacionProducto().getNroReferencia(), "nroref4");

        assertEquals(ManejadorEspProductos.getInstance().getEspecificacionProducto("nroref3").getListaProductos().get(1).getDataEspecificacionProducto().getDescripcion(), "mordido");
        
        controlarProducto.eliminarEspecificacionProducto("nroref3");
        //controlarProducto.eliminarEspecificacionProducto("nroref4");
        controlarProducto.eliminarCategoria("cate3");        
        controlarUsuario.eliminarUsuario("bbunny");
        
    }
//    
//    @Test  
//    public void TestModificarProducto() {
//
//        Integer idUsuariosControlador = Fabrica.getInstance().getControladorUsuarios(null).getId();
//        IControladorUsuarios controlarUsuario = Fabrica.getInstance().getControladorUsuarios(idUsuariosControlador);
//        Integer idProductosControlador = Fabrica.getInstance().getControladorProductos(null).getId();
//        IControladorProductos controlarProducto = Fabrica.getInstance().getControladorProductos(idProductosControlador);
//
//        Calendar cal = Calendar.getInstance();
//        cal.set(1960, 11, 1);
//        controlarUsuario.ingresarDatosProveedor(new DataProveedor("bbunny", "passa" ,"Bugs", "Bunny", "bbunny@mail.com", cal, "Zanahorias", "www.zanahorias.com"));
//        controlarUsuario.guardarUsuario();
//
//        DataCategoria cat4 = new DataCategoria("kat", null);
//        controlarProducto.ingresarDatosCategoria(cat4);
//        controlarProducto.guardarCategoria();
//
//        controlarProducto.elegirProveedor("bbunny");
//        DataProveedor proveedor = new DataProveedor(ManejadorUsuarios.getInstance().getProveedor("bbunny"));
//        DataEspecificacionProducto espProducto = new DataEspecificacionProducto("prod2", "Producto1", "descripcion 1", new HashMap(), (float) 12.0, proveedor, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
//        controlarProducto.ingresarDatosProductos(espProducto);
//        controlarProducto.ingresarEspecificacion("estado", "nuevo");
//        controlarProducto.ingresarEspecificacion("color", "gris");
//        Boolean algo =true;
//        DataProducto prodUnidad = new DataProducto(5, "idesp1", algo, espProducto);
//        controlarProducto.ingresarDatosUnidad(prodUnidad);
//        controlarProducto.agregarCategoriaAEspecificacion("kat");
//        controlarProducto.agregarImagen("img");
//        controlarProducto.guardarProducto();
//
//        //Modificar Producto
//        DataEspecificacionProducto espProducto2 = new DataEspecificacionProducto("prod3", "Cambio", "nuevo", new HashMap(), (float) 2500.0, proveedor, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
//
//        controlarProducto.modificarDatosEspecificacionProducto(espProducto2);
//
//        if (controlarProducto.validarInfo()) {
//            controlarProducto.guardarEspProductoModificado();
//        }
//
//        assertTrue(ManejadorEspProductos.getInstance().getEspecificacionProducto("prod3").getListaProductos().get(5).getEspecificacionProducto().getPrecio() == (float) 2500);
//        assertEquals(ManejadorEspProductos.getInstance().getEspecificacionProducto("prod3").getListaProductos().get(5).getEspecificacionProducto().getDescripcion(), "nuevo");
//        assertEquals(ManejadorEspProductos.getInstance().getEspecificacionProducto("prod3").getListaProductos().get(5).getEspecificacionProducto().getNombre(), "Cambio");
//        assertEquals(ManejadorEspProductos.getInstance().getEspecificacionProducto("prod3").getListaProductos().get(5).getEspecificacionProducto().getNroReferencia(), "prod2");
//
//        controlarUsuario.eliminarUsuario("bbunny");
//        controlarProducto.eliminarCategoria("kat");
//        controlarProducto.eliminarEspecificacionProducto("prod3");
//        controlarProducto.eliminarEspecificacionProducto("prod2");        
//        
//    }
}
