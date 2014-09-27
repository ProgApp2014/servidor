package Controlador.DataTypes;

import Controlador.Clases.Usuario;
import java.util.Calendar;

public class DataUsuario {
    
    private String nickname;
    private String password;
    private String nombre;
    private String apellido;
    private String email;
    private Calendar fechaNacimiento;
    private String imagen;
    
    
    public DataUsuario(Usuario u){
        
    }
    
    public DataUsuario(String nickname, String password, String nombre, String apellido, String email, Calendar fechaNacimiento) {
        this.nickname = nickname;
        this.password = password;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
    }
    
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Calendar getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Calendar fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagenes) {
        this.imagen = imagenes;
    }
    
}
