package Controlador.Clases;

import Controlador.DataTypes.DataClienteCompraProducto;
import Controlador.DataTypes.DataEstadosOrdenes;
import Controlador.DataTypes.DataOrdenCompra;
import Controlador.DataTypes.EstadoOrden;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

@Entity
public class OrdenCompra implements Serializable {

    @Id
    @Column(name = "NRO_ORDEN")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer nroOrden;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    @Column(name = "PRECIO")
    private Float precioTotal;
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, mappedBy = "Orden")
    @JoinColumn(name = "ORDEN_ID")
    private List<ClienteCompraProducto> clienteCompraProducto;
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, mappedBy = "orden")
    @JoinColumn(name = "ID")
    private List<EstadosOrdenes> estados;

    public OrdenCompra(Integer nroOrden, List<ClienteCompraProducto> clienteCompraProducto) {
        this.nroOrden = nroOrden;
        this.fecha = new Date();
        this.clienteCompraProducto = clienteCompraProducto;
        this.estados = new ArrayList();
    }

    public OrdenCompra(Integer nroOrden, Date fecha, List<ClienteCompraProducto> clienteCompraProducto, List<EstadosOrdenes> estados) {
        this.nroOrden = nroOrden;
        this.fecha = fecha;
        this.clienteCompraProducto = clienteCompraProducto;
        this.estados = new ArrayList();
        Iterator it = estados.iterator();
        while (it.hasNext()) {
            this.estados.add(new EstadosOrdenes((DataEstadosOrdenes) it.next()));
        }
    }

    public OrdenCompra(DataOrdenCompra doc) {
        this.nroOrden = doc.getNroOrden();

        this.fecha = doc.getFecha().getTime();

        this.estados = new ArrayList();
        Iterator it = doc.getEstados().iterator();
        while (it.hasNext()) {
            this.estados.add(new EstadosOrdenes((DataEstadosOrdenes) it.next()));
        }
    }

    public OrdenCompra() {
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

    public List<ClienteCompraProducto> getClienteCompraProducto() {
        return clienteCompraProducto;
    }

    public void setClienteCompraProducto(List<ClienteCompraProducto> clienteCompraProducto) {
        this.clienteCompraProducto = clienteCompraProducto;
    }

    public Float getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Float pt) {
        this.precioTotal = pt;
    }

    public List<EstadosOrdenes> getEstados() {
        return estados;
    }

    public void setEstados(List<EstadosOrdenes> estados) {
        this.estados = estados;
    }

    public Cliente getCliente() {
        Iterator<ClienteCompraProducto> it = this.getClienteCompraProducto().iterator();
        while (it.hasNext()) {
            ClienteCompraProducto cliProd = it.next();
            return cliProd.getCliente();
        }
        return null;
    }
    
    public int getEstadoActual(){
        return this.estados.get(this.estados.size() - 1).getEstado();
    }

    @Override
    public String toString() {
        return this.getNroOrden() + "  --  " + this.getFecha() + "  --  " + this.getClienteCompraProducto();
    }

}
