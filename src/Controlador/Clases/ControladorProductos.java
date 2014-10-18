package Controlador.Clases;

import Controlador.DataTypes.DataCategoria;
import Controlador.DataTypes.DataComentario;
import Controlador.DataTypes.DataEspecificacionProducto;
import Controlador.DataTypes.DataOrdenCompra;
import Controlador.DataTypes.DataProducto;
import Controlador.DataTypes.DataProveedor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import static java.util.Objects.isNull;

public class ControladorProductos implements IControladorProductos{
    
    private Integer id;
    private EspecificacionProducto espProductoModificada;
    private Proveedor proveedorElegido;
    private EspecificacionProducto espProdElegido;
    private Map<String,String> especificaciones = new HashMap();
    private EspecificacionProducto nuevoEspProducto;
    private Categoria nuevaCategoria;
    private Categoria categoriaElegida;
    private List<Categoria> categoriasElegidas = new ArrayList();
    private List<String> imagenes = new ArrayList<>();
    private List<Producto> productosAAgregar = new ArrayList();
    
    //    - prvLst Set<Proveedor>
//    - espLst : map<string,string>
//    - prdList : Productos
//    - lstCategorias  : Set<Categoria>
    
    @Override
    public Integer getId(){
        return this.id;
    }
    
    @Override
    public void setId(Integer id){
        this.id = id;
    }
    
    @Override
    public List<DataProveedor> listarProveedores(){
        List<DataProveedor> dataProveedor = new ArrayList<>();
        ManejadorUsuarios.getInstance().obtenerProveedores().entrySet().stream().map((cliente) -> cliente.getValue()).forEach((valor) -> {
            dataProveedor.add(new DataProveedor(valor));
        });
        return dataProveedor;
    }
    
    @Override
    public void elegirProveedor(String nickname){
        proveedorElegido = ManejadorUsuarios.getInstance().getProveedor(nickname);
 
    }
    
    @Override
    public void ingresarDatosProductos(DataEspecificacionProducto espProducto){
        nuevoEspProducto = new EspecificacionProducto(espProducto,proveedorElegido);
    }
    
    @Override
    public Boolean elegirTipoProducto(){
        return true;
    }
    
    @Override
    public void agregarMultiplesProductosAutogenerados(Integer cantidad){
        int newId = ManejadorProductos.getInstance().obtenerProductos().keySet().size();
        for(Integer i = 0; i < cantidad; i++){
            productosAAgregar.add(new Producto(newId, nuevoEspProducto));
            newId++;
        }
    }
    
