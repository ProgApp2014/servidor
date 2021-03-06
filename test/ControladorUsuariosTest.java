/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Controlador.Clases.Fabrica;
import Controlador.Clases.IControladorUsuarios;
import Controlador.Clases.ManejadorUsuarios;
import Controlador.DataTypes.DataCliente;
import Controlador.DataTypes.DataProveedor;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Calendar;
import static java.util.Objects.isNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author mauro
 */
public class ControladorUsuariosTest{

    @Test
    public void testAltadeUsuario () throws Exception {
        try{
            //agrego un usuario cliente
            Integer idUsuariosControlador = Fabrica.getInstance().getControladorUsuarios(null).getId();
            IControladorUsuarios controlarUsuario = Fabrica.getInstance().getControladorUsuarios(idUsuariosControlador);

            Calendar cal = Calendar.getInstance();
            cal.set(1960, 11, 1);
            DataCliente cliente1 = new DataCliente("dduck", "pasaguord1", "Daffy", "Duck", "dduck@gmail.com", cal,false);
            controlarUsuario.ingresarDatosCliente(cliente1);
            controlarUsuario.elegirCliente("dduck");

            controlarUsuario.guardarUsuario();

            assertEquals (ManejadorUsuarios.getInstance().getCliente("dduck").getNickname(), "dduck");
            assertEquals (ManejadorUsuarios.getInstance().getCliente("dduck").getEmail(), "dduck@gmail.com");        
            assertEquals (ManejadorUsuarios.getInstance().getCliente("dduck").getNombre(), "Daffy");
            assertEquals (ManejadorUsuarios.getInstance().getCliente("dduck").getApellido(), "Duck");
            assertEquals (ManejadorUsuarios.getInstance().getCliente("dduck").getFechaNacimiento().compareTo(cal), 0);
            assertEquals (ManejadorUsuarios.getInstance().getCliente("dduck").validarDatosUsuario(), true);
            assertTrue (controlarUsuario.esCliente("dduck"));
            assertTrue (!isNull (controlarUsuario.listarClientes()));

            //agrego un usuario proveedor
            DataProveedor proveedor1 = new DataProveedor ("pmarmol", "pasagourd2","Pablo", "Marmol", "marmol@gmail.com", cal, "Xcel", "www.xcel.com");        
            controlarUsuario.ingresarDatosProveedor(proveedor1);
            controlarUsuario.guardarUsuario(); 
            controlarUsuario.elegirProveedor("pmarmol");

            assertEquals (ManejadorUsuarios.getInstance().getProveedor("pmarmol").getNickname(), "pmarmol");
            assertEquals (ManejadorUsuarios.getInstance().getProveedor("pmarmol").getEmail(), "marmol@gmail.com");
            assertEquals (ManejadorUsuarios.getInstance().getProveedor("pmarmol").getNombre(), "Pablo");
            assertEquals (ManejadorUsuarios.getInstance().getProveedor("pmarmol").getApellido(), "Marmol");
            assertEquals (ManejadorUsuarios.getInstance().getProveedor("pmarmol").getFechaNacimiento().compareTo(cal), 0);
            assertEquals (ManejadorUsuarios.getInstance().getProveedor("pmarmol").getNombreCompania(), "Xcel");
            assertEquals (ManejadorUsuarios.getInstance().getProveedor("pmarmol").getLinkSitio(), "www.xcel.com");
            assertEquals (ManejadorUsuarios.getInstance().getProveedor("pmarmol").validarDatosUsuario(), true);    
            assertTrue (controlarUsuario.esProveedor("pmarmol"));
            assertTrue (!isNull (controlarUsuario.listarProveedores()));


            controlarUsuario.eliminarUsuario("pmarmol");


            //crear usuario con nickname repetido
            DataCliente cliente2 = new DataCliente("dduck", "pasaguord3", "Darwin", "Duck", "darwinduck@gmail.com", cal,true);
            assertTrue (!controlarUsuario.validarDatosUsuario());


            //crear usuario con email repetido

            DataCliente cliente3 = new DataCliente("darwind", "pasaguord4", "Darwin", "Duck", "dduck@gmail.com", cal,false);
            assertTrue (!controlarUsuario.validarDatosUsuario());
            GeneralTest.deleteAllData();
        }catch(Exception e){
            GeneralTest.deleteAllData();
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            throw new Exception(e.getMessage() + " " + errors.toString());
        }
//        controlarUsuario.eliminarUsuario("dduck");
    }  
    
