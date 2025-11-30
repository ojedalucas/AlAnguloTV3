package model.logic;

import java.sql.SQLException;
import java.time.LocalDateTime;

import Util.Exceptions.CamposVaciosException;
import data.dao.*;
import model.domain.*;

@SuppressWarnings("unused")
public class RateModel {
    private ReseniaDAO verificadorResenia;
    private UsuarioDAO verificadorUsuario;
    private PeliculaDAO verificadorPelicula;
    private Usuario user;
    private Pelicula pelicula;
    
    public RateModel(Usuario user, Pelicula pelicula){
        this.user = user;
        this.pelicula = pelicula;
        verificadorResenia = new ReseniaDAOjdbl ();
        verificadorUsuario = new UsuarioDAOjdbl();
        verificadorPelicula = new PeliculaDAOjdbl();
    }

    public void agregarResenia(int rating, String comentario) throws SQLException, CamposVaciosException{
        if (rating == 0 | comentario.isBlank())
            throw new CamposVaciosException();

        Resenia resenia=new Resenia(0, rating, comentario, false, LocalDateTime.now(), user.getIdUsuario(), pelicula.getId());
        verificadorResenia.cargarResenia(resenia);
    } 
}