package Controller;

import Model.Window.MainMenuModel;
import View.MainMenuView;

@SuppressWarnings("unused")
public class MainMenuController {
    private MainMenuView ventana;
    private MainMenuModel modelo;

    public MainMenuController(MainMenuView ventana, MainMenuModel modelo){
        this.ventana = ventana;
        this.modelo = modelo;
    }
}
