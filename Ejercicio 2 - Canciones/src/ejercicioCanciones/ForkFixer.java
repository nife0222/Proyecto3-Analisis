/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejercicioCanciones;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.NotSupportedException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nicolas
 */
public class ForkFixer  extends RecursiveAction {
    
    private Mp3File[] _source;  // Arreglo donde vendran las canciones sin arreglar
    private int _start;     // Punto de inicio para el recorrido
    private int _length;    // Cantidad de espacios que recorrera desde _start
    private final ArrayList<Cancion> _correctSongs;
    //private Cancion[] _destination;

    public ForkFixer(Mp3File[] src, int start, int length, ArrayList songs) {
        _source = src;
        _start = start;
        _length = length;
        _correctSongs = songs;
        //_destination = dst;
    }
    
    protected void computeDirectly() throws IOException, NotSupportedException {
        
        for (int SourceIndex = _start; SourceIndex < _start + _length; SourceIndex++){
            System.out.println(" -> ITERACION NUMERO " + (SourceIndex+1));
            int bestSongNamePos = 0;
            int bestArtistPos = 0;
            int bestGenrePos = 0;
            if(_source[SourceIndex].hasId3v2Tag()){
                ID3v2 tag  = _source[SourceIndex].getId3v2Tag();
                for(int csvIndex = 0; csvIndex < _correctSongs.size(); csvIndex++){
                    if(Levenshtein.Distance(tag.getTitle(), _correctSongs.get(csvIndex).getNombreCancion()) < 
                       Levenshtein.Distance(tag.getTitle(), _correctSongs.get(bestSongNamePos).getNombreCancion())){ //Para nombre de cancion
                        bestSongNamePos = csvIndex;
                    }
                    if(Levenshtein.Distance(tag.getArtist(), _correctSongs.get(csvIndex).getArtista()) < 
                       Levenshtein.Distance(tag.getArtist(), _correctSongs.get(bestArtistPos).getArtista())){ //Para artista
                        bestArtistPos = csvIndex;
                    }
                    if(Levenshtein.Distance(tag.getGenreDescription(), _correctSongs.get(csvIndex).getGenero()) < 
                       Levenshtein.Distance(tag.getGenreDescription(), _correctSongs.get(bestGenrePos).getGenero())){ //Para genero
                        bestGenrePos = csvIndex;
                    }
                }
                // Reemplazo aqui:
                System.out.println(" From: " + tag.getTitle() + " To: " + _correctSongs.get(bestSongNamePos).getNombreCancion());
                
                tag.setTitle(_correctSongs.get(bestSongNamePos).getNombreCancion());
                tag.setArtist(_correctSongs.get(bestArtistPos).getArtista());
                _source[SourceIndex].setId3v2Tag(tag);
                
                int length =  _source[SourceIndex].getFilename().length();
                String newFileName = _source[SourceIndex].getFilename().substring(0,length-4) + "2" + ".mp3";
                _source[SourceIndex].save(newFileName);
                
            }
            
        }
        
        // Aqui recorro mi sub-Arreglo de Canciones y las comparo con los datos del archivo CSV para arreglarla, y 
        // directamente reparar el MP3 y asi no manejar dos arreglos.
        System.out.println("- computeDirectly() Executed -");
    }

    protected static final int MAX_SONGS_PER_COMPUTE = 10;
    @Override
    protected void compute() {
        if (_length < MAX_SONGS_PER_COMPUTE) {
            try {
                computeDirectly();
            } catch (IOException ex) {
                Logger.getLogger(ForkFixer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NotSupportedException ex) {
                Logger.getLogger(ForkFixer.class.getName()).log(Level.SEVERE, null, ex);
            }
            return;
        }        
        int split = _length / 2;
        invokeAll(new ForkFixer(_source, _start, split, _correctSongs),
                  new ForkFixer(_source, _start+split, _length-split, _correctSongs));
        //Si no lo subdivido en algo mas pequeno y los vuelvo a invocar en dos partes
    }
    
    
    
    
    public static void FixSongs(String directoryPath, ArrayList correctSong){
        try {
            File folder = new File(directoryPath);
            File[] listFiles = folder.listFiles(); //Hace una lista de todos los archivos dentro de FOLDER que son
            ArrayList<Mp3File> songMp3Files = new ArrayList();
            for (File listFile : listFiles) {
                if (listFile.isFile()) {
                    String path = listFile.getAbsolutePath();
                    Mp3File mp3file = new Mp3File(path);
                    songMp3Files.add(mp3file);
                    //System.out.println(mp3file.getFilename());
                }
            }
            Mp3File[] Mp3FilesList = songMp3Files.toArray(new Mp3File[0]);
            
            for(int i=0; i<Mp3FilesList.length; i++){
                System.out.println(Mp3FilesList[i].getFilename());
            }
            // Primero traigo todas las MP3 del directorio esppecificado y las meto en un arreglo
        
            ForkFixer forkFixer = new ForkFixer(Mp3FilesList, 0, Mp3FilesList.length, correctSong);
            // Luego inicializa un nuevo ForkFixer y le paso este arreglo por parametros, junto con el ArrayList del CSV
            
            ForkJoinPool pool = new ForkJoinPool();
            long startTime = System.currentTimeMillis();
            pool.invoke(forkFixer);
            long endTime = System.currentTimeMillis();
            System.out.println("Process took " + (endTime - startTime) + " milliseconds.");
            // Hago el ForkJoinPool e invoco mi ForkFixer, imprimo el tiempo que duro ejecutandose
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
