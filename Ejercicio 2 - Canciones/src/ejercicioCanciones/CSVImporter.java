/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejercicioCanciones;

import java.util.ArrayList;
import com.csvreader.CsvReader;
import java.io.FileNotFoundException;
import java.io.IOException;
/**
 *
 * @author Nicolas
 */
public class CSVImporter {
    
    public static ArrayList importarCanciones(String filePath){
        try{

            ArrayList<Cancion> canciones = new ArrayList<Cancion>();

            CsvReader cancionesImport = new CsvReader(filePath);
            cancionesImport.readHeaders();
            cancionesImport.setDelimiter(';');

            while (cancionesImport.readRecord()){
                String nombreCancion = cancionesImport.get(0);
                String artista = cancionesImport.get(1);
                String genero = cancionesImport.get(2);
                canciones.add(new Cancion(nombreCancion, artista, genero));
                //System.out.println("NC: "+nombreCancion+" A: "+artista+" G: "+genero);
            }

            cancionesImport.close();
 
            return canciones;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
            
    }   
}
