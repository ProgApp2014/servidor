package Controlador.Middleware;

import Controlador.Clases.*;
import Controlador.DataTypes.DataCategoria;
import Controlador.DataTypes.DataCliente;
import Controlador.DataTypes.DataClienteCompraProducto;
import Controlador.DataTypes.DataEspecificacionProducto;
import Controlador.DataTypes.DataOrdenCompra;
import Controlador.DataTypes.DataProducto;
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
    public ControladorOrdenesWS(){}

     
    @WebMethod(exclude = true)
    public void ControladorOrdenesWS(){
         endpoint = Endpoint.publish("http://localhost:9128/ordenesWS", this);
    }
    @WebMethod
    public Integer getId(){
        Integer idOrdenesControlador = Fabrica.getInstance().getControladorOrdenes(null).getId();
        return idOrdenesControlador;
    }
    
 
    public List<DataCliente> listarClientes(Integer idOrdenesControlador){
        List<DataCliente> l = Fabrica.getInstance().getControladorOrdenes(idOrdenesControlador).listarClientes();
 
        return l;

    }

    public void elegirCliente(String nickname,Integer idOrdenesControlador){
        Fabrica.getInstance().getControladorOrdenes(idOrdenesControlador).elegirCliente(nickname);
    }

    public List<DataCategoria> listarCategorias(Integer idOrdenesControlador){
        List<DataCategoria> l = Fabrica.getInstance().getControladorOrdenes(idOrdenesControlador).listarCategorias();
        return l;

    }

    public void elegirCategoria(String categoria,Integer idOrdenesControlador){
        Fabrica.getInstance().getControladorOrdenes(idOrdenesControlador).elegirCategoria(categoria);

    }

    public List<DataEspecificacionProducto> listarEspecificacionProductos(Integer idOrdenesControlador){
        List<DataEspecificacionProducto> l = Fabrica.getInstance().getControladorOrdenes(idOrdenesControlador).listarEspecificacionProductos();
        return l;

    }

    public void elegirEspecificacionProducto(String nroRef,Integer idOrdenesControlador){
        Fabrica.getInstance().getControladorOrdenes(idOrdenesControlador).elegirEspecificacionProducto(nroRef);
    }

    public List<DataEspecificacionProducto> listarProductosEnOrden(Integer idOrdenesControlador){

        List<DataEspecificacionProducto> l = Fabrica.getInstance().getControladorOrdenes(idOrdenesControlador).listarProductosEnOrden();

        return l;

    }

    public void elegirProducto(Integer id,Integer idOrdenesControlador){
        Fabrica.getInstance().getControladorOrdenes(idOrdenesControlador).elegirProducto(id);
    }

    public void elegirCantidadProducto(Integer cantidad,Integer idOrdenesControlador){
        Fabrica.getInstance().getControladorOrdenes(idOrdenesControlador).elegirCantidadProducto(cantidad);
    }

    public void generarItemOrden(Integer idOrdenesControlador){
        Fabrica.getInstance().getControladorOrdenes(idOrdenesControlador).generarItemOrden();
    }

    public void guardarOrden(DataOrdenCompra dataOrden,Integer idOrdenesControlador){

        Fabrica.getInstance().getControladorOrdenes(idOrdenesControlador).guardarOrden(dataOrden);
    }

    public List<DataOrdenCompra> listarOrdenes(Integer idOrdenesControlador){

        List<DataOrdenCompra> l = Fabrica.getInstance().getControladorOrdenes(idOrdenesControlador).listarOrdenes();
        return l;

    }

    public void elegirOrden(Integer nroOrden,Integer idOrdenesControlador){
        Fabrica.getInstance().getControladorOrdenes(idOrdenesControlador).elegirOrden(nroOrden);

    }

    public void borrarOrdenCompra(Integer idOrdenesControlador){
        Fabrica.getInstance().getControladorOrdenes(idOrdenesControlador).borrarOrdenCompra();
    }

    public DataOrdenCompra mostrarDatosOrden(Integer idOrdenesControlador){
        DataOrdenCompra l = Fabrica.getInstance().getControladorOrdenes(idOrdenesControlador).mostrarDatosOrden();
        return l;

    }

    public void removerEspecificacionProducto(String ref,Integer idOrdenesControlador){
        Fabrica.getInstance().getControladorOrdenes(idOrdenesControlador).removerEspecificacionProducto(ref);
    }

}
