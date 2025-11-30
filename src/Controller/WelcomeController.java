package controller;


import java.sql.SQLException;

import model.logic.LoadingModel;
import model.logic.RegistrationModel;
import model.logic.WelcomeModel;
import util.UserSession;
import util.exceptions.*;
import view.LoadingView;
import view.RegistrationView;
import view.WelcomeView;

public class WelcomeController {
    private WelcomeView ventana;
    private WelcomeModel modelo;

    public WelcomeController(WelcomeView ventana, WelcomeModel modelo){
        this.ventana = ventana;
        this.modelo = modelo;
        this.ventana.addLoginListener(e -> logicaIngreso());
        this.ventana.addRegisterListener(e -> logicaRegistro());
    }

    private void logicaIngreso(){   
        String email = this.ventana.getEmail();
        String contrasenia = this.ventana.getPassword();

        try {
            UserSession.iniciarSesion(modelo.iniciarSesion(email, contrasenia));
            LoadingView loadV = new LoadingView();
            LoadingModel loadM = new LoadingModel();
            new LoadingController(loadV, loadM);
            loadV.setVisible(true);
            this.ventana.dispose();
        } catch (CamposVaciosException | FormatoEmailException | LoginIncorrectoException e) {
            ventana.showErrorMessage(e.getMessage());
        } catch (SQLException ex) {
            // Error inesperado de base de datos
            ex.printStackTrace();
            ventana.showErrorMessage("Error interno. Intente nuevamente.");
        }
    }

    private void logicaRegistro(){
        RegistrationView regV = new RegistrationView();
        RegistrationModel regM = new RegistrationModel();
        new RegistrationController(regV, regM);
        regV.setVisible(true);
        this.ventana.dispose();
    }

}
