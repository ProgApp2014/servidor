package Controlador.Middleware;

import Controlador.Clases.*;
import Controlador.DataTypes.DataCliente;
import Controlador.DataTypes.DataEspecificacionProducto;
import Controlador.DataTypes.DataOrdenCompra;
import Controlador.DataTypes.DataProveedor;
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
public class ControladorUsuariosWS {

    private Endpoint endpoint = null;

    //Constructor
    public ControladorUsuariosWS() {
    }

    @WebMethod(exclude = true)
    public void publicar() {
        endpoint = Endpoint.publish("http://localhost:8094/usuariosWS", this);
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
    public DataCliente[] listarClientes(Integer idUsuariosControlador) {
        List<DataCliente> l = Fabrica.getInstance().getControladorUsuarios(idUsuariosControlador).listarClientes();
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
    public void elegirCliente(String nickname, Integer idUsuariosControlador) {
        Fabrica.getInstance().getControladorUsuarios(idUsuariosControlador).elegirCliente(nickname);

    }

    @WebMethod
    public DataCliente mostrarDatosCliente(Integer idUsuariosControlador) {
        DataCliente l = Fabrica.getInstance().getControladorUsuarios(idUsuariosControlador).mostrarDatosCliente();
        return l;

    }

    @WebMethod
    public DataOrdenCompra[] listarOrdenesCliente(Integer idUsuariosControlador) {
        List<DataOrdenCompra> l = Fabrica.getInstance().getControladorUsuarios(idUsuariosControlador).listarOrdenesCliente();
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
    public DataProveedor[] listarProveedores(Integer idUsuariosControlador) {
        List<DataProveedor> l = Fabrica.getInstance().getControladorUsuarios(idUsuariosControlador).listarProveedores();
        DataProveedor[] ll = new DataProveedor[l.size()];
        Iterator it = l.iterator();
        int index = 0;
        while (it.hasNext()) {
            ll[index] = (DataProveedor) it.next();
            index++;
        }
        return ll;

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
    public DataEspecificacionProducto[] listarProductosProveedor(Integer idUsuariosControlador) {
        List<DataEspecificacionProducto> l = Fabrica.getInstance().getControladorUsuarios(idUsuariosControlador).listarProductosProveedor();
        DataEspecificacionProducto[] ll = new DataEspecificacionProducto[l.size()];
        Iterator it = l.iterator();
        int index = 0;
        while (it.hasNext()) {
            ll[index] = (DataEspecificacionProducto) it.next();
            index++;
        }
        return ll;
    }
}
