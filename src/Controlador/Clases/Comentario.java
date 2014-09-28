package Controlador.Clases;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Comentario  implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "PADRE")
    private Comentario padre;
    
    @ManyToOne
    @JoinColumn(name = "CLIENTE_ID")
    private Cliente cliente;
    
    @ManyToOne
    @JoinColumn(name = "PRODUCTO_ID")
    private EspecificacionProducto especificacionProducto;
    private String comentario;
    
    public Comentario(){}
    
    public Comentario(Integer id, Cliente cliente, EspecificacionProducto especificacionProducto, String comentario, Comentario padre) {
        this.id = id;
        this.cliente = cliente;
        this.especificacionProducto = especificacionProducto;
        this.comentario = comentario;
        this.padre = padre;
    }

    public Cliente getCliente() {
        return cliente;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Comentario getPadre() {
        return padre;
    }
    
    public void setPadre(Comentario padre) {
        this.padre = padre;
    }
    
    public EspecificacionProducto getEspecificacionProducto() {
        return especificacionProducto;
    }

    public void setEspecificacionProducto(EspecificacionProducto especificacionProducto) {
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
