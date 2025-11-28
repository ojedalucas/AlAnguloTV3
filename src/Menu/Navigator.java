package Menu; // Cambio de paquete

import View.*; // Ahora necesitamos importar las vistas
import Model.*;
import Model.Window.*;
import Controller.*; 

public class Navigator {

    // 1. Instancia única (Singleton)
    private static Navigator instance;

    // 2. Referencias a las Vistas
    private WelcomeView welcomeView;
    private RegistrationView registrationView;
    private MainMenuView mainMenuView;
    private RateView rateView;
    private ExitoView exitoView;
    private InfoView infoView;
    private LoadingView loadingView;

    // 3. Referencia al Usuario de la sesión
    private Usuario usuarioActual;

    // 4. Constructor privado
    private Navigator() {
        // --- A. Instanciación de VISTAS ---
        welcomeView = new WelcomeView();
        registrationView = new RegistrationView();
        mainMenuView = new MainMenuView();
        rateView = new RateView();
        exitoView = new ExitoView();
        infoView = new InfoView();
        loadingView = new LoadingView();

        // --- B. Instanciación de MODELOS ---
        WelcomeModel welcomeModel = new WelcomeModel();
        RegistrationModel registrationModel = new RegistrationModel();
        MainMenuModel mainMenuModel = new MainMenuModel();
        RateModel rateModel = new RateModel();
        ExitoModel exitoModel = new ExitoModel();
        InfoModel infoModel = new InfoModel();
        LoadingModel loadingModel = new LoadingModel();

        // --- C. Instanciación de CONTROLADORES ---
        new WelcomeController(welcomeView, welcomeModel);
        new RegistrationController(registrationView, registrationModel);
        new MainMenuController(mainMenuView, mainMenuModel);
        new RateController(rateView, rateModel);
        new ExitoController(exitoView, exitoModel);
        new InfoController(infoView, infoModel);
        new LoadingController(loadingView, loadingModel);
    }

    // 5. Método para obtener la instancia
    public static Navigator getInstance() {
        if (instance == null) {
            instance = new Navigator();
        }
        return instance;
    }

    // --- MÉTODOS DE NAVEGACIÓN ---

    public void showWelcome() {
        hideAll();
        welcomeView.setVisible(true);
    }

    public void showRegistration() {
        hideAll();
        registrationView.setVisible(true);
    }

    public void showMainMenu() {
        hideAll();
        mainMenuView.setVisible(true);
    }

    public void showRate() {
        hideAll();
        rateView.setVisible(true);
    }

    public void showExito() {
        hideAll();
        exitoView.setVisible(true);
    }

    public void showInfo() {
        hideAll();
        infoView.setVisible(true);
    }

    public void showLoading() {
        hideAll();
        loadingView.setVisible(true);
    }

    // --- MÉTODO AUXILIAR PRIVADO ---
    private void hideAll() {
        welcomeView.setVisible(false);
        registrationView.setVisible(false);
        mainMenuView.setVisible(false);
        rateView.setVisible(false);
        exitoView.setVisible(false);
        infoView.setVisible(false);
        loadingView.setVisible(false);
    }
}

/*
 * ==========================================================================
 * GUÍA DE USO EN TU CLASE MAIN
 * ==========================================================================
 * package Main;
 * import Menu.Navigator;
 * * public class Main {
 * public static void main(String[] args) {
 * // 1. Guardar la instancia en una variable
 * Navigator nav = Navigator.getInstance();
 * * // 2. Iniciar el programa mostrando la primera ventana
 * nav.showWelcome();
 * * // Ejemplos de uso posterior:
 * // nav.showLoading();
 * // nav.showMainMenu();
 * }
 * }
 */