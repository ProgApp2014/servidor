/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Clases;

import static Controlador.Clases.Main.controlarProducto;
import Controlador.DataTypes.DataOrdenCompra;
import Controlador.DataTypes.EstadoOrden;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author rodro
 */
public class Utils {

    public static final void generarDatosDePrueba() {
        Integer idUsuariosControlador = null;
        Integer idProductosControlador = null;;
        Integer idOrdenesControlador = null;;
        IControladorUsuarios controlarUsuario;
        IControladorProductos controlarProducto;
        IControladorOrdenes controlarOrden;
        try {
            idUsuariosControlador = Fabrica.getInstance().getControladorUsuarios(null).getId();
            idProductosControlador = Fabrica.getInstance().getControladorProductos(null).getId();
            idOrdenesControlador = Fabrica.getInstance().getControladorOrdenes(null).getId();
        } catch (Exception e) {
            Writer writer = new StringWriter();
            PrintWriter printWriter = new PrintWriter(writer);
            e.printStackTrace(printWriter);
            String s = writer.toString();
            System.out.println(s);
        }

        controlarUsuario = Fabrica.getInstance().getControladorUsuarios(idUsuariosControlador);
        controlarProducto = Fabrica.getInstance().getControladorProductos(idProductosControlador);
        controlarOrden = Fabrica.getInstance().getControladorOrdenes(idOrdenesControlador);

        Calendar cal1 = Calendar.getInstance();
        cal1.set(1960, 11, 1);
        Proveedor p1 = new Proveedor("Tim1", md5("tim123"), "Tim", "Cook", "tim.cook@apple.com", cal1, "Apple", " http://www.apple.com");
        Calendar cal2 = Calendar.getInstance();
        cal2.set(1965, 9, 2);
        Proveedor p2 = new Proveedor("Eddy", md5("edd"), "Eduardo", "Cue", "eddy.cue@samsung.com", cal2, "Samsung", "http://www.samsung.com");
        Calendar cal3 = Calendar.getInstance();
        cal3.set(1965, 9, 2);
        Proveedor p3 = new Proveedor("CraigX", md5("craig@"), "Craig", "Federighi", "craig.feder@sony.com", cal3, "Sony", "http://us.playstation.com");
        Calendar cal4 = Calendar.getInstance();
        cal4.set(1967, 2, 12);
        Proveedor p4 = new Proveedor("Johnny", md5("john"), "Jonathan", "Ive", "johnny.ive@outlook.com", cal4, "Microsoft", "http://www.xbox.com");
        Calendar cal5 = Calendar.getInstance();
        cal5.set(1963, 8, 5);
        Proveedor p5 = new Proveedor("OpenPeter", md5("peter42"), "Peter", "Oppenhemier", "peter.open@htc.com", cal5, "HTC", "http://www.htc.com");

        Calendar cal6 = Calendar.getInstance();
        cal6.set(1963, 7, 5);
        Cliente c1 = new Cliente("Dan", md5("danr"), "Daniel", "Riccio", "dan.riccio@gmail.com", cal6,true);
        Calendar cal7 = Calendar.getInstance();
        cal7.set(1961, 10, 7);
        Cliente c2 = new Cliente("Phil", md5("philip61"), "Philip", "Schiller", "phil.schiller@gmail.com", cal7,false);
        Calendar cal8 = Calendar.getInstance();
        cal8.set(1959, 12, 3);
        Cliente c3 = new Cliente("BruceS", md5("bruces"), "Bruce", "Sewell", "bruce.sewell@gmail.com", cal8,true);
        Calendar cal9 = Calendar.getInstance();
        cal9.set(1964, 11, 27);
        Cliente c4 = new Cliente("JeffW", md5("jeffw"), "Jeff", "Wiliams", "jeff.williams@gmail.com", cal9,false);
        Calendar cal10 = Calendar.getInstance();
        cal10.set(1980, 8, 26);
        Cliente c5 = new Cliente("Ricky", md5("rickyr"), "Ricky", "Ricon", "ricky.r@gmail.com", cal9,false);

        p1.setImagen("cook.jpg");
        p2.setImagen("cue.jpg");
        p3.setImagen("craigx.jpg");
        p4.setImagen("john.jpg");
        c2.setImagen("phil.jpg");
        if (!ManejadorUsuarios.getInstance().obtenerClientes().containsKey(p1.getNickname())) {
            ManejadorUsuarios.getInstance().agregarUsuario(p1);
        }
        if (!ManejadorUsuarios.getInstance().obtenerClientes().containsKey(p2.getNickname())) {
            ManejadorUsuarios.getInstance().agregarUsuario(p2);
        }
        if (!ManejadorUsuarios.getInstance().obtenerClientes().containsKey(p3.getNickname())) {
            ManejadorUsuarios.getInstance().agregarUsuario(p3);
        }
        if (!ManejadorUsuarios.getInstance().obtenerClientes().containsKey(p4.getNickname())) {
            ManejadorUsuarios.getInstance().agregarUsuario(p4);
        }
        if (!ManejadorUsuarios.getInstance().obtenerClientes().containsKey(p5.getNickname())) {
            ManejadorUsuarios.getInstance().agregarUsuario(p5);
        }

        if (!ManejadorUsuarios.getInstance().obtenerClientes().containsKey(c1.getNickname())) {
            ManejadorUsuarios.getInstance().agregarUsuario(c1);
        }
        if (!ManejadorUsuarios.getInstance().obtenerClientes().containsKey(c2.getNickname())) {
            ManejadorUsuarios.getInstance().agregarUsuario(c2);
        }
        if (!ManejadorUsuarios.getInstance().obtenerClientes().containsKey(c3.getNickname())) {
            ManejadorUsuarios.getInstance().agregarUsuario(c3);
        }
        if (!ManejadorUsuarios.getInstance().obtenerClientes().containsKey(c4.getNickname())) {
            ManejadorUsuarios.getInstance().agregarUsuario(c4);
        }
        if (!ManejadorUsuarios.getInstance().obtenerClientes().containsKey(c5.getNickname())) {
            ManejadorUsuarios.getInstance().agregarUsuario(c5);
        }

        /*Categorias*/
        ManejadorCategorias.getInstance().agregarCategoria(new Categoria("Celulares", null));
        ManejadorCategorias.getInstance().agregarCategoria(new Categoria("Sistemas Operativos", ManejadorCategorias.getInstance().getCategoria("Celulares")));
        ManejadorCategorias.getInstance().agregarCategoria(new Categoria("iOS", ManejadorCategorias.getInstance().getCategoria("Sistemas Operativos")));
        ManejadorCategorias.getInstance().agregarCategoria(new Categoria("Android", ManejadorCategorias.getInstance().getCategoria("Sistemas Operativos")));
        ManejadorCategorias.getInstance().agregarCategoria(new Categoria("Windows Phone", ManejadorCategorias.getInstance().getCategoria("Sistemas Operativos")));
        ManejadorCategorias.getInstance().agregarCategoria(new Categoria("Symbian", ManejadorCategorias.getInstance().getCategoria("Sistemas Operativos")));
        ManejadorCategorias.getInstance().agregarCategoria(new Categoria("Blackberry OS", ManejadorCategorias.getInstance().getCategoria("Sistemas Operativos")));
        ManejadorCategorias.getInstance().agregarCategoria(new Categoria("Equipos", ManejadorCategorias.getInstance().getCategoria("Celulares")));
        ManejadorCategorias.getInstance().agregarCategoria(new Categoria("iPhone", ManejadorCategorias.getInstance().getCategoria("Equipos")));
        ManejadorCategorias.getInstance().agregarCategoria(new Categoria("Nexus", ManejadorCategorias.getInstance().getCategoria("Equipos")));
        ManejadorCategorias.getInstance().agregarCategoria(new Categoria("Samsung", ManejadorCategorias.getInstance().getCategoria("Equipos")));
        ManejadorCategorias.getInstance().agregarCategoria(new Categoria("Galaxy S3", ManejadorCategorias.getInstance().getCategoria("Samsung")));
        ManejadorCategorias.getInstance().agregarCategoria(new Categoria("Galaxy S4", ManejadorCategorias.getInstance().getCategoria("Samsung")));
        ManejadorCategorias.getInstance().agregarCategoria(new Categoria("Galaxy Ace", ManejadorCategorias.getInstance().getCategoria("Samsung")));
        ManejadorCategorias.getInstance().agregarCategoria(new Categoria("Blackberry", ManejadorCategorias.getInstance().getCategoria("Equipos")));
        ManejadorCategorias.getInstance().agregarCategoria(new Categoria("Nokia", ManejadorCategorias.getInstance().getCategoria("Equipos")));
        ManejadorCategorias.getInstance().agregarCategoria(new Categoria("Accesorios", ManejadorCategorias.getInstance().getCategoria("Celulares")));
        ManejadorCategorias.getInstance().agregarCategoria(new Categoria("Protectores", ManejadorCategorias.getInstance().getCategoria("Accesorios")));
        ManejadorCategorias.getInstance().agregarCategoria(new Categoria("Baterías", ManejadorCategorias.getInstance().getCategoria("Accesorios")));
        ManejadorCategorias.getInstance().agregarCategoria(new Categoria("Apple", null));
        ManejadorCategorias.getInstance().agregarCategoria(new Categoria("Videojuegos", null));
        ManejadorCategorias.getInstance().agregarCategoria(new Categoria("Playstation", ManejadorCategorias.getInstance().getCategoria("Videojuegos")));
        ManejadorCategorias.getInstance().agregarCategoria(new Categoria("Xbox", ManejadorCategorias.getInstance().getCategoria("Videojuegos")));
        /**
         * productos*
         */

        /////////////////Producto 1
        Map<String, String> esppr1 = new HashMap();
        List<Producto> prodpr1 = new ArrayList();
        List<Categoria> catpr1 = new ArrayList();
        List<Producto> productospr1 = new ArrayList();
        catpr1.add(ManejadorCategorias.getInstance().getCategoria("iPhone"));
        catpr1.add(ManejadorCategorias.getInstance().getCategoria("iOS"));
        catpr1.add(ManejadorCategorias.getInstance().getCategoria("Apple"));
        EspecificacionProducto pr1 = new EspecificacionProducto("IPH5", "iPhone 5", "El último celular de Apple", esppr1, (float) 199.0, p1, catpr1, prodpr1, new ArrayList(), new ArrayList(), new ArrayList());
        List imgList = new ArrayList();
        imgList.add("pr1.png");
        pr1.setImagenes(imgList);
        controlarProducto.elegirEspProducto("IPH5");
        for (Integer i = 0; i < 10; i++) {
            productospr1.add(new Producto(i, pr1));
        }
        pr1.setListaProductos(productospr1);

        /////////////////Producto 2
        Map<String, String> esppr2 = new HashMap();
        List<Producto> prodpr2 = new ArrayList();
        List<Categoria> catpr2 = new ArrayList();
        List<Producto> productospr2 = new ArrayList();
        esppr2.put("Capacidad", "16 GB");
        esppr2.put("Peso", "140 g");
        esppr2.put("Pantalla", "3.5”");
        esppr2.put("Versiones de Wifi", "b/g/n");
        catpr2.add(ManejadorCategorias.getInstance().getCategoria("iPhone"));
        catpr2.add(ManejadorCategorias.getInstance().getCategoria("iOS"));
        catpr2.add(ManejadorCategorias.getInstance().getCategoria("Apple"));
        EspecificacionProducto pr2 = new EspecificacionProducto("IPH4", "iPhone 4S", "El siguiente celular al iPhone 4", esppr2, (float) 99.0, p1, catpr2, prodpr2, new ArrayList(), new ArrayList(), new ArrayList());
        imgList = new ArrayList();
        imgList.add("pr2.jpg");
        pr2.setImagenes(imgList);
        controlarProducto.elegirEspProducto("IPH4");
        productospr2 = new ArrayList();
        for (Integer i = 0; i < 10; i++) {
            productospr2.add(new Producto(i, pr2));
        }
        pr2.setListaProductos(productospr2);

        /////////////////Producto 3
        Map<String, String> esppr3 = new HashMap();
        List<Producto> prodpr3 = new ArrayList();
        List<Categoria> catpr3 = new ArrayList();
        List<Producto> productospr3 = new ArrayList();
        esppr3.put("Capacidad", "8 GB");
        esppr3.put("Peso", "139 g");
        esppr3.put("Pantalla", "4.7”");
        esppr3.put("Versión de Android", "4.3");
        catpr3.add(ManejadorCategorias.getInstance().getCategoria("Android"));
        catpr3.add(ManejadorCategorias.getInstance().getCategoria("Nexus"));
        EspecificacionProducto pr3 = new EspecificacionProducto("NEX4", "Nexus4", "El celular de Google", esppr3, (float) 299.0, p2, catpr3, prodpr3, new ArrayList(), new ArrayList(), new ArrayList());
        imgList = new ArrayList();
        imgList.add("pr3.jpg");
        pr3.setImagenes(imgList);
        controlarProducto.elegirEspProducto("NEX4");
        productospr3 = new ArrayList();
        for (Integer i = 0; i < 10; i++) {
            productospr3.add(new Producto(i, pr3));
        }
        pr3.setListaProductos(productospr3);

        /////////////////Producto 4
        Map<String, String> esppr4 = new HashMap();
        List<Producto> prodpr4 = new ArrayList();
        List<Categoria> catpr4 = new ArrayList();
        List<Producto> productospr4 = new ArrayList();
        esppr4.put("Dimensiones", "136.6 x 70.6 x 8.6 mm");
        esppr4.put("Peso", "133 g");
        esppr4.put("Pantalla", "4.8”");
        esppr4.put("Versión de Android", "4.0.4");
        catpr4.add(ManejadorCategorias.getInstance().getCategoria("Android"));
        catpr4.add(ManejadorCategorias.getInstance().getCategoria("Galaxy S3"));
        EspecificacionProducto pr4 = new EspecificacionProducto("GA3", "Samsung Galaxy S3", "La versión S3 de la línea Samsung Galaxy", esppr4, (float) 415.0, p2, catpr4, prodpr4, new ArrayList(), new ArrayList(), new ArrayList());
        imgList = new ArrayList();
        imgList.add("pr4.jpg");
        pr4.setImagenes(imgList);
        controlarProducto.elegirEspProducto("GA3");
        productospr4 = new ArrayList();
        for (Integer i = 0; i < 10; i++) {
            productospr4.add(new Producto(i, pr4));
        }
        pr4.setListaProductos(productospr4);

        /////////////////Producto 5
        Map<String, String> esppr5 = new HashMap();
        List<Producto> prodpr5 = new ArrayList();
        List<Categoria> catpr5 = new ArrayList();
        List<Producto> productospr5 = new ArrayList();
        esppr5.put("Dimensiones", "136.6 x 69.8 x 7.9 mm");
        esppr5.put("Peso", "130 g");
        esppr5.put("Pantalla", "4.99”");
        esppr5.put("Versión de Android", "4.2.2");
        catpr5.add(ManejadorCategorias.getInstance().getCategoria("Android"));
        catpr5.add(ManejadorCategorias.getInstance().getCategoria("Galaxy S4"));
        EspecificacionProducto pr5 = new EspecificacionProducto("GA4", "Samsung Galaxy S4", "La versión S4 de la línea Samsung Galaxy", esppr5, (float) 839.99, p2, catpr5, prodpr5, new ArrayList(), new ArrayList(), new ArrayList());
        imgList = new ArrayList();
        imgList.add("pr5.jpg");
        pr5.setImagenes(imgList);
        controlarProducto.elegirEspProducto("GA4");
        productospr5 = new ArrayList();
        for (Integer i = 0; i < 10; i++) {
            productospr5.add(new Producto(i, pr5));
        }
        pr5.setListaProductos(productospr5);

        /////////////////Producto 6
        Map<String, String> esppr6 = new HashMap();
        List<Producto> prodpr6 = new ArrayList();
        List<Categoria> catpr6 = new ArrayList();
        List<Producto> productospr6 = new ArrayList();
        esppr6.put("Dimensiones", "112.4 x 59.9 x 11.5 mm");
        esppr6.put("Peso", "113 g");
        esppr6.put("Pantalla", ": 3.5”");
        esppr6.put("Versión de Android", "2.3");
        catpr6.add(ManejadorCategorias.getInstance().getCategoria("Android"));
        catpr6.add(ManejadorCategorias.getInstance().getCategoria("Galaxy Ace"));
        EspecificacionProducto pr6 = new EspecificacionProducto("AS5", "Galaxy Ace S5830", "La versión Ace de la línea Samsung Galaxy", esppr6, (float) 237.0, p2, catpr6, prodpr6, new ArrayList(), new ArrayList(), new ArrayList());
        imgList = new ArrayList();
        imgList.add("pr6.jpg");
        pr6.setImagenes(imgList);
        controlarProducto.elegirEspProducto("AS5");
        productospr6 = new ArrayList();
        for (Integer i = 0; i < 10; i++) {
            productospr6.add(new Producto(i, pr6));
        }
        pr6.setListaProductos(productospr6);

        /////////////////Producto 7
        Map<String, String> esppr7 = new HashMap();
        List<Producto> prodpr7 = new ArrayList();
        List<Categoria> catpr7 = new ArrayList();
        List<Producto> productospr7 = new ArrayList();
        esppr7.put("Dimensiones", "12.5 cm x 6.7 cm x 2.0 cm");
        esppr7.put("Peso", "44 g");
        catpr7.add(ManejadorCategorias.getInstance().getCategoria("Protectores"));
        EspecificacionProducto pr7 = new EspecificacionProducto("PCG", "Protector de cuero para Galaxy", "Asombroso protector de cuero para Samsung Galaxy I900", esppr7, (float) 3.5, p2, catpr7, prodpr7, new ArrayList(), new ArrayList(), new ArrayList());
        imgList = new ArrayList();
        imgList.add("pr7.jpg");
        pr7.setImagenes(imgList);
        controlarProducto.elegirEspProducto("PCG");
        productospr7 = new ArrayList();
        for (Integer i = 0; i < 10; i++) {
            productospr7.add(new Producto(i, pr7));
        }
        pr7.setListaProductos(productospr7);

        /////////////////Producto 8
        Map<String, String> esppr8 = new HashMap();
        List<Producto> prodpr8 = new ArrayList();
        List<Categoria> catpr8 = new ArrayList();
        List<Producto> productospr8 = new ArrayList();
        esppr8.put("Dimensiones", "12.4 cm x 7.0 cm x 1.3 cm");
        esppr8.put("Peso", "26 g");
        catpr8.add(ManejadorCategorias.getInstance().getCategoria("Protectores"));
        EspecificacionProducto pr8 = new EspecificacionProducto("PMH", "Protector de aluminio para HTC", "El mejor protector de aluminio para HTC Desire HD", esppr8, (float) 3.4, p5, catpr8, prodpr8, new ArrayList(), new ArrayList(), new ArrayList());
        imgList = new ArrayList();
        imgList.add("pr8.jpg");
        pr8.setImagenes(imgList);
        controlarProducto.elegirEspProducto("PMH");
        productospr8 = new ArrayList();
        for (Integer i = 0; i < 10; i++) {
            productospr8.add(new Producto(i, pr8));
        }
        pr8.setListaProductos(productospr8);

        /////////////////Producto 9
        Map<String, String> esppr9 = new HashMap();
        List<Producto> prodpr9 = new ArrayList();
        List<Categoria> catpr9 = new ArrayList();
        List<Producto> productospr9 = new ArrayList();
        esppr9.put("Capacidad", "16 GB");
        esppr9.put("Peso", "652 g");
        esppr9.put("Pantalla", "9.7”");
        esppr9.put("Procesador", "A6X");
        catpr9.add(ManejadorCategorias.getInstance().getCategoria("Apple"));
        catpr9.add(ManejadorCategorias.getInstance().getCategoria("iOS"));
        EspecificacionProducto pr9 = new EspecificacionProducto("IRD", "iPad Retina Display", "La última tableta de Apple con pantalla Retina", esppr9, (float) 499.0, p1, catpr9, prodpr9, new ArrayList(), new ArrayList(), new ArrayList());
        imgList = new ArrayList();
        imgList.add("pr9.jpg");
        pr9.setImagenes(imgList);
        controlarProducto.elegirEspProducto("IRD");
        productospr9 = new ArrayList();
        for (Integer i = 0; i < 10; i++) {
            productospr9.add(new Producto(i, pr9));
        }
        pr9.setListaProductos(productospr9);

        /////////////////Producto 10
        Map<String, String> esppr10 = new HashMap();
        List<Producto> prodpr10 = new ArrayList();
        List<Categoria> catpr10 = new ArrayList();
        List<Producto> productospr10 = new ArrayList();
        esppr10.put("Capacidad", "16 GB");
        esppr10.put("Peso", "308 g");
        esppr10.put("Pantalla", "7.9”");
        esppr10.put("Procesador", "A5");
        catpr10.add(ManejadorCategorias.getInstance().getCategoria("Apple"));
        catpr10.add(ManejadorCategorias.getInstance().getCategoria("iOS"));
        EspecificacionProducto pr10 = new EspecificacionProducto("IM", "iPad Mini", "La primera tableta chica de Apple", esppr10, (float) 329.0, p1, catpr10, prodpr10, new ArrayList(), new ArrayList(), new ArrayList());
        imgList = new ArrayList();
        imgList.add("pr10.jpg");
        pr10.setImagenes(imgList);
        controlarProducto.elegirEspProducto("IM");
        productospr10 = new ArrayList();
        for (Integer i = 0; i < 10; i++) {
            productospr10.add(new Producto(i, pr10));
        }
        pr10.setListaProductos(productospr10);

        /////////////////Producto 11
        Map<String, String> esppr11 = new HashMap();
        List<Producto> prodpr11 = new ArrayList();
        List<Categoria> catpr11 = new ArrayList();
        List<Producto> productospr11 = new ArrayList();
        esppr11.put("Dimensiones", "7.5 cm x 4.2 cm x 1.8 cm");
        esppr11.put("Peso", "111 g");
        catpr11.add(ManejadorCategorias.getInstance().getCategoria("Xbox"));
        EspecificacionProducto pr11 = new EspecificacionProducto("RIX", "Receptor inalámbrico para Xbox", "Receptor inalámbrico de color negro para controles de Xbox 360", esppr11, (float) 10.99, p4, catpr11, prodpr11, new ArrayList(), new ArrayList(), new ArrayList());
        imgList = new ArrayList();
        imgList.add("pr11.jpg");
        pr11.setImagenes(imgList);
        controlarProducto.elegirEspProducto("RIX");
        productospr11 = new ArrayList();
        for (Integer i = 0; i < 10; i++) {
            productospr11.add(new Producto(i, pr11));
        }
        pr11.setListaProductos(productospr11);

        /////////////////Producto 12
        Map<String, String> esppr12 = new HashMap();
        List<Producto> prodpr12 = new ArrayList();
        List<Categoria> catpr12 = new ArrayList();
        List<Producto> productospr12 = new ArrayList();
        esppr12.put("Garantía", "3 meses");
        esppr12.put("Dimensiones", "5.91 in x 4.33 in x 1.77 in");
        esppr12.put("Peso", "7.83 oz");
        catpr12.add(ManejadorCategorias.getInstance().getCategoria("Xbox"));
        EspecificacionProducto pr12 = new EspecificacionProducto("CIX", "Control inalámbrico para Xbox", "Control inalámbrico de 2.4 GHz para Xbox 360 ", esppr12, (float) 27.27, p4, catpr12, prodpr12, new ArrayList(), new ArrayList(), new ArrayList());
        imgList = new ArrayList();
        imgList.add("pr12.jpg");
        pr12.setImagenes(imgList);
        controlarProducto.elegirEspProducto("CIX");
        productospr12 = new ArrayList();
        for (Integer i = 0; i < 10; i++) {
            productospr12.add(new Producto(i, pr12));
        }
        pr12.setListaProductos(productospr12);

        /////////////////Producto 13
        Map<String, String> esppr13 = new HashMap();
        List<Producto> prodpr13 = new ArrayList();
        List<Categoria> catpr13 = new ArrayList();
        List<Producto> productospr13 = new ArrayList();
        esppr13.put("Dimensiones", "0 in x 0 in x 0 in");
        esppr13.put("Peso", "7.83 oz");
        catpr13.add(ManejadorCategorias.getInstance().getCategoria("Playstation"));
        EspecificacionProducto pr13 = new EspecificacionProducto("CHP", "Cable HDMI para PS3", "Es un cable HDMI para PS3", esppr13, (float) 7.99, p3, catpr13, prodpr13, new ArrayList(), new ArrayList(), new ArrayList());
        imgList = new ArrayList();
        imgList.add("pr13.jpg");
        pr13.setImagenes(imgList);
        controlarProducto.elegirEspProducto("CHP");
        productospr13 = new ArrayList();
        for (Integer i = 0; i < 10; i++) {
            productospr13.add(new Producto(i, pr13));
        }
        pr13.setListaProductos(productospr13);

        /////////////////Producto 14
        Map<String, String> esppr14 = new HashMap();
        List<Producto> prodpr14 = new ArrayList();
        List<Categoria> catpr14 = new ArrayList();
        List<Producto> productospr14 = new ArrayList();
        esppr14.put("Dimensiones", "16.0 cm x 9.5 cm x 5.0 cm");
        esppr14.put("Peso", "184 g");
        catpr14.add(ManejadorCategorias.getInstance().getCategoria("Playstation"));
        EspecificacionProducto pr14 = new EspecificacionProducto("CP3", "Control para PS3", "Control inalámbrico Dualshock 3 de color azul para Playstation 3", esppr14, (float) 30.8, p3, catpr14, prodpr14, new ArrayList(), new ArrayList(), new ArrayList());
        imgList = new ArrayList();
        imgList.add("pr14.jpg");
        pr14.setImagenes(imgList);
        controlarProducto.elegirEspProducto("CP3");
        productospr14 = new ArrayList();
        for (Integer i = 0; i < 10; i++) {
            productospr14.add(new Producto(i, pr14));
        }
        pr14.setListaProductos(productospr14);
        
        /////////////////Producto 15
        Map<String, String> esppr15 = new HashMap();
        List<Producto> prodpr15 = new ArrayList();
        List<Categoria> catpr15 = new ArrayList();
        List<Producto> productospr15 = new ArrayList();
        esppr15.put("Capacidad", "16 GB");
        esppr15.put("Peso", "112 g");
        esppr15.put("Pantalla", "4 p");
        esppr15.put("Versiones de Wifi", "a/b/g/n");
        catpr15.add(ManejadorCategorias.getInstance().getCategoria("iPhone"));
        catpr15.add(ManejadorCategorias.getInstance().getCategoria("iOS"));
        catpr15.add(ManejadorCategorias.getInstance().getCategoria("Apple"));
        EspecificacionProducto pr15 = new EspecificacionProducto("IPH5S", "iPhone 5S", "La evolucion del iPhone 5, con Touch ID y A7", esppr15, (float) 199.0, p1, catpr15, prodpr15, new ArrayList(), new ArrayList(), new ArrayList());
        controlarProducto.elegirEspProducto("IPH5S");
        productospr15 = new ArrayList();
        for (Integer i = 0; i < 10; i++) {
            productospr15.add(new Producto(i, pr15));
        }
        pr15.setListaProductos(productospr15);
        
        /////////////////Producto 16
        Map<String, String> esppr16 = new HashMap();
        List<Producto> prodpr16 = new ArrayList();
        List<Categoria> catpr16 = new ArrayList();
        List<Producto> productospr16 = new ArrayList();
        esppr16.put("Capacidad", "16 GB");
        esppr16.put("Peso", "132 g");
        esppr16.put("Pantalla", "4 p");
        esppr16.put("Versiones de Wifi", "a/b/g/n");
        catpr16.add(ManejadorCategorias.getInstance().getCategoria("iPhone"));
        catpr16.add(ManejadorCategorias.getInstance().getCategoria("iOS"));
        catpr16.add(ManejadorCategorias.getInstance().getCategoria("Apple"));
        EspecificacionProducto pr16 = new EspecificacionProducto("IPH5C", "iPhone 5C", "Dale color a tu vida con esta gama de iPhone", esppr16, (float) 99.0, p1, catpr16, prodpr16, new ArrayList(), new ArrayList(), new ArrayList());
        controlarProducto.elegirEspProducto("IPH5C");
        productospr16 = new ArrayList();
        for (Integer i = 0; i < 10; i++) {
            productospr16.add(new Producto(i, pr16));
        }
        pr16.setListaProductos(productospr16);
        
        /////////////////Producto 17
        Map<String, String> esppr17 = new HashMap();
        List<Producto> prodpr17 = new ArrayList();
        List<Categoria> catpr17 = new ArrayList();
        List<Producto> productospr17 = new ArrayList();
        esppr17.put("Capacidad", "16 GB");
        esppr17.put("Peso", "469 g");
        esppr17.put("Pantalla", "9.7 p");
        esppr17.put("Versiones de Wifi", "a/b/g/n con MIMO");
        catpr17.add(ManejadorCategorias.getInstance().getCategoria("iOS"));
        catpr17.add(ManejadorCategorias.getInstance().getCategoria("Apple"));
        EspecificacionProducto pr17 = new EspecificacionProducto("IPAA", "iPad Air", "Mas ligero, mas delgado y con mejor procesador que el anterior iPad", esppr17, (float) 499.0, p1, catpr17, prodpr17, new ArrayList(), new ArrayList(), new ArrayList());
        controlarProducto.elegirEspProducto("IPAA");
        for (Integer i = 0; i < 10; i++) {
            productospr17.add(new Producto(i, pr17));
        }
        pr17.setListaProductos(productospr17);
        
        /////////////////Producto 18
        Map<String, String> esppr18 = new HashMap();
        List<Producto> prodpr18 = new ArrayList();
        List<Categoria> catpr18 = new ArrayList();
        List<Producto> productospr18 = new ArrayList();
        esppr18.put("Capacidad", "16 GB");
        esppr18.put("Peso", "332 g");
        esppr18.put("Pantalla", "7.9 p");
        esppr18.put("Versiones de Wifi", "a/b/g/n con MIMO");
        catpr18.add(ManejadorCategorias.getInstance().getCategoria("iOS"));
        catpr18.add(ManejadorCategorias.getInstance().getCategoria("Apple"));
        EspecificacionProducto pr18 = new EspecificacionProducto("IPAM", "iPad Mini Retina Display", "Igual que el modelo anterior, solo que con pantalla Retina", esppr18, (float) 399.0, p1, catpr18, prodpr18, new ArrayList(), new ArrayList(), new ArrayList());
        controlarProducto.elegirEspProducto("IPAM");
        for (Integer i = 0; i < 10; i++) {
            productospr18.add(new Producto(i, pr18));
        }
        pr18.setListaProductos(productospr18);

        if (!ManejadorEspProductos.getInstance().obtenerEspecificacionProductos().containsKey(pr1.getNroReferencia())) {
            ManejadorEspProductos.getInstance().agregarEspecificacionProducto(pr1);
        }
        if (!ManejadorEspProductos.getInstance().obtenerEspecificacionProductos().containsKey(pr2.getNroReferencia())) {
            ManejadorEspProductos.getInstance().agregarEspecificacionProducto(pr2);
        }
        if (!ManejadorEspProductos.getInstance().obtenerEspecificacionProductos().containsKey(pr3.getNroReferencia())) {
            ManejadorEspProductos.getInstance().agregarEspecificacionProducto(pr3);
        }
        if (!ManejadorEspProductos.getInstance().obtenerEspecificacionProductos().containsKey(pr4.getNroReferencia())) {
            ManejadorEspProductos.getInstance().agregarEspecificacionProducto(pr4);
        }
        if (!ManejadorEspProductos.getInstance().obtenerEspecificacionProductos().containsKey(pr5.getNroReferencia())) {
            ManejadorEspProductos.getInstance().agregarEspecificacionProducto(pr5);
        }
        if (!ManejadorEspProductos.getInstance().obtenerEspecificacionProductos().containsKey(pr6.getNroReferencia())) {
            ManejadorEspProductos.getInstance().agregarEspecificacionProducto(pr6);
        }
        if (!ManejadorEspProductos.getInstance().obtenerEspecificacionProductos().containsKey(pr7.getNroReferencia())) {
            ManejadorEspProductos.getInstance().agregarEspecificacionProducto(pr7);
        }
        if (!ManejadorEspProductos.getInstance().obtenerEspecificacionProductos().containsKey(pr8.getNroReferencia())) {
            ManejadorEspProductos.getInstance().agregarEspecificacionProducto(pr8);
        }
        if (!ManejadorEspProductos.getInstance().obtenerEspecificacionProductos().containsKey(pr9.getNroReferencia())) {
            ManejadorEspProductos.getInstance().agregarEspecificacionProducto(pr9);
        }
        if (!ManejadorEspProductos.getInstance().obtenerEspecificacionProductos().containsKey(pr10.getNroReferencia())) {
            ManejadorEspProductos.getInstance().agregarEspecificacionProducto(pr10);
        }
        if (!ManejadorEspProductos.getInstance().obtenerEspecificacionProductos().containsKey(pr11.getNroReferencia())) {
            ManejadorEspProductos.getInstance().agregarEspecificacionProducto(pr11);
        }
        if (!ManejadorEspProductos.getInstance().obtenerEspecificacionProductos().containsKey(pr12.getNroReferencia())) {
            ManejadorEspProductos.getInstance().agregarEspecificacionProducto(pr12);
        }
        if (!ManejadorEspProductos.getInstance().obtenerEspecificacionProductos().containsKey(pr13.getNroReferencia())) {
            ManejadorEspProductos.getInstance().agregarEspecificacionProducto(pr13);
        }
        if (!ManejadorEspProductos.getInstance().obtenerEspecificacionProductos().containsKey(pr14.getNroReferencia())) {
            ManejadorEspProductos.getInstance().agregarEspecificacionProducto(pr14);
        }
        if (!ManejadorEspProductos.getInstance().obtenerEspecificacionProductos().containsKey(pr15.getNroReferencia())) {
            ManejadorEspProductos.getInstance().agregarEspecificacionProducto(pr15);
        }
        if (!ManejadorEspProductos.getInstance().obtenerEspecificacionProductos().containsKey(pr16.getNroReferencia())) {
            ManejadorEspProductos.getInstance().agregarEspecificacionProducto(pr16);
        }
        if (!ManejadorEspProductos.getInstance().obtenerEspecificacionProductos().containsKey(pr17.getNroReferencia())) {
            ManejadorEspProductos.getInstance().agregarEspecificacionProducto(pr17);
        }
        if (!ManejadorEspProductos.getInstance().obtenerEspecificacionProductos().containsKey(pr18.getNroReferencia())) {
            ManejadorEspProductos.getInstance().agregarEspecificacionProducto(pr18);
        }

        if (ManejadorOrdenes.getInstance().obtenerOrdenes().isEmpty()) {
            controlarOrden.elegirCliente("Dan");
            controlarOrden.elegirEspecificacionProducto("IPH5");
            controlarOrden.elegirProducto(ManejadorEspProductos.getInstance().getEspecificacionProducto("IPH5").getListaProductos().get(0).getId());
            controlarOrden.generarItemOrden();
            controlarOrden.elegirEspecificacionProducto("IRD");
            controlarOrden.elegirProducto(ManejadorEspProductos.getInstance().getEspecificacionProducto("IRD").getListaProductos().get(0).getId());
            controlarOrden.elegirProducto(ManejadorEspProductos.getInstance().getEspecificacionProducto("IRD").getListaProductos().get(1).getId());
            controlarOrden.elegirEspecificacionProducto("IM");
            controlarOrden.elegirProducto(ManejadorEspProductos.getInstance().getEspecificacionProducto("IM").getListaProductos().get(0).getId());
            controlarOrden.elegirProducto(ManejadorEspProductos.getInstance().getEspecificacionProducto("IM").getListaProductos().get(1).getId());
            controlarOrden.generarItemOrden();
            DataOrdenCompra dataOrden = new DataOrdenCompra(1);
            dataOrden.setFecha(getDateFromString("12/8/2013"));
            controlarOrden.guardarOrden(dataOrden);
            OrdenCompra orden1 = ManejadorOrdenes.getInstance().getOrden(controlarOrden.getUltimaOrdenGuardada());
            orden1.getEstados().get(0).setFecha(getDateFromString("12/8/2013"));
            EstadosOrdenes estadoPreparada1 = new EstadosOrdenes(0,orden1,EstadoOrden.ORDEN_PREPARADA.getValue());
            estadoPreparada1.setFecha(getDateFromString("13/8/2013"));
            orden1.getEstados().add(estadoPreparada1);
            EstadosOrdenes estadoConfirmada1 = new EstadosOrdenes(0,orden1,EstadoOrden.ORDEN_CONFIRMADA.getValue());
            estadoConfirmada1.setFecha(getDateFromString("20/8/2013"));
            orden1.getEstados().add(estadoConfirmada1);
            ManejadorOrdenes.getInstance().modificarOrden(orden1);
                                
            controlarOrden.elegirCliente("Dan");
            controlarOrden.elegirEspecificacionProducto("NEX4");
            controlarOrden.elegirProducto(ManejadorEspProductos.getInstance().getEspecificacionProducto("NEX4").getListaProductos().get(0).getId());
            controlarOrden.elegirProducto(ManejadorEspProductos.getInstance().getEspecificacionProducto("NEX4").getListaProductos().get(1).getId());
            controlarOrden.elegirProducto(ManejadorEspProductos.getInstance().getEspecificacionProducto("NEX4").getListaProductos().get(2).getId());
            controlarOrden.generarItemOrden();
            DataOrdenCompra dataOrden2 = new DataOrdenCompra(2);
            dataOrden2.setFecha(getDateFromString("19/8/2013"));
            controlarOrden.guardarOrden(dataOrden2);
            OrdenCompra orden2 = ManejadorOrdenes.getInstance().getOrden(controlarOrden.getUltimaOrdenGuardada());
            orden2.getEstados().get(0).setFecha(getDateFromString("19/8/2013"));
            ManejadorOrdenes.getInstance().modificarOrden(orden2);

            controlarOrden.elegirCliente("Phil");
            controlarOrden.elegirEspecificacionProducto("CHP");
            controlarOrden.elegirProducto(ManejadorEspProductos.getInstance().getEspecificacionProducto("CHP").getListaProductos().get(0).getId());
            controlarOrden.elegirProducto(ManejadorEspProductos.getInstance().getEspecificacionProducto("CHP").getListaProductos().get(1).getId());
            controlarOrden.generarItemOrden();
            controlarOrden.elegirEspecificacionProducto("CP3");
            controlarOrden.elegirProducto(ManejadorEspProductos.getInstance().getEspecificacionProducto("CP3").getListaProductos().get(0).getId());
            controlarOrden.elegirProducto(ManejadorEspProductos.getInstance().getEspecificacionProducto("CP3").getListaProductos().get(1).getId());
            controlarOrden.elegirProducto(ManejadorEspProductos.getInstance().getEspecificacionProducto("CP3").getListaProductos().get(2).getId());
            controlarOrden.generarItemOrden();
            DataOrdenCompra dataOrden3 = new DataOrdenCompra(3);
            dataOrden3.setFecha(getDateFromString("12/8/2013"));
            controlarOrden.guardarOrden(dataOrden3);
            OrdenCompra orden3 = ManejadorOrdenes.getInstance().getOrden(controlarOrden.getUltimaOrdenGuardada());
            orden3.getEstados().get(0).setFecha(getDateFromString("12/8/2013"));
            EstadosOrdenes estadoPreparada3 = new EstadosOrdenes(0,orden3,EstadoOrden.ORDEN_PREPARADA.getValue());
            estadoPreparada3.setFecha(getDateFromString("30/8/2013"));
            orden3.getEstados().add(estadoPreparada3);
            ManejadorOrdenes.getInstance().modificarOrden(orden3);

            controlarOrden.elegirCliente("BruceS");
            controlarOrden.elegirEspecificacionProducto("CIX");
            controlarOrden.elegirProducto(ManejadorEspProductos.getInstance().getEspecificacionProducto("CIX").getListaProductos().get(0).getId());
            controlarOrden.elegirProducto(ManejadorEspProductos.getInstance().getEspecificacionProducto("CIX").getListaProductos().get(1).getId());
            controlarOrden.elegirProducto(ManejadorEspProductos.getInstance().getEspecificacionProducto("CIX").getListaProductos().get(2).getId());
            controlarOrden.elegirProducto(ManejadorEspProductos.getInstance().getEspecificacionProducto("CIX").getListaProductos().get(3).getId());
            controlarOrden.generarItemOrden();
            DataOrdenCompra dataOrden4 = new DataOrdenCompra(4);
            dataOrden4.setFecha(getDateFromString("19/9/2013"));
            controlarOrden.guardarOrden(dataOrden4);
            OrdenCompra orden4 = ManejadorOrdenes.getInstance().getOrden(controlarOrden.getUltimaOrdenGuardada());
            orden4.getEstados().get(0).setFecha(getDateFromString("19/9/2013"));
            EstadosOrdenes estadoPreparada4 = new EstadosOrdenes(0,orden4,EstadoOrden.ORDEN_PREPARADA.getValue());
            estadoPreparada4.setFecha(getDateFromString("20/9/2013"));
            orden4.getEstados().add(estadoPreparada4);
            EstadosOrdenes estadoConfirmada4 = new EstadosOrdenes(0,orden4,EstadoOrden.ORDEN_CONFIRMADA.getValue());
            estadoConfirmada4.setFecha(getDateFromString("25/9/2013"));
            orden4.getEstados().add(estadoConfirmada4);
            ManejadorOrdenes.getInstance().modificarOrden(orden4);

            controlarOrden.elegirCliente("JeffW");
            controlarOrden.elegirEspecificacionProducto("PCG");
            controlarOrden.elegirProducto(ManejadorEspProductos.getInstance().getEspecificacionProducto("PCG").getListaProductos().get(0).getId());
            controlarOrden.generarItemOrden();
            DataOrdenCompra dataOrden5 = new DataOrdenCompra(5);
            dataOrden5.setFecha(getDateFromString("17/9/2013"));
            controlarOrden.guardarOrden(dataOrden5);
            OrdenCompra orden5 = ManejadorOrdenes.getInstance().getOrden(controlarOrden.getUltimaOrdenGuardada());
            orden5.getEstados().get(0).setFecha(getDateFromString("17/9/2013"));
            EstadosOrdenes estadoPreparada5 = new EstadosOrdenes(0,orden5,EstadoOrden.ORDEN_PREPARADA.getValue());
            estadoPreparada5.setFecha(getDateFromString("20/9/2013"));
            orden5.getEstados().add(estadoPreparada5);
            EstadosOrdenes estadoConfirmada5 = new EstadosOrdenes(0,orden5,EstadoOrden.ORDEN_CONFIRMADA.getValue());
            estadoConfirmada5.setFecha(getDateFromString("25/9/2013"));
            orden5.getEstados().add(estadoConfirmada5);
            ManejadorOrdenes.getInstance().modificarOrden(orden5);
            
            controlarOrden.elegirCliente("Phil");
            controlarOrden.elegirEspecificacionProducto("IPH5");
            controlarOrden.elegirProducto(ManejadorEspProductos.getInstance().getEspecificacionProducto("IPH5").getListaProductos().get(0).getId());
            controlarOrden.generarItemOrden();
            DataOrdenCompra dataOrden6 = new DataOrdenCompra(3);
            dataOrden6.setFecha(getDateFromString("9/8/2013"));
            controlarOrden.guardarOrden(dataOrden6);
            OrdenCompra orden6 = ManejadorOrdenes.getInstance().getOrden(controlarOrden.getUltimaOrdenGuardada());
            orden6.getEstados().get(0).setFecha(getDateFromString("9/8/2013"));
            EstadosOrdenes estadoPreparada6 = new EstadosOrdenes(0,orden6,EstadoOrden.ORDEN_PREPARADA.getValue());
            estadoPreparada6.setFecha(getDateFromString("12/9/2013"));
            orden6.getEstados().add(estadoPreparada6);
            EstadosOrdenes estadoConfirmada6 = new EstadosOrdenes(0,orden6,EstadoOrden.ORDEN_CONFIRMADA.getValue());
            estadoConfirmada6.setFecha(getDateFromString("19/9/2013"));
            orden6.getEstados().add(estadoConfirmada6);
            ManejadorOrdenes.getInstance().modificarOrden(orden6);
            
            controlarOrden.elegirCliente("Ricky");
            controlarOrden.elegirEspecificacionProducto("IPH5");
            controlarOrden.elegirProducto(ManejadorEspProductos.getInstance().getEspecificacionProducto("IPH5").getListaProductos().get(0).getId());
            controlarOrden.generarItemOrden();
            controlarOrden.elegirEspecificacionProducto("IPH5C");
            controlarOrden.elegirProducto(ManejadorEspProductos.getInstance().getEspecificacionProducto("IPH5C").getListaProductos().get(0).getId());
            controlarOrden.generarItemOrden();
            DataOrdenCompra dataOrden7 = new DataOrdenCompra(3);
            dataOrden7.setFecha(getDateFromString("12/8/2013"));
            controlarOrden.guardarOrden(dataOrden7);
            OrdenCompra orden7 = ManejadorOrdenes.getInstance().getOrden(controlarOrden.getUltimaOrdenGuardada());
            orden7.getEstados().get(0).setFecha(getDateFromString("12/8/2013"));
            EstadosOrdenes estadoPreparada7 = new EstadosOrdenes(0,orden7,EstadoOrden.ORDEN_PREPARADA.getValue());
            estadoPreparada7.setFecha(getDateFromString("13/9/2013"));
            orden7.getEstados().add(estadoPreparada7);
            EstadosOrdenes estadoConfirmada7 = new EstadosOrdenes(0,orden7,EstadoOrden.ORDEN_CONFIRMADA.getValue());
            estadoConfirmada7.setFecha(getDateFromString("20/8/2013"));
            orden7.getEstados().add(estadoConfirmada7);
            ManejadorOrdenes.getInstance().modificarOrden(orden7);
            
            controlarOrden.elegirCliente("Ricky");
            controlarOrden.elegirEspecificacionProducto("IPH4");
            controlarOrden.elegirProducto(ManejadorEspProductos.getInstance().getEspecificacionProducto("IPH4").getListaProductos().get(0).getId());
            controlarOrden.generarItemOrden();
            controlarOrden.elegirEspecificacionProducto("IPAA");
            controlarOrden.elegirProducto(ManejadorEspProductos.getInstance().getEspecificacionProducto("IPAA").getListaProductos().get(0).getId());
            controlarOrden.generarItemOrden();
            controlarOrden.elegirEspecificacionProducto("IM");
            controlarOrden.elegirProducto(ManejadorEspProductos.getInstance().getEspecificacionProducto("IM").getListaProductos().get(0).getId());
            controlarOrden.generarItemOrden();
            controlarOrden.elegirEspecificacionProducto("IRD");
            controlarOrden.elegirProducto(ManejadorEspProductos.getInstance().getEspecificacionProducto("IRD").getListaProductos().get(0).getId());
            controlarOrden.generarItemOrden();
            DataOrdenCompra dataOrden8 = new DataOrdenCompra(3);
            dataOrden8.setFecha(getDateFromString("13/9/2013"));
            controlarOrden.guardarOrden(dataOrden8);
            OrdenCompra orden8 = ManejadorOrdenes.getInstance().getOrden(controlarOrden.getUltimaOrdenGuardada());
            orden8.getEstados().get(0).setFecha(getDateFromString("13/9/2013"));
            EstadosOrdenes estadoPreparada8 = new EstadosOrdenes(0,orden8,EstadoOrden.ORDEN_PREPARADA.getValue());
            estadoPreparada8.setFecha(getDateFromString("14/9/2013"));
            orden8.getEstados().add(estadoPreparada8);
            EstadosOrdenes estadoConfirmada8 = new EstadosOrdenes(0,orden8,EstadoOrden.ORDEN_CONFIRMADA.getValue());
            estadoConfirmada8.setFecha(getDateFromString("21/9/2013"));
            orden8.getEstados().add(estadoConfirmada8);
            ManejadorOrdenes.getInstance().modificarOrden(orden8);
            
            
            controlarOrden.elegirCliente("Ricky");
            controlarOrden.elegirEspecificacionProducto("IPAM");
            controlarOrden.elegirProducto(ManejadorEspProductos.getInstance().getEspecificacionProducto("IPAM").getListaProductos().get(0).getId());
            controlarOrden.generarItemOrden();
            controlarOrden.elegirEspecificacionProducto("IPH5C");
            controlarOrden.elegirProducto(ManejadorEspProductos.getInstance().getEspecificacionProducto("IPH5C").getListaProductos().get(0).getId());
            controlarOrden.generarItemOrden();
            controlarOrden.elegirEspecificacionProducto("IPH4");
            controlarOrden.elegirProducto(ManejadorEspProductos.getInstance().getEspecificacionProducto("IPH4").getListaProductos().get(0).getId());
            controlarOrden.generarItemOrden();
            controlarOrden.elegirEspecificacionProducto("IPH5S");
            controlarOrden.elegirProducto(ManejadorEspProductos.getInstance().getEspecificacionProducto("IPH5S").getListaProductos().get(0).getId());
            controlarOrden.generarItemOrden();
            DataOrdenCompra dataOrden9 = new DataOrdenCompra(3);
            dataOrden9.setFecha(getDateFromString("14/9/2013"));
            controlarOrden.guardarOrden(dataOrden9);
            OrdenCompra orden9 = ManejadorOrdenes.getInstance().getOrden(controlarOrden.getUltimaOrdenGuardada());
            orden9.getEstados().get(0).setFecha(getDateFromString("14/9/2013"));
            EstadosOrdenes estadoPreparada9 = new EstadosOrdenes(0,orden9,EstadoOrden.ORDEN_PREPARADA.getValue());
            estadoPreparada9.setFecha(getDateFromString("15/9/2013"));
            orden9.getEstados().add(estadoPreparada9);
            EstadosOrdenes estadoConfirmada9 = new EstadosOrdenes(0,orden9,EstadoOrden.ORDEN_CONFIRMADA.getValue());
            estadoConfirmada9.setFecha(getDateFromString("20/9/2013"));
            orden9.getEstados().add(estadoConfirmada9);
            ManejadorOrdenes.getInstance().modificarOrden(orden9);
            
            controlarOrden.elegirCliente("Ricky");
            controlarOrden.elegirEspecificacionProducto("IPAA");
            controlarOrden.elegirProducto(ManejadorEspProductos.getInstance().getEspecificacionProducto("IPAA").getListaProductos().get(0).getId());
            controlarOrden.generarItemOrden();
            controlarOrden.elegirEspecificacionProducto("IM");
            controlarOrden.elegirProducto(ManejadorEspProductos.getInstance().getEspecificacionProducto("IM").getListaProductos().get(0).getId());
            controlarOrden.generarItemOrden();
            controlarOrden.elegirEspecificacionProducto("IRD");
            controlarOrden.elegirProducto(ManejadorEspProductos.getInstance().getEspecificacionProducto("IRD").getListaProductos().get(0).getId());
            controlarOrden.generarItemOrden();
            DataOrdenCompra dataOrden10 = new DataOrdenCompra(3);
            dataOrden10.setFecha(getDateFromString("10/10/2013"));
            controlarOrden.guardarOrden(dataOrden10);
            OrdenCompra orden10 = ManejadorOrdenes.getInstance().getOrden(controlarOrden.getUltimaOrdenGuardada());
            orden10.getEstados().get(0).setFecha(getDateFromString("10/10/2013"));
            EstadosOrdenes estadoPreparada10 = new EstadosOrdenes(0,orden10,EstadoOrden.ORDEN_PREPARADA.getValue());
            estadoPreparada10.setFecha(getDateFromString("11/10/2013"));
            orden10.getEstados().add(estadoPreparada10);
            EstadosOrdenes estadoConfirmada10 = new EstadosOrdenes(0,orden10,EstadoOrden.ORDEN_CONFIRMADA.getValue());
            estadoConfirmada10.setFecha(getDateFromString("20/10/2013"));
            orden10.getEstados().add(estadoConfirmada10);
            ManejadorOrdenes.getInstance().modificarOrden(orden10);
            
            controlarOrden.elegirCliente("Ricky");
            controlarOrden.elegirEspecificacionProducto("IPH5");
            controlarOrden.elegirProducto(ManejadorEspProductos.getInstance().getEspecificacionProducto("IPH5").getListaProductos().get(0).getId());
            controlarOrden.generarItemOrden();
            controlarOrden.elegirEspecificacionProducto("IRD");
            controlarOrden.elegirProducto(ManejadorEspProductos.getInstance().getEspecificacionProducto("IRD").getListaProductos().get(0).getId());
            controlarOrden.generarItemOrden();
            controlarOrden.elegirEspecificacionProducto("IM");
            controlarOrden.elegirProducto(ManejadorEspProductos.getInstance().getEspecificacionProducto("IM").getListaProductos().get(0).getId());
            controlarOrden.generarItemOrden();
            controlarOrden.elegirEspecificacionProducto("IPH5C");
            controlarOrden.elegirProducto(ManejadorEspProductos.getInstance().getEspecificacionProducto("IPH5C").getListaProductos().get(0).getId());
            controlarOrden.generarItemOrden();
            DataOrdenCompra dataOrden11 = new DataOrdenCompra(3);
            dataOrden11.setFecha(getDateFromString("25/10/2013"));
            controlarOrden.guardarOrden(dataOrden11);
            OrdenCompra orden11 = ManejadorOrdenes.getInstance().getOrden(controlarOrden.getUltimaOrdenGuardada());
            orden11.getEstados().get(0).setFecha(getDateFromString("25/10/2013"));
            EstadosOrdenes estadoCancelada11 = new EstadosOrdenes(0,orden11,EstadoOrden.ORDEN_CANCELADA.getValue());
            estadoCancelada11.setFecha(getDateFromString("26/10/2013"));
            orden11.getEstados().add(estadoCancelada11);
            ManejadorOrdenes.getInstance().modificarOrden(orden11);
            
            controlarOrden.elegirCliente("Ricky");
            controlarOrden.elegirEspecificacionProducto("IPH5");
            controlarOrden.elegirProducto(ManejadorEspProductos.getInstance().getEspecificacionProducto("IPH5").getListaProductos().get(0).getId());
            controlarOrden.generarItemOrden();
            controlarOrden.elegirEspecificacionProducto("IRD");
            controlarOrden.elegirProducto(ManejadorEspProductos.getInstance().getEspecificacionProducto("IRD").getListaProductos().get(0).getId());
            controlarOrden.generarItemOrden();
            controlarOrden.elegirEspecificacionProducto("IM");
            controlarOrden.elegirProducto(ManejadorEspProductos.getInstance().getEspecificacionProducto("IM").getListaProductos().get(0).getId());
            controlarOrden.generarItemOrden();
            controlarOrden.elegirEspecificacionProducto("IPH5C");
            controlarOrden.elegirProducto(ManejadorEspProductos.getInstance().getEspecificacionProducto("IPH5C").getListaProductos().get(0).getId());
            controlarOrden.generarItemOrden();
            DataOrdenCompra dataOrden12 = new DataOrdenCompra(3);
            dataOrden12.setFecha(getDateFromString("24/10/2013"));
            controlarOrden.guardarOrden(dataOrden12);
            OrdenCompra orden12 = ManejadorOrdenes.getInstance().getOrden(controlarOrden.getUltimaOrdenGuardada());
            orden12.getEstados().get(0).setFecha(getDateFromString("24/10/2013"));
            EstadosOrdenes estadoPreparada12 = new EstadosOrdenes(0,orden12,EstadoOrden.ORDEN_PREPARADA.getValue());
            estadoPreparada12.setFecha(getDateFromString("25/10/2013"));
            orden12.getEstados().add(estadoPreparada12);
            EstadosOrdenes estadoConfirmada12 = new EstadosOrdenes(0,orden12,EstadoOrden.ORDEN_CONFIRMADA.getValue());
            estadoConfirmada12.setFecha(getDateFromString("1/11/2013"));
            orden12.getEstados().add(estadoConfirmada12);
            ManejadorOrdenes.getInstance().modificarOrden(orden12);

            Comentario com1 = agregarComentario("Dan", "IPH5", null, "El mejor iPhone hasta el momento. Es la mejor compra que he hecho en años. Le pasa el trapo a todos los teléfonos Android.", "19/9/2013");
            Comentario com2 = agregarComentario("Phil", "IPH5", com1.getId(), "Me parece que tu comentario es un poco desubicado. Hay muy buenos teléfonos que creo que mejoran las prestaciones de este, como el Samsung Galaxy S4.", "19/9/2013");
            Comentario com3 = agregarComentario("Dan", "IPH5", com2.getId(), "No creo, supe tener un Galaxy S2 y lo tenía que reiniciar todos los días. Nunca más vuelvo a Android.", "20/9/2013");
            agregarComentario("Phil", "IPH5", com3.getId(), "El mejor iPhone hasta el momento. Es la mejor compra que he hech", "20/9/2013");

            agregarComentario("BruceS", "CIX", null, "¡Excelente control! Puedo disfrutar de mi GTA V sin la molestia de cables.", "25/9/2013");
            agregarComentario("BruceS", "CIX", null, "Retracto lo que escribí antes....se me rompió a los 3 dias. Me han estafado.", "28/9/2013");
            agregarComentario("JeffW", "PCG", null, "Cumple su cometido. No he notado ninguna rayita nueva en mi Samsung.", "25/9/2013");
            
            agregarReclamo("Phil", "IPH5",  "Me demoro mas de un mes en llegar el telefono, estaria bueno que mejoraran los tiempos de envio.","19/9/2013");
            agregarReclamo("BruceS", "CIX",  "Lo puse en los comentarios y quiero escribirlo aca. Su producto me vino defectuoso, espero un reembolso de dinero","28/9/2013");
            agregarReclamo("BruceS", "CIX",  "No he tenido respuesta de parte suya. Estaria bueno que mejoraran su servicio de atencion al cliente, porque me parece que el actual es pesimo","15/10/2013");
            agregarReclamo("Ricky", "IPH5",  "Me han enviado un iPhone 4 en lugar del iPhone 5 que encargue..","1/11/2013");
            agregarReclamo("Ricky", "IRD",  "Vino con algunas rayitas. Estaria bueno que controlatan que estas cosas no pasaran en el traslado del producto.","1/11/2013");
            agregarReclamo("Ricky", "IM",  "Este dispositivo vino fallado de fabrica, me gustaria que me lo reembolsaran.","1/11/2013");
            agregarReclamo("Ricky", "IPH5C",  "No vinieron los auriculares incluidos en la caja.","1/11/2013");
    
            agregarPuntaje("Dan","IPH5",5);
            agregarPuntaje("Dan","IM",5);
            agregarPuntaje("Dan","IRD",5);
            agregarPuntaje("Phil","IPH5",3);
            agregarPuntaje("Ricky","IPH5",3);
            agregarPuntaje("Ricky","IM",4);
            agregarPuntaje("Ricky","IRD",3);
        }
    }
    
