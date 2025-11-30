package Controller;

import View.ExitoView;
import View.RateView;

import java.sql.SQLException;

import Model.Logic.ExitoModel;
import Model.Logic.RateModel;
import Util.Exceptions.CamposVaciosException;

@SuppressWarnings("unused")
public class RateController {
    private RateView ventana;
    private RateModel modelo;

    public RateController(RateView ventana, RateModel modelo){
        this.ventana = ventana;
        this.modelo = modelo;
        this.ventana.addGuardarListener(e -> logicaGuardar());
    }

    private void logicaGuardar(){
        int rating = ventana.getRating();
        String comentario = ventana.getComentario();
        boolean exito = true;
        try {
            modelo.agregarResenia(rating, comentario);
            ExitoView exitoV = new ExitoView();
            ExitoModel exitoM = new ExitoModel();
            new ExitoController(exitoV, exitoM);
            exitoV.setVisible(true);
            this.ventana.dispose();
        } catch (CamposVaciosException e){
            ventana.showErrorMessage(e.getMessage());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    
}
