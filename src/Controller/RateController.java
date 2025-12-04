package controller;

import model.logic.*;
import util.exceptions.CamposVaciosException;
import view.ExitoView;
import view.PrincipalView;
import view.RateView;
import java.sql.SQLException;

public class RateController {
    private RateView ventana;
    private RateModel modelo;

    public RateController(RateView ventana, RateModel modelo){
        this.ventana = ventana;
        this.modelo = modelo;
        this.ventana.setTitleText(modelo.getPelicula().getTitulo());
        this.ventana.addGuardarListener(e -> logicaGuardar());
    }

    private void logicaGuardar(){
        int rating = ventana.getRating();
        String comentario = ventana.getComentario();
        try {
            modelo.agregarResenia(rating, comentario);
            ExitoView exitoV = new ExitoView();
            exitoV.setVisible(true);
            exitoV.addContinuarListener(e -> logicaContinuar(exitoV));

        } catch (CamposVaciosException e){
            ventana.showErrorMessage(e.getMessage());
        } catch (SQLException ex) {
            ex.printStackTrace();
            ventana.showErrorMessage("Error en base de datos.");
        }
    }

    private void logicaContinuar(ExitoView exitoV){
        PrincipalView principalV = new PrincipalView();
        PrincipalModel principalM = new PrincipalModel(false);
        new PrincipalController(principalV, principalM);
        principalV.setVisible(true);
        exitoV.dispose();
        this.ventana.dispose();
    }
}