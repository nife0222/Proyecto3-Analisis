/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejercicioCanciones;

/**
 *
 * @author Nicolas
 */
public class Cancion {
    private String _nombreCancion;
    private String _artista;
    private String _genero;
    
    public Cancion(String pNombreCancion, String pArtista, String pGenero){
        this._nombreCancion = pNombreCancion;
        this._artista = pArtista;
        this._genero = pGenero;
    }

    public String getNombreCancion() {
        return _nombreCancion;
    }

    public void setNombreCancion(String pNombreCancion) {
        this._nombreCancion = pNombreCancion;
    }

    public String getArtista() {
        return _artista;
    }

    public void setArtista(String pArtista) {
        this._artista = pArtista;
    }

    public String getGenero() {
        return _genero;
    }

    public void setGenero(String pGenero) {
        this._genero = pGenero;
    }
    
    
    
}
