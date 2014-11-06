/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador.DataTypes;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author rodro
 */
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class DataMapEspProductos implements Serializable{
    private DataEspecificacionProducto[] list;
    private String categoria;
    
    public DataMapEspProductos(){
    
    }
    public DataEspecificacionProducto[] getList() {
        return list;
    }

    public void setList(DataEspecificacionProducto[] list) {
        this.list = list;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    
}
