package Controller;

import Model.Logic.InfoModel;
import Model.Logic.PrincipalModel;
import View.InfoView;
import View.PrincipalView;

public class InfoController {
    private InfoView ventana;
    private InfoModel modelo;

    public InfoController(InfoView ventana, InfoModel modelo){
        this.ventana = ventana;
        this.modelo = modelo;
        this.ventana.addContinuarListener(e-> logicaContinuar());
        this.ventana.setTitleText(this.modelo.getPelicula().getTitulo());
        this.ventana.setYearText(String.valueOf(this.modelo.getPelicula().getAnio()));
        this.ventana.setResumenText(this.modelo.getPelicula().getResumen());
    }

    private void logicaContinuar(){
        PrincipalView principalV = new PrincipalView();
        PrincipalModel principalM = new PrincipalModel(false);
        new PrincipalController(principalV, principalM);
        principalV.setVisible(true);
        this.ventana.dispose();
    }
}
