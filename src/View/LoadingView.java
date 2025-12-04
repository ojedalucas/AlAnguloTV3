package view;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

public class LoadingView extends Panel {

    private String imgFileName = "util/images/LoadingViewGif.gif"; 

    public LoadingView() {
        // 1. Configuración básica
        setBackground(Color.WHITE);
        
        // Layout principal
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // --- 1. Título ---
        Label welcomeTitle = new Label("Bienvenido a la Plataforma");
        welcomeTitle.setFont(new Font("Arial", Font.BOLD, 32)); 
        welcomeTitle.setAlignment(Label.CENTER);
        
        gbc.gridx = 0; 
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0; 
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(40, 20, 20, 20); 
        add(welcomeTitle, gbc);

        // --- 2. Imagen Central (Canvas Personalizado) ---
        LoadingImagePlaceholder imagePlaceholder = new LoadingImagePlaceholder();
        imagePlaceholder.setPreferredSize(new Dimension(500, 350)); 
        
        gbc.gridy = 1;
        gbc.weighty = 1.0; 
        gbc.fill = GridBagConstraints.BOTH; 
        gbc.insets = new Insets(0, 40, 0, 40); 
        add(imagePlaceholder, gbc);
        
        // --- 3. Mensaje de Carga ---
        Label loadingMessage = new Label("Un momento por favor...");
        loadingMessage.setFont(new Font("Arial", Font.ITALIC, 18)); 
        loadingMessage.setForeground(Color.DARK_GRAY);
        loadingMessage.setAlignment(Label.CENTER);

        gbc.gridy = 2;
        gbc.weighty = 0.0; 
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(20, 20, 40, 20); 
        add(loadingMessage, gbc);
    }

    // --- Clase interna para manejo de Imagen (Adaptada para GIF) ---
    class LoadingImagePlaceholder extends Canvas {
        private Image img;

        public LoadingImagePlaceholder() {
            URL imgURL = getClass().getClassLoader().getResource(imgFileName);

            if (imgURL != null) {
                // Toolkit maneja GIFs animados nativamente
                img = Toolkit.getDefaultToolkit().getImage(imgURL);
                
                MediaTracker tracker = new MediaTracker(this);
                tracker.addImage(img, 0);
                try {
                    // Esperamos a que cargue la información del archivo (cabecera y primer frame)
                    tracker.waitForAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.err.println("No se encontró el GIF: " + imgFileName);
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

            // Lógica "Aspect Fill" original
            double scaleX = (double) canvasWidth / imgWidth;
            double scaleY = (double) canvasHeight / imgHeight;
            
            // --- CAMBIO AQUÍ ---
            // Multiplicamos por 0.75 para que ocupe el 75% del espacio disponible.
            // Si lo quieres más chico, baja el número (ej. 0.50).
            // Si lo quieres un poco más grande, súbelo (ej. 0.85).
            double scale = Math.min(scaleX, scaleY) * 0.75; 

            int scaledWidth = (int) (imgWidth * scale);
            int scaledHeight = (int) (imgHeight * scale);

            // El cálculo de X e Y sigue funcionando perfecto para centrar la imagen reducida
            int x = (canvasWidth - scaledWidth) / 2;
            int y = (canvasHeight - scaledHeight) / 2;

            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            
            g2d.drawImage(img, x, y, scaledWidth, scaledHeight, this);
        }
    }

    public static void main(String[] args) {
        // Adapté el main para crear un Frame, de lo contrario un Panel solo no se ve.
        Frame frame = new Frame("Vista de Carga");
        LoadingView view = new LoadingView();
        
        frame.add(view);
        frame.setSize(800, 600); // Tamaño de ejemplo para ver el layout
        frame.setLocationRelativeTo(null); // Centrar en pantalla
        
        // Permite cerrar la ventana
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        
        frame.setVisible(true);
    }
}
