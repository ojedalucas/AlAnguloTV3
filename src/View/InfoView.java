package View;

import java.awt.*;
import java.awt.event.*;

public class InfoView extends Frame{
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

    }

    public static void main(String[] args) {
        InfoView view = new InfoView();
        view.setVisible(true);
    }

}
