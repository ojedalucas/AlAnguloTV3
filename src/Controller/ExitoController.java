package Controller;

import Model.Logic.ExitoModel;
import Model.Logic.PrincipalModel;
import View.ExitoView;
import View.PrincipalView;

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
        PrincipalView principalV = new PrincipalView();
        PrincipalModel principalM = new PrincipalModel();
        new PrincipalController(principalV, principalM);
        principalV.setVisible(true);
        this.ventana.dispose();
    }
}
