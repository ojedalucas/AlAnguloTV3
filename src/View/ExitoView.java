package View;

import java.awt.*;
import java.awt.event.*;

public class ExitoView extends Frame {
    public ExitoView(){
        // Configuro ventana
        super("Información");
        setSize(400, 250);
        setBackground(Color.WHITE);
        setLocationRelativeTo(null);

        // Lógica para cerrar la ventana
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        // Layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 20, 0, 20);

        // Texto
        Label texto1 = new Label("Se registró correctamente su calificación.");
        texto1.setFont(new Font("Arial", Font.PLAIN, 16));
        texto1.setAlignment(Label.CENTER);
        add(texto1, gbc);
        Label texto2 = new Label("Muchas gracias.");
        texto2.setFont(new Font("Arial", Font.PLAIN, 16));
        texto2.setAlignment(Label.CENTER);
        gbc.gridy = 1;
        add(texto2, gbc);


        // Botón
        Button continuar = new Button("Continuar");
        continuar.setBackground(new Color(30, 144, 255));
        continuar.setForeground(Color.WHITE);
        Font continuarFont = new Font("Arial", Font.BOLD, 16);
        continuar.setFont(continuarFont);
        gbc.insets = new Insets(20,20,20,20);
        gbc.gridy = 2;
        add(continuar, gbc);
    }

    public static void main(String[] args) {
        ExitoView view = new ExitoView();
        view.setVisible(true);
    }
}
