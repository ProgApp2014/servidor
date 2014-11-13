/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Clases;

import Controlador.DataTypes.DataEstadosOrdenes;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author dario
 */

@Entity
public class EstadosOrdenes implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "NRO_ORDEN")
    private OrdenCompra orden;
    
    private Integer estado;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fecha;
    
    public EstadosOrdenes(){
        this.fecha = new Date();
    }
    
    public EstadosOrdenes(Integer id, OrdenCompra orden, Integer estado) {
        this.id = id;
        this.orden = orden;
        this.estado = estado;
        this.fecha = new Date();
    }
    
    public EstadosOrdenes(DataEstadosOrdenes eo) {
        this.id = eo.getId();
        this.orden = new OrdenCompra(eo.getOrden());
        this.estado = eo.getEstado();
        this.fecha = eo.getFecha();
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public OrdenCompra getOrden() {
        return orden;
    }
    
    public void setCliente(OrdenCompra orden) {
        this.orden = orden;
    }
    
    public Integer getEstado() {
        return estado;
    }
    
    public void setEstado(Integer estado) {
        this.estado = estado;
    }
    
    public Date getFecha() {
        return fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
}
