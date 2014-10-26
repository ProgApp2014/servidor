package Controlador.DataTypes;

import Controlador.Clases.EspecificacionProducto;
import Controlador.Clases.Producto;
import Controlador.Clases.Proveedor;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType; 

@XmlAccessorType(XmlAccessType.FIELD)
public class DataProducto {
    
    private Integer id;
    private String idEspecifico;
    private Boolean enOrden;
    private DataEspecificacionProducto especificacionProducto;
    public DataProducto(){}
    public DataProducto(Producto p) {
        this.id = p.getId();
        this.idEspecifico = p.getIdEspecifico();
        this.enOrden = p.getEnOrden();
        this.especificacionProducto = p.getDataEspecificacionProducto();
    }
    
    public DataProducto(Integer id, DataEspecificacionProducto especificacionProducto) {
        this.id = id;
        this.idEspecifico = null;
        this.enOrden = false;
        this.especificacionProducto = especificacionProducto;
    }
    
    public DataProducto(Integer id, String idEspecifico, Boolean enOrden, DataEspecificacionProducto especificacionProducto) {
        this.id = id;
        this.idEspecifico = idEspecifico;
        this.enOrden = enOrden;
        this.especificacionProducto = especificacionProducto;
    }

    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Boolean getEnOrden() {
        return enOrden;
    }
    
    public void setEnOrden(Boolean enOrden) {
        this.enOrden = enOrden;
    }
    
    public String getIdEspecifico() {
        return idEspecifico;
    }
    
    public void setIdEspecifico(String id) {
        this.idEspecifico = id;
    }
    
    public DataEspecificacionProducto getEspecificacionProducto() {
        return especificacionProducto;
    }
    
    public EspecificacionProducto getObjectEspecificacionProducto() {
        Proveedor prov = new Proveedor(especificacionProducto.getProveedor());
        return new EspecificacionProducto(especificacionProducto, prov);
    }
    
    public void setEspecificacionProducto(DataEspecificacionProducto especificacionProducto) {
        this.especificacionProducto = especificacionProducto;
    }
    
    @Override
    public String toString() {
        return this.getId() + "  --  " + this.getEspecificacionProducto();
    }
    
}
