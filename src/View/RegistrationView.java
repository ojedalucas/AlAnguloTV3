package View;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RegistrationView extends Frame {

    // Campos reutilizables
    private TextField txtNombre;
    private TextField txtApellido;
    private TextField txtDNI;
    private TextField txtEmail;
    private TextField txtPass;
    private Button btnRegistrar;

    public RegistrationView() {
        // 1. Configuración básica de la ventana
        super("Plataforma de Streaming - Registro");
        setSize(600, 650); // Un poco más alta para acomodar el título
        setBackground(Color.WHITE);
        setLocationRelativeTo(null);

        // Usamos GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Fuentes Estandarizadas
        Font titleFont = new Font("Arial", Font.BOLD, 32);
        Font labelFont = new Font("Arial", Font.BOLD, 16);
        Font inputFont = new Font("Arial", Font.PLAIN, 16);

        // --- 0. TÍTULO PRINCIPAL ---
        Label mainTitle = new Label("Registro de Usuario");
        mainTitle.setFont(titleFont);
        mainTitle.setAlignment(Label.CENTER);
        
        gbc.gridx = 0; 
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Ocupa todo el ancho
        gbc.insets = new Insets(20, 20, 30, 20); // Margen inferior amplio
        gbc.anchor = GridBagConstraints.CENTER;
        add(mainTitle, gbc);

        // --- CONFIGURACIÓN COMÚN PARA EL FORMULARIO ---
        gbc.gridwidth = 1; // Reseteamos a 1 columna
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Insets para los campos: (Arriba, Izq, Abajo, Der)
        // Separamos un poco más verticalmente
        Insets labelInsets = new Insets(10, 40, 10, 10); 
        Insets inputInsets = new Insets(10, 0, 10, 40);

        // --- FILA 1: Nombres ---
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.weightx = 0.0; // La etiqueta no crece
        gbc.insets = labelInsets;
        Label lblNombre = new Label("Nombres:");
        lblNombre.setFont(labelFont);
        add(lblNombre, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0; // El input absorbe el espacio extra
        gbc.insets = inputInsets;
        txtNombre = new TextField(20);
        txtNombre.setFont(inputFont);
        txtNombre.setPreferredSize(new Dimension(200, 30)); // Altura más cómoda
        add(txtNombre, gbc);

        // --- FILA 2: Apellidos ---
        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.weightx = 0.0;
        gbc.insets = labelInsets;
        Label lblApellido = new Label("Apellidos:");
        lblApellido.setFont(labelFont);
        add(lblApellido, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.insets = inputInsets;
        txtApellido = new TextField(20);
        txtApellido.setFont(inputFont);
        txtApellido.setPreferredSize(new Dimension(200, 30));
        add(txtApellido, gbc);

        // --- FILA 3: DNI ---
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.weightx = 0.0;
        gbc.insets = labelInsets;
        Label lblDNI = new Label("DNI:");
        lblDNI.setFont(labelFont);
        add(lblDNI, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.insets = inputInsets;
        txtDNI = new TextField(20);
        txtDNI.setFont(inputFont);
        txtDNI.setPreferredSize(new Dimension(200, 30));
        add(txtDNI, gbc);

        // --- FILA 4: E-mail ---
        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.weightx = 0.0;
        gbc.insets = labelInsets;
        Label lblEmail = new Label("E-mail:");
        lblEmail.setFont(labelFont);
        add(lblEmail, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.insets = inputInsets;
        txtEmail = new TextField(20);
        txtEmail.setFont(inputFont);
        txtEmail.setPreferredSize(new Dimension(200, 30));
        add(txtEmail, gbc);

        // --- FILA 5: Password ---
        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.weightx = 0.0;
        gbc.insets = labelInsets;
        Label lblPass = new Label("Password:");
        lblPass.setFont(labelFont);
        add(lblPass, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.insets = inputInsets;
        txtPass = new TextField(20);
        txtPass.setFont(inputFont);
        txtPass.setEchoChar('*'); 
        txtPass.setPreferredSize(new Dimension(200, 30));
        add(txtPass, gbc);

        // --- FILA 6: Botón Registrar ---
        btnRegistrar = new Button("Registrar");
        btnRegistrar.setFont(new Font("Arial", Font.BOLD, 16));
        btnRegistrar.setBackground(new Color(30, 144, 255)); // Azul estandarizado
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setPreferredSize(new Dimension(160, 40)); // Tamaño estandarizado

        gbc.gridy = 6;
        gbc.gridx = 0;
        gbc.gridwidth = 2; // Ocupa todo el ancho
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(40, 0, 20, 0); // Margen superior grande
        
        add(btnRegistrar, gbc);

        // 3. Lógica para cerrar la ventana
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    // Métodos públicos para acceder y manipular campos y botón
    public String getNombre() { return txtNombre.getText(); }
    public String getApellido() { return txtApellido.getText(); }
    public String getDNI() { return txtDNI.getText(); }
    public String getEmail() { return txtEmail.getText(); }
    public String getPassword() { return txtPass.getText(); }

    public void clearFields() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtDNI.setText("");
        txtEmail.setText("");
        txtPass.setText("");
    }

    public Button getRegisterButton() {
        return btnRegistrar;
    }

    public static void main(String[] args) {
        RegistrationView view = new RegistrationView();
        view.setVisible(true);
    }
}
