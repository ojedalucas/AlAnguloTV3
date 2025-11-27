package View;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ExitoView extends Frame {
    // Componentes reutilizables
    private Label texto1;
    private Label texto2;
    private Button continuar;

    public ExitoView(){
        // Configuro ventana
        super("Información");
        // Hice la ventana un poco más ancha para que el título entre cómodamente
        setSize(450, 280); 
        setBackground(Color.WHITE);
        setLocationRelativeTo(null);
        setResizable(false); // Recomendado para diálogos de éxito

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
        gbc.insets = new Insets(10, 20, 10, 20); // Márgenes unificados

        // Texto 1 (Ahora actúa como Título Principal)
        texto1 = new Label("¡Calificación registrada!");
        // Aumenté el tamaño y puse Negrita para dar jerarquía
        texto1.setFont(new Font("Arial", Font.BOLD, 22)); 
        texto1.setAlignment(Label.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(texto1, gbc);

        // Texto 2 (Subtítulo / Detalle)
        texto2 = new Label("Muchas gracias por su opinión.");
        texto2.setFont(new Font("Arial", Font.PLAIN, 16));
        texto2.setForeground(Color.DARK_GRAY); // Un gris oscuro para diferenciar del título
        texto2.setAlignment(Label.CENTER);
        gbc.gridy = 1;
        add(texto2, gbc);


        // Botón
        continuar = new Button("Continuar");
        continuar.setBackground(new Color(30, 144, 255));
        continuar.setForeground(Color.WHITE);
        continuar.setFont(new Font("Arial", Font.BOLD, 16));
        // Tamaño fijo para que coincida con InfoView
        continuar.setPreferredSize(new Dimension(160, 40)); 
        
        gbc.insets = new Insets(30, 20, 20, 20); // Más espacio arriba del botón
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE; // No estirar el botón
        gbc.anchor = GridBagConstraints.CENTER;
        add(continuar, gbc);
    }

    // Métodos públicos
    public Button getContinuarButton() {
        return continuar;
    }

    public static void main(String[] args) {
        ExitoView view = new ExitoView();
        view.setVisible(true);
    }
}
