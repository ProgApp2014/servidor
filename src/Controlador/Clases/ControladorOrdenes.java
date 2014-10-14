package Controlador.Clases;

import Controlador.DataTypes.DataCategoria;
import Controlador.DataTypes.DataCliente;
import Controlador.DataTypes.DataClienteCompraProducto;
import Controlador.DataTypes.DataEspecificacionProducto;
import Controlador.DataTypes.DataOrdenCompra;
import Controlador.DataTypes.DataProducto;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ControladorOrdenes implements IControladorOrdenes{
    
    private Integer id;
    private Cliente clienteElegido;
    private Categoria categoriaElegida;
    private EspecificacionProducto espProdElegido;
    private List<Producto> productosElegidos = new ArrayList<>();
    private List<ClienteCompraProducto> cliComProds = new ArrayList<>();
//    private OrdenCompra nuevaOrden;
    private OrdenCompra ordenElegida;
    private Integer ultimaOrdenGuardada;
    
    
    @Override
    public Integer getId(){
        return this.id;
    }
    
    @Override
    public void setId(Integer id){
        this.id = id;
    }
    
    @Override
    public Integer getUltimaOrdenGuardada(){
        return this.ultimaOrdenGuardada;
    }
    
    @Override
    public List<DataCliente> listarClientes(){
        List<DataCliente> dataCliente = new ArrayList<>();
        ManejadorUsuarios.getInstance().obtenerClientes().entrySet().stream().map((cliente) -> cliente.getValue()).forEach((valor) -> {
            dataCliente.add(new DataCliente(valor));
        });
        return dataCliente;
    }
    
    @Override
    public void elegirCliente(String nickname){
        clienteElegido = ManejadorUsuarios.getInstance().getCliente(nickname);
    }
    
    @Override
    public List<DataCategoria> listarCategorias(){
        List<DataCategoria> dataCategoria = new ArrayList<>();
        ManejadorCategorias.getInstance().obtenerCategorias().stream().forEach((valor) -> {
            dataCategoria.add(new DataCategoria(valor,true));
        });
        return dataCategoria;
    }
    
    @Override
    public void elegirCategoria(String categoria){
        categoriaElegida = ManejadorCategorias.getInstance().getCategoria(categoria);
    }
    
    @Override
    public List<DataEspecificacionProducto> listarEspecificacionProductos(){
        List<DataEspecificacionProducto> dataEspecificacionProducto = new ArrayList<>();
        ManejadorEspProductos.getInstance().obtenerEspecificacionProductos().entrySet().stream().map((espProducto) -> espProducto.getValue()).forEach((valor) -> {
            if(valor.getCategorias().contains(categoriaElegida.getNombre())){
                dataEspecificacionProducto.add(new DataEspecificacionProducto(valor,true));
            }
        });
        return dataEspecificacionProducto;
    }
    
    @Override
    public void elegirEspecificacionProducto(String nroRef){
        espProdElegido = ManejadorEspProductos.getInstance().getEspecificacionProducto(nroRef);
    }
    
    @Override
    public List<DataProducto> listarProductosEnEspecificacion(){
        List<DataProducto> result = new ArrayList<>();
        Iterator it = espProdElegido.getListaProductos().iterator();
        while(it.hasNext()){
            Producto current = (Producto) it.next();
            result.add(new DataProducto(current));
        }
        return result;
    }
    
    @Override
    public void removerEspecificacionProducto(String nroRef){
            espProdElegido = null;
    }
    //@Override
//    public void elegirMetodoDeSeleccion(String metodo){
//        
//    }
    
    @Override
    public List<DataEspecificacionProducto> listarProductosEnOrden(){
        List<DataEspecificacionProducto> dataProductos = new ArrayList<>();
        ordenElegida.getClienteCompraProducto().forEach((cliProd) -> {
            DataClienteCompraProducto clienteproducto = new DataClienteCompraProducto(cliProd);
            DataEspecificacionProducto aux = clienteproducto.getProducto().getEspecificacionProducto();
            aux.setPrecio(clienteproducto.getPrecio()); //precio al momento de realizar la compra
            dataProductos.add(clienteproducto.getProducto().getEspecificacionProducto());
        });
        return dataProductos;
    }
    
    @Override
    public void elegirProducto(Integer id){
        Iterator it = espProdElegido.getListaProductos().iterator();
        while(it.hasNext()){
            Producto current = (Producto) it.next();
            if(current.getId() == id)
                productosElegidos.add(current);
        }     
        
    }
    @Override
    public void elegirCantidadProducto(Integer cantidad){
         System.out.println(cantidad+" cantidad<<<<");
        Iterator it =  espProdElegido.getListaProductos().iterator();
        int indice = 0;
        while(it.hasNext() && indice<cantidad){
            Producto current = (Producto) it.next();
            if(!current.getEnOrden()){
                productosElegidos.add(current);
                indice++;
            }
        }
    }
    
    @Override
    public List<DataEspecificacionProducto> listarProductosCategoria(){
        List<DataEspecificacionProducto> result = new ArrayList<>();
        categoriaElegida.getListaProductos().entrySet().stream().map((espProd) -> espProd.getValue()).forEach((valor) -> {
            result.add(new DataEspecificacionProducto(valor,true));
        });
        return result;
    }
    
    @Override
    public void generarItemOrden(){
        productosElegidos.stream().forEach((productoElegido) -> {
            cliComProds.add(new ClienteCompraProducto(clienteElegido, productoElegido, espProdElegido.getPrecio(), new OrdenCompra()));
        });
        productosElegidos = new ArrayList<>();
    }
    
    @Override
    public void guardarOrden(DataOrdenCompra dataOrden){
        OrdenCompra orden = new OrdenCompra(dataOrden);
        List<ClienteCompraProducto> cliComProd = new ArrayList<>();
        
        Float tempSumTotal = 0.0f; 
        for(ClienteCompraProducto cliProd : cliComProds){
            
            cliComProd.add(cliProd);
            tempSumTotal += cliProd.getPrecio();
        }
        
        
        cliComProds.forEach((clienteproducto) -> {
            clienteproducto.setOrden(orden);
            clienteproducto.setCliente(clienteElegido);
        });
        
        System.out.println(cliComProds+" >clicom");
        orden.setPrecioTotal(tempSumTotal);
        orden.setClienteCompraProducto(cliComProd);
        ManejadorOrdenes.getInstance().agregarOrden(orden);
        ultimaOrdenGuardada = orden.getNroOrden();
        cliComProds = new ArrayList<>();
    }
    
    //@Override
//    public void imprimirDatosOrden(){
//        
//    }
    @Override
    public List<DataOrdenCompra> listarOrdenes(){
        List<DataOrdenCompra> dataOrdenCompra = new ArrayList<>();
        ManejadorOrdenes.getInstance().obtenerOrdenes().entrySet().stream().map((orden) -> orden.getValue()).forEach((valor) -> {
            dataOrdenCompra.add(new DataOrdenCompra(valor));
        });
        return dataOrdenCompra;
    }
    
    @Override
    public void elegirOrden(Integer nroOrden){
        ordenElegida = ManejadorOrdenes.getInstance().getOrden(nroOrden);
    }
    
//    @Override
//    public Boolean confirmarEliminacion(){
//        return true;
//    }
    
    @Override
    public void borrarOrdenCompra(){
        ManejadorOrdenes.getInstance().eliminarOrden(ordenElegida.getNroOrden());
    }
    
    @Override
    public DataOrdenCompra mostrarDatosOrden(){
        DataOrdenCompra dataOrden = new DataOrdenCompra(ordenElegida);
        return dataOrden;
    }

   
}
