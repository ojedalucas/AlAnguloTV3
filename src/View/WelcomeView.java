package view;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class WelcomeView extends Frame {
    
    private TextField txtEmail;
    private TextField txtPass;
    private Button btnLogin;
    private Button btnRegister;
    private Label lblError;
    private String imgFileName = "/util/images/WelcomeViewImage.jpg"; 

    public WelcomeView() {
        super("Plataforma de Streaming");
        setSize(900, 550);
        setLayout(new GridLayout(1, 2));
        setBackground(Color.WHITE); 
        setLocationRelativeTo(null);

        // --- COLUMNA 1: IMAGEN (IZQUIERDA) --- 
        ImagePlaceholder imagePanel = new ImagePlaceholder();
        add(imagePanel);

        // --- COLUMNA 2: FORMULARIO (DERECHA) ---
        Panel formPanel = new Panel();
        formPanel.setLayout(new GridBagLayout()); 
        formPanel.setBackground(Color.WHITE);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 30, 10, 30);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Fuentes Estandarizadas
        Font titleFont = new Font("Arial", Font.BOLD, 32);
        Font labelFont = new Font("Arial", Font.BOLD, 16);
        Font inputFont = new Font("Arial", Font.PLAIN, 16);

        // 1. Título Principal
        Label titleLabel = new Label("Iniciar Sesión");
        titleLabel.setFont(titleFont);
        titleLabel.setAlignment(Label.CENTER);
        
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 20, 30, 20);
        formPanel.add(titleLabel, gbc);

        // 2. Email
        gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 30, 5, 30);
        
        Label lblEmail = new Label("E-mail:");
        lblEmail.setFont(labelFont);
        gbc.gridy = 1; gbc.gridx = 0;
        gbc.gridwidth = 2;
        formPanel.add(lblEmail, gbc);

        txtEmail = new TextField(25);
        txtEmail.setFont(inputFont);
        txtEmail.setPreferredSize(new Dimension(300, 30));
        gbc.gridy = 2; gbc.gridx = 0;
        formPanel.add(txtEmail, gbc);

        // 3. Password
        Label lblPass = new Label("Contraseña:");
        lblPass.setFont(labelFont);
        gbc.gridy = 3; gbc.gridx = 0;
        gbc.insets = new Insets(15, 30, 5, 30);
        formPanel.add(lblPass, gbc);

        txtPass = new TextField(25);
        txtPass.setEchoChar('*');
        txtPass.setFont(inputFont);
        txtPass.setPreferredSize(new Dimension(300, 30));
        gbc.gridy = 4; gbc.gridx = 0;
        gbc.insets = new Insets(5, 30, 5, 30);
        formPanel.add(txtPass, gbc);

        // 4. Label de Error
        lblError = new Label(""); 
        lblError.setForeground(Color.RED);
        lblError.setFont(new Font("Arial", Font.BOLD, 12));
        lblError.setAlignment(Label.CENTER); 
        
        gbc.gridy = 5; gbc.gridx = 0;
        gbc.insets = new Insets(10, 30, 10, 30);
        formPanel.add(lblError, gbc);

        // 5. Botón Ingresar
        btnLogin = new Button("Ingresar");
        btnLogin.setBackground(new Color(30, 144, 255));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("Arial", Font.BOLD, 16));
        btnLogin.setPreferredSize(new Dimension(160, 40));
        
        gbc.gridy = 6; gbc.gridx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 30, 0); 
        formPanel.add(btnLogin, gbc);

        // 6. Sección Registro (Separador + Botón)
        // Usamos un panel interno para agrupar texto y botón
        Panel registerPanel = new Panel(new GridBagLayout());
        registerPanel.setBackground(Color.WHITE);
        
        Label lblNoUser = new Label("¿Aún no tienes cuenta?");
        lblNoUser.setFont(new Font("Arial", Font.PLAIN, 14));
        lblNoUser.setForeground(Color.DARK_GRAY);
        
        btnRegister = new Button("Crear Cuenta");
        btnRegister.setBackground(new Color(240, 240, 240));
        btnRegister.setForeground(Color.BLACK);
        btnRegister.setFont(new Font("Arial", Font.BOLD, 14));
        btnRegister.setPreferredSize(new Dimension(140, 35));

        GridBagConstraints gbcReg = new GridBagConstraints();
        gbcReg.gridx = 0; gbcReg.gridy = 0;
        registerPanel.add(lblNoUser, gbcReg);
        
        gbcReg.gridy = 1;
        gbcReg.insets = new Insets(5, 0, 0, 0);
        registerPanel.add(btnRegister, gbcReg);

        gbc.gridy = 7; gbc.gridx = 0;
        gbc.insets = new Insets(10, 0, 0, 0);
        formPanel.add(registerPanel, gbc);

        add(formPanel);

        // Listener cerrar
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
            URL imgURL = getClass().getResource(imgFileName);

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

    public String getEmail() { return txtEmail.getText(); }
    public String getPassword() { return txtPass.getText(); }
    public void clearFields() {
        txtEmail.setText("");
        txtPass.setText("");
        lblError.setText(""); 
    }
    public void addLoginListener(ActionListener listener){
        btnLogin.addActionListener(listener);
    }
    public void addRegisterListener(ActionListener listener){
        btnRegister.addActionListener(listener);
    }

    public void showErrorMessage(String mensaje) {
        lblError.setText(mensaje);
        lblError.revalidate(); 
    }
}