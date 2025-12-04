package view;

import java.awt.*;
import java.awt.event.*;

public class PrincipalView extends Frame {

    private Panel mainPanel;
    private CardLayout layout;
    private LoadingView loadingView;
    private MainMenuView mainMenuView;
    public final String CARD_LOADING = "loading";
    public final String CARD_MAIN = "main";

    public PrincipalView() {
        addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
        });
        layout = new CardLayout();
        mainPanel = new Panel(layout);
        loadingView = new LoadingView();
        mainMenuView = new MainMenuView();

        mainPanel.add(loadingView, CARD_LOADING);
        mainPanel.add(mainMenuView, CARD_MAIN);

        add(mainPanel);
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void mostrarPantallaDeCarga() {
        layout.show(mainPanel, CARD_LOADING);
    }

    public void mostrarContenidoPrincipal() {
        layout.show(mainPanel, CARD_MAIN);
    }

    public LoadingView getLoadingView() {
        return loadingView;
    }

    public MainMenuView getMainMenuView() {
        return mainMenuView;
    }
}
