import Controller.WelcomeController;
import Model.Window.WelcomeModel;
import View.WelcomeView;

public class Main {
    public static void main(String[] args){
        WelcomeView ventana = new WelcomeView();
        WelcomeModel modelo = new WelcomeModel();
        new WelcomeController(ventana, modelo);
        ventana.setVisible(true);
    }
}
