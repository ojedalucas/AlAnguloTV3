import Controller.WelcomeController;
import View.WelcomeView;
import Model.WelcomeModel;

public class Main {
    public static void main(String[] args){
        WelcomeView ventana = new WelcomeView();
        WelcomeModel modelo = new WelcomeModel();
        new WelcomeController(ventana, modelo);
        ventana.setVisible(true);
    }
}
