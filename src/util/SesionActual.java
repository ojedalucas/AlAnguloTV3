package util;

import java.util.ArrayList;
import model.domain.Pelicula;
import model.domain.Usuario;

public class SesionActual {
    private static Usuario usuarioActual;
    private static ArrayList<Pelicula> peliculasActuales;
    private static boolean primerVisita = true;

    public static void iniciarSesion(Usuario u){
        usuarioActual = u;
    }

    public static void cerrarSesion(){
        usuarioActual = null;
        peliculasActuales = null;
    }
    
    public static boolean getPrimerVisita(){
        return primerVisita;
    }

    public static void setPrimerVisita(boolean condicion){
        primerVisita = condicion;
    }

    public static Usuario getUsuarioActual(){
        return usuarioActual;
    }

    public static boolean haySesion(){
        return usuarioActual != null;
    }

    public static void setPeliculasActuales(ArrayList<Pelicula> peliculas){
        peliculasActuales = peliculas;
    }

    public static ArrayList<Pelicula> getPeliculasActuales(){
        return peliculasActuales;
    }

    public static Pelicula getPeliculaPos(int i){
        return peliculasActuales.get(i);
    }
    
}