    @Test
    public void testVerInformacionCliente () throws Exception {
        try{
            //cargar clientes
            Integer idUsuariosControlador = Fabrica.getInstance().getControladorUsuarios(null).getId();
            IControladorUsuarios controlarUsuario = Fabrica.getInstance().getControladorUsuarios(idUsuariosControlador); 

            Calendar cal = Calendar.getInstance();
            cal.set(1960, 11, 1);
            DataCliente cliente1 = new DataCliente("piedra", "pasaguord5" , "Pedro", "Picapiedra", "ppiedra@gmail.com", cal,true);
            controlarUsuario.ingresarDatosCliente(cliente1);
            controlarUsuario.guardarUsuario();         
            DataCliente cliente2 = new DataCliente("pmar", "pasaguord6","Pablo", "Marmol", "pmarmol@gmail.com", cal,false);   
            controlarUsuario.ingresarDatosCliente(cliente2);
            controlarUsuario.guardarUsuario();        
            DataCliente cliente3 = new DataCliente("loco", "pasaguord7","Pajaro", "Loco", "ploco@gmail.com", cal,true);               
            controlarUsuario.ingresarDatosCliente(cliente3);
            controlarUsuario.guardarUsuario();                


            //Listar clientes      
            assertTrue (!isNull (ManejadorUsuarios.getInstance().getCliente("piedra")));
            assertTrue (!isNull (ManejadorUsuarios.getInstance().getCliente("pmar")));
            assertTrue (!isNull (ManejadorUsuarios.getInstance().getCliente("loco")));
            GeneralTest.deleteAllData();
        }catch(Exception e){
            GeneralTest.deleteAllData();
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            throw new Exception(e.getMessage() + " " + errors.toString());
        }
//        controlarUsuario.eliminarUsuario("piedra");
//        controlarUsuario.eliminarUsuario("pmar");
//        controlarUsuario.eliminarUsuario("loco");     
    }
    
    @Test
    public void testVerInformacionProveedor () throws Exception {
        try{
            //cargar clientes
            Integer idUsuariosControlador = Fabrica.getInstance().getControladorUsuarios(null).getId();
            IControladorUsuarios controlarUsuario = Fabrica.getInstance().getControladorUsuarios(idUsuariosControlador);

            Calendar cal1 = Calendar.getInstance();
            cal1.set(1960, 11, 1);
            Calendar cal2 = Calendar.getInstance();
            cal2.set(1930, 10, 1);
            Calendar cal3 = Calendar.getInstance();
            cal3.set(1987, 05, 1);
            DataProveedor proveed1 = new DataProveedor("jotarod", "pasaguord8","Juan", "Rodriguez", "jotarod@gmail.com", cal1,"Juegos", "www.juegos.com");
            DataProveedor proveed2 = new DataProveedor("nmar", "pasaguord9","Natalia", "Mar", "nmar@gmail.com", cal2, "Newpc", "www.newpc.com");        
            DataProveedor proveed3 = new DataProveedor("sdum", "pasaguord10", "Sergio", "Dumas", "sdum@gmail.com", cal3,"Insumos", "www.insumos.com"); 
            controlarUsuario.ingresarDatosProveedor(proveed1);
            controlarUsuario.guardarUsuario();
            controlarUsuario.ingresarDatosProveedor(proveed2);
            controlarUsuario.guardarUsuario();
            controlarUsuario.ingresarDatosProveedor(proveed3);
            controlarUsuario.guardarUsuario();


            assertTrue (!isNull (ManejadorUsuarios.getInstance().getProveedor("jotarod")));
            assertTrue (!isNull (ManejadorUsuarios.getInstance().getProveedor("nmar")));
            assertTrue (!isNull (ManejadorUsuarios.getInstance().getProveedor("sdum")));
            GeneralTest.deleteAllData();
        }catch(Exception e){
            GeneralTest.deleteAllData();
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            throw new Exception(e.getMessage() + " " + errors.toString());
        }
//        controlarUsuario.eliminarUsuario("jotarod");
//        controlarUsuario.eliminarUsuario("nmar");
//        controlarUsuario.eliminarUsuario("sdum");
    }
    
}
