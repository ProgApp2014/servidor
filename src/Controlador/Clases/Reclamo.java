package Controlador.Clases;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

@Entity
public class Reclamo  implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    
    @ManyToOne
    @JoinColumn(name = "CLIENTE_ID")
    private Cliente cliente;
    
    @ManyToOne
    @JoinColumn(name = "PRODUCTO_ID")
    private EspecificacionProducto especificacionProducto;
    private String reclamo;
    
    public Reclamo(){
        this.fecha = new Date();
    }
    
    public Reclamo(Integer id, Cliente cliente, EspecificacionProducto especificacionProducto, String reclamo) {
        this.id = id;
        this.cliente = cliente;
        this.especificacionProducto = especificacionProducto;
        this.reclamo = reclamo;
        this.fecha = new Date();
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
    public EspecificacionProducto getEspecificacionProducto() {
        return especificacionProducto;
    }

    public void setEspecificacionProducto(EspecificacionProducto especificacionProducto) {
        this.especificacionProducto = especificacionProducto;
    }
    
    public String getReclamo() {
        return reclamo;
    }

    public void setReclamo(String reclamo) {
        this.reclamo = reclamo;
    }
    
}
