package Util;

import java.util.ArrayList;
import java.util.Comparator;

import Model.Domain.Pelicula;

public class MoviesData {
    private static ArrayList<Pelicula> infoPeliculas;

    public static void cargarPeliculasMemoria(ArrayList<Pelicula> peliculas){
        infoPeliculas = peliculas;
    }

    public static ArrayList<Pelicula> getInfoPeliculas(){
        return infoPeliculas;
    }

    public static Pelicula getPeliculaId(int id){
        for(Pelicula p: infoPeliculas){
            if (p.getId() == id){
                return p;
            }
        }
        return null;
    }

    public static Pelicula getPeliculaTitulo(String titulo){
        for (Pelicula p: infoPeliculas){
            if (p.getTitulo().equals(titulo)){
                return p;
            }
        }
        return null;
    }

    public static void ordenarPorRatingDescendente() {
        if (infoPeliculas != null) {
            infoPeliculas.sort(
                Comparator.comparingDouble(Pelicula::getRatingPromedio).reversed()
            );
        }
    }

}