    public static void agregarPuntaje(String nickname, String nroRef, Integer Punto) {
        EspecificacionProducto aModificar = ManejadorEspProductos.getInstance().getEspecificacionProducto(nroRef);
        List<Puntaje> puntajes = aModificar.getPuntajes();
        Puntaje puntajeAAgregar = new Puntaje();
        puntajeAAgregar.setCliente(ManejadorUsuarios.getInstance().getCliente(nickname));
        puntajeAAgregar.setEspecificacionProducto(aModificar);
       
        puntajeAAgregar.setPuntaje(Punto);
        puntajes.add(puntajeAAgregar);
        ManejadorEspProductos.getInstance().getEspecificacionProducto(nroRef).setPuntajes(puntajes);
        ManejadorEspProductos.getInstance().modificarProducto(aModificar);
    }
    
    public static void agregarReclamo(String nickname, String nroRef, String Rec, String fecha) {
        EspecificacionProducto aModificar = ManejadorEspProductos.getInstance().getEspecificacionProducto(nroRef);
        List<Reclamo> reclamo = aModificar.getReclamo();
        Reclamo reclamoAAgregar = new Reclamo();
        reclamoAAgregar.setCliente(ManejadorUsuarios.getInstance().getCliente(nickname));
        reclamoAAgregar.setEspecificacionProducto(aModificar);
        reclamoAAgregar.setFecha(getDateFromString(fecha));
       
        reclamoAAgregar.setReclamo(Rec);
        reclamo.add(reclamoAAgregar);
        ManejadorEspProductos.getInstance().getEspecificacionProducto(nroRef).setReclamo(reclamo);
        ManejadorEspProductos.getInstance().modificarProducto(aModificar);
    }

