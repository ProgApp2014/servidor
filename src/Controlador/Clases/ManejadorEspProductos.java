package Controlador.Clases;

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

public class ManejadorEspProductos {
    
    private static ManejadorEspProductos instance = null;
    Map<String,EspecificacionProducto> especificacionProductos = new HashMap();
    
    EntityManagerFactory EntityManagerFactory = Persistence.createEntityManagerFactory("ProgramacionAppPU");
    EntityManager entityManager = EntityManagerFactory.createEntityManager();
    
    public static ManejadorEspProductos getInstance(){
        if(ManejadorEspProductos.instance == null){
            
            ManejadorEspProductos.instance = new ManejadorEspProductos();
        }
        return ManejadorEspProductos.instance;
    }
    
    private ManejadorEspProductos(){
    
    }
    
    public void agregarEspecificacionProducto(EspecificacionProducto especificacionProducto){
        especificacionProductos.put(especificacionProducto.getNroReferencia(), especificacionProducto);
        especificacionProducto.getCategorias().stream().forEach((valor) -> {
            ManejadorCategorias.getInstance().getCategoria(valor.getNombre()).agregarProducto(especificacionProducto);
        });

        //guardo la especificacion de producto en bd
        entityManager.getTransaction().begin();
        entityManager.persist(especificacionProducto);
        entityManager.getTransaction().commit();
    }
    
    public Map<String,EspecificacionProducto> obtenerEspecificacionProductos(){
        //obtengo todas las categorias de la bd
        Query query = entityManager.createQuery("SELECT e FROM EspecificacionProducto e", EspecificacionProducto.class);
        
        //las guardo en la colecion
        List<EspecificacionProducto> listEspProd = query.getResultList();
        especificacionProductos.clear();
        listEspProd.stream().forEach((esp) -> {
            especificacionProductos.put(esp.getNroReferencia(), esp);
        });
        return especificacionProductos;
    }
    
    public void modificarProducto(EspecificacionProducto especificacionProducto){
        if(entityManager.find(EspecificacionProducto.class, especificacionProducto.getNroReferencia()) == null){
           throw new IllegalArgumentException("Unknown Employee id");
       }
       entityManager.getTransaction().begin();
       entityManager.merge(especificacionProducto);
       entityManager.getTransaction().commit();
    }
    
    public EspecificacionProducto getEspecificacionProducto(String nroRef){
        return this.obtenerEspecificacionProductos().get(nroRef);
    }
    
    public void eliminarEspecificacionProducto (String nroRef){
        EspecificacionProducto aBorrar = especificacionProductos.get(nroRef);
        especificacionProductos.remove(nroRef);
        
        entityManager.getTransaction().begin();
        entityManager.remove(aBorrar);
        entityManager.getTransaction().commit();
    }    
    
    public List<EspecificacionProducto> buscarEspProductos(String keyword){
        Query query = entityManager.createQuery("SELECT DISTINCT e FROM EspecificacionProducto e JOIN e.categorias c where upper(e.nombre) LIKE :x or upper(c.nombre) LIKE :y", EspecificacionProducto.class);
        query.setParameter("x", '%' + keyword.toUpperCase() + '%');
        query.setParameter("y", '%' + keyword.toUpperCase() + '%');
        List<EspecificacionProducto> listEspProd = query.getResultList();
        
        return listEspProd;
    }
    
    public Map<String,List<EspecificacionProducto>> buscarEspProductosSeparados(String keyword, String Orden){
        Map<String,List<EspecificacionProducto>> result = new HashMap();
        String orderBy = Orden.equals("nombre") ? " ORDER BY upper(e.nombre) ASC" : Orden.equals("precio") ? " ORDER BY e.precio DESC" : "";
        Query query = entityManager.createQuery("SELECT DISTINCT e FROM EspecificacionProducto e where upper(e.nombre) LIKE :x"+orderBy, EspecificacionProducto.class);
        query.setParameter("x", '%' + keyword.toUpperCase() + '%');
        List<EspecificacionProducto> listEspProd = query.getResultList();
        result.put("productos", listEspProd);
        List<String> idObtenidas = new ArrayList();
        Iterator it = listEspProd.iterator();
        while(it.hasNext()){
            EspecificacionProducto current = (EspecificacionProducto)it.next();
            idObtenidas.add(current.getNroReferencia());
        }
        query = entityManager.createQuery("SELECT DISTINCT e FROM EspecificacionProducto e JOIN e.categorias c where upper(c.nombre) LIKE :y AND e.nroReferencia NOT IN :ids"+orderBy, EspecificacionProducto.class);
        query.setParameter("y", '%' + keyword.toUpperCase() + '%');
        query.setParameter("ids", idObtenidas);
        result.put("categorias", query.getResultList());
        
        return result;
    }
    
}
