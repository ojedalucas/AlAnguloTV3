package View;

import java.awt.*;
import java.awt.event.*;

public class RateView extends Frame{

    public RateView(){
        // Configuro ventana
        super("Plataforma de Streaming - Calificar Película");
        setSize(800, 500);
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
        add(titleLabel, gbc);

        // Calificar
        Label calificarLabel = new Label("Calificacion: ");
        calificarLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(calificarLabel, gbc);

        // Estrellas
        Panel estrellasPanel = new Panel(new FlowLayout());
        Font starFont = new Font("Symbol", Font.PLAIN, 20);
        for (int i=0; i<10; i++){
            Button estrella = new Button("\u2606");
            estrella.setFont(starFont);
            estrella.setForeground(new Color(30, 144, 255));
            estrellasPanel.add(estrella);
        }
        gbc.gridx = 1;
        add(estrellasPanel, gbc);

        // Comentario
        Label comentarioLabel = new Label("Comentario: ");
        comentarioLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.weighty = 1;
        gbc.gridx= 0;
        gbc.gridy = 2;
        add(comentarioLabel, gbc);

        // Caja de comentario
        TextArea cajaComentario = new TextArea();
        gbc.gridx = 1;
        gbc.weightx = 1;
        add(cajaComentario, gbc);

        // Botón
        Button guardar = new Button("Guardar");
        guardar.setBackground(new Color(30, 144, 255));
        guardar.setForeground(Color.WHITE);
        Font guardarFont = new Font("Arial", Font.BOLD, 16);
        guardar.setFont(guardarFont);
        gbc.gridy = 3;
        add(guardar, gbc);
    }


    public static void main(String[] args) {
        RateView view = new RateView();
        view.setVisible(true);
    }
}