    private static Comentario agregarComentario(String nickname, String nroRef, Integer padre, String Comentario, String fecha) {
        EspecificacionProducto aModificar = ManejadorEspProductos.getInstance().getEspecificacionProducto(nroRef);
        List<Comentario> comentarios = aModificar.getComentarios();
        Comentario comentarioAAgregar = new Comentario();
        comentarioAAgregar.setCliente(ManejadorUsuarios.getInstance().getCliente(nickname));
        comentarioAAgregar.setEspecificacionProducto(aModificar);
        comentarioAAgregar.setFecha(getDateFromString(fecha));
        Iterator it = comentarios.iterator();
        while (it.hasNext()) {
            Comentario current = (Comentario) it.next();

            if (Objects.equals(current.getId(), padre)) {
                comentarioAAgregar.setPadre(current);
            }
        }
        comentarioAAgregar.setComentario(Comentario);
        comentarios.add(comentarioAAgregar);
        ManejadorEspProductos.getInstance().getEspecificacionProducto(nroRef).setComentarios(comentarios);
        ManejadorEspProductos.getInstance().modificarProducto(aModificar);
        return aModificar.getComentarios().get(aModificar.getComentarios().size() - 1);
    }

    public static String formatString(String s) {

        if (s != null) {

            return s.trim();
        }
        return "";
    }

