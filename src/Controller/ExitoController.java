package Controller;

import Model.Window.ExitoModel;
import Model.Window.MainMenuModel;
import View.ExitoView;
import View.MainMenuView;

@SuppressWarnings("unused")
public class ExitoController {
    private ExitoView ventana;
    private ExitoModel modelo;

    public ExitoController(ExitoView ventana, ExitoModel modelo){
        this.ventana = ventana;
        this.modelo = modelo;
        this.ventana.addContinuarListener(e -> logicaContinuar());
    }

    private void logicaContinuar(){
        MainMenuView mainV = new MainMenuView();
        MainMenuModel mainM = new MainMenuModel();
        new MainMenuController(mainV, mainM);
        mainV.setVisible(true);
        this.ventana.dispose();
    }
}
