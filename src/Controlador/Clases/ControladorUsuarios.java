package Controlador.Clases;

import Controlador.DataTypes.DataCliente;
import Controlador.DataTypes.DataEspecificacionProducto;
import Controlador.DataTypes.DataOrdenCompra;
import Controlador.DataTypes.DataProveedor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Pattern;

public class ControladorUsuarios implements IControladorUsuarios {

    private Integer id;
    private Usuario nuevoUsuario;
    private Cliente clienteElegido;
    private Proveedor proveedorElegido;
    private String Errors;

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

//    - prvLst : Set<Proveedor> 
    @Override
    public void ingresarDatosCliente(DataCliente cliente) {
        nuevoUsuario = new Cliente(cliente);
    }

    @Override
    public void ingresarDatosProveedor(DataProveedor proveedor) {
        nuevoUsuario = new Proveedor(proveedor);
    }

    @Override
    public Boolean validarDatosUsuario() {
        Errors = "";
       
        Pattern p = Pattern.compile("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$");
        Boolean invalidaEmail = !p.matcher(nuevoUsuario.getEmail()).matches();
        if(invalidaEmail){
        
            Errors = "El formato de email es incorrecto";
            return true;
        }      
        for (Entry<String, Usuario> iter : ManejadorUsuarios.getInstance().obtenerUsuarios().entrySet()) {
            Boolean validateEmail = iter.getValue().getEmail().equals(nuevoUsuario.getEmail());
            Boolean valdiateNickname = iter.getValue().getNickname().equals(nuevoUsuario.getNickname());
            if (valdiateNickname) {
                Errors = "El usuario con este Nickname ya existe";
            }
            if (validateEmail) {
                String emailMsg = "El usuario con este Email ya existe";
                Errors += (valdiateNickname) ? "," + emailMsg : emailMsg;
            }
            if (valdiateNickname || validateEmail) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void guardarUsuario() {
        ManejadorUsuarios.getInstance().agregarUsuario(nuevoUsuario);
        System.out.println("Nuevo Usuario " + nuevoUsuario);
    }

    //@Override
//    public void cancelar(){
//        
//    }
    @Override
    public List<DataCliente> listarClientes() {
        List<DataCliente> dataCliente = new ArrayList<>();
        ManejadorUsuarios.getInstance().obtenerClientes().entrySet().stream().map((cliente) -> cliente.getValue()).forEach((valor) -> {
            dataCliente.add(new DataCliente(valor));
        });
        return dataCliente;
    }

    @Override
    public void elegirCliente(String nickname) {
        clienteElegido = ManejadorUsuarios.getInstance().getCliente(nickname);
    }

    @Override
    public DataCliente mostrarDatosCliente() {
        DataCliente dataCliente = new DataCliente(clienteElegido);
        return dataCliente;
    }

    @Override
    public List<DataOrdenCompra> listarOrdenesCliente() {
        List<DataOrdenCompra> dataOrdenCompra = new ArrayList<>();
        ManejadorOrdenes.getInstance().obtenerOrdenes().entrySet().stream().map((orden) -> orden.getValue()).forEach((valor) -> {
            if (valor.getCliente().getNickname().equals(clienteElegido.getNickname())) {
                dataOrdenCompra.add(new DataOrdenCompra(valor,true));
            }
        });
        return dataOrdenCompra;
    }

    @Override
    public List<DataProveedor> listarProveedores() {
        List<DataProveedor> dataProveedor = new ArrayList<>();
        ManejadorUsuarios.getInstance().obtenerProveedores().entrySet().stream().map((proovedor) -> proovedor.getValue()).forEach((valor) -> {
            dataProveedor.add(new DataProveedor(valor));
        });
        return dataProveedor;
    }

    @Override
    public void elegirProveedor(String nickname) {
        proveedorElegido = ManejadorUsuarios.getInstance().getProveedor(nickname);
    }

    @Override
    public DataProveedor mostrarDatosProveedor() {
        DataProveedor dataProveedor = new DataProveedor(proveedorElegido);
        return dataProveedor;
    }

    @Override
    public String getErrors() {
        return this.Errors;
    }
    
    @Override
    public void eliminarUsuario(String nickname){
        ManejadorUsuarios.getInstance().eliminarUsuario(nickname);
    }
    
    @Override
    public Boolean login(String nickname, String hashPassword){
        Cliente usuCliente = ManejadorUsuarios.getInstance().getCliente(nickname);
        if(usuCliente != null){
            if(usuCliente.getPassword().equals(hashPassword)){
                return true;
            }
        }else{
            Proveedor usuProveedor = ManejadorUsuarios.getInstance().getProveedor(nickname);
            if(usuProveedor != null){
                if(usuProveedor.getPassword().equals(hashPassword)){
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public List<DataEspecificacionProducto> listarProductosProveedor(){
        List<DataEspecificacionProducto> result = new ArrayList();
        Iterator it = ManejadorEspProductos.getInstance().obtenerEspecificacionProductos().values().iterator();
        while(it.hasNext()){
            EspecificacionProducto current = (EspecificacionProducto)it.next();
            if(current.getProveedor().getNickname().equals(proveedorElegido.getNickname())){
                result.add(new DataEspecificacionProducto(current,false));
            }
        }
        return result;
    }
    
    @Override
    public Boolean esProveedor(String nickname){
        return ManejadorUsuarios.getInstance().getTipo(nickname) == "Proveedor";
    }
    
    @Override
    public Boolean esCliente(String nickname){
        return ManejadorUsuarios.getInstance().getTipo(nickname) == "Cliente";
    }
    
    @Override
    public void habilitarNotificaciones(String nickname){
        Cliente usuCliente = ManejadorUsuarios.getInstance().getCliente(nickname);
        usuCliente.setRecibeNotificacion(Boolean.TRUE);
        ManejadorUsuarios.getInstance().modificarCliente(usuCliente);
    }
    
    @Override
    public void deshabilitarNotificaciones(String nickname){
        Cliente usuCliente = ManejadorUsuarios.getInstance().getCliente(nickname);
        usuCliente.setRecibeNotificacion(Boolean.FALSE);
        ManejadorUsuarios.getInstance().modificarCliente(usuCliente);
    }
    
}
