package Controlador.DataTypes;

import Controlador.Clases.ClienteCompraProducto;
import Controlador.Clases.OrdenCompra;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType; 

@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class DataOrdenCompra {
    
    private Integer nroOrden;
    private Date fecha;
    private Float precioTotal;
    private ArrayList<DataClienteCompraProducto> clienteCompraProducto;
    public DataOrdenCompra(){}
    public DataOrdenCompra(OrdenCompra oc) {
        this.nroOrden = oc.getNroOrden();
        this.fecha = oc.getFecha();
        this.precioTotal = oc.getPrecioTotal();
        this.clienteCompraProducto = new ArrayList<DataClienteCompraProducto>();
        Iterator it = oc.getClienteCompraProducto().iterator();
        while(it.hasNext()){
            clienteCompraProducto.add(new DataClienteCompraProducto((ClienteCompraProducto)it.next()));
        }
    }
    
    public DataOrdenCompra(Integer nroOrden) {
        this.nroOrden = nroOrden;
        this.fecha = new Date();
        this.precioTotal = 0.0f;
    }
    
    public DataOrdenCompra(Integer nroOrden, Date fecha, Float precioTotal, ArrayList<DataClienteCompraProducto> clienteCompraProducto) {
        this.nroOrden = nroOrden;
        this.fecha = fecha;
        this.precioTotal = precioTotal;
        this.clienteCompraProducto = new ArrayList<>();
        this.clienteCompraProducto.addAll(clienteCompraProducto);
    }
    
    public DataOrdenCompra(Integer nroOrden, ArrayList<DataClienteCompraProducto> clienteCompraProducto) {
        this.nroOrden = nroOrden;
        this.fecha = new Date();
        this.precioTotal = 0.0f;
        this.clienteCompraProducto = new ArrayList<>();
        this.clienteCompraProducto.addAll(clienteCompraProducto);
    }

    public Integer getNroOrden() {
        return nroOrden;
    }
    
    public void setNroOrden(Integer nroOrden) {
        this.nroOrden = nroOrden;
    }
    
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public String getFechaFormateada(){
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
        String formatted = format1.format(fecha.getTime());
        return formatted;
    }
    
    public ArrayList<DataClienteCompraProducto> getClienteCompraProducto() {
        return clienteCompraProducto;
    }

    public void setClienteCompraProducto(ArrayList<DataClienteCompraProducto> clienteCompraProducto) {
        this.clienteCompraProducto = new ArrayList<>();
        this.clienteCompraProducto.addAll(clienteCompraProducto);
    }
    
    public Float getPrecioTotal() {
        return precioTotal;
    }
    
    public void setPrecioTotal(Float pt) {
        this.precioTotal = pt;
    }
    
    @Override
    public String toString() {
        return this.getNroOrden() + "  --  " + this.getFecha() + "  --  " + this.getClienteCompraProducto() ;
    }
    
}
