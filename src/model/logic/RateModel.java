package model.logic;

import java.sql.SQLException;
import java.time.LocalDateTime;
import data.dao.*;
import model.domain.*;
import util.SesionActual;
import util.exceptions.CamposVaciosException;

public class RateModel {
    private ReseniaDAO verificadorResenia;
    private Pelicula pelicula;
    
    public RateModel(Pelicula p){
        this.pelicula = p;
        verificadorResenia = new ReseniaDAOjdbl ();
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