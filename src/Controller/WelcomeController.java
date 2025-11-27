package Controller;

import View.LoadingView;
import View.RegistrationView;
import View.WelcomeView;
import TP2.Database.UsuarioDAOjdbl;
import TP2.Modelo.Usuario;

public class WelcomeController {
    private WelcomeView ventana;
    private UsuarioDAOjdbl verificador;

    public WelcomeController(WelcomeView ventana, UsuarioDAOjdbl verificador){
        this.ventana = ventana;
        this.verificador = verificador;
        this.ventana.addLoginListener(e -> logicaIngreso());
        this.ventana.addRegisterListener(e -> logicaRegistro());
    }

    public void logicaIngreso(){   
        String email = this.ventana.getEmail();
        String contrasenia = this.ventana.getPassword();
        Usuario user = verificador.iniciarSesion(email, contrasenia);
        if (user != null){
            LoadingView load = new LoadingView();
            new LoadingController(load);
            load.setVisible(true);
            this.ventana.dispose();
        } else {
            ventana.showLoginError();
        }
    }

    private void logicaRegistro(){
        RegistrationView reg = new RegistrationView();
        new RegistrationController(reg, verificador);
        reg.setVisible(true);
        this.ventana.dispose();
    }

}
