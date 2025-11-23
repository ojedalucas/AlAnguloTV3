package View;

import java.awt.*;
import java.awt.event.*;

public class RateView extends Frame{

    // Estrellas y calificación seleccionada
    private Button[] estrellas;
    private int rating = 0; // 0..5
    private TextArea cajaComentario;
    private Button guardar;

    public RateView(){
        // Configuro ventana
        super("Plataforma de Streaming - Calificar Película");
        setSize(1000, 560);
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
        gbc.insets = new Insets(18,28,18,28);

        // --- Título grande arriba a la izquierda ---
        Label titleLabel = new Label("Titulo de la Pelicula");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.WEST;
        add(titleLabel, gbc);

        // --- Etiqueta "Calificación:" a la izquierda ---
        Label calificarLabel = new Label("Calificación:");
        calificarLabel.setFont(new Font("Arial", Font.PLAIN, 26));
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1; gbc.anchor = GridBagConstraints.WEST; gbc.weightx = 0;
        add(calificarLabel, gbc);

        // --- Panel de estrellas (alineado con caja de comentario, en columna 1) ---
        Panel estrellasPanel = new Panel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        estrellas = new Button[5];
        Font starFont = new Font("Dialog", Font.PLAIN, 34);
        for (int i = 0; i < 5; i++) {
            final int idx = i + 1;
            Button estrella = new Button("\u2606");
            estrella.setFont(starFont);
            estrella.setForeground(new Color(30, 144, 255)); // azul para outline
            estrella.setBackground(Color.WHITE);
            estrella.setPreferredSize(new Dimension(48, 48));
            estrella.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    setRating(idx);
                }
            });
            estrellas[i] = estrella;
            estrellasPanel.add(estrella);
        }
        gbc.gridx = 1; gbc.gridy = 1; gbc.anchor = GridBagConstraints.WEST; gbc.fill = GridBagConstraints.NONE; gbc.weightx = 1;
        add(estrellasPanel, gbc);

        // Inicializar visualmente
        updateStars();

        // --- Comentario (label y caja) ---
        Label comentarioLabel = new Label("Comentario:");
        comentarioLabel.setFont(new Font("Arial", Font.PLAIN, 26));
        gbc.gridx = 0; gbc.gridy = 2; gbc.anchor = GridBagConstraints.NORTHWEST; gbc.weighty = 0.0;
        add(comentarioLabel, gbc);

        cajaComentario = new TextArea();
        cajaComentario.setFont(new Font("Arial", Font.PLAIN, 16));
        cajaComentario.setPreferredSize(new Dimension(520, 160));
        gbc.gridx = 1; gbc.gridy = 2; gbc.weightx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        add(cajaComentario, gbc);

        // --- Botón Guardar centrado abajo ---
        guardar = new Button("Guardar");
        guardar.setBackground(new Color(30, 144, 255));
        guardar.setForeground(Color.WHITE);
        guardar.setFont(new Font("Arial", Font.BOLD, 18));
        guardar.setPreferredSize(new Dimension(160, 48));
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER; gbc.fill = GridBagConstraints.NONE; gbc.weighty = 1.0;
        add(guardar, gbc);
    }

    private void setRating(int value) {
        if (value < 0) value = 0;
        if (value > 5) value = 5;
        rating = value;
        updateStars();
    }

    private void updateStars() {
        if (estrellas == null) return;
        for (int i = 0; i < estrellas.length; i++) {
            if (i < rating) {
                estrellas[i].setLabel("\u2605"); // filled star
                estrellas[i].setForeground(new Color(255, 200, 0)); // gold-ish
            } else {
                estrellas[i].setLabel("\u2606"); // hollow star
                estrellas[i].setForeground(new Color(192, 192, 192)); // grey
            }
        }
    }

    public int getRating() {
        return rating;
    }

    public Button getGuardarButton() {
        return guardar;
    }

    public String getComentario() {
        return cajaComentario.getText();
    }

    public static void main(String[] args) {
        RateView view = new RateView();
        view.setVisible(true);
    }
}
