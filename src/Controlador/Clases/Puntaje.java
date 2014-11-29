

/**
 *
 * @author mauro
 */
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
public class Puntaje implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fecha;
    
    @ManyToOne
    @JoinColumn(name = "CLIENTE_ID")
    private Cliente cliente;
    
    @ManyToOne
    @JoinColumn(name = "PRODUCTO_ID")
    private EspecificacionProducto especificacionProducto;
    private Integer puntaje;
    
    public Puntaje(){
        this.fecha = new Date();
    }
    
    public Puntaje(Integer id, Cliente cliente, EspecificacionProducto especificacionProducto, Integer puntaje, Date fecha) {
        this.id = id;
        this.cliente = cliente;
        this.especificacionProducto = especificacionProducto;
        this.puntaje = puntaje;
        this.fecha = fecha;
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
    
    public Integer getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Integer puntaje) {
        this.puntaje = puntaje;
    }
    
}