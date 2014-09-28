package Controlador.DataTypes;

import Controlador.Clases.Comentario;

public class DataComentario {
    private Integer id;
    private DataComentario padre;
    private DataCliente cliente;
    private DataEspecificacionProducto especificacionProducto;
    private String comentario;
    
    public DataComentario(Comentario c) {
        
    }
    
    public DataComentario(DataCliente cliente, DataEspecificacionProducto especificacionProducto, String comentario, Integer id, DataComentario padre) {
        this.id = id;
        this.padre = padre;
        this.cliente = cliente;
        this.especificacionProducto = especificacionProducto;
        this.comentario = comentario;
    }

    public Integer getid() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public DataComentario getPadre() {
        return padre;
    }
    
    public void setPadre(DataComentario padre) {
        this.padre = padre;
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
    
    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
}
