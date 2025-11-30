package controller;

import model.logic.LoadingModel;
import view.LoadingView;

@SuppressWarnings("unused")
public class LoadingController {
    private LoadingView ventana;
    private LoadingModel modelo;

    public LoadingController(LoadingView ventana, LoadingModel modelo){
        this.ventana = ventana;
        this.modelo = modelo;
    }
}
