package Model.Logic;

import java.sql.SQLException;
import java.util.ArrayList;

import Data.Dao.PeliculaDAO;
import Data.Dao.PeliculaDAOjdbl;
import Util.MoviesData;
//import Util.UserSession;
import service.LectorCSV;
import Model.Domain.Pelicula;

public class PrincipalModel {
    private PeliculaDAO dbPelicula;

    public PrincipalModel(){
        dbPelicula = new PeliculaDAOjdbl();
    }

    public void cargarPeliculas() throws SQLException {
        dbPelicula.eliminarTodas();
        MoviesData.cargarPeliculasMemoria(LectorCSV.leerPeliculas("/csv/movies_database.csv"));
        MoviesData.ordenarPorRatingDescendente();
        for (Pelicula p: MoviesData.getInfoPeliculas()){
            dbPelicula.cargarPelicula(p);
        }
    }

    public Object[][] seleccionarPeliculas() {
        ArrayList<Pelicula> peliculas = MoviesData.getInfoPeliculas();
        int numFilas = Math.min(10, peliculas.size());
        Object[][] data = new Object[numFilas][4];
        for (int i = 0; i < numFilas; i++) {
            Pelicula p = peliculas.get(i);
            data[i][0] = recortarTexto(p.getTitulo(), 25);
            data[i][1] = recortarTexto(p.getGenero().toString(), 20);
            data[i][2] = recortarTexto(p.getResumen(), 50);
            data[i][3] = true;
            //data[i][3] = dbPelicula.fueCalificada(p.getId(), UserSession.getUsuarioActual().getIdUsuario());
        }
        return data;
    }

    public static String recortarTexto(String texto, int maxChars) {
        if (texto == null) return "";
        if (texto.length() <= maxChars) return texto;
        return texto.substring(0, maxChars - 3) + "..."; // agrega "..." al final
    }

}
