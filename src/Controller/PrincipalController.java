package controller;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import model.domain.Pelicula;
import model.logic.*;
import service.ConsultaPeliculasOMDb;
import util.SesionActual;
import util.comparator.ComparatorGenero;
import util.comparator.ComparatorTitulo;
import view.*;

public class PrincipalController {
    private PrincipalView ventana;
    private PrincipalModel modelo;
    private MainMenuView panelPrincipal;
    private ArrayList<Button> botones;
    private boolean tituloAscendente = true;
    private boolean generoAscendente = true;

    public PrincipalController(PrincipalView ventana, PrincipalModel modelo){
        this.ventana = ventana;
        this.modelo = modelo;
        this.panelPrincipal = this.ventana.getMainMenuView();
        panelPrincipal.setNombreUsuario(SesionActual.getUsuarioActual().getNombreUsuario());
        panelPrincipal.addCerrarSesionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logicaCerrarSesion();
            }
        });
        panelPrincipal.addBuscarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logicaBuscar();
            }
        });
        botones = panelPrincipal.getListaBotonesCabecera();
        botones.get(1).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    logicaOrdenarTitulo();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        botones.get(2).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                logicaOrdenarGenero();
                } catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });

        ventana.mostrarPantallaDeCarga();
        Thread loader = new Thread(new Runnable() {
            @Override
            public void run(){
                try{
                    if (SesionActual.getPrimerVisita()){
                        modelo.cargarPeliculas();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
                java.awt.EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run(){
                        try {
                            ventana.mostrarContenidoPrincipal();
                            modelo.seleccionarPeliculas();
                            mostrarPeliculas();
                            botonesCalificar();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
        loader.start();
    }

    private static String recortarTexto(String texto, int maxChars) {
        if (texto == null) return "";
        if (texto.length() <= maxChars) return texto;
        return texto.substring(0, maxChars - 3) + "..."; // agrega "..." al final
    }

    private void mostrarPeliculas() throws SQLException {
        int numFilas = Math.min(10, SesionActual.getPeliculasActuales().size());
        Object[][] data = new Object[numFilas][6];
        for (int i = 0; i < numFilas; i++) {
            Pelicula p = SesionActual.getPeliculaPos(i);
            data[i][0] = p.getPoster();
            data[i][1] = recortarTexto(p.getTitulo(), 25);
            data[i][2] = recortarTexto(p.getGenero().toString(), 20);
            data[i][3] = recortarTexto(p.getResumen(), 50);
            data[i][4] = !modelo.existeResenia(SesionActual.getUsuarioActual().getIdUsuario(), p.getId());
            data[i][5] = p.getId();
        }
        panelPrincipal.actualizarListaPeliculas(data);
        botonesCalificar();
    }

    private void botonesCalificar(){
        ArrayList<Button> botones = panelPrincipal.getListaBotonesCalificar();
        for (int i=0; i< botones.size(); i++){
            Button boton = botones.get(i);
            for (ActionListener al : boton.getActionListeners()) {
                boton.removeActionListener(al);
            }
            final int index = i;
            botones.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    logicaCalificar(index);
                }
            });
        }
    }

    private void logicaCalificar(int i){
        RateView rateV = new RateView();
        RateModel rateM = new RateModel(SesionActual.getPeliculaPos(i));
        new RateController(rateV, rateM);
        rateV.setVisible(true);
        this.ventana.dispose();
    }

    private void logicaCerrarSesion(){
        SesionActual.cerrarSesion();
        WelcomeView welV = new WelcomeView();
        WelcomeModel welM = new WelcomeModel();
        new WelcomeController(welV, welM);
        welV.setVisible(true);
        this.ventana.dispose();
    }

    private void logicaBuscar(){
        Pelicula p = ConsultaPeliculasOMDb.consultarPelicula(panelPrincipal.getTxtBuscador());
        InfoView infoV = new InfoView();
        InfoModel infoM = new InfoModel(p);
        new InfoController(infoV, infoM);
        infoV.setVisible(true);
        ventana.dispose();
    }

    private void logicaOrdenarTitulo() throws SQLException {
        Collections.sort(SesionActual.getPeliculasActuales(), new ComparatorTitulo());
        if (!tituloAscendente)
            Collections.reverse(SesionActual.getPeliculasActuales());
        tituloAscendente = !tituloAscendente;
        mostrarPeliculas();
    }

    private void logicaOrdenarGenero() throws SQLException {
        Collections.sort(SesionActual.getPeliculasActuales(), new ComparatorGenero());
        if (!generoAscendente)
            Collections.reverse(SesionActual.getPeliculasActuales());
        generoAscendente = !generoAscendente;
        mostrarPeliculas();
    }

}
