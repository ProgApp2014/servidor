package Controlador.Middleware;

import Controlador.Clases.*;
import Controlador.DataTypes.DataCategoria;
import Controlador.DataTypes.DataComentario;
import Controlador.DataTypes.DataEspecificacionProducto;
import Controlador.DataTypes.DataProducto;
import Controlador.DataTypes.DataProveedor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.ws.Endpoint;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class ControladorProductosWS {

    private Endpoint endpoint = null;

    public ControladorProductosWS() {

    }

    @WebMethod(exclude = true)
    public void publicar() {
        endpoint = Endpoint.publish("http://localhost:8094/productosWS", this);

    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }

    @WebMethod
    public Integer getId() {
        Integer idProductosControlador = Fabrica.getInstance().getControladorProductos(null).getId();
        return idProductosControlador;
    }

    @WebMethod
    public ArrayList<DataProveedor> listarProveedores(Integer idProductosControlador) {

        List<DataProveedor> l = Fabrica.getInstance().getControladorProductos(idProductosControlador).listarProveedores();
        ArrayList<DataProveedor> ll = new ArrayList<DataProveedor>();
        ll.addAll(l);
        return ll;
    }

    @WebMethod
    public void elegirProveedor(String nickname, Integer idProductosControlador) {
        Fabrica.getInstance().getControladorProductos(idProductosControlador).elegirProveedor(nickname);
    }

    @WebMethod
    public void ingresarDatosProductos(DataEspecificacionProducto espProducto, Integer idProductosControlador) {
        Fabrica.getInstance().getControladorProductos(idProductosControlador).ingresarDatosProductos(espProducto);
    }

    @WebMethod
    public Boolean elegirTipoProducto(Integer idProductosControlador) {

        return null;
    }

    @WebMethod
    public void agregarMultiplesProductosAutogenerados(Integer cantidad, Integer idProductosControlador) {

        Fabrica.getInstance().getControladorProductos(idProductosControlador).agregarMultiplesProductosAutogenerados(cantidad);
    }

    @WebMethod
    public void ingresarDatosUnidad(DataProducto producto, Integer idProductosControlador) {

        Fabrica.getInstance().getControladorProductos(idProductosControlador).ingresarDatosUnidad(producto);
    }

    @WebMethod
    public ArrayList<DataCategoria> listarCategorias(Integer idProductosControlador) {
        List<DataCategoria> l = Fabrica.getInstance().getControladorProductos(idProductosControlador).listarCategorias();
        ArrayList<DataCategoria> ll = new ArrayList<DataCategoria>();
        ll.addAll(l);
        return ll;
    }

    @WebMethod
    public void elegirCategoria(String categoria, Integer idProductosControlador) {
        Fabrica.getInstance().getControladorProductos(idProductosControlador).elegirCategoria(categoria);
    }

    @WebMethod
    public ArrayList<DataEspecificacionProducto> listarProductosCategoria(Integer idProductosControlador) {
        List<DataEspecificacionProducto> l = Fabrica.getInstance().getControladorProductos(idProductosControlador).listarProductosCategoria();
        ArrayList<DataEspecificacionProducto> ll = new ArrayList<DataEspecificacionProducto>();
        ll.addAll(l);
        return ll;
    }

    @WebMethod
    public DataEspecificacionProducto mostrarDatosProducto(String numRef, Integer idProductosControlador) {
        DataEspecificacionProducto l = Fabrica.getInstance().getControladorProductos(idProductosControlador).mostrarDatosProducto(numRef);
        return l;
    }

    @WebMethod
    public Boolean controlarErrores(Integer idProductosControlador) {

        return Fabrica.getInstance().getControladorProductos(idProductosControlador).controlarErrores();
    }

    @WebMethod
    public void guardarProducto(Integer idProductosControlador) {
        Fabrica.getInstance().getControladorProductos(idProductosControlador).guardarProducto();
    }

    @WebMethod
    public DataCategoria elegirCategoriaPadre(String categoria, Integer idProductosControlador) {
        DataCategoria l = Fabrica.getInstance().getControladorProductos(idProductosControlador).elegirCategoriaPadre(categoria);
        return l;
    }

    @WebMethod
    public void ingresarDatosCategoria(DataCategoria categoria, Integer idProductosControlador) {

        Fabrica.getInstance().getControladorProductos(idProductosControlador).ingresarDatosCategoria(categoria);
    }

    @WebMethod
    public void asociarCategoriaPadre(DataCategoria padre, Integer idProductosControlador) {

        Fabrica.getInstance().getControladorProductos(idProductosControlador).asociarCategoriaPadre(padre);
    }

    @WebMethod
    public void guardarCategoria(Integer idProductosControlador) {
        Fabrica.getInstance().getControladorProductos(idProductosControlador).guardarCategoria();
    }

    @WebMethod
    public void elegirEspProducto(String numRef, Integer idProductosControlador) {
        Fabrica.getInstance().getControladorProductos(idProductosControlador).elegirEspProducto(numRef);
    }

    @WebMethod
    public DataProducto mostrarInformacionProducto(Integer idProductosControlador) {
        DataProducto l = Fabrica.getInstance().getControladorProductos(idProductosControlador).mostrarInformacionProducto();
        return l;
    }

    @WebMethod
    public void modificarDatosEspecificacionProducto(DataEspecificacionProducto espProducto, Integer idProductosControlador) {
        Fabrica.getInstance().getControladorProductos(idProductosControlador).modificarDatosEspecificacionProducto(espProducto);
    }

    @WebMethod
    public void agregarImagen(String rutaImagen, Integer idProductosControlador) {
        Fabrica.getInstance().getControladorProductos(idProductosControlador).agregarImagen(rutaImagen);
    }

    @WebMethod
    public void agregarCategoria(DataCategoria categoria, Integer idProductosControlador) {
        Fabrica.getInstance().getControladorProductos(idProductosControlador).agregarCategoria(categoria);
    }

    @WebMethod
    public Boolean validarInfo(Integer idProductosControlador) {

        return Fabrica.getInstance().getControladorProductos(idProductosControlador).validarInfo();
    }

    @WebMethod
    public void ingresarEspecificacion(String clave, String desc, Integer idProductosControlador) {
        Fabrica.getInstance().getControladorProductos(idProductosControlador).ingresarEspecificacion(clave, desc);
    }

    @WebMethod
    public void agregarCategoriaAEspecificacion(String categoria, Integer idProductosControlador) {
        Fabrica.getInstance().getControladorProductos(idProductosControlador).agregarCategoriaAEspecificacion(categoria);
    }

    @WebMethod
    public ArrayList<String> listarImagenesAModificar(Integer idProductosControlador) {

        List<String> l = Fabrica.getInstance().getControladorProductos(idProductosControlador).listarImagenesAModificar();
        ArrayList<String> ll = new ArrayList<String>();
        ll.addAll(l);
        return ll;
    }

    @WebMethod
    public void borrarImagen(String rutaImagen, Integer idProductosControlador) {
        Fabrica.getInstance().getControladorProductos(idProductosControlador).borrarImagen(rutaImagen);
    }

    @WebMethod
    public ArrayList<DataCategoria> listarCategoriasAModificar(Integer idProductosControlador) {
        List<DataCategoria> l = Fabrica.getInstance().getControladorProductos(idProductosControlador).listarCategoriasAModificar();
        ArrayList<DataCategoria> ll = new ArrayList<DataCategoria>();
        ll.addAll(l);
        return ll;
    }

    @WebMethod
    public void borrarCategoriaAEspecificacion(String categoria, Integer idProductosControlador) {
    }

    @WebMethod
    public void guardarEspProductoModificado(Integer idProductosControlador) {
        Fabrica.getInstance().getControladorProductos(idProductosControlador).guardarEspProductoModificado();
    }

    @WebMethod
    public Boolean categoryAlreadyExist(String categoria, Integer idProductosControlador) {

        return Fabrica.getInstance().getControladorProductos(idProductosControlador).categoryAlreadyExist(categoria);
    }

    @WebMethod
    public ArrayList<DataEspecificacionProducto> buscarProductos(String keyword, Integer idProductosControlador) {
        List<DataEspecificacionProducto> l = Fabrica.getInstance().getControladorProductos(idProductosControlador).buscarProductos(keyword);
        ArrayList<DataEspecificacionProducto> ll = new ArrayList<DataEspecificacionProducto>();
        ll.addAll(l);
        return ll;
    }

    @WebMethod
    public HashMap<String, ArrayList<DataEspecificacionProducto>> buscarProductosSeparados(String keyword, String Orden, Integer idProductosControlador) {
        Map<String, List<DataEspecificacionProducto>> l = Fabrica.getInstance().getControladorProductos(idProductosControlador).buscarProductosSeparados(keyword, Orden);
        HashMap<String, ArrayList<DataEspecificacionProducto>> ll = new HashMap<String, ArrayList<DataEspecificacionProducto>>();
        Iterator it = l.keySet().iterator();
        while(it.hasNext()){
            String key = (String) it.next();
            
            ArrayList<DataEspecificacionProducto> value = new ArrayList<DataEspecificacionProducto>();
            value.addAll(l.get(key));
            ll.put(key, value);
        
        }
        return ll; 
                
    }

    @WebMethod
    public Boolean puedeComentar(String nickname, String nroRef, Integer idProductosControlador) {
        return Fabrica.getInstance().getControladorProductos(idProductosControlador).puedeComentar(nickname, nroRef);
    }

    @WebMethod
    public ArrayList<DataComentario> listarComentarios(String nroRef, Integer idProductosControlador) {
        List<DataComentario> l = Fabrica.getInstance().getControladorProductos(idProductosControlador).listarComentarios(nroRef);
        ArrayList<DataComentario> ll = new ArrayList<DataComentario>();
        ll.addAll(l);
        return ll;
    }

    @WebMethod
    public void agregarComentario(String nickname, String nroRef, Integer padre, String Comentario, Integer idProductosControlador) {
        Fabrica.getInstance().getControladorProductos(idProductosControlador).agregarComentario(nickname, nroRef, padre, Comentario);
    }

    @WebMethod
    public Boolean verificarEspecificacionProducto(String nroRef, Integer idProductosControlador) {
        return Fabrica.getInstance().getControladorProductos(idProductosControlador).verificarEspecificacionProducto(nroRef);
    }

}
