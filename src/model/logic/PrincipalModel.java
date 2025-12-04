package Model.Logic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import Data.Dao.*;
import Util.MoviesData;
import Util.SesionActual;
import service.LectorCSV;
import Model.Domain.Pelicula;

public class PrincipalModel {
    private PeliculaDAO dbPelicula;
    private ReseniaDAO dbResenia;
    private boolean recargarLista;

    public PrincipalModel(boolean modoRecarga){
        dbPelicula = new PeliculaDAOjdbl();
        dbResenia = new ReseniaDAOjdbl();
        recargarLista = modoRecarga;
    }

    public void cargarPeliculas() throws SQLException {
        dbPelicula.eliminarTodas();
        MoviesData.cargarPeliculasMemoria(LectorCSV.leerPeliculas("/csv/movies_database.csv"));
        MoviesData.ordenarPorRatingDescendente();
        for (Pelicula p: MoviesData.getInfoPeliculas()){
            dbPelicula.cargarPelicula(p);
        }
    }

    public void seleccionarPeliculas() throws SQLException {
        if (recargarLista){
            int numFilas = Math.min(10, MoviesData.getInfoPeliculas().size());
            ArrayList<Pelicula> peliculas = new ArrayList<>();
            if (SesionActual.getPrimerVisita()){
                SesionActual.setPrimerVisita(false);
                for (int i = 0; i < numFilas; i++)
                    peliculas.add(MoviesData.getPeliculaPos(i));
            } else {
                Random rng = new Random();
                for (int i = 0; i < numFilas; i++){
                    int iRandom = rng.nextInt(MoviesData.getInfoPeliculas().size());
                    peliculas.add(MoviesData.getPeliculaPos(iRandom));
                }
            }
            SesionActual.setPeliculasActuales(peliculas);
        }
    }

    public boolean existeResenia(int idUsuario, int idPelicula) throws SQLException {
        return dbResenia.existeResenia(idUsuario, idPelicula);
    }

    public boolean hayPeliculasCargadas() throws SQLException {
        return !dbPelicula.baseVacia();
    }

}
