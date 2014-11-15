package Controlador.Middleware;

import Controlador.Clases.*;
import Controlador.DataTypes.DataCategoria;
import Controlador.DataTypes.DataCliente;
import Controlador.DataTypes.DataEspecificacionProducto;
import Controlador.DataTypes.DataOrdenCompra;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.ws.Endpoint;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class ControladorOrdenesWS {

    private Endpoint endpoint = null;

    //Constructor
    public ControladorOrdenesWS() {
    }

    @WebMethod(exclude = true)
    public void publicar() {
        endpoint = Endpoint.publish("http://localhost:8094/ordenesWS", this);
    }

    @WebMethod
    public Integer getId() {
        Integer idOrdenesControlador = Fabrica.getInstance().getControladorOrdenes(null).getId();
        return idOrdenesControlador;
    }

    @WebMethod
    public DataCliente[] listarClientes(Integer idOrdenesControlador) {
        List<DataCliente> l = Fabrica.getInstance().getControladorOrdenes(idOrdenesControlador).listarClientes();
        DataCliente[] ll = new DataCliente[l.size()];
        Iterator it = l.iterator();
        int index = 0;
        while (it.hasNext()) {
            ll[index] = (DataCliente) it.next();
            index++;
        }
        return ll;

    }

    @WebMethod
    public void elegirCliente(String nickname, Integer idOrdenesControlador) {
        Fabrica.getInstance().getControladorOrdenes(idOrdenesControlador).elegirCliente(nickname);
    }

    @WebMethod
    public DataEspecificacionProducto[] listarProductosCategoria(Integer idOrdenesControlador) {
        List<DataEspecificacionProducto> l = Fabrica.getInstance().getControladorOrdenes(idOrdenesControlador).listarProductosCategoria();
        DataEspecificacionProducto[] ll = new DataEspecificacionProducto[l.size()];
        Iterator it = l.iterator();
        int index = 0;
        while (it.hasNext()) {
            ll[index] = (DataEspecificacionProducto) it.next();
            index++;
        }
        return ll;

    }

    @WebMethod
    public DataCategoria[] listarCategorias(Integer idOrdenesControlador) {
        List<DataCategoria> l = Fabrica.getInstance().getControladorOrdenes(idOrdenesControlador).listarCategorias();
        DataCategoria[] ll = new DataCategoria[l.size()];
        Iterator it = l.iterator();
        int index = 0;
        while (it.hasNext()) {
            ll[index] = (DataCategoria) it.next();
            index++;
        }
        return ll;

    }

    @WebMethod
    public void elegirCategoria(String categoria, Integer idOrdenesControlador) {
        Fabrica.getInstance().getControladorOrdenes(idOrdenesControlador).elegirCategoria(categoria);

    }

    @WebMethod
    public DataEspecificacionProducto[] listarEspecificacionProductos(Integer idOrdenesControlador) {
        List<DataEspecificacionProducto> l = Fabrica.getInstance().getControladorOrdenes(idOrdenesControlador).listarEspecificacionProductos();
        DataEspecificacionProducto[] ll = new DataEspecificacionProducto[l.size()];
        Iterator it = l.iterator();
        int index = 0;
        while (it.hasNext()) {
            ll[index] = (DataEspecificacionProducto) it.next();
            index++;
        }
        return ll;

    }

    @WebMethod
    public void elegirEspecificacionProducto(String nroRef, Integer idOrdenesControlador) {
        Fabrica.getInstance().getControladorOrdenes(idOrdenesControlador).elegirEspecificacionProducto(nroRef);
    }

    @WebMethod
    public DataEspecificacionProducto[] listarProductosEnOrden(Integer idOrdenesControlador) {

        List<DataEspecificacionProducto> l = Fabrica.getInstance().getControladorOrdenes(idOrdenesControlador).listarProductosEnOrden();
        DataEspecificacionProducto[] ll = new DataEspecificacionProducto[l.size()];
        Iterator it = l.iterator();
        int index = 0;
        while (it.hasNext()) {
            ll[index] = (DataEspecificacionProducto) it.next();
            index++;
        }
        return ll;

    }

    @WebMethod
    public void elegirProducto(Integer id, Integer idOrdenesControlador) {
        Fabrica.getInstance().getControladorOrdenes(idOrdenesControlador).elegirProducto(id);
    }

    @WebMethod
    public void elegirCantidadProducto(Integer cantidad, Integer idOrdenesControlador) {
        Fabrica.getInstance().getControladorOrdenes(idOrdenesControlador).elegirCantidadProducto(cantidad);
    }

    @WebMethod
    public void generarItemOrden(Integer idOrdenesControlador) {
        Fabrica.getInstance().getControladorOrdenes(idOrdenesControlador).generarItemOrden();
    }

    @WebMethod
    public void guardarOrden(DataOrdenCompra dataOrden, Integer idOrdenesControlador) {

        Fabrica.getInstance().getControladorOrdenes(idOrdenesControlador).guardarOrden(dataOrden);
    }

    @WebMethod
    public DataOrdenCompra[] listarOrdenes(Integer idOrdenesControlador) {

        List<DataOrdenCompra> l = Fabrica.getInstance().getControladorOrdenes(idOrdenesControlador).listarOrdenes();
        DataOrdenCompra[] ll = new DataOrdenCompra[l.size()];
        Iterator it = l.iterator();
        int index = 0;
        while (it.hasNext()) {
            ll[index] = (DataOrdenCompra) it.next();
            index++;
        }
        return ll;

    }

    @WebMethod
    public void elegirOrden(Integer nroOrden, Integer idOrdenesControlador) {
        Fabrica.getInstance().getControladorOrdenes(idOrdenesControlador).elegirOrden(nroOrden);

    }

    @WebMethod
    public void borrarOrdenCompra(Integer idOrdenesControlador) {
        Fabrica.getInstance().getControladorOrdenes(idOrdenesControlador).borrarOrdenCompra();
    }

    @WebMethod
    public DataOrdenCompra mostrarDatosOrden(Integer idOrdenesControlador) {
        DataOrdenCompra l = Fabrica.getInstance().getControladorOrdenes(idOrdenesControlador).mostrarDatosOrden();
        return l;

    }

    @WebMethod
    public void removerEspecificacionProducto(String ref, Integer idOrdenesControlador) {
        Fabrica.getInstance().getControladorOrdenes(idOrdenesControlador).removerEspecificacionProducto(ref);
    }
    
    @WebMethod
    public void agregarEstadoOrdenRecibida(Integer nroOrden, Integer idOrdenesControlador){
        Fabrica.getInstance().getControladorOrdenes(idOrdenesControlador).agregarEstadoOrdenRecibida(nroOrden);
    }
    
    @WebMethod
    public void agregarEstadoOrdenCancelada(Integer nroOrden, Integer idOrdenesControlador){
        Fabrica.getInstance().getControladorOrdenes(idOrdenesControlador).agregarEstadoOrdenCancelada(nroOrden);
    }
    
    @WebMethod
    public void agregarEstadoOrdenConfirmada(Integer nroOrden, Integer idOrdenesControlador){
        Fabrica.getInstance().getControladorOrdenes(idOrdenesControlador).agregarEstadoOrdenConfirmada(nroOrden);
    }
    
    @WebMethod
    public void agregarEstadoOrdenPreparada(Integer nroOrden, Integer idOrdenesControlador){
        Fabrica.getInstance().getControladorOrdenes(idOrdenesControlador).agregarEstadoOrdenPreparada(nroOrden);
    }
    
    public DataOrdenCompra[] listarOrdenesAPreparar(Integer idOrdenesControlador){
        
        List<DataOrdenCompra> l = Fabrica.getInstance().getControladorOrdenes(idOrdenesControlador).listarOrdenesAPreparar();
        DataOrdenCompra[] ll = new DataOrdenCompra[l.size()];
        Iterator it = l.iterator();
        int index = 0;
        while (it.hasNext()) {
            ll[index] = (DataOrdenCompra) it.next();
            index++;
        }
        return ll;
    }

}
