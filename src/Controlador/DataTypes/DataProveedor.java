package Controlador.DataTypes;

import Controlador.Clases.Proveedor;
import java.util.Calendar;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType; 

@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class DataProveedor extends DataUsuario{
    
    private String nombreCompania;
    private String linkSitio;
    
    public DataProveedor(){
    
    }
    public DataProveedor(String nickname, String password, String nombre, String apellido, String email, Calendar fechaNacimiento, String nombreCompania, String linkSitio) {
        super(nickname, password, nombre, apellido, email, fechaNacimiento);
        this.nombreCompania = nombreCompania;
        this.linkSitio = linkSitio;
    }
    
    public DataProveedor(Proveedor p) {
        super(p.getNickname(), p.getPassword(), p.getNombre(), p.getApellido(), p.getEmail(), p.getFechaNacimiento());
        this.nombreCompania = p.getNombreCompania();
        this.linkSitio = p.getLinkSitio();
        this.setImagen(p.getImagen());
    }

    public String getNombreCompania() {
        return nombreCompania;
    }

    public void setNombreCompania(String nombreCompania) {
        this.nombreCompania = nombreCompania;
    }

    public String getLinkSitio() {
        return linkSitio;
    }

    public void setLinkSitio(String linkSitio) {
        this.linkSitio = linkSitio;
    }
    
    @Override
    public String toString() {
        return this.getNickname() + "  --  " + this.getNombre() + "  --  " + this.getApellido();
    }
    
}
