package Controlador.Clases;

import Controlador.DataTypes.DataCliente;
import Controlador.DataTypes.DataClienteCompraProducto;
import Controlador.DataTypes.DataProducto;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ClienteCompraProducto implements Serializable {
    
    @Id
    @ManyToOne
    @JoinColumn(name = "CLIENTE_ID")
    private Cliente cliente;
    @Id
    @ManyToOne
    @JoinColumn(name = "PRODUCTO_ID")
    private Producto producto;
    private Float precio;
    @Id
    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.ALL})
    @JoinColumn(name="ORDEN_ID")
    private OrdenCompra Orden;
    
    public ClienteCompraProducto(Cliente cliente, Producto producto, Float precio,OrdenCompra Orden) {
        this.cliente = cliente;
        this.producto = producto;
        this.precio = precio;
        this.Orden = Orden; 
    }
    
    public ClienteCompraProducto(DataClienteCompraProducto dccp) {
        this.precio = dccp.getPrecio();
    }
    
    public ClienteCompraProducto() {
        this.precio = 0.0f;
        this.cliente = null;
        this.producto = null;
        this.Orden = null;
    }

    public Cliente getCliente() {
        return cliente;
    }
    
    public DataCliente getDataCliente() {
        return new DataCliente(cliente);
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public Producto getProducto() {
        return producto;
    }
    
    public DataProducto getDataProducto() {
        return new DataProducto(producto);
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    public OrdenCompra getOrden() {
        return Orden;
    }
    
    public void setOrden(OrdenCompra orden) {
        this.Orden = orden;
    }
    
    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }
    
    public OrdenCompra obtenerOrdenCompra(){
        return null;
    }
    
    @Override
    public String toString() {
        return this.getCliente() + "  --  " + this.getProducto() + "  --  " + this.getPrecio() ;
    }
    
}
