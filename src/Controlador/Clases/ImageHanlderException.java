/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador.Clases;

/**
 *
 * @author rodro
 */
public class ImageHanlderException extends Exception {
    private String message;
    
    
    public ImageHanlderException(String message) {
        this.message = message;
    }
    
    @Override
    public String getMessage(){
        return this.message;
    }       
}
