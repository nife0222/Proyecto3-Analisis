/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejercicioCanciones;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.NotSupportedException;
import com.mpatric.mp3agic.UnsupportedTagException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.*;
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
    public static void main(String[] args) throws IOException, UnsupportedTagException, InvalidDataException, NotSupportedException {
        /*
        Mp3File mp3file = new Mp3File("C:/Users/Nicolas/Desktop/La  Flaca.mp3");
        ID3v2 tag = mp3file.getId3v2Tag();
        
        System.out.println(mp3file.getFilename());
        System.out.println(tag.getArtist());
        tag.setArtist("AAAAAAAAAAA");
        System.out.println(tag.getArtist());
        System.out.println(mp3file.getId3v2Tag().getArtist());
        mp3file.setId3v2Tag(tag);
        mp3file.save("C:/Users/Nicolas/Desktop/La  Flaca2.mp3");
        
        String s = "aaaaaaaaaaaa.mp3";
        System.out.println(s.substring(0, s.length()-4) + "2" + ".mp3");
        */
        
        ArrayList<Cancion> listaCanciones = CSVImporter.importarCanciones("C:/Users/Nicolas/Desktop/SongsCSV.csv");
        ForkFixer.FixSongs("C:/Users/Nicolas/Desktop/Songs", listaCanciones); // Especifica el directiorio de las canciones a arreglar.
        
        
        int processors = Runtime.getRuntime().availableProcessors();
            System.out.println(Integer.toString(processors) + " processor"
                + (processors != 1 ? "s are " : " is ")
                + "available");
                
    }
    
}
