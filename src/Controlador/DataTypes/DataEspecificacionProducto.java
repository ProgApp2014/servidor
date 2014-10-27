package Controlador.DataTypes;

import Controlador.Clases.EspecificacionProducto;
import Controlador.Clases.Producto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType; 

@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class DataEspecificacionProducto {
    
    private String nroReferencia;
    private String nombre;
    private String descripcion;
    private HashMap<String,String> especificacion;
    private Float precio;
    private DataProveedor proveedor;
    private ArrayList<String> imagenes;
    private ArrayList<DataCategoria> categorias;
    private ArrayList<DataProducto> productos;
    private ArrayList<DataComentario> comentarios;
    public DataEspecificacionProducto(){}
    public DataEspecificacionProducto(EspecificacionProducto ep, boolean conCategorias) {
        this.nroReferencia = ep.getNroReferencia();
        this.nombre = ep.getNombre();
        this.descripcion = ep.getDescripcion();
        this.especificacion = new HashMap<>();
        
        Iterator it = ep.getEspecificacion().keySet().iterator();
        while(it.hasNext()){
            String key =(String) it.next();
            this.especificacion.put(key,ep.getEspecificacion().get(key));
        }
        
        
        this.precio = ep.getPrecio();
        this.proveedor = ep.getDataProveedor();
        this.imagenes = new ArrayList<>();
        this.imagenes.addAll(ep.getImagenes());
        
        if(conCategorias){
            this.categorias = new ArrayList<>();
            ep.getCategorias().stream().forEach((valor) -> {
                this.categorias.add(new DataCategoria(valor, false));
            });
            this.productos = new ArrayList();
            ep.getListaProductos().forEach((producto) -> {
               productos.add(new DataProducto(producto));
            });
            this.comentarios = new ArrayList();
            if(ep.getComentarios() != null  && !ep.getComentarios().isEmpty()){
                ep.getComentarios().forEach((comentario) -> {
                   comentarios.add(new DataComentario(comentario));
                });
            }
        }else{
            this.categorias = new ArrayList<DataCategoria>();
            this.productos = new ArrayList();
            this.comentarios = new ArrayList();
        }
    }
    
    public DataEspecificacionProducto(String nroReferencia, String nombre, String descripcion, HashMap<String,String> especificacion, Float precio, DataProveedor proveedor, ArrayList<String> imagenes, ArrayList<DataCategoria> categorias,ArrayList<DataProducto> productos,ArrayList<DataComentario> comentarios) {
        this.nroReferencia = nroReferencia;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.especificacion = especificacion;
        this.precio = precio;
        this.proveedor = proveedor;
        this.imagenes = imagenes;
        this.categorias = categorias;
        
        this.productos = new ArrayList<>();
        this.productos.addAll(productos);
        this.comentarios = new ArrayList<>();
        this.comentarios.addAll(comentarios);
    }

    public String getNroReferencia() {
        return nroReferencia;
    }

    public void setNroReferencia(String nroReferencia) {
        this.nroReferencia = nroReferencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public HashMap<String,String> getEspecificacion() {
        return especificacion;
    }

    public void setEspecificacion(HashMap<String,String> especificacion) {
        this.especificacion = especificacion;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }
    
    public DataProveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(DataProveedor proveedor) {
        this.proveedor = proveedor;
    }

    public ArrayList<String> getImagenes() {
        return imagenes;
    }

    public void setImagenes(ArrayList<String> imagenes) {
        this.imagenes = imagenes;
    }
    
    public ArrayList<DataCategoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(ArrayList<DataCategoria> categorias) {
        this.categorias = new ArrayList<>();
        this.categorias.addAll(categorias);
    }
    
    public ArrayList<DataProducto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<DataProducto> productos) {
        this.productos = new ArrayList<>();
        this.productos.addAll(productos);
    }
    
    public ArrayList<DataComentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(ArrayList<DataComentario> comentarios) {
        this.comentarios = new ArrayList<>();
        this.comentarios.addAll(comentarios);
    }
    
    public int getStock(){
        Integer result = 0;
        Iterator<DataProducto> it = (Iterator<DataProducto>)productos.iterator();
        while(it.hasNext()) {
            DataProducto current = it.next();
            if(!current.getEnOrden())
                result++;
        }
        return result;
            
    }
    @Override
    public String toString() {
        return this.getNroReferencia() + "  --  " + this.getNombre();
    }
    
}
