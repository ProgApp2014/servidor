package Controlador.DataTypes;

import Controlador.Clases.ClienteCompraProducto;
import Controlador.Clases.EstadosOrdenes;
import Controlador.Clases.OrdenCompra;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class DataOrdenCompra {

    private Integer nroOrden;
    private Calendar fecha;
    private Float precioTotal;
    private ArrayList<DataClienteCompraProducto> clienteCompraProducto;
    private ArrayList<DataEstadosOrdenes> estados;

    public DataOrdenCompra() {
        this.fecha = Calendar.getInstance();
        this.fecha.setTime(new Date());
        this.estados = new ArrayList();
    }

    public DataOrdenCompra(OrdenCompra oc) {
 
    }

    public DataOrdenCompra(OrdenCompra oc, Boolean conEstados) {

        this.nroOrden = oc.getNroOrden();
        this.fecha = Calendar.getInstance();
        this.fecha.setTime(oc.getFecha());
        this.precioTotal = oc.getPrecioTotal();
        this.clienteCompraProducto = new ArrayList<DataClienteCompraProducto>();
        Iterator it = oc.getClienteCompraProducto().iterator();
        while (it.hasNext()) {
            clienteCompraProducto.add(new DataClienteCompraProducto((ClienteCompraProducto) it.next()));
        }
        estados = new ArrayList();
        if (conEstados) {
            Iterator it2 = oc.getEstados().iterator();
            while (it2.hasNext()) {
                estados.add(new DataEstadosOrdenes((EstadosOrdenes) it2.next()));
            }
        }

    }

    public DataOrdenCompra(Integer nroOrden) {
        this.nroOrden = nroOrden;
        this.fecha = Calendar.getInstance();
        this.fecha.setTime(new Date());
        this.precioTotal = 0.0f;
        this.estados = new ArrayList();
    }
 
    public DataOrdenCompra(Integer nroOrden, Date fecha, Float precioTotal, ArrayList<DataClienteCompraProducto> clienteCompraProducto, ArrayList<DataEstadosOrdenes> estados) {
 
        this.nroOrden = nroOrden;
        this.fecha = Calendar.getInstance();
        this.fecha.setTime(fecha);
        this.precioTotal = precioTotal;
        this.clienteCompraProducto = new ArrayList<>();
        this.clienteCompraProducto.addAll(clienteCompraProducto);
        this.estados = new ArrayList<>();
        this.estados.addAll(estados);
    }


    public DataOrdenCompra(Integer nroOrden, ArrayList<DataClienteCompraProducto> clienteCompraProducto, ArrayList<DataEstadosOrdenes> estados) {
 
        this.nroOrden = nroOrden;
        this.fecha = Calendar.getInstance();
        this.fecha.setTime(new Date());
        this.precioTotal = 0.0f;
        this.clienteCompraProducto = new ArrayList<>();
        this.clienteCompraProducto.addAll(clienteCompraProducto);
        this.estados = new ArrayList<>();
        this.estados.addAll(estados);
    }

    public Integer getNroOrden() {
        return nroOrden;
    }

    public void setNroOrden(Integer nroOrden) {
        this.nroOrden = nroOrden;
    }

    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = Calendar.getInstance();
        this.fecha.setTime(fecha);
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
 
    
    public ArrayList<DataEstadosOrdenes> getEstados() {
        return estados;
    }

    public void setEstados(ArrayList<DataEstadosOrdenes> estados) {
        this.estados = new ArrayList<>();
        this.estados.addAll(estados);
    }
 
    @Override

    public String toString() {
        return this.getNroOrden() + "  --  " + this.getFecha() + "  --  " + this.getClienteCompraProducto();
    }

}
