package View;

import java.awt.*;
import java.awt.event.*;

public class InfoView extends Frame{
    // Botón continuar en la parte inferior
    private Button btnContinuar;

    public InfoView() {
        // Configuro ventana
        super("Plataforma de Streaming  -   Información");
        setSize(700, 450);
        setBackground(Color.WHITE);
        setLocationRelativeTo(null);

        // Lógica para cerrar la ventana
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        // Layout de la ventana
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20,20,20,20);
        gbc.anchor = GridBagConstraints.NORTHWEST;

        // Titulo
        Label titleLabel = new Label("Titulo de la Pelicula");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.weightx = 1;
        add(titleLabel, gbc);

        // Año
        Label anoLabel = new Label("Año:");
        anoLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridy = 1;
        add(anoLabel, gbc);

        // Resumen
        Label resumenLabel = new Label("Resumen:");
        resumenLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridy = 2;
        gbc.weighty = 1;
        add(resumenLabel, gbc);

        // --- Botón "Continuar" centrado en la parte inferior ---
        btnContinuar = new Button("Continuar");
        btnContinuar.setBackground(new Color(30, 144, 255)); // gris claro
        btnContinuar.setForeground(Color.WHITE);
        btnContinuar.setFont(new Font("Arial", Font.BOLD, 16));
        btnContinuar.setPreferredSize(new Dimension(160, 40));

        gbc.gridy = 3;
        gbc.weighty = 0; // no expandir
        gbc.anchor = GridBagConstraints.PAGE_END;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        add(btnContinuar, gbc);


    }

    // Método público para obtener el botón "Continuar"
    public Button getContinuarButton() {
        return btnContinuar;
    }

    public static void main(String[] args) {
        InfoView view = new InfoView();
        view.setVisible(true);
    }

}
