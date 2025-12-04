package view;

import java.awt.*;
import java.awt.event.*;

public class RateView extends Frame {

    // Componentes
    private StarPanel[] estrellas; 
    private int rating = 0; 
    private TextArea cajaComentario;
    private Button guardar;
    private Label titleLabel;
    private Label lblError;

    public RateView(){
        super("Plataforma de Streaming - Calificar Película");
        setSize(700, 600);
        setBackground(Color.WHITE);
        setLocationRelativeTo(null);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 30, 10, 30);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // --- 1. Título ---
        titleLabel = new Label("Título de la Película");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        gbc.gridx = 0; 
        gbc.gridy = 0; 
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(20, 30, 20, 30); 
        add(titleLabel, gbc);

        // --- 2. Sección de Calificación ---
        Label calificarLabel = new Label("Tu Calificación:");
        calificarLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 30, 5, 30);
        add(calificarLabel, gbc);

        // Panel contenedor de las estrellas
        Panel estrellasPanel = new Panel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        estrellas = new StarPanel[5];
        
        for (int i = 0; i < 5; i++) {
            estrellas[i] = new StarPanel(i + 1);
            estrellasPanel.add(estrellas[i]);
        }
        
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 25, 20, 30);
        add(estrellasPanel, gbc);

        updateStars();

        // --- 3. Comentarios ---
        Label comentarioLabel = new Label("Tu Opinión:");
        comentarioLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 30, 5, 30);
        add(comentarioLabel, gbc);

        cajaComentario = new TextArea("", 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
        cajaComentario.setFont(new Font("Arial", Font.PLAIN, 16));
        cajaComentario.setBackground(new Color(250, 250, 250));
        
        gbc.gridy = 4;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 30, 10, 30);
        add(cajaComentario, gbc);

        // --- NUEVO: 4. Label de Error ---
        lblError = new Label(""); 
        lblError.setForeground(Color.RED);
        lblError.setFont(new Font("Arial", Font.BOLD, 12));
        lblError.setAlignment(Label.CENTER);
        lblError.setVisible(false);
        
        gbc.gridy = 5;
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 30, 5, 30); 
        add(lblError, gbc);

        // --- 5. Botón Guardar ---
        guardar = new Button("Guardar");
        guardar.setBackground(new Color(30, 144, 255));
        guardar.setForeground(Color.WHITE);
        guardar.setFont(new Font("Arial", Font.BOLD, 16));
        guardar.setPreferredSize(new Dimension(160, 40));

        gbc.gridy = 6;
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 30, 30, 30);
        add(guardar, gbc);
    }

    private void setRating(int value) {
        rating = value; 
        updateStars(); 
    }

    private void updateStars() {
        if (estrellas == null) return;
        for (int i = 0; i < estrellas.length; i++) {
            boolean llena = (i < rating);
            estrellas[i].setFilled(llena);
        }
    }

    public void setTitleText(String title) {
        if (titleLabel != null) titleLabel.setText(title);
    }

    public void showErrorMessage(String mensaje) {
        lblError.setText(mensaje);
        lblError.revalidate(); 
    }
    public int getRating() { return rating; }
    public void addGuardarListener(ActionListener listener){
        guardar.addActionListener(listener);
    }
    public String getComentario() { return cajaComentario.getText(); }

    // --- CLASE INTERNA PARA DIBUJAR LA ESTRELLA ---
    class StarPanel extends Panel {
        private boolean filled;
        private int id;
        private final int[] xPoints = {25, 31, 47, 36, 40, 25, 10, 14, 3, 19};
        private final int[] yPoints = {5, 18, 18, 29, 45, 35, 45, 29, 18, 18};

        public StarPanel(int id) {
            this.id = id;
            this.setLayout(null); 
            this.setBackground(Color.WHITE);
            this.setCursor(new Cursor(Cursor.HAND_CURSOR));
            
            this.setPreferredSize(new Dimension(50, 50));
            this.setSize(50, 50);

            addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    RateView.this.setRating(StarPanel.this.id);
                }
            });
        }

        public void setFilled(boolean filled) {
            this.filled = filled;
            repaint(); 
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g); 
            
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            if (filled) {
                g2.setColor(new Color(255, 200, 0)); 
                g2.fillPolygon(xPoints, yPoints, 10);
                
                g2.setColor(new Color(200, 150, 0));
                g2.drawPolygon(xPoints, yPoints, 10);
            } else {
                g2.setColor(Color.LIGHT_GRAY); 
                g2.setStroke(new BasicStroke(2)); 
                g2.drawPolygon(xPoints, yPoints, 10);
            }
        }
    }
}