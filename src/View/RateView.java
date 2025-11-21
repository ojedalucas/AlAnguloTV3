package View;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//import javax.swing.border.Border;

public class RateView extends Frame {

    public RateView() {
        // 1. Configuración básica de la ventana
        super("Plataforma de Streaming - Calificar Película");
        setSize(1000, 600);
        setBackground(Color.WHITE);

        // Centrar ventana en pantalla
        setLocationRelativeTo(null);

        // Usamos BorderLayout
        setLayout(new BorderLayout());

        // --- PANEL---
        // Creamos un panel interno para agrupar los campos
        Panel formPanel = new Panel();
        formPanel.setBackground(Color.WHITE);
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;        // alineado a la izquierda
        gbc.insets = new Insets(20, 20, 20, 20);      // margen interior

        // Fuente para las etiquetas
        Font labelFont = new Font("Arial", Font.BOLD, 24);
        // Fuente para los campos de texto
        Font textFont = new Font("Arial", Font.PLAIN, 20);

        // ================= TÍTULO =================
        Label titleLbl = new Label("Título de la Película");
        titleLbl.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(titleLbl,gbc);

        // ================= CALIFICACIÓN =================
        Label ratingLbl = new Label("Calificación:");
        ratingLbl.setFont(textFont);

        // Panel para las estrellas
        Panel starsPanel = new Panel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        Font starFont = new Font("Arial Unicode MS", Font.PLAIN, 20);

        // Crear 10 estrellas (por ahora solo dibujadas)
        for (int i = 0; i < 10; i++) {
            Label star = new Label("\u2606");   // Estrella vacía
            star.setFont(starFont);
            starsPanel.add(star);
        }

        // Panel combinado: label + estrellas
        Panel ratingRow = new Panel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        ratingRow.setBackground(Color.WHITE);
        ratingRow.add(ratingLbl);
        ratingRow.add(starsPanel);

        // Esto coloca todo junto en una sola celda
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        formPanel.add(ratingRow, gbc);


        // === WRAPPER para alinear izquierda ===
        Panel wrapper = new Panel(new BorderLayout());
        wrapper.setBackground(Color.WHITE);
        wrapper.add(formPanel, BorderLayout.WEST);

        // Añadir panel principal
        add(wrapper, BorderLayout.NORTH);

        // --- Lógica para cerrar la ventana ---
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        RateView view = new RateView();
        view.setVisible(true);
    }
}
