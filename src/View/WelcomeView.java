package View;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class WelcomeView extends Frame {
    // Campos reutilizables
    private TextField txtEmail;
    private TextField txtPass;
    private Button btnLogin;
    private Button btnRegister;
    private Label lblError;
    private String imgFileName = "Image/WelcomeViewIPrincipalImage.jpg"; 

    public WelcomeView() {
        super("Plataforma de Streaming");
        setSize(800, 500); 
        setLayout(new BorderLayout());
        setBackground(Color.WHITE); 
        setLocationRelativeTo(null);

        // --- HEADER ---
        Panel headerPanel = new Panel(new FlowLayout(FlowLayout.LEFT));
        headerPanel.setBackground(Color.WHITE);
        Label titleLabel = new Label("Bienvenido a la Plataforma de Streaming");
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        titleLabel.setForeground(Color.GRAY);
        headerPanel.add(titleLabel);
        
        add(headerPanel, BorderLayout.NORTH);

        // --- PANEL CENTRAL ---
        Panel mainPanel = new Panel(new GridLayout(1, 2)); 
        
        // 1. IZQUIERDA: Imagen
        ImagePlaceholder imagePanel = new ImagePlaceholder();
        mainPanel.add(imagePanel);

        // 2. DERECHA: Formulario
        Panel formPanel = new Panel();
        formPanel.setLayout(new GridBagLayout()); 
        formPanel.setBackground(Color.WHITE);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); 
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // -- Componentes --
        Label lblEmail = new Label("E-mail");
        lblEmail.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0; gbc.gridy = 0; 
        gbc.weightx = 0.3; 
        formPanel.add(lblEmail, gbc);

        txtEmail = new TextField(20);
        gbc.gridx = 1; gbc.gridy = 0; 
        gbc.weightx = 0.7; 
        formPanel.add(txtEmail, gbc);

        Label lblPass = new Label("Password:");
        lblPass.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.weightx = 0.3;
        formPanel.add(lblPass, gbc);

        txtPass = new TextField(20);
        txtPass.setEchoChar('*');
        gbc.gridx = 1; gbc.gridy = 1;
        gbc.weightx = 0.7;
        formPanel.add(txtPass, gbc);

        // Label de Error
        lblError = new Label(""); 
        lblError.setForeground(Color.RED);
        lblError.setFont(new Font("Arial", Font.BOLD, 12));
        lblError.setAlignment(Label.CENTER); 
        
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2; 
        gbc.weightx = 1.0;
        gbc.insets = new Insets(5, 0, 5, 0); 
        formPanel.add(lblError, gbc);

        // Botón Ingresar
        btnLogin = new Button("Ingresar");
        btnLogin.setBackground(new Color(30, 144, 255));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("Arial", Font.BOLD, 12));
        
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 30, 0); 
        formPanel.add(btnLogin, gbc);

        // Panel Registro
        Panel registerPanel = new Panel(new FlowLayout(FlowLayout.CENTER));
        Label lblNoUser = new Label("¿Aún no sos usuario?");
        btnRegister = new Button("Registrate");
        btnRegister.setBackground(new Color(30, 144, 255));
        btnRegister.setForeground(Color.WHITE);
        btnRegister.setFont(new Font("Arial", Font.BOLD, 12));
        
        registerPanel.add(lblNoUser);
        registerPanel.add(btnRegister);

        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 0, 0, 0);
        formPanel.add(registerPanel, gbc);

        mainPanel.add(formPanel);
        add(mainPanel, BorderLayout.CENTER);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    // --- CLASE INTERNA DE IMAGEN ---
    class ImagePlaceholder extends Canvas {
        private Image img;

        public ImagePlaceholder() {
            // USAMOS LA VARIABLE PRIVADA DEFINIDA ARRIBA
            // getClassLoader().getResource() busca desde la raíz del classpath
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

            // Lógica de escalado tipo "Aspect Fill" (recortar sobrantes)
            double scaleX = (double) canvasWidth / imgWidth;
            double scaleY = (double) canvasHeight / imgHeight;
            double scale = Math.max(scaleX, scaleY);

            int scaledWidth = (int) Math.ceil(imgWidth * scale);
            int scaledHeight = (int) Math.ceil(imgHeight * scale);

            int x = (canvasWidth - scaledWidth) / 2;
            int y = (canvasHeight - scaledHeight) / 2;

            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.drawImage(img, x, y, scaledWidth, scaledHeight, this);
        }
    }

    // Métodos públicos
    public String getEmail() { return txtEmail.getText(); }
    public String getPassword() { return txtPass.getText(); }
    public void clearFields() {
        txtEmail.setText("");
        txtPass.setText("");
        lblError.setText(""); 
    }
    public Button getLoginButton() { return btnLogin; }
    public Button getRegisterButton() { return btnRegister; }

    public void showLoginError() {
        lblError.setText("Usuario o contraseña incorrecta");
        lblError.revalidate(); 
    }

    public static void main(String[] args) {
        WelcomeView view = new WelcomeView();
        view.setVisible(true);
        
        try {
            Thread.sleep(2000);
            view.showLoginError();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}