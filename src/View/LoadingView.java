package View;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoadingView extends Frame {

    public LoadingView() {
        // 1. Configuración básica de la ventana
        super("Plataforma de Streaming - Bienvenida");
        setSize(800, 600); // Un tamaño considerable
        setBackground(Color.WHITE);
        
        // Usamos GridBagLayout para centrar los componentes vertical y horizontalmente
        setLayout(new GridBagLayout());
        
        // Centrar ventana en pantalla
        setLocationRelativeTo(null);

        // --- PANEL PRINCIPAL DE CONTENIDO ---
        Panel contentPanel = new Panel();
        contentPanel.setLayout(new GridBagLayout());
        contentPanel.setBackground(Color.WHITE);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15); // Márgenes para los componentes

        // --- FILA 1: Título "Bienvenido a la Plataforma de Streaming" ---
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 1; // Ocupa una columna
        gbc.anchor = GridBagConstraints.WEST; // Alineado a la izquierda
        Label welcomeTitle = new Label("Bienvenido a la Plataforma de Streaming");
        welcomeTitle.setFont(new Font("Arial", Font.BOLD, 22)); // Más grande y en negrita
        contentPanel.add(welcomeTitle, gbc);

        // --- FILA 2: Espacio para la imagen de carga ---
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER; // Centrado
        gbc.fill = GridBagConstraints.BOTH; // Rellenar espacio disponible
        gbc.weightx = 1.0; gbc.weighty = 1.0; // Expandirse vertical y horizontalmente

        // Usamos un Canvas personalizado para el placeholder de la imagen
        LoadingImagePlaceholder imagePlaceholder = new LoadingImagePlaceholder();
        imagePlaceholder.setPreferredSize(new Dimension(300, 200)); // Tamaño sugerido
        contentPanel.add(imagePlaceholder, gbc);
        
        // --- FILA 3: Mensaje "Un momento por favor....." ---
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER; // Centrado
        gbc.fill = GridBagConstraints.NONE; // No estirar
        gbc.weightx = 0; gbc.weighty = 0; // No expandirse
        Label loadingMessage = new Label("Un momento por favor.....");
        loadingMessage.setFont(new Font("Arial", Font.PLAIN, 18));
        contentPanel.add(loadingMessage, gbc);

        // Agregar el panel de contenido a la ventana principal
        // Aquí usamos otro GridBagLayout para centrar el contentPanel en el Frame
        GridBagConstraints frameGbc = new GridBagConstraints();
        frameGbc.anchor = GridBagConstraints.CENTER;
        add(contentPanel, frameGbc);

        // --- Lógica para cerrar la ventana ---
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    // Clase interna para dibujar el placeholder de la imagen de carga
    class LoadingImagePlaceholder extends Canvas {
        public void paint(Graphics g) {
            super.paint(g); // Llama al método paint de la superclase

            int width = getWidth();
            int height = getHeight();

            // Dibujar el recuadro gris y el borde
            g.setColor(Color.LIGHT_GRAY);
            g.drawRect(0, 0, width - 1, height - 1);

            // Texto "Loading image"
            g.setColor(Color.DARK_GRAY);
            g.setFont(new Font("Arial", Font.ITALIC, 14));
            String loadingText = "\"Loading image\"";
            FontMetrics fm = g.getFontMetrics();
            int xLoading = (width - fm.stringWidth(loadingText)) / 2;
            int yLoading = fm.getHeight() + 10; // Un poco abajo del top
            g.drawString(loadingText, xLoading, yLoading);

            // Recuadro amarillo para el texto de sugerencia
            g.setColor(new Color(255, 255, 204)); // Amarillo claro
            int yellowBoxWidth = (int)(width * 0.7);
            int yellowBoxHeight = (int)(height * 0.4);
            int yellowBoxX = (width - yellowBoxWidth) / 2;
            int yellowBoxY = (height / 2) - (yellowBoxHeight / 2) + 20; // Centrado verticalmente
            g.fillRect(yellowBoxX, yellowBoxY, yellowBoxWidth, yellowBoxHeight);
            g.setColor(Color.GRAY);
            g.drawRect(yellowBoxX, yellowBoxY, yellowBoxWidth, yellowBoxHeight);

            // Texto de sugerencia dentro del recuadro amarillo
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.PLAIN, 12));
            String suggestion1 = "Elija alguna imagen para";
            String suggestion2 = "mostrar mientras se";
            String suggestion3 = "realiza el proceso en";
            String suggestion4 = "segundo plano";

            int textY = yellowBoxY + fm.getHeight() + 5;
            g.drawString(suggestion1, (width - fm.stringWidth(suggestion1)) / 2, textY);
            g.drawString(suggestion2, (width - fm.stringWidth(suggestion2)) / 2, textY + fm.getHeight());
            g.drawString(suggestion3, (width - fm.stringWidth(suggestion3)) / 2, textY + 2 * fm.getHeight());
            g.drawString(suggestion4, (width - fm.stringWidth(suggestion4)) / 2, textY + 3 * fm.getHeight());

            // Rectángulo rojo pequeño
            g.setColor(Color.RED);
            int redRectSize = 20;
            g.fillRect(yellowBoxX + (yellowBoxWidth / 2) - (redRectSize / 2), yellowBoxY + 5, redRectSize, redRectSize);
        }
    }

    public static void main(String[] args) {
        LoadingView view = new LoadingView();
        view.setVisible(true);
    }
}
