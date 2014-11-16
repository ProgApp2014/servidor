package Controlador.Clases;

import Controlador.DataTypes.DataCliente;
import Controlador.DataTypes.DataEspecificacionProducto;
import Controlador.DataTypes.DataOrdenCompra;
import Controlador.DataTypes.DataProveedor;
import java.util.ArrayList;
import java.util.List;

public interface IControladorUsuarios {
    
    public Integer getId();
    public void setId(Integer id);
    public void ingresarDatosCliente(DataCliente cliente);
    public void ingresarDatosProveedor(DataProveedor proveedor);
    public Boolean validarDatosUsuario();
    public void guardarUsuario();
//    public void cancelar();
    public List<DataCliente> listarClientes();
    public void elegirCliente(String nickname);
    public DataCliente mostrarDatosCliente();
    public List<DataOrdenCompra> listarOrdenesCliente();
    public List<DataProveedor> listarProveedores();
    public void elegirProveedor(String nickname);
    public DataProveedor mostrarDatosProveedor();
    public String getErrors();
    public void eliminarUsuario(String nickname);
    public Boolean login(String nickname, String hashPassword);
    public Boolean esProveedor(String nickname);
    public Boolean esCliente(String nickname);
    public List<DataEspecificacionProducto> listarProductosProveedor();
    public void habilitarNotificaciones(String nickname);
    public void deshabilitarNotificaciones(String nickname);
}