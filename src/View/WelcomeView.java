package View;

import java.awt.*;
import java.awt.event.*;

public class WelcomeView extends Frame {
    // Campos reutilizables para permitir acceso desde métodos
    private TextField txtEmail;
    private TextField txtPass;
    private Button btnLogin;
    private Button btnRegister;

    public WelcomeView() {
        // 1. Configuración básica de la ventana
        super("Plataforma de Streaming");
        setSize(800, 500); // Tamaño similar a la imagen
        setLayout(new BorderLayout());
        setBackground(Color.WHITE); // Fondo blanco limpio
        
        // Centrar la ventana en la pantalla
        setLocationRelativeTo(null);

        // --- HEADER (Título Superior) ---
        Panel headerPanel = new Panel(new FlowLayout(FlowLayout.LEFT));
        headerPanel.setBackground(Color.WHITE);
        Label titleLabel = new Label("Bienvenido a la Plataforma de Streaming");
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        titleLabel.setForeground(Color.GRAY);
        headerPanel.add(titleLabel);
        
        add(headerPanel, BorderLayout.NORTH);

        // --- PANEL CENTRAL (Dividido en 2: Izquierda Imagen, Derecha Login) ---
        Panel mainPanel = new Panel(new GridLayout(1, 2)); // 1 fila, 2 columnas
        
        // 1. IZQUIERDA: Espacio para la imagen
        // Usamos un Canvas personalizado para dibujar el "placeholder"
        ImagePlaceholder imagePanel = new ImagePlaceholder();
        mainPanel.add(imagePanel);

        // 2. DERECHA: Formulario de Login
        Panel formPanel = new Panel();
        formPanel.setLayout(new GridBagLayout()); // El mejor layout para formularios centrados
        formPanel.setBackground(Color.WHITE);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Márgenes entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // -- Componentes del Formulario --
        
        // Etiqueta E-mail
        Label lblEmail = new Label("E-mail");
        lblEmail.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0; gbc.gridy = 0; // Columna 0, Fila 0
        gbc.weightx = 0.3; // Ocupa menos espacio
        formPanel.add(lblEmail, gbc);

        // Campo de texto E-mail
        txtEmail = new TextField(20);
        gbc.gridx = 1; gbc.gridy = 0; // Columna 1, Fila 0
        gbc.weightx = 0.7; // Ocupa más espacio
        formPanel.add(txtEmail, gbc);

        // Etiqueta Password
        Label lblPass = new Label("Password:");
        lblPass.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.weightx = 0.3;
        formPanel.add(lblPass, gbc);

        // Campo de texto Password
        txtPass = new TextField(20);
        txtPass.setEchoChar('*');
        gbc.gridx = 1; gbc.gridy = 1;
        gbc.weightx = 0.7;
        formPanel.add(txtPass, gbc);

        // Botón Ingresar
        btnLogin = new Button("Ingresar");
        btnLogin.setBackground(new Color(30, 144, 255));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("Arial", Font.BOLD, 12));
        
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(30, 0, 30, 0);
        formPanel.add(btnLogin, gbc);

        // Panel inferior para "Registro"
        Panel registerPanel = new Panel(new FlowLayout(FlowLayout.CENTER));
        Label lblNoUser = new Label("¿Aún no sos usuario?");
        btnRegister = new Button("Registrate");
        btnRegister.setBackground(new Color(30, 144, 255));
        btnRegister.setForeground(Color.WHITE);
        btnRegister.setFont(new Font("Arial", Font.BOLD, 12));
        
        registerPanel.add(lblNoUser);
        registerPanel.add(btnRegister);

        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 0, 0, 0);
        formPanel.add(registerPanel, gbc);

        // Agregar panel de formulario al panel principal
        mainPanel.add(formPanel);

        // Agregar todo a la ventana
        add(mainPanel, BorderLayout.CENTER);

        // --- Lógica de Cierre ---
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    // Clase interna para dibujar el cuadro donde iría la imagen
    class ImagePlaceholder extends Canvas {
        public void paint(Graphics g) {
            // Fondo suave
            g.setColor(new Color(255, 228, 196)); // Un color beige claro
            g.fillRect(20, 20, getWidth() - 40, getHeight() - 40);

            // Borde
            g.setColor(Color.GRAY);
            g.drawRect(20, 20, getWidth() - 40, getHeight() - 40);

            // Texto indicativo
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.ITALIC, 16));
            String texto = "Espacio para Imagen (Dog Streaming)";
            // Calcular posición para centrar texto
            FontMetrics fm = g.getFontMetrics();
            int x = (getWidth() - fm.stringWidth(texto)) / 2;
            int y = (getHeight() / 2);
            g.drawString(texto, x, y);
        }
    }

    // Métodos públicos para uso de botones

    public String getEmail() {
        return txtEmail.getText();
    }

    public String getPassword() {
        return txtPass.getText();
    }

    public void clearFields() {
        txtEmail.setText("");
        txtPass.setText("");
    }

    public Button getLoginButton() {
        return btnLogin;
    }

    public Button getRegisterButton() {
        return btnRegister;
    }

    // Muestra un diálogo modal informando credenciales incorrectas.
    // Mientras el diálogo está visible, los campos y botones quedan deshabilitados.
    public void showIncorrectCredentialsDialog() {
        // Deshabilitar entrada en la vista principal
        if (txtEmail != null) txtEmail.setEnabled(false);
        if (txtPass != null) txtPass.setEnabled(false);
        if (btnLogin != null) btnLogin.setEnabled(false);
        if (btnRegister != null) btnRegister.setEnabled(false);

        final Dialog dialog = new Dialog(this, "Error de autenticación", true);
        dialog.setLayout(new GridBagLayout());
        GridBagConstraints dgbc = new GridBagConstraints();
        dgbc.insets = new Insets(10,10,10,10);
        dgbc.gridx = 0; dgbc.gridy = 0;

        Label msg = new Label("Usuario o contraseña incorrecta, por favor intente nuevamente");
        msg.setFont(new Font("Arial", Font.PLAIN, 14));
        dialog.add(msg, dgbc);

        Button ok = new Button("Aceptar");
        ok.setFont(new Font("Arial", Font.BOLD, 12));
        dgbc.gridy = 1;
        dialog.add(ok, dgbc);

        // Re-habilitar componentes cuando se cierre el diálogo
        Runnable restore = () -> {
            if (txtEmail != null) txtEmail.setEnabled(true);
            if (txtPass != null) txtPass.setEnabled(true);
            if (btnLogin != null) btnLogin.setEnabled(true);
            if (btnRegister != null) btnRegister.setEnabled(true);
        };

        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
                dialog.dispose();
                restore.run();
            }
        });

        dialog.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dialog.setVisible(false);
                dialog.dispose();
                restore.run();
            }
        });

        dialog.pack();
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true); // modal: bloqueará interacción hasta cerrarse
    }
    // Método main para probar la vista
    public static void main(String[] args) {
        WelcomeView view = new WelcomeView();
        view.setVisible(true);
            // Mostrar diálogo modal de credenciales incorrectas al inicio (modo prueba)
            view.showIncorrectCredentialsDialog();
    }
}