package Controlador.DataTypes;

import Controlador.Clases.EspecificacionProducto;
import Controlador.Clases.Producto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DataEspecificacionProducto {
    
    private String nroReferencia;
    private String nombre;
    private String descripcion;
    private Map<String,String> especificacion;
    private Float precio;
    private DataProveedor proveedor;
    private List<String> imagenes;
    private List<DataCategoria> categorias;
    private List<DataProducto> productos;
    private List<DataComentario> comentarios;
    
    public DataEspecificacionProducto(EspecificacionProducto ep, boolean conCategorias) {
        this.nroReferencia = ep.getNroReferencia();
        this.nombre = ep.getNombre();
        this.descripcion = ep.getDescripcion();
        this.especificacion = ep.getEspecificacion();
        this.precio = ep.getPrecio();
        this.proveedor = ep.getDataProveedor();
        this.imagenes = ep.getImagenes();
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
    
    public DataEspecificacionProducto(String nroReferencia, String nombre, String descripcion, Map<String,String> especificacion, Float precio, DataProveedor proveedor, ArrayList<String> imagenes, ArrayList<DataCategoria> categorias,List<DataProducto> productos,List<DataComentario> comentarios) {
        this.nroReferencia = nroReferencia;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.especificacion = especificacion;
        this.precio = precio;
        this.proveedor = proveedor;
        this.imagenes = imagenes;
        this.categorias = categorias;
        this.productos = productos;
        this.comentarios = comentarios;
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

    public Map<String,String> getEspecificacion() {
        return especificacion;
    }

    public void setEspecificacion(Map<String,String> especificacion) {
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

    public List<String> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<String> imagenes) {
        this.imagenes = imagenes;
    }
    
    public List<DataCategoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<DataCategoria> categorias) {
        this.categorias = categorias;
    }
    
    public List<DataProducto> getProductos() {
        return productos;
    }

    public void setProductos(List<DataProducto> productos) {
        this.productos = productos;
    }
    
    public List<DataComentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<DataComentario> comentarios) {
        this.comentarios = comentarios;
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
