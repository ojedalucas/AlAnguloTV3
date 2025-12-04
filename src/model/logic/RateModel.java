package model.logic;

import java.sql.SQLException;
import java.time.LocalDateTime;

import data.dao.*;
import model.domain.*;
import util.SesionActual;
import util.exceptions.CamposVaciosException;

@SuppressWarnings("unused")
public class RateModel {
    private ReseniaDAO verificadorResenia;
    private UsuarioDAO verificadorUsuario;
    private PeliculaDAO verificadorPelicula;
    private Pelicula pelicula;
    
    public RateModel(Pelicula p){
        this.pelicula = p;
        verificadorResenia = new ReseniaDAOjdbl ();
        verificadorUsuario = new UsuarioDAOjdbl();
        verificadorPelicula = new PeliculaDAOjdbl();
    }

    public void agregarResenia(int rating, String comentario) throws SQLException, CamposVaciosException{
        if (rating == 0 | comentario.isBlank())
            throw new CamposVaciosException();
        Resenia resenia=new Resenia(0, rating, comentario, false, LocalDateTime.now(), SesionActual.getUsuarioActual().getIdUsuario(), pelicula.getId());
        verificadorResenia.cargarResenia(resenia);
    } 

    public Pelicula getPelicula(){
        return pelicula;
    }
}