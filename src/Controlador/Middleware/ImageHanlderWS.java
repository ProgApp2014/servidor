/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador.Middleware;

import Controlador.Clases.ImageHandler;
import Controlador.Clases.ImageHanlderException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Endpoint;

/**
 *
 * @author rodro
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public class ImageHanlderWS {
    
    private Endpoint endpoint = null;

    //Constructor
    public ImageHanlderWS() {
    }

    @WebMethod(exclude = true)
    public void publicar() {
        endpoint = Endpoint.publish("http://localhost:8094/imageWS", this);
    }
    
    @WebMethod
    public String saveImage(byte[]bytes,String name ){
        try {
            return ImageHandler.getInstance().saveImage(bytes, name);
        } catch (ImageHanlderException ex) {
            Logger.getLogger(ImageHanlderWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "ERROR";
    }
    @WebMethod
    public byte[] getImage(String fileName){
        try {
            return ImageHandler.getInstance().getImage(fileName);
        } catch (Exception ex) {
            Logger.getLogger(ImageHanlderWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
