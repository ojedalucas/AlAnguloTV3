package controller;

import java.sql.SQLException;

import model.logic.RegistrationModel;
import model.logic.WelcomeModel;
import util.exceptions.*;
import view.RegistrationView;
import view.WelcomeView;

public class RegistrationController {
    private RegistrationView ventana;
    private RegistrationModel modelo;

    public RegistrationController(RegistrationView ventana, RegistrationModel modelo){
        this.ventana = ventana;
        this.modelo = modelo;
        this.ventana.addRegisterListener(e -> logicaRegistro());
    }
    private void logicaRegistro(){
        String username = ventana.getUsername();
        String nombre = ventana.getNombre();
        String apellido = ventana.getApellido();
        String DNI = ventana.getDNI();
        String email = ventana.getEmail();
        String contrasenia = ventana.getPassword();

        try{
            modelo.registrarUsuario(username, nombre, apellido, DNI, email, contrasenia);
            WelcomeView welV = new WelcomeView();
            WelcomeModel welM = new WelcomeModel();
            new WelcomeController(welV, welM);
            welV.setVisible(true);
            this.ventana.dispose();
        } catch (CamposVaciosException | FormatoEmailException | FormatoDniException | DniYaExisteException | EmailYaExisteException e) {
            ventana.showErrorMessage(e.getMessage());
        } catch (SQLException ex) {
            ex.printStackTrace();
            ventana.showErrorMessage("Error interno. Intente nuevamente.");
        } catch (NumberFormatException exN) {
            ventana.showErrorMessage("El DNI debe contener solo números.");
        }
    }
}
