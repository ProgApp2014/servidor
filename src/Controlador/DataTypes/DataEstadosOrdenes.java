
package Controlador.DataTypes;

import Controlador.Clases.EstadosOrdenes;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class DataEstadosOrdenes {
    private Integer id;
    
    private DataOrdenCompra orden;
    
    private Integer estado;
    
    private Date fecha;
    
    public DataEstadosOrdenes(){}
    
    public DataEstadosOrdenes(EstadosOrdenes eo){
        this.id = eo.getId();
        this.orden = new DataOrdenCompra(eo.getOrden(),false);
        this.estado = eo.getEstado();
        this.fecha = eo.getFecha();
    }
    
    public DataEstadosOrdenes(Integer id, DataOrdenCompra orden, Integer estado, Date fecha) {
        this.id = id;
        this.orden = orden;
        this.estado = estado;
        this.fecha = fecha;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public DataOrdenCompra getOrden() {
        return orden;
    }
    
    public void setCliente(DataOrdenCompra orden) {
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
