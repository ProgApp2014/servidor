package Controlador.Clases;

import Controlador.DataTypes.DataCliente;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("Cliente")
public class Cliente extends Usuario implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private Boolean recibeNotificacion;
   
    public Cliente() {
        this.recibeNotificacion = true;
    }
    
    public Cliente(String nickname, String password, String nombre, String apellido, String email, Calendar fechaNacimiento, Boolean recibeNotificacion) {
        super(nickname, password, nombre, apellido, email, fechaNacimiento);
        this.recibeNotificacion = recibeNotificacion;
    }
    
    public Cliente(DataCliente dc) {
        super(dc.getNickname(), dc.getPassword(), dc.getNombre(), dc.getApellido(), dc.getEmail(), dc.getFechaNacimiento());
        this.recibeNotificacion = dc.getRecibeNotificacion();
        this.setImagen(dc.getImagen());
        
    }
    
    public ClienteCompraProducto obtenerClienteCompraProducto() {
        return null;
    }
    
    public void setRecibeNotificacion(Boolean recibeNotificacion){
        this.recibeNotificacion = recibeNotificacion;
    }
    
    public Boolean getRecibeNotificacion(){
        return this.recibeNotificacion;
    }
    
    @Override
    public String toString() {
        return this.getNickname() + "  --  " + this.getNombre() + "  --  " + this.getApellido() + " -- " + this.getImagen();
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getNickname() != null ? getNickname().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        return (this.getNickname() != null || other.getNickname() == null) && (this.getNickname() == null || this.getNickname().equals(other.getNickname()));
    }
    
}
