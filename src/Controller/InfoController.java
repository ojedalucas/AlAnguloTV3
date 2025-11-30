package controller;

import model.logic.InfoModel;
import model.logic.MainMenuModel;
import view.InfoView;
import view.MainMenuView;

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
        MainMenuView mainV = new MainMenuView();
        MainMenuModel mainM = new MainMenuModel();
        new MainMenuController(mainV, mainM);
        mainV.setVisible(true);
        this.ventana.dispose();
    }
}