    /**
     * @return Regresa la fecha en formato string
     *
     */
    public static String formatDate(Date s) {
        if (s != null) {
            DateFormat dateFormat = new SimpleDateFormat(Constantes.DATE_FORMAT);

            return dateFormat.format(s);
        }
        return "";
    }

    public static String formatDate(Calendar s) {
        if (s != null) {
            DateFormat dateFormat = new SimpleDateFormat(Constantes.DATE_FORMAT);

            return dateFormat.format(s);
        }
        return "";
    }

    public static Date getDateFromString(String value) {
        try {
            return new SimpleDateFormat(Constantes.DATE_FORMAT).parse(value);
        } catch (ParseException ex) {
            return null;
        }
    }

    public static String md5(String input) {

        String md5 = null;

        if (null == input) {
            return null;
        }

        try {

            //Create MessageDigest object for MD5
            MessageDigest digest = MessageDigest.getInstance("MD5");

            //Update input string in message digest
            digest.update(input.getBytes(), 0, input.length());

            //Converts message digest value in base 16 (hex)
            md5 = new BigInteger(1, digest.digest()).toString(16);

        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();
        }
        return md5;
    }
    
    public static String genComentarioTemplate(){
        String result = "";
        result += "<!DOCTYPE html>" +
            "<html xmlns='http://www.w3.org/1999/xhtml'>" +
            "<head>" +
            "    <meta content='text/html; charset=utf-8' http-equiv='Content-Type'>" +
            "    <meta content='width=device-width, initial-scale=1.0' name='viewport'>" +
            "    <title>Nuevo comentario</title>" +
            "    <style>" +
            "            /*<![CDATA[*/" +
            "        #outlook a {padding:0;} /* Force Outlook to provide a \"view in browser\" menu link. */" +
            "        body{width:100% !important; -webkit-text-size-adjust:100%; -ms-text-size-adjust:100%; margin:0; padding:0;}" +
            "        .ExternalClass {width:100%;} /* Force Hotmail to display emails at full width */" +
            "        .ExternalClass, .ExternalClass p, .ExternalClass span, .ExternalClass font, .ExternalClass td, .ExternalClass div {line-height: 100%;} /* Force Hotmail to display normal line spacing.  More on that: http://www.emailonacid.com/forum/viewthread/43/ */" +
            "        #backgroundTable {margin:0; padding:0; width:100% !important; line-height: 100% !important;}" +
            "        img {outline:none; text-decoration:none; -ms-interpolation-mode: bicubic;}" +
            "        a img {border:none;}" +
            "        .image_fix {display:block;}" +
            "        Bring inline: Yes." +
            "        p {margin: 1em 0;}" +
            "        h1, h2, h3, h4, h5, h6 {color: black !important;}" +
            "        h1 a, h2 a, h3 a, h4 a, h5 a, h6 a {color: blue !important;}" +
            "        h1 a:active, h2 a:active,  h3 a:active, h4 a:active, h5 a:active, h6 a:active {" +
            "            color: red !important; /* Preferably not the same color as the normal header link color.  There is limited support for psuedo classes in email clients, this was added just for good measure. */" +
            "        }" +
            "        h1 a:visited, h2 a:visited,  h3 a:visited, h4 a:visited, h5 a:visited, h6 a:visited {" +
            "            color: purple !important; /* Preferably not the same color as the normal header link color. There is limited support for psuedo classes in email clients, this was added just for good measure. */" +
            "        }" +
            "        table td {border-collapse: collapse;}" +
            "        table { border-collapse:collapse; mso-table-lspace:0pt; mso-table-rspace:0pt; }" +
            "        a {color: orange;}" +
            "        a:link { color: orange; }" +
            "        a:visited { color: blue; }" +
            "        a:hover { color: green; }" +
            "            /*]]>*/" +
            "    </style>" +
            "</head>" +
            "<body style='background: #f4f7f9; font-family:Helvetica Neue, Helvetica, Arial;'>" +
            "<table align='center' bgcolor='#f4f7f9' border='0' cellpadding='0' cellspacing='0' id='backgroundTable' style='background: #f4f7f9;' width='100%'>" +
            "    <tr>" +
            "        <td align='center'>" +
            "            <center>" +
            "                <table border='0' cellpadding='30' cellspacing='0' style='margin-left: auto;margin-right: auto;width:600px;text-align:center;' width='600'>" +
            "                    <tr>" +
            "                        <td align='left' style='background: #ffffff; border: 1px solid #dce1e5;' valign='top' width=''>" +
            "                            <table border='0' cellpadding='0' cellspacing='0' width='100%'>" +
            "                                <tr>" +
            "                                    <td align='center' valign='top'>" +
            "                                        <h2>Ha recibido un comentario</h2>" +
            "                                    </td>" +
            "                                </tr>" +
            "                                <tr>" +
            "                                    <td align='center' style='border-top: 1px solid #dce1e5;border-bottom: 1px solid #dce1e5;' valign='top'>" +
            "                                        <p style='margin: 1em 0;'>" +
            "                                            <strong>Producto:</strong>" +
            "                                            {!1}" +
            "                                        </p>" +
            "                                    </td>" +
            "                                </tr>" +
            "                                <tr>" +
            "                                    <td align='center' style='border-top: 1px solid #dce1e5;border-bottom: 1px solid #dce1e5;' valign='top'>" +
            "                                        <p style='margin: 1em 0;'>" +
            "                                            <br>" +
            "                                            {!0}" +
            "                                        </p>" +
            "                                    </td>" +
            "                                </tr>" +
            "                            </table>" +
            "                        </td>" +
            "                    </tr>" +
            "                </table>" +
            "            </center>" +
            "        </td>" +
            "    </tr>" +
            "</table>" +
            "</body>" +
            "</html>";
        return result;
    }
    
