import Controller.WelcomeController;
import Model.Database.UsuarioDAOjdbl;
import View.WelcomeView;

public class Main {
    public static void main(String[] args){
        WelcomeView ventana = new WelcomeView();
        UsuarioDAOjdbl verificador = new UsuarioDAOjdbl(null);
        new WelcomeController(ventana, verificador);
        ventana.setVisible(true);
    }
}
