package Controlador.Clases;

import Controlador.DataTypes.DataCategoria;
import Controlador.DataTypes.DataComentario;
import Controlador.DataTypes.DataEspecificacionProducto;
import Controlador.DataTypes.DataProducto;
import Controlador.DataTypes.DataProveedor;
import Controlador.DataTypes.DataReclamo;
import java.util.List;
import java.util.Map;

public interface IControladorProductos {
    
    public Integer getId();
    public void setId(Integer id);
    public List<DataProveedor> listarProveedores();    
    public void elegirProveedor(String nickname);
    public void ingresarDatosProductos(DataEspecificacionProducto espProducto);
    public Boolean elegirTipoProducto();
    public void agregarMultiplesProductosAutogenerados(Integer cantidad);
    public void ingresarDatosUnidad(DataProducto producto);
    public List<DataCategoria> listarCategorias();
    public void elegirCategoria(String categoria);
    public List<DataEspecificacionProducto> listarProductosCategoria();
    public DataEspecificacionProducto mostrarDatosProducto(String numRef);
    public void listarImagenesDisco();
    public Boolean controlarErrores();
    public void guardarProducto();
    public DataCategoria elegirCategoriaPadre(String categoria);
    public void ingresarDatosCategoria(DataCategoria categoria);
//    public Boolean tienePadre();
    public void asociarCategoriaPadre(DataCategoria padre);
    public void guardarCategoria();
    public void elegirEspProducto(String numRef);
    public DataProducto mostrarInformacionProducto();
    //public ArrayList<DataEspecificacionProducto> listarEspecificacionProductos();
    public void modificarDatosEspecificacionProducto(DataEspecificacionProducto espProducto);
    public void agregarImagen(String rutaImagen);
    public void agregarCategoria(DataCategoria categoria);
    public Boolean validarInfo();
    public void ingresarEspecificacion(String clave, String desc);
    public void agregarCategoriaAEspecificacion(String categoria);
    public List<String> listarImagenesAModificar();
    public void borrarImagen(String rutaImagen);
    public List<DataCategoria> listarCategoriasAModificar();
    public void borrarCategoriaAEspecificacion(String categoria);
    public void guardarEspProductoModificado();
    public Boolean categoryAlreadyExist(String categoria);
    public void eliminarCategoria(String nombre);
    public void eliminarEspecificacionProducto (String nroRef);
    public void agregarComentario(String nickname, String nroRef, Integer padre, String Comentario);
    public List<DataEspecificacionProducto> buscarProductos(String keyword);
    public Map<String,List<DataEspecificacionProducto>> buscarProductosSeparados(String keyword, String Orden);
    public Boolean puedeComentar(String nickname, String nroRef);
    public List<DataComentario> listarComentarios(String nroRef);
    public List<DataProducto> listarProductosEnEspecificacion();
    public Boolean verificarEspecificacionProducto(String numRef);
    public void agregarReclamo(String nickname, String nroRef, String Rec);
    public Boolean puedeReclamar(String nickname, String nroRef);
    public List<DataReclamo> listarReclamos(String nickname);
    public void agregarPuntaje(String nickname, String nroRef, Integer puntaje);
    public Float obtenerPuntjePromedio(String nroRef);
    public Boolean puedePuntuar(String nickname, String nroRef);
    public Float obtenerPromedioPorEstrella(String nroRef, Integer estrella);
    public Integer obtenerPuntosPorEstrella(String nroRef, Integer estrella);
}
    