    public static String genNuevoProductoTemplate(){
        String result = "";
        result += "<!DOCTYPE html>" +
            "<html xmlns='http://www.w3.org/1999/xhtml'>" +
            "<head>" +
            "    <meta content='text/html; charset=utf-8' http-equiv='Content-Type'>" +
            "    <meta content='width=device-width, initial-scale=1.0' name='viewport'>" +
            "    <title>Nuevo producto</title>" +
            "    <style>" +
            "            /*<![CDATA[*/" +
            "        #outlook a {padding:0;} /* Force Outlook to provide a \"view in browser\" menu link. */" +
            "        body{width:100% !important; -webkit-text-size-adjust:100%; -ms-text-size-adjust:100%; margin:0; padding:0;}" +
            "        .ExternalClass {width:100%;} /* Force Hotmail to display emails at full width */" +
            "        .ExternalClass, .ExternalClass p, .ExternalClass span, .ExternalClass font, .ExternalClass td, .ExternalClass div {line-height: 100%;} /* Force Hotmail to display normal line spacing.  More on that: http://www.emailonacid.com/forum/viewthread/43/ */" +
            "        #backgroundTable {margin:0; padding:0; width:100% !important; line-height: 100% !important;}" +
            "        img {outline:none; text-decoration:none; -ms-interpolation-mode: bicubic;}" +
            "        a img {border:none;}" +
            "        .image_fix {display:block;}" +
            "        Bring inline: Yes." +
            "        p {margin: 1em 0;}" +
            "        h1, h2, h3, h4, h5, h6 {color: black !important;}" +
            "        h1 a, h2 a, h3 a, h4 a, h5 a, h6 a {color: blue !important;}" +
            "        h1 a:active, h2 a:active,  h3 a:active, h4 a:active, h5 a:active, h6 a:active {" +
            "            color: red !important; /* Preferably not the same color as the normal header link color.  There is limited support for psuedo classes in email clients, this was added just for good measure. */" +
            "        }" +
            "        h1 a:visited, h2 a:visited,  h3 a:visited, h4 a:visited, h5 a:visited, h6 a:visited {" +
            "            color: purple !important; /* Preferably not the same color as the normal header link color. There is limited support for psuedo classes in email clients, this was added just for good measure. */" +
            "        }" +
            "        table td {border-collapse: collapse;}" +
            "        table { border-collapse:collapse; mso-table-lspace:0pt; mso-table-rspace:0pt; }" +
            "        a {color: orange;}" +
            "        a:link { color: orange; }" +
            "        a:visited { color: blue; }" +
            "        a:hover { color: green; }" +
            "            /*]]>*/" +
            "    </style>" +
            "</head>" +
            "<body style='background: #f4f7f9; font-family:Helvetica Neue, Helvetica, Arial;'>" +
            "<table align='center' bgcolor='#f4f7f9' border='0' cellpadding='0' cellspacing='0' id='backgroundTable' style='background: #f4f7f9;' width='100%'>" +
            "    <tr>" +
            "        <td align='center'>" +
            "            <center>" +
            "                <table border='0' cellpadding='30' cellspacing='0' style='margin-left: auto;margin-right: auto;width:600px;text-align:center;' width='600'>" +
            "                    <tr>" +
            "                        <td align='left' style='background: #ffffff; border: 1px solid #dce1e5;' valign='top' width=''>" +
            "                            <table border='0' cellpadding='0' cellspacing='0' width='100%'>" +
            "                                <tr>" +
            "                                    <td align='center' valign='top'>" +
            "                                        <h2>El proveedor {!1} ingreso un nuevo producto</h2>" +
            "                                    </td>" +
            "                                </tr>" +
            "                                <tr>" +
            "                                    <td align='center' style='border-top: 1px solid #dce1e5;border-bottom: 1px solid #dce1e5;' valign='top'>" +
            "                                        <p style='margin: 1em 0;'>" +
            "                                            <strong>Producto:</strong>" +
            "                                            {!0}" +
            "                                        </p>" +
            "                                    </td>" +
            "                                </tr>" +
            "                            </table>" +
            "                        </td>" +
            "                    </tr>" +
            "                </table>" +
            "            </center>" +
            "        </td>" +
            "    </tr>" +
            "</table>" +
            "</body>" +
            "</html>";
        return result;
    }
    
