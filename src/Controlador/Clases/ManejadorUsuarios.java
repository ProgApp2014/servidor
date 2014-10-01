package Controlador.Clases;

import static com.sun.org.apache.bcel.internal.Repository.instanceOf;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class ManejadorUsuarios {
    
    private static ManejadorUsuarios instance = null;
    Map<String,Usuario> usuarios = new HashMap();
    
    EntityManagerFactory EntityManagerFactory = Persistence.createEntityManagerFactory("ProgramacionAppPU");
    EntityManager entityManager = EntityManagerFactory.createEntityManager();
    
    public static ManejadorUsuarios getInstance(){
        if(ManejadorUsuarios.instance == null){
            ManejadorUsuarios.instance = new ManejadorUsuarios();
        }
        return ManejadorUsuarios.instance;
    }
    
    private ManejadorUsuarios(){
    
    }
    
    public void agregarUsuario(Usuario usuario){
        usuarios.put(usuario.getNickname(), usuario);
        
        //guardo la categoria en bd
        entityManager.getTransaction().begin();
        entityManager.persist(usuario);
        entityManager.getTransaction().commit();
    }
    
    public Map<String,Usuario> obtenerUsuarios(){
        //obtengo todas las categorias de la bd
        Query query = entityManager.createQuery("SELECT u FROM Usuario u");
        
        //las guardo en la colecion
        List<Usuario> listUsuarios = query.getResultList();
        usuarios.clear();
        listUsuarios.stream().forEach((usu) -> {
            usuarios.put(usu.getNickname(), usu);
        });
        return usuarios;
    }
    
    public Map<String,Cliente> obtenerClientes(){
        //obtengo todas las categorias de la bd
        Query query = entityManager.createQuery("SELECT c FROM Cliente c");
        
        //las guardo en la colecion
        Map<String,Cliente> clientes = new HashMap();
        List<Cliente> listClientes = query.getResultList();
        listClientes.stream().forEach((cli) -> {
            clientes.put(cli.getNickname(), cli);
        });
        return clientes;
    }
    
    public Map<String,Proveedor> obtenerProveedores(){
        //obtengo todas las categorias de la bd
        Query query = entityManager.createQuery("SELECT c FROM Proveedor c");
        
        //las guardo en la colecion
        Map<String,Proveedor> proveedores = new HashMap();
        List<Proveedor> listProveedores = query.getResultList();
        listProveedores.stream().forEach((pro) -> {
            proveedores.put(pro.getNickname(), pro);
        });
        return proveedores;
    }
    
    public Cliente getCliente(String nickname){
        return this.obtenerClientes().get(nickname);
    }
    
    public Proveedor getProveedor(String nickname){
        return this.obtenerProveedores().get(nickname);
    }
    
    public String getTipo(String nickname){
        Map<String,Usuario> listaUsuarios = this.obtenerUsuarios();
        if((listaUsuarios.get(nickname) instanceof Cliente)){
            return "Cliente";
        }else if((listaUsuarios.get(nickname) instanceof Proveedor)){
            return "Proveedor";
        }
        return "Desconocido";   
    }

    public void eliminarUsuario(String nickname){
        Usuario aBorrar = usuarios.get(nickname);
        usuarios.remove(nickname);
        
        entityManager.getTransaction().begin();
        entityManager.remove(aBorrar);
        entityManager.getTransaction().commit();
    }
}
