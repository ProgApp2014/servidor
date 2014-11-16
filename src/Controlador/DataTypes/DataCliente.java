package Controlador.DataTypes;

import Controlador.Clases.Cliente;
import java.util.Calendar;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class DataCliente extends DataUsuario {
    
    private Boolean recibeNotificacion;

    public DataCliente() {
        this.recibeNotificacion = true;
    }

    public DataCliente(Cliente c) {
        super(c.getNickname(), c.getNickname(), c.getNombre(), c.getApellido(), c.getEmail(), c.getFechaNacimiento());
        this.recibeNotificacion = c.getRecibeNotificacion();
        this.setImagen(c.getImagen());
    }

    public DataCliente(String nickname, String password, String nombre, String apellido, String email, Calendar fechaNacimiento, Boolean recibeNotificacion) {
        super(nickname, password, nombre, apellido, email, fechaNacimiento);
        this.recibeNotificacion = recibeNotificacion;
    }
    
    public void setRecibeNotificacion(Boolean recibeNotificacion){
        this.recibeNotificacion = recibeNotificacion;
    }
    
    public Boolean getRecibeNotificacion(){
        return this.recibeNotificacion;
    }

    @Override
    public String toString() {
        return this.getNickname() + "  --  " + this.getNombre() + "  --  " + this.getApellido();
    }

}
