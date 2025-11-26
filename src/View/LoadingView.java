package View;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

public class LoadingView extends Frame {

    // Variable privada con la ruta de la imagen
    // Asegúrate de que este archivo exista en tu carpeta de recursos (src/imagenes)
    private String imgFileName = "Image/LoadingViewPrincipalImage.jpg"; 

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

        // --- FILA 2: Espacio para la imagen de carga (AHORA CON IMAGEN REAL) ---
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER; // Centrado
        gbc.fill = GridBagConstraints.BOTH; // Rellenar espacio disponible
        gbc.weightx = 1.0; gbc.weighty = 1.0; // Expandirse vertical y horizontalmente

        // Usamos el Canvas personalizado modificado
        LoadingImagePlaceholder imagePlaceholder = new LoadingImagePlaceholder();
        imagePlaceholder.setPreferredSize(new Dimension(300, 200)); // Tamaño sugerido inicial
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
        GridBagConstraints frameGbc = new GridBagConstraints();
        frameGbc.anchor = GridBagConstraints.CENTER;
        frameGbc.fill = GridBagConstraints.BOTH; // Permitir que el panel interno crezca
        frameGbc.weightx = 1.0;
        frameGbc.weighty = 1.0;
        add(contentPanel, frameGbc);

        // --- Lógica para cerrar la ventana ---
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    // Clase interna MODIFICADA para cargar y dibujar la imagen
    class LoadingImagePlaceholder extends Canvas {
        private Image img;

        public LoadingImagePlaceholder() {
            // Cargar imagen usando la variable privada de la clase externa
            URL imgURL = getClass().getClassLoader().getResource(imgFileName);

            if (imgURL != null) {
                img = Toolkit.getDefaultToolkit().getImage(imgURL);
                
                // Asegurar carga completa antes de pintar
                MediaTracker tracker = new MediaTracker(this);
                tracker.addImage(img, 0);
                try {
                    tracker.waitForAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.err.println("Error: No se encontró la imagen en: " + imgFileName);
            }
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);

            if (img == null) return;

            int canvasWidth = getWidth();
            int canvasHeight = getHeight();
            int imgWidth = img.getWidth(this);
            int imgHeight = img.getHeight(this);

            if (imgWidth <= 0 || imgHeight <= 0) return;

            // --- Lógica de Aspect Fill (Escalar y Recortar) ---
            
            // Calculamos la escala necesaria para cada dimensión
            double scaleX = (double) canvasWidth / imgWidth;
            double scaleY = (double) canvasHeight / imgHeight;

            // Elegimos la escala MAYOR para asegurar que se cubra todo el canvas
            // (esto causará que la otra dimensión se salga de los bordes = recorte)
            double scale = Math.max(scaleX, scaleY);

            // Nuevas dimensiones
            int scaledWidth = (int) Math.ceil(imgWidth * scale);
            int scaledHeight = (int) Math.ceil(imgHeight * scale);

            // Coordenadas para centrar la imagen (pueden ser negativas para recortar)
            int x = (canvasWidth - scaledWidth) / 2;
            int y = (canvasHeight - scaledHeight) / 2;

            Graphics2D g2d = (Graphics2D) g;
            // Suavizado de imagen para mejor calidad
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            
            // Dibujar imagen
            g2d.drawImage(img, x, y, scaledWidth, scaledHeight, this);
        }
    }

    public static void main(String[] args) {
        LoadingView view = new LoadingView();
        view.setVisible(true);
    }
}
