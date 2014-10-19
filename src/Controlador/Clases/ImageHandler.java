/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Clases;
 
import Controlador.Clases.ImageHanlderException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date; 

/**
 *
 * @author rodro
 */
public class ImageHandler {
 
    private static final String IMAGE_NAME = "images";
    private static final String ROOT = System.getProperty("user.dir") + File.separator;
    private static final String IMAGE_FOLDER = ROOT + IMAGE_NAME + File.separator;

    public ImageHandler() {

        Boolean success = (new File(ROOT + IMAGE_NAME)).mkdirs();
        if (!success) {
            System.out.println("Folder already exist");
        }
    }
 

    /**
     * 
     * @param inputStream
     * @return The full name of the created file 
     * @throws ImageHanlderException if something go wrong 
     */
    public String saveInputStream(InputStream inputStream,String fileNameTmp) throws ImageHanlderException {
        try {
            Date d = new Date();
            Timestamp t;
            t = new Timestamp(d.getTime());
            t = new Timestamp(d.getTime());
            String fileName =  t.toString()+fileNameTmp;
            File img = new File(IMAGE_FOLDER+fileName);
            FileOutputStream ot = new FileOutputStream(img);
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                ot.write(bytes, 0, read);
            }
            return fileName;
        } catch (Exception e) {
            throw new ImageHanlderException(e.getMessage() + " " + e.getStackTrace());
        }
    }
}
