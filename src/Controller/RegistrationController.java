package Controller;

import View.RegistrationView;
import View.WelcomeView;
import Model.RegistrationModel;
import Model.WelcomeModel;

@SuppressWarnings("unused")
public class RegistrationController {
    private RegistrationView ventana;
    private RegistrationModel modelo;

    public RegistrationController(RegistrationView ventana, RegistrationModel modelo){
        this.ventana = ventana;
        this.modelo = modelo;
        this.ventana.addRegisterListener(e -> logicaRegistro());
    }
    private void logicaRegistro(){
        String nombre = ventana.getNombre();
        String apellido = ventana.getApellido();
        String DNI = ventana.getDNI();
        String email = ventana.getEmail();
        String contrasenia = ventana.getPassword();
        boolean exito = true;
        /* 
        if (nombre.isBlank() || apellido.isBlank() || DNI.isBlank() || email.isBlank() || contrasenia.isBlank()){
            exito = false;
            ventana.showLoginError("Debe completar todas las casillas.");
        }
        if (modelo.existeDNI(dni)){
            exito = false;
            ventana.showLoginError("El DNI ingresado ya está asociado a una cuenta.");
        }
        if (modelo.formatoEmail(email) == false){
            exito = false;
            ventana.showLoginError("Email debe cumplir con el formato: xxx@yyy");
        }
        if (modelo.existeEmail(email)){
            exito = false;
            ventana.showLoginError("El email ingresado ya está asociado a una cuenta."); 
        }
        */
        if (exito){
            /* Usuario user = modelo.registrarUsuario(nombre, apellido, DNI, email, contrasenia) */
            WelcomeView welV = new WelcomeView();
            WelcomeModel welM = new WelcomeModel();
            new WelcomeController(welV, welM);
            welV.setVisible(true);
            this.ventana.dispose();
        }
    }
}
