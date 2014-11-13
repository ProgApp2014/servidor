package Controlador.DataTypes;

import Controlador.Clases.Reclamo;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class DataReclamo implements Serializable {
    private Integer id;
    private DataCliente cliente;
    private DataEspecificacionProducto especificacionProducto;
    private String reclamo;
    private Calendar fecha;
    
    public DataReclamo() {}
    
    public DataReclamo(Reclamo r) {
        this.id = r.getId();
        this.cliente = new DataCliente(r.getCliente());
        this.especificacionProducto = new DataEspecificacionProducto(r.getEspecificacionProducto(),false);
        this.reclamo = r.getReclamo();
        this.fecha =  Calendar.getInstance();
        this.fecha.setTime(r.getFecha());
    }
    
    public DataReclamo(DataCliente cliente, DataEspecificacionProducto especificacionProducto, String reclamo, Integer id, Date fecha) {
        this.id = id;
        this.cliente = cliente;
        this.especificacionProducto = especificacionProducto;
        this.reclamo = reclamo;
        this.fecha =  Calendar.getInstance();
        this.fecha.setTime(fecha);
    }

    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public DataCliente getCliente() {
        return cliente;
    }
    
    public void setCliente(DataCliente cliente) {
        this.cliente = cliente;
    }
    
    public DataEspecificacionProducto getEspecificacionProducto() {
        return especificacionProducto;
    }

    public void setEspecificacionProducto(DataEspecificacionProducto especificacionProducto) {
        this.especificacionProducto = especificacionProducto;
    }
    
    public String getReclamo() {
        return reclamo;
    }

    public void setReclamo(String reclamo) {
        this.reclamo = reclamo;
    }
    
    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }
    
}
