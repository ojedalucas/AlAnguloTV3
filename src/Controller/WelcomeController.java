package Controller;

import View.LoadingView;
import View.RegistrationView;
import View.WelcomeView;
import TP2.Database.UsuarioDAOjdbl;
import TP2.Modelo.Usuario;

@SuppressWarnings("unused")
public class WelcomeController {
    private WelcomeView ventana;
    // private WelcomeModel modelo;

    public WelcomeController(WelcomeView ventana /*, WelcomeModel modelo */){
        this.ventana = ventana;
        // this.modelo = modelo;
        this.ventana.addLoginListener(e -> logicaIngreso());
        this.ventana.addRegisterListener(e -> logicaRegistro());
    }

    public void logicaIngreso(){   
        String email = this.ventana.getEmail();
        String contrasenia = this.ventana.getPassword();
        boolean exito = true;
        if (email.isBlank() || contrasenia.isBlank()){
            exito = false;
            ventana.showLoginError("Debe completar todas las casillas.");
        }
        /* if (modelo.formatoEmail(email) == false){
            exito = false;
            ventana.showLoginError("Email debe cumplir con el formato: xxx@yyy");
        }
        if (exito){
            Usuario user = modelo.iniciarSesion(email, contrasenia);
            if (user != null){
                LoadingView loadV = new LoadingView();
                LoadingModel loadM = new LoadingModel();
                new LoadingController(loadV, loadM);
                loadV.setVisible(true);
                this.ventana.dispose();
            } else {
                ventana.showLoginError("Email o contraseña incorrectos.");
            }
        }
        */
    }

    private void logicaRegistro(){
        RegistrationView regV = new RegistrationView();
        // RegistrationModel regM = new RegistrationModel();
        new RegistrationController(regV /*, modelo*/);
        regV.setVisible(true);
        this.ventana.dispose();
    }

}
