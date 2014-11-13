package Controlador.DataTypes;

import Controlador.Clases.Reclamo;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class DataReclamo implements Serializable {
    private Integer id;
    private DataCliente cliente;
    private DataEspecificacionProducto especificacionProducto;
    private String reclamo;
    
    public DataReclamo() {}
    
    public DataReclamo(Reclamo r) {
        this.id = r.getId();
        this.cliente = new DataCliente(r.getCliente());
        this.especificacionProducto = new DataEspecificacionProducto(r.getEspecificacionProducto(),false);
        this.reclamo = r.getReclamo();
    }
    
    public DataReclamo(DataCliente cliente, DataEspecificacionProducto especificacionProducto, String reclamo, Integer id) {
        this.id = id;
        this.cliente = cliente;
        this.especificacionProducto = especificacionProducto;
        this.reclamo = reclamo;
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
    
}
