/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejercicioCanciones;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
//import org.farng.mp3.MP3File;
//import org.farng.mp3.TagException;

/**
 *
 * @author Nicolas
 */
public class Ejercicio2Canciones {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        try {
            File sourceFile = new File("C:/Users/Nicolas/Desktop");
            sourceFile.setReadable(true);
            sourceFile.setWritable(true);
            System.out.println(sourceFile.canRead());
            
            
            
            
            
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
