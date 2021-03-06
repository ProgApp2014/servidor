package Controlador.DataTypes;

import Controlador.Clases.Categoria;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType; 

@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class DataCategoria implements Serializable{
    
    private String nombre;
    private DataCategoria padre;
    private HashMap<String,DataEspecificacionProducto> listaProductos;
    
    public DataCategoria(){
    }
    public DataCategoria(Categoria c, boolean conProductos) {
        this.nombre = c.getNombre();
        if(c.getPadre() == null){
            this.padre = null;
        }else{
            this.padre = c.getDataPadre();
        }
        this.listaProductos = new HashMap();
        if(conProductos){
            c.getListaProductos().entrySet().forEach((producto) -> {
               this.listaProductos.put(producto.getKey(),new DataEspecificacionProducto(producto.getValue(),true));
            });
        }
    }
       
    public DataCategoria(String nombre, DataCategoria padre) {
        this.nombre = nombre;
        this.padre = padre;
    }

    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public DataCategoria getPadre() {
        return padre;
    }
    
    public void setPadre(DataCategoria padre) {
        this.padre = padre;
    }
    
    public HashMap<String,DataEspecificacionProducto> getListaProductos() {
        return this.listaProductos;
    }
    
    public void setListaProductos(HashMap<String,DataEspecificacionProducto> productos) {
        this.listaProductos =   productos;
    }
    
    @Override
    public String toString() {
        return this.getNombre() + "  --  " + this.getPadre();
    }

    public boolean tienePadre() {
        return this.padre != null;
    }
    
}
