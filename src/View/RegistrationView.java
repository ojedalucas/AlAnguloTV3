package view;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;

public class RegistrationView extends Frame {

    private TextField txtNombre;
    private TextField txtApellido;
    private TextField txtDNI;
    private TextField txtUsername; 
    private TextField txtEmail;
    private TextField txtPass;
    private Button btnRegistrar;
    private Button btnVolver;
    private Label lblError;

    public RegistrationView() {
        // Configuración básica de la ventana
        super("Plataforma de Streaming - Registro");
        setResizable(false); 
        setSize(600, 650); 
        setBackground(Color.WHITE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        Font titleFont = new Font("Arial", Font.BOLD, 32);
        Font labelFont = new Font("Arial", Font.BOLD, 16);
        Font inputFont = new Font("Arial", Font.PLAIN, 16);
        btnVolver = new Button("<"); 
        btnVolver.setFont(new Font("Dialog", Font.BOLD, 24)); 
        btnVolver.setBackground(new Color(30, 144, 255)); 
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setPreferredSize(new Dimension(50, 40));
        gbc.gridx = 0;
        gbc.gridy = 0; 
        gbc.gridwidth = 1;
        gbc.weightx = 0.0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.NORTHWEST; 
        gbc.insets = new Insets(10, 10, 0, 0); 
        add(btnVolver, gbc);

        // --- TÍTULO PRINCIPAL ---
        Label mainTitle = new Label("Registro de Usuario");
        mainTitle.setFont(titleFont);
        mainTitle.setAlignment(Label.CENTER);
        
        gbc.gridx = 0; 
        gbc.gridy = 1;
        gbc.gridwidth = 2; 
        gbc.insets = new Insets(10, 20, 30, 20);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL; 
        add(mainTitle, gbc);

        // --- CONFIGURACIÓN COMÚN PARA EL FORMULARIO ---
        gbc.gridwidth = 1; 
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        Insets labelInsets = new Insets(10, 40, 10, 10); 
        Insets inputInsets = new Insets(10, 0, 10, 40);

        // --- Nombres ---
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

        // --- Apellidos ---
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

        // --- DNI ---
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

        // --- Nombre de Usuario ---
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

        // --- E-mail ---
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

        // --- Password ---
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

        // --- Label de Error ---
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

        // --- Botón Registrar ---
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
        gbc.gridy = 10;
        gbc.weighty = 1.0;
        add(new Label(""), gbc);

        // Lógica para cerrar la ventana
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public String getUsername() {return txtUsername.getText(); } 
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
    
    public void addBackListener(ActionListener listener){
        btnVolver.addActionListener(listener);
    }
}