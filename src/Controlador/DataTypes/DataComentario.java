package Controlador.DataTypes;

import Controlador.Clases.Comentario;
import java.util.Calendar;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType; 

@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class DataComentario {
    private Integer id;
    private DataComentario padre;
    private DataCliente cliente;
    private DataEspecificacionProducto especificacionProducto;
    private String comentario;
    private Calendar fecha;
    public DataComentario(){}
    public DataComentario(Comentario c) {
        this.id = c.getId();
//        System.out.println(c.getId()+"  cliente "+c.getCliente());
//        System.out.println(c.getId()+"  Padre "+(c.getPadre()!=null?c.getPadre().getId():""));
        this.padre = c.getPadre() == null?null:new DataComentario(c.getPadre());
        this.cliente = new DataCliente(c.getCliente());
        this.especificacionProducto = new DataEspecificacionProducto(c.getEspecificacionProducto(),false);
        this.comentario = c.getComentario();
        this.fecha =  Calendar.getInstance();
        this.fecha.setTime(c.getFecha());
                
    }

   
    
    public DataComentario(DataCliente cliente, DataEspecificacionProducto especificacionProducto, String comentario, Integer id, DataComentario padre) {
        this.id = id;
        this.padre = padre;
        this.cliente = cliente;
        this.especificacionProducto = especificacionProducto;
        this.comentario = comentario;
    }

    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public DataComentario getPadre() {
        return padre;
    }
    
    public void setPadre(DataComentario padre) {
        this.padre = padre;
    }
    
    public DataCliente getCliente() {
        return cliente;
    }
    
    public void setCliente(DataCliente cliente) {
        this.cliente = cliente;
    }
     public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }
    
    public DataEspecificacionProducto getEspecificacionProducto() {
        return especificacionProducto;
    }

    public void setEspecificacionProducto(DataEspecificacionProducto especificacionProducto) {
        this.especificacionProducto = especificacionProducto;
    }
    
    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
    public boolean tienePadre() {
        return this.padre != null;
    }
}
