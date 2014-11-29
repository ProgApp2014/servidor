package Controlador.DataTypes;

import Controlador.Clases.Puntaje;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class DataPuntaje implements Serializable {
    private Integer id;
    private DataCliente cliente;
    private DataEspecificacionProducto especificacionProducto;
    private Integer puntaje;
    private Calendar fecha;
    
    public DataPuntaje() {}
    
    public DataPuntaje(Puntaje p) {
        this.id = p.getId();
        this.cliente = new DataCliente(p.getCliente());
        this.especificacionProducto = new DataEspecificacionProducto(p.getEspecificacionProducto(),false);
        this.puntaje = p.getPuntaje();
        this.fecha =  Calendar.getInstance();
        this.fecha.setTime(p.getFecha());
    }
    
    public DataPuntaje(DataCliente cliente, DataEspecificacionProducto especificacionProducto, Integer puntaje, Integer id, Date fecha) {
        this.id = id;
        this.cliente = cliente;
        this.especificacionProducto = especificacionProducto;
        this.puntaje = puntaje;
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
    
    public Integer getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Integer puntaje) {
        this.puntaje = puntaje;
    }
    
    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }
    
}
