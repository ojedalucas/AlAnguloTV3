package Controller;

import Model.Logic.InfoModel;
import Model.Logic.PrincipalModel;
import View.InfoView;
import View.MainMenuView;
import View.PrincipalView;

@SuppressWarnings("unused")
public class InfoController {
    private InfoView ventana;
    private InfoModel modelo;

    public InfoController(InfoView ventana, InfoModel modelo){
        this.ventana = ventana;
        this.modelo = modelo;
        this.ventana.addContinuarListener(e-> logicaContinuar());
        /*
        this.ventana.setTitleText(modelo.getTitle());
        this.ventana.setYearText(modelo.getYear());
        this.ventana.setResumenText(modelo.getResumen());
        */
    }

    private void logicaContinuar(){
        PrincipalView principalV = new PrincipalView();
        PrincipalModel principalM = new PrincipalModel();
        new PrincipalController(principalV, principalM);
        principalV.setVisible(true);
        this.ventana.dispose();
    }
}
