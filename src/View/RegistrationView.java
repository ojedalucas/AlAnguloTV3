package view;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;

public class RegistrationView extends Frame {

    // Campos reutilizables
    private TextField txtNombre;
    private TextField txtApellido;
    private TextField txtDNI;
    // NUEVO: Campo de usuario
    private TextField txtUsername; 
    private TextField txtEmail;
    private TextField txtPass;
    private Button btnRegistrar;
    
    // NUEVO: Botón Volver
    private Button btnVolver;
    
    // Etiqueta de error
    private Label lblError;

    public RegistrationView() {
        // 1. Configuración básica de la ventana
        super("Plataforma de Streaming - Registro");
        setResizable(false); 
        // Aumenté un poco más la altura para que entre el nuevo campo cómodamente
        setSize(600, 650); 
        setBackground(Color.WHITE);
        setLocationRelativeTo(null);

        // Usamos GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Fuentes Estandarizadas
        Font titleFont = new Font("Arial", Font.BOLD, 32);
        Font labelFont = new Font("Arial", Font.BOLD, 16);
        Font inputFont = new Font("Arial", Font.PLAIN, 16);

        // --- NUEVO: BOTÓN VOLVER (Fila 0) ---
        // --- BOTÓN VOLVER (Corrección para que no salga un cuadrado) ---
        // Usamos el símbolo "<" que es universal y seguro
        btnVolver = new Button("<"); 
        
        // Usamos "Dialog" y tamaño 24 para que el "<" parezca una flecha robusta
        btnVolver.setFont(new Font("Dialog", Font.BOLD, 24)); 
        
        // Mismo color azul
        btnVolver.setBackground(new Color(30, 144, 255)); 
        btnVolver.setForeground(Color.WHITE);
        
        // Ajustamos el tamaño para que sea cuadrado
        btnVolver.setPreferredSize(new Dimension(50, 40));

        gbc.gridx = 0;
        gbc.gridy = 0; 
        gbc.gridwidth = 1;
        gbc.weightx = 0.0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.NORTHWEST; 
        gbc.insets = new Insets(10, 10, 0, 0); 
        add(btnVolver, gbc);

        // --- 0. TÍTULO PRINCIPAL (Movido a Fila 1) ---
        Label mainTitle = new Label("Registro de Usuario");
        mainTitle.setFont(titleFont);
        mainTitle.setAlignment(Label.CENTER);
        
        gbc.gridx = 0; 
        gbc.gridy = 1; // Antes era 0
        gbc.gridwidth = 2; 
        gbc.insets = new Insets(10, 20, 30, 20); // Ajusté un poco el margen superior
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Asegurar que se centre bien
        add(mainTitle, gbc);

        // --- CONFIGURACIÓN COMÚN PARA EL FORMULARIO ---
        gbc.gridwidth = 1; 
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER; // Reset anchor
        
        // Insets para los campos: (Arriba, Izq, Abajo, Der)
        Insets labelInsets = new Insets(10, 40, 10, 10); 
        Insets inputInsets = new Insets(10, 0, 10, 40);

        // --- FILA 2: Nombres (Antes era 1) ---
        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.weightx = 0.0;
        gbc.insets = labelInsets;
        Label lblNombre = new Label("Nombres:");
        lblNombre.setFont(labelFont);
        add(lblNombre, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.insets = inputInsets;
        txtNombre = new TextField(20);
        txtNombre.setFont(inputFont);
        txtNombre.setPreferredSize(new Dimension(200, 30));
        add(txtNombre, gbc);

        // --- FILA 3: Apellidos (Antes era 2) ---
        gbc.gridy = 3;
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

        // --- FILA 4: DNI (Antes era 3) ---
        gbc.gridy = 4;
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

        // --- FILA 5: Nombre de Usuario (Antes era 4) ---
        gbc.gridy = 5; 
        gbc.gridx = 0;
        gbc.weightx = 0.0;
        gbc.insets = labelInsets;
        Label lblUser = new Label("Usuario:");
        lblUser.setFont(labelFont);
        add(lblUser, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.insets = inputInsets;
        txtUsername = new TextField(20);
        txtUsername.setFont(inputFont);
        txtUsername.setPreferredSize(new Dimension(200, 30));
        add(txtUsername, gbc);

        // --- FILA 6: E-mail (Antes era 5) ---
        gbc.gridy = 6;
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

        // --- FILA 7: Password (Antes era 6) ---
        gbc.gridy = 7;
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

        // --- FILA 8: Label de Error (Antes era 7) ---
        lblError = new Label(""); 
        lblError.setForeground(Color.RED);
        lblError.setFont(new Font("Arial", Font.BOLD, 12));
        lblError.setAlignment(Label.CENTER);
        lblError.setVisible(false); 
        
        gbc.gridy = 8; 
        gbc.gridx = 0;
        gbc.gridwidth = 2; 
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 30, 5, 30); 
        add(lblError, gbc);

        // --- FILA 9: Botón Registrar (Antes era 8) ---
        btnRegistrar = new Button("Registrar");
        btnRegistrar.setFont(new Font("Arial", Font.BOLD, 16));
        btnRegistrar.setBackground(new Color(30, 144, 255)); 
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setPreferredSize(new Dimension(160, 40)); 

        gbc.gridy = 9; 
        gbc.gridx = 0;
        gbc.gridwidth = 2; 
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 0, 20, 0); 
        
        add(btnRegistrar, gbc);

        gbc.gridy = 10; // Fila siguiente
        gbc.weighty = 1.0; // Esto hace que este espacio vacío ocupe todo el sobrante vertical
        add(new Label(""), gbc);

        // 3. Lógica para cerrar la ventana
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    // Métodos públicos para acceder y manipular campos y botón
    
    public String getUsername() { 
        return txtUsername.getText(); 
    } 
    
    public String getNombre() { return txtNombre.getText(); }
    public String getApellido() { return txtApellido.getText(); }
    public String getDNI() { return txtDNI.getText(); }
    public String getEmail() { return txtEmail.getText(); }
    public String getPassword() { return txtPass.getText(); }

    public void clearFields() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtDNI.setText("");
        txtUsername.setText("");
        txtEmail.setText("");
        txtPass.setText("");
        
        lblError.setText("");
        lblError.setVisible(false);
    }

    public void showErrorMessage(String mensaje) {
        lblError.setText(mensaje);
        lblError.revalidate(); 
    }

    public void addRegisterListener(ActionListener listener){
        btnRegistrar.addActionListener(listener);
    }
    
    // NUEVO: Método para el botón volver
    public void addBackListener(ActionListener listener){
        btnVolver.addActionListener(listener);
    }

    public static void main(String[] args) {
        RegistrationView view = new RegistrationView();
        view.setVisible(true);
    }
}