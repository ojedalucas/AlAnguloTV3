package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.logic.InfoModel;
import model.logic.PrincipalModel;
import view.InfoView;
import view.PrincipalView;

public class InfoController {
    private InfoView ventana;
    private InfoModel modelo;

    public InfoController(InfoView ventana, InfoModel modelo){
        this.ventana = ventana;
        this.modelo = modelo;
        this.ventana.addContinuarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                logicaContinuar();
            }
        });
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
