package view;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;

public class ExitoView extends Frame {
    private Label texto1;
    private Label texto2;
    private Button continuar;

    public ExitoView(){
        // Configuro ventana
        super("Información");
        setSize(450, 280); 
        setBackground(Color.WHITE);
        setLocationRelativeTo(null);
        setResizable(false);

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
        gbc.insets = new Insets(10, 20, 10, 20);

        // Texto 1 (Título Principal)
        texto1 = new Label("¡Calificación registrada!");
        texto1.setFont(new Font("Arial", Font.BOLD, 22)); 
        texto1.setAlignment(Label.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(texto1, gbc);

        // Texto 2 (Subtítulo)
        texto2 = new Label("Muchas gracias por su opinión.");
        texto2.setFont(new Font("Arial", Font.PLAIN, 16));
        texto2.setForeground(Color.DARK_GRAY);
        texto2.setAlignment(Label.CENTER);
        gbc.gridy = 1;
        add(texto2, gbc);

        // Botón
        continuar = new Button("Continuar");
        continuar.setBackground(new Color(30, 144, 255));
        continuar.setForeground(Color.WHITE);
        continuar.setFont(new Font("Arial", Font.BOLD, 16));
        continuar.setPreferredSize(new Dimension(160, 40)); 
        gbc.insets = new Insets(30, 20, 20, 20);
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        add(continuar, gbc);
    }

    public void addContinuarListener(ActionListener listener){
        continuar.addActionListener(listener);
    }
}
