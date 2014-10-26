package Controlador.Middleware;

import Controlador.Clases.*;
import Controlador.DataTypes.DataCliente;
import Controlador.DataTypes.DataEspecificacionProducto;
import Controlador.DataTypes.DataOrdenCompra;
import Controlador.DataTypes.DataProveedor;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.ws.Endpoint;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class ControladorUsuariosWS {
    private Endpoint endpoint = null;
    //Constructor
    public ControladorUsuariosWS(){}

     
    @WebMethod(exclude = true)
    public void ControladorUsuariosWS(){
         endpoint = Endpoint.publish("http://localhost:9128/usuariosWS", this);
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
            return endpoint;
    }
    
    @WebMethod
    public Integer getId() {
        Integer idUsuariosControlador = Fabrica.getInstance().getControladorUsuarios(null).getId();
        return idUsuariosControlador;
    }

    @WebMethod
    public void ingresarDatosCliente(DataCliente cliente, Integer idUsuariosControlador) {
        Fabrica.getInstance().getControladorUsuarios(idUsuariosControlador).ingresarDatosCliente(cliente);
    }

    @WebMethod
    public void ingresarDatosProveedor(DataProveedor proveedor, Integer idUsuariosControlador) {
        Fabrica.getInstance().getControladorUsuarios(idUsuariosControlador).ingresarDatosProveedor(proveedor);
    }

    @WebMethod
    public Boolean validarDatosUsuario(Integer idUsuariosControlador) {
        return Fabrica.getInstance().getControladorUsuarios(idUsuariosControlador).validarDatosUsuario();

    }

    @WebMethod
    public void guardarUsuario(Integer idUsuariosControlador) {
        Fabrica.getInstance().getControladorUsuarios(idUsuariosControlador).guardarUsuario();
    }

    @WebMethod
    public List<DataCliente> listarClientes(Integer idUsuariosControlador) {
        List<DataCliente> l = Fabrica.getInstance().getControladorUsuarios(idUsuariosControlador).listarClientes();

        return l;
    }

    @WebMethod
    public void elegirCliente(String nickname, Integer idUsuariosControlador) {
        Fabrica.getInstance().getControladorUsuarios(idUsuariosControlador).elegirCliente(nickname);

    }

    @WebMethod
    public DataCliente mostrarDatosCliente(Integer idUsuariosControlador) {
        DataCliente l = Fabrica.getInstance().getControladorUsuarios(idUsuariosControlador).mostrarDatosCliente();
        return l;

    }

    @WebMethod
    public List<DataOrdenCompra> listarOrdenesCliente(Integer idUsuariosControlador) {
        List<DataOrdenCompra> l = Fabrica.getInstance().getControladorUsuarios(idUsuariosControlador).listarOrdenesCliente();
 
        return l;

    }

    @WebMethod
    public List<DataProveedor> listarProveedores(Integer idUsuariosControlador) {
        List<DataProveedor> l = Fabrica.getInstance().getControladorUsuarios(idUsuariosControlador).listarProveedores();
 
        return l;

    }

    @WebMethod
    public void elegirProveedor(String nickname, Integer idUsuariosControlador) {
        Fabrica.getInstance().getControladorUsuarios(idUsuariosControlador).elegirProveedor(nickname);
    }

    @WebMethod
    public DataProveedor mostrarDatosProveedor(Integer idUsuariosControlador) {
        DataProveedor l = Fabrica.getInstance().getControladorUsuarios(idUsuariosControlador).mostrarDatosProveedor();

        return l;

    }

    @WebMethod
    public String getErrors(Integer idUsuariosControlador) {
        return Fabrica.getInstance().getControladorUsuarios(idUsuariosControlador).getErrors();

    }

    @WebMethod
    public Boolean login(String nickname, String hashPassword, Integer idUsuariosControlador) {
        return Fabrica.getInstance().getControladorUsuarios(idUsuariosControlador).login(nickname, hashPassword);
    }

    @WebMethod
    public Boolean esProveedor(String nickname, Integer idUsuariosControlador) {
        return Fabrica.getInstance().getControladorUsuarios(idUsuariosControlador).esProveedor(nickname);
    }

    @WebMethod
    public Boolean esCliente(String nickname, Integer idUsuariosControlador) {
        return Fabrica.getInstance().getControladorUsuarios(idUsuariosControlador).esCliente(nickname);
    }

    @WebMethod
    public List<DataEspecificacionProducto> listarProductosProveedor(Integer idUsuariosControlador) {
        return Fabrica.getInstance().getControladorUsuarios(idUsuariosControlador).listarProductosProveedor();
    }
}