    public static String genCambioDeEstadoTemplate(){
        String result = "";
        result += "<!DOCTYPE html>" +
            "<html xmlns='http://www.w3.org/1999/xhtml'>" +
            "<head>" +
            "    <meta content='text/html; charset=utf-8' http-equiv='Content-Type'>" +
            "    <meta content='width=device-width, initial-scale=1.0' name='viewport'>" +
            "    <title>Nueva orden</title>" +
            "    <style>" +
            "            /*<![CDATA[*/" +
            "        #outlook a {padding:0;} /* Force Outlook to provide a \"view in browser\" menu link. */" +
            "        body{width:100% !important; -webkit-text-size-adjust:100%; -ms-text-size-adjust:100%; margin:0; padding:0;}" +
            "        .ExternalClass {width:100%;} /* Force Hotmail to display emails at full width */" +
            "        .ExternalClass, .ExternalClass p, .ExternalClass span, .ExternalClass font, .ExternalClass td, .ExternalClass div {line-height: 100%;} /* Force Hotmail to display normal line spacing.  More on that: http://www.emailonacid.com/forum/viewthread/43/ */" +
            "        #backgroundTable {margin:0; padding:0; width:100% !important; line-height: 100% !important;}" +
            "        img {outline:none; text-decoration:none; -ms-interpolation-mode: bicubic;}" +
            "        a img {border:none;}" +
            "        .image_fix {display:block;}" +
            "        Bring inline: Yes." +
            "        p {margin: 1em 0;}" +
            "        h1, h2, h3, h4, h5, h6 {color: black !important;}" +
            "        h1 a, h2 a, h3 a, h4 a, h5 a, h6 a {color: blue !important;}" +
            "        h1 a:active, h2 a:active,  h3 a:active, h4 a:active, h5 a:active, h6 a:active {" +
            "            color: red !important; /* Preferably not the same color as the normal header link color.  There is limited support for psuedo classes in email clients, this was added just for good measure. */" +
            "        }" +
            "        h1 a:visited, h2 a:visited,  h3 a:visited, h4 a:visited, h5 a:visited, h6 a:visited {" +
            "            color: purple !important; /* Preferably not the same color as the normal header link color. There is limited support for psuedo classes in email clients, this was added just for good measure. */" +
            "        }" +
            "        table td {border-collapse: collapse;}" +
            "        table { border-collapse:collapse; mso-table-lspace:0pt; mso-table-rspace:0pt; }" +
            "        a {color: orange;}" +
            "        a:link { color: orange; }" +
            "        a:visited { color: blue; }" +
            "        a:hover { color: green; }" +
            "            /*]]>*/" +
            "    </style>" +
            "</head>" +
            "<body style='background: #f4f7f9; font-family:Helvetica Neue, Helvetica, Arial;'>" +
            "<table align='center' bgcolor='#f4f7f9' border='0' cellpadding='0' cellspacing='0' id='backgroundTable' style='background: #f4f7f9;' width='100%'>" +
            "    <tr>" +
            "        <td align='center'>" +
            "            <center>" +
            "                <table border='0' cellpadding='30' cellspacing='0' style='margin-left: auto;margin-right: auto;width:600px;text-align:center;' width='600'>" +
            "                    <tr>" +
            "                        <td align='left' style='background: #ffffff; border: 1px solid #dce1e5;' valign='top' width=''>" +
            "                            <table border='0' cellpadding='0' cellspacing='0' width='100%'>" +
            "                                <tr>" +
            "                                    <td align='center' valign='top'>" +
            "                                        <h2>Gracias por comprar en Direct Market!</h2>" +
            "                                    </td>" +
            "                                </tr>" +
            "                                <tr>" +
            "                                    <td align='center' valign='top'>" +
            "                                        <h4 style='color: #f34541 !important'>Hola {!USER} hemos recibido una orden.</h4>" +
            "                                    </td>" +
            "                                </tr>" +
            "                                <tr>" +
            "                                    <td align='center' style='border-top: 1px solid #dce1e5;border-bottom: 1px solid #dce1e5;' valign='top'>" +
            "                                        <p style='margin: 1em 0;'>" +
            "                                            <strong>Orden:</strong>" +
            "                                            {!0}" +
            "                                        </p>" +
            "                                        <p style='margin: 1em 0;'>" +
            "                                            <strong>Estado:</strong>" +
            "                                            {!1}" +
            "                                        </p>" +
            "                                    </td>" +
            "                                </tr>" +
            "                            </table>" +
            "                        </td>" +
            "                    </tr>" +
            "                </table>" +
            "            </center>" +
            "        </td>" +
            "    </tr>" +
            "</table>" +
            "</body>" +
            "</html>";
        return result;
    }
}
