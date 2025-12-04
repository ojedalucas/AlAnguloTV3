package view;

import java.awt.*;
import java.net.URL;

public class LoadingView extends Panel {

    private String imgFileName = "util/images/LoadingViewGif.gif"; 

    public LoadingView() {
        // 1. Configuración básica
        setBackground(Color.WHITE);
        
        // Layout principal
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        // --- Título ---
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

        // --- Imagen ---
        LoadingImagePlaceholder imagePlaceholder = new LoadingImagePlaceholder();
        imagePlaceholder.setPreferredSize(new Dimension(500, 350)); 
        
        gbc.gridy = 1;
        gbc.weighty = 1.0; 
        gbc.fill = GridBagConstraints.BOTH; 
        gbc.insets = new Insets(0, 40, 0, 40); 
        add(imagePlaceholder, gbc);
        
        // --- Mensaje de Carga ---
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

    // --- Clase interna para manejo de GIF ---
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

            double scaleX = (double) canvasWidth / imgWidth;
            double scaleY = (double) canvasHeight / imgHeight;
            double scale = Math.min(scaleX, scaleY) * 0.75; 
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
}
