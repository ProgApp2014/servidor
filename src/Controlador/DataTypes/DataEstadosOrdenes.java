package Controlador.DataTypes;

import Controlador.Clases.EstadosOrdenes;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class DataEstadosOrdenes implements Serializable {

    private Integer id;

    private DataOrdenCompra orden;

    private Integer estado;

    private Date fecha;

    private String nombre;

    public DataEstadosOrdenes() {
    }

    public DataEstadosOrdenes(EstadosOrdenes eo) {
        this.id = eo.getId();
        this.orden = new DataOrdenCompra(eo.getOrden(), false);
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

    public String getNombre() {
        String res;
        res = estado == 0 ? "Recibida" : estado == 1 ? "Preparada" : estado == 2 ? "Confirmada" : "Cancelada";
        return res;

    }

    public void setNombre(String nombre) {
        //this.nombre = nombre;
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
