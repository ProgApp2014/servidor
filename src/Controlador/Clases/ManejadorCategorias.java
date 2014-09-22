package Controlador.Clases;

import Controlador.DataTypes.DataEspecificacionProducto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class ManejadorCategorias {
    
    private static ManejadorCategorias instance = null;
    List<Categoria> categorias = new ArrayList();
    
    EntityManagerFactory EntityManagerFactory = Persistence.createEntityManagerFactory("ProgramacionAppPU");
    EntityManager entityManager = EntityManagerFactory.createEntityManager();
    
    public static ManejadorCategorias getInstance(){
        if(ManejadorCategorias.instance == null){
            ManejadorCategorias.instance = new ManejadorCategorias();
        }
        return ManejadorCategorias.instance;
    }
    
    private ManejadorCategorias(){
    
    }
    
    public void agregarCategoria(Categoria categoria){
        //la agrego a la colecion
        categorias.add(categoria);
        
        categoria.getListaProductos().entrySet().forEach((producto) -> {
            ManejadorEspProductos.getInstance().getEspecificacionProducto(producto.getKey()).agregarCategoria(categoria);
        });
        
        //guardo la categoria en bd
        entityManager.getTransaction().begin();
        entityManager.persist(categoria);
        entityManager.getTransaction().commit();
    }
    
    public List<Categoria> obtenerCategorias(){
        //obtengo todas las categorias de la bd
        Query query = entityManager.createQuery("SELECT c FROM Categoria c", Categoria.class);
        //las guardo en la colecion
        List<Categoria> listCategorias = query.getResultList();
        categorias.clear();
        listCategorias.stream().forEach((cat) -> {
            entityManager.refresh(cat);
            categorias.add(cat);
        });
        categorias = query.getResultList();
        return categorias;
    }
    
    public Categoria getCategoria(String nombre){
        Iterator it = this.obtenerCategorias().iterator();
        while(it.hasNext()){
            Categoria current = (Categoria)it.next();
            if(current.getNombre() == nombre)
                return current;
        }
        return null;
    }
    
    public void eliminarCategoria (String nombre){
        Categoria aBorrar = new Categoria();
        Iterator<Categoria> it = categorias.iterator();
        while(it.hasNext()){
            Categoria current = it.next();
            if(current.getNombre() == nombre){
                aBorrar = current;
                break;
            }
        }
        categorias.remove(nombre);
        
        entityManager.getTransaction().begin();
        entityManager.remove(aBorrar);
        entityManager.getTransaction().commit();
    }
    
}
