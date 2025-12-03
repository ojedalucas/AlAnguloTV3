package Controller;

import Model.Logic.PrincipalModel;
import Util.UserSession;
import View.MainMenuView;
import View.PrincipalView;

@SuppressWarnings("unused")
public class PrincipalController {
    private PrincipalView ventana;
    private PrincipalModel modelo;
    private MainMenuView panelPrincipal;

    public PrincipalController(PrincipalView ventana, PrincipalModel modelo){
        this.ventana = ventana;
        this.modelo = modelo;
        this.panelPrincipal = this.ventana.getMainMenuView();
        panelPrincipal.setNombreUsuario(UserSession.getUsuarioActual().getNombreUsuario());
        ventana.mostrarPantallaDeCarga();
        Thread loader = new Thread(() -> {
            try{
                modelo.cargarPeliculas();
            } catch (Exception e){
                e.printStackTrace();
            }
            java.awt.EventQueue.invokeLater(() -> {
                ventana.mostrarContenidoPrincipal();
                Object[][] data = modelo.seleccionarPeliculas();
                panelPrincipal.actualizarListaPeliculas(data);
            });
        });
        loader.start();
    }
}
