package app;
import java.sql.SQLException;

import Controller.WelcomeController;
import data.ConnectionManager;
import model.logic.WelcomeModel;
import View.WelcomeView;

public class Main {
    public static void main(String[] args){
        try {
            ConnectionManager.iniciar();
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        WelcomeView ventana = new WelcomeView();
        WelcomeModel modelo = new WelcomeModel();
        new WelcomeController(ventana, modelo);
        ventana.setVisible(true);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
            ConnectionManager.apagar();
            } catch (SQLException ex) {
            ex.printStackTrace();
            }
        }));
    }
}
