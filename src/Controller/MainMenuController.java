package Controller;

import View.MainMenuView;
import Model.MainMenuModel;

@SuppressWarnings("unused")
public class MainMenuController {
    private MainMenuView ventana;
    private MainMenuModel modelo;

    public MainMenuController(MainMenuView ventana, MainMenuModel modelo){
        this.ventana = ventana;
        this.modelo = modelo;
    }
}
