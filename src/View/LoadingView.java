package View;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

public class LoadingView extends Frame {

    // Ruta de la imagen
    private String imgFileName = "util/image/LoadingViewPrincipalImage.jpg"; 

    public LoadingView() {
        // 1. Configuración básica
        super("Plataforma de Streaming - Bienvenida");
        setSize(800, 600);
        setBackground(Color.WHITE);
        setLocationRelativeTo(null);
        
        // Layout principal
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // --- Lógica para cerrar ---
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        // --- 1. Título ---
        Label welcomeTitle = new Label("Bienvenido a la Plataforma");
        welcomeTitle.setFont(new Font("Arial", Font.BOLD, 32)); // Fuente grande estilo InfoView
        welcomeTitle.setAlignment(Label.CENTER);
        
        gbc.gridx = 0; 
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0; // No estirar verticalmente
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(40, 20, 20, 20); // Margen superior amplio
        add(welcomeTitle, gbc);

        // --- 2. Imagen Central (Canvas Personalizado) ---
        LoadingImagePlaceholder imagePlaceholder = new LoadingImagePlaceholder();
        // Le damos un tamaño preferido para que GridBagLayout sepa qué hacer al inicio
        imagePlaceholder.setPreferredSize(new Dimension(500, 350)); 
        
        gbc.gridy = 1;
        gbc.weighty = 1.0; // Este componente ocupa todo el espacio vertical sobrante
        gbc.fill = GridBagConstraints.BOTH; // Se estira en ambas direcciones
        gbc.insets = new Insets(0, 40, 0, 40); // Márgenes laterales para encuadrar la imagen
        add(imagePlaceholder, gbc);
        
        // --- 3. Mensaje de Carga ---
        Label loadingMessage = new Label("Un momento por favor...");
        // Estilo sutil: Gris oscuro y cursiva para indicar proceso
        loadingMessage.setFont(new Font("Arial", Font.ITALIC, 18)); 
        loadingMessage.setForeground(Color.DARK_GRAY);
        loadingMessage.setAlignment(Label.CENTER);

        gbc.gridy = 2;
        gbc.weighty = 0.0; // No estirar
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(20, 20, 40, 20); // Margen inferior amplio
        add(loadingMessage, gbc);
    }

    // --- Clase interna para manejo de Imagen (Mantenemos tu lógica de Aspect Fill) ---
    class LoadingImagePlaceholder extends Canvas {
        private Image img;

        public LoadingImagePlaceholder() {
            URL imgURL = getClass().getClassLoader().getResource(imgFileName);

            if (imgURL != null) {
                img = Toolkit.getDefaultToolkit().getImage(imgURL);
                MediaTracker tracker = new MediaTracker(this);
                tracker.addImage(img, 0);
                try {
                    tracker.waitForAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.err.println("No se encontró la imagen: " + imgFileName);
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

            // Lógica "Aspect Fill" (Recorta para llenar)
            double scaleX = (double) canvasWidth / imgWidth;
            double scaleY = (double) canvasHeight / imgHeight;
            
            // Usamos Math.min para "Fit" (ver toda la imagen) o Math.max para "Fill" (llenar el cuadro)
            // Dado que es una pantalla de carga, "Fit" (Math.min) suele ser más seguro para que no se recorte el logo/arte,
            // pero si prefieres que llene todo el cuadro sin bordes blancos, usa Math.max.
            // Voy a usar Math.min para que la imagen se vea completa y centrada estéticamente.
            double scale = Math.min(scaleX, scaleY); 

            int scaledWidth = (int) (imgWidth * scale);
            int scaledHeight = (int) (imgHeight * scale);

            int x = (canvasWidth - scaledWidth) / 2;
            int y = (canvasHeight - scaledHeight) / 2;

            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            
            g2d.drawImage(img, x, y, scaledWidth, scaledHeight, this);
        }
    }

    public static void main(String[] args) {
        LoadingView view = new LoadingView();
        view.setVisible(true);
    }
}
