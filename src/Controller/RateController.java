package Controller;

import View.RateView;
import View.ExitoView;
import Model.RateModel;
import Model.ExitoModel;

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
        if (rating == 0 || comentario.isBlank()){
            exito = false;
            // ventana.showLoginError("Debe completar ambos campos.");
        }
        if (exito){
            // modelo.agregarReseña(rating, comentario);
            ExitoView exitoV = new ExitoView();
            ExitoModel exitoM = new ExitoModel();
            new ExitoController(exitoV, exitoM);
            exitoV.setVisible(true);
            this.ventana.dispose();
        }
    }
    
}