    @Override
    public void ingresarDatosUnidad(DataProducto producto){
        productosAAgregar.add(new Producto(producto));
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
    public List<DataCategoria> listarCategoriasAModificar(){
        List<DataCategoria> dataCategoria = new ArrayList<>();
        espProductoModificada.getCategorias().stream().forEach((valor) -> {
            categoriasElegidas.add(valor);
            dataCategoria.add(new DataCategoria(valor,true));
        });
        return dataCategoria;
    }
    
    @Override
    public void agregarCategoriaAEspecificacion(String categoria){
        categoriasElegidas.add(ManejadorCategorias.getInstance().getCategoria(categoria));
    }
    
    @Override
    public void borrarCategoriaAEspecificacion(String categoria){
        categoriasElegidas.remove(categoria);
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
    public DataEspecificacionProducto mostrarDatosProducto(String numRef){
        EspecificacionProducto productoElegido = ManejadorEspProductos.getInstance().getEspecificacionProducto(numRef);
        DataEspecificacionProducto dataProducto = new DataEspecificacionProducto(productoElegido,true);
        return dataProducto;
    }
    
    @Override
    public void listarImagenesDisco(){
        
    }
    
    @Override
    public List<String> listarImagenesAModificar(){
        imagenes = espProductoModificada.getImagenes();
        return imagenes;
    }
    
    @Override
    public Boolean controlarErrores(){
        if(!isNull(ManejadorEspProductos.getInstance().getEspecificacionProducto(nuevoEspProducto.getNroReferencia()))){
            return false;
        }    
        if(categoriasElegidas.isEmpty())
            return false;
        for(Entry<String,EspecificacionProducto> iter : ManejadorEspProductos.getInstance().obtenerEspecificacionProductos().entrySet()){
            if(iter.getValue().getNombre().equals(nuevoEspProducto.getNombre())){
                return false;
            }
        }
        return true;
    }
    
    @Override
    public void guardarProducto(){
        
        System.err.println("prod ");
        nuevoEspProducto.setCategorias(categoriasElegidas);
        nuevoEspProducto.setEspecificacion(especificaciones);
        System.err.println("prod "+productosAAgregar);
        nuevoEspProducto.setImagenes(imagenes);
        nuevoEspProducto.setListaProductos(productosAAgregar);
        ManejadorEspProductos.getInstance().agregarEspecificacionProducto(nuevoEspProducto);

        especificaciones = new HashMap();
        imagenes = new ArrayList<>();
        productosAAgregar = new ArrayList();
    }
    
    @Override
    public DataCategoria elegirCategoriaPadre(String categoria){
        return new DataCategoria(ManejadorCategorias.getInstance().getCategoria(categoria), true);
    }
    
    @Override
    public void ingresarDatosCategoria(DataCategoria categoria){
        nuevaCategoria = new Categoria(categoria);
        if(!isNull(categoria.getPadre())){
            asociarCategoriaPadre(categoria.getPadre());
        }
    }
    
    //@Override
//    public Boolean tienePadre(){
//        return true;
//    }
    
    @Override
    public void asociarCategoriaPadre(DataCategoria padre){
        Categoria catPadre = ManejadorCategorias.getInstance().getCategoria(padre.getNombre());
        nuevaCategoria.setPadre(catPadre);
    }
    @Override 
    public Boolean categoryAlreadyExist(String categoria){
        return  ManejadorCategorias.getInstance().getCategoria(categoria) != null;
    
    }
    @Override
    public void guardarCategoria(){   
        ManejadorCategorias.getInstance().agregarCategoria(nuevaCategoria);
    }
    
    @Override
    public void elegirEspProducto(String numRef){
        espProdElegido = ManejadorEspProductos.getInstance().getEspecificacionProducto(numRef);
    }
    
    @Override
    public DataProducto mostrarInformacionProducto(){
        return null;
    }
    
    @Override
    public void agregarComentario(String nickname, String nroRef, Integer padre, String Comentario){
        EspecificacionProducto aModificar = ManejadorEspProductos.getInstance().getEspecificacionProducto(nroRef);
        List<Comentario> comentarios = aModificar.getComentarios();
        Comentario comentarioAAgregar = new Comentario();
        comentarioAAgregar.setCliente(ManejadorUsuarios.getInstance().getCliente(nickname));
        comentarioAAgregar.setEspecificacionProducto(aModificar);
        Iterator it = comentarios.iterator();
        while(it.hasNext()){
            Comentario current = (Comentario)it.next();
            if(Objects.equals(current.getId(), padre)){
                comentarioAAgregar.setPadre(current);
            }
        }
        comentarioAAgregar.setComentario(Comentario);
        comentarios.add(comentarioAAgregar);
        ManejadorEspProductos.getInstance().getEspecificacionProducto(nroRef).setComentarios(comentarios);
        ManejadorEspProductos.getInstance().modificarProducto(aModificar);
    }
    
    /*@Override
    public ArrayList<DataEspecificacionProducto> listarEspecificacionProductos(){
        ArrayList<DataEspecificacionProducto> result = new ArrayList<DataEspecificacionProducto>();
        ManejadorEspProductos.getInstance().obtenerEspecificacionProductos().entrySet().stream().map((espProd) -> espProd.getValue()).forEach((valor) -> {
            result.add(new DataEspecificacionProducto(valor));
        });
        return result;
    }*/
    
    @Override
    public void modificarDatosEspecificacionProducto(DataEspecificacionProducto espProducto){
        espProductoModificada = new EspecificacionProducto(espProducto,ManejadorUsuarios.getInstance().getProveedor(espProducto.getProveedor().getNickname()));
    }
    
    @Override
    public void agregarImagen(String rutaImagen){
        imagenes.add(rutaImagen);
    }
    
    @Override
    public void borrarImagen(String rutaImagen){
        imagenes.remove(rutaImagen);
    }
    
    @Override
    public void agregarCategoria(DataCategoria categoria){
        
    }
    
    @Override
    public Boolean validarInfo(){   
        if(categoriasElegidas.isEmpty())
            return false;
        for(Entry<String,EspecificacionProducto> iter : ManejadorEspProductos.getInstance().obtenerEspecificacionProductos().entrySet()){
            if(!iter.getValue().getNroReferencia().equals(espProductoModificada.getNroReferencia()) && iter.getValue().getNombre().equals(nuevoEspProducto.getNombre())){
                return false;
            }
        }
        return true;
    }
    
    @Override
    public void ingresarEspecificacion(String clave, String desc){
        especificaciones.put(clave, desc);
    }
    
    @Override
    public void guardarEspProductoModificado(){
        espProductoModificada.setCategorias(categoriasElegidas);
        espProductoModificada.setEspecificacion(especificaciones);
        espProductoModificada.setImagenes(imagenes);
        espProductoModificada.setListaProductos(productosAAgregar);
        //ManejadorEspProductos.getInstance().obtenerEspecificacionProductos().remove(espProductoModificada.getNroReferencia());
        ManejadorEspProductos.getInstance().modificarProducto(espProductoModificada);
    }
    
    @Override
    public void eliminarCategoria(String nombre){
        ManejadorCategorias.getInstance().eliminarCategoria(nombre);
    }
    
    @Override
    public void eliminarEspecificacionProducto(String nroRef){
        ManejadorEspProductos.getInstance().eliminarEspecificacionProducto(nroRef);
    }
    
    @Override
    public List<DataEspecificacionProducto> buscarProductos(String keyword){
        List<DataEspecificacionProducto> result = new ArrayList();
        List<EspecificacionProducto> resultadosBusqueda = ManejadorEspProductos.getInstance().buscarEspProductos(keyword);
        Iterator it = resultadosBusqueda.iterator();
        while(it.hasNext()){
            EspecificacionProducto current = (EspecificacionProducto)it.next();
            result.add(new DataEspecificacionProducto(current,false));
        }
        return result;
    }
    
    @Override
    public Map<String,List<DataEspecificacionProducto>> buscarProductosSeparados(String keyword,String Orden){
        Map<String,List<DataEspecificacionProducto>> result = new HashMap();
        Map<String,List<EspecificacionProducto>> resultadosBusqueda = ManejadorEspProductos.getInstance().buscarEspProductosSeparados(keyword,Orden);
        if(Orden.equals("ventas"))
            ordenarPorVentas(resultadosBusqueda);
        resultadosBusqueda.keySet().forEach((tipo) -> {
            List<DataEspecificacionProducto> aAgregar = new ArrayList();
            Iterator it = resultadosBusqueda.get(tipo).iterator();
            while(it.hasNext()){
                EspecificacionProducto current = (EspecificacionProducto)it.next();
                aAgregar.add(new DataEspecificacionProducto(current,false));
            }
            result.put(tipo,aAgregar);
        });
        return result;
    }
    
    static Map<String,List<EspecificacionProducto>> ordenarPorVentas(Map<String,List<EspecificacionProducto>> arreglo) { 
        Map<String,List<EspecificacionProducto>> result = new HashMap();
        List<EspecificacionProducto> listaPorProducto = arreglo.get("productos");
        for(int i = 0; i < listaPorProducto.size() - 1; i++) { 
            for(int j = 0; j < listaPorProducto.size() - 1; j++) { 
                DataEspecificacionProducto current = new DataEspecificacionProducto(listaPorProducto.get(j),true);
                DataEspecificacionProducto next = new DataEspecificacionProducto(listaPorProducto.get(j+1),true);
                if ((current.getProductos().size() - current.getStock()) < (next.getProductos().size() - next.getStock())) { 
                    EspecificacionProducto tmp = listaPorProducto.get(j+1); 
                    listaPorProducto.set(j+1, listaPorProducto.get(j)); 
                    listaPorProducto.set(j,tmp); 
                } 
            } 
        } 
        result.put("productos", listaPorProducto);
        List<EspecificacionProducto> listaPorCategoria = arreglo.get("categorias");
        for(int i = 0; i < listaPorCategoria.size() - 1; i++) { 
            for(int j = 0; j < listaPorCategoria.size() - 1; j++) { 
                DataEspecificacionProducto current = new DataEspecificacionProducto(listaPorCategoria.get(j),true);
                DataEspecificacionProducto next = new DataEspecificacionProducto(listaPorCategoria.get(j+1),true);
                if ((current.getProductos().size() - current.getStock()) < (next.getProductos().size() - next.getStock())) { 
                    EspecificacionProducto tmp = listaPorCategoria.get(j+1); 
                    listaPorCategoria.set(j+1, listaPorCategoria.get(j)); 
                    listaPorCategoria.set(j,tmp); 
                } 
            } 
        } 
        result.put("categorias", listaPorCategoria);
        return result;
    }
    
    @Override
    public Boolean puedeComentar(String nickname, String nroRef){
        Iterator it = ManejadorOrdenes.getInstance().obtenerOrdenes().values().iterator();
        while(it.hasNext()){
            OrdenCompra current = (OrdenCompra) it.next();
            if (current.getCliente().getNickname().equals(nickname)) {
                Iterator it2 = current.getClienteCompraProducto().iterator();
                while(it2.hasNext()){
                    ClienteCompraProducto current2 = (ClienteCompraProducto)it2.next();
                    if(current2.getProducto().getEspecificacionProducto().getNroReferencia().equals(nroRef)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    @Override
    public List<DataComentario> listarComentarios(String nroRef){
        List<DataComentario> dataComentario = new ArrayList<>();
        EspecificacionProducto espElegida = ManejadorEspProductos.getInstance().getEspecificacionProducto(nroRef);
        Iterator it = espElegida.getComentarios().iterator();
        while(it.hasNext()){
            Comentario current = (Comentario) it.next();
            dataComentario.add(new DataComentario(current));
        }
        return dataComentario;
    }
}
