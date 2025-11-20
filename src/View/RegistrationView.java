package View;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RegistrationView extends Frame {

    public RegistrationView() {
        // 1. Configuración básica de la ventana
        super("Plataforma de Streaming - Registro");
        setSize(600, 500);
        setBackground(Color.WHITE);
        
        // Usamos GridBagLayout para centrar todo el formulario en la ventana
        setLayout(new GridBagLayout());
        
        // Centrar ventana en pantalla
        setLocationRelativeTo(null);

        // --- PANEL DEL FORMULARIO ---
        // Creamos un panel interno para agrupar los campos
        Panel formPanel = new Panel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);

        // Configuración de restricciones (GridBagConstraints)
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Márgenes: Arriba, Izq, Abajo, Der
        gbc.fill = GridBagConstraints.HORIZONTAL; // Que los campos se estiren
        
        // Fuente para las etiquetas
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        // Fuente para los campos de texto
        Font textFont = new Font("Arial", Font.PLAIN, 14);

        // --- FILA 1: Nombres ---
        gbc.gridx = 0; gbc.gridy = 0; // Columna 0, Fila 0
        Label lblNombre = new Label("Nombres:");
        lblNombre.setFont(labelFont);
        formPanel.add(lblNombre, gbc);

        gbc.gridx = 1; gbc.gridy = 0; // Columna 1, Fila 0
        TextField txtNombre = new TextField(25);
        txtNombre.setFont(textFont);
        formPanel.add(txtNombre, gbc);

        // --- FILA 2: Apellidos ---
        gbc.gridx = 0; gbc.gridy = 1;
        Label lblApellido = new Label("Apellidos:");
        lblApellido.setFont(labelFont);
        formPanel.add(lblApellido, gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        TextField txtApellido = new TextField(25);
        txtApellido.setFont(textFont);
        formPanel.add(txtApellido, gbc);

        // --- FILA 3: DNI ---
        gbc.gridx = 0; gbc.gridy = 2;
        Label lblDNI = new Label("DNI:");
        lblDNI.setFont(labelFont);
        formPanel.add(lblDNI, gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        TextField txtDNI = new TextField(25);
        txtDNI.setFont(textFont);
        formPanel.add(txtDNI, gbc);

        // --- FILA 4: E-mail ---
        gbc.gridx = 0; gbc.gridy = 3;
        Label lblEmail = new Label("E-mail:");
        lblEmail.setFont(labelFont);
        formPanel.add(lblEmail, gbc);

        gbc.gridx = 1; gbc.gridy = 3;
        TextField txtEmail = new TextField(25);
        txtEmail.setFont(textFont);
        formPanel.add(txtEmail, gbc);

        // --- FILA 5: Password ---
        gbc.gridx = 0; gbc.gridy = 4;
        Label lblPass = new Label("Password:");
        lblPass.setFont(labelFont);
        formPanel.add(lblPass, gbc);

        gbc.gridx = 1; gbc.gridy = 4;
        TextField txtPass = new TextField(25);
        txtPass.setFont(textFont);
        txtPass.setEchoChar('*'); // Ocultar caracteres
        formPanel.add(txtPass, gbc);

        // --- FILA 6: Botón Registrar ---
        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 2; // Que ocupe 2 columnas (ancho completo)
        gbc.fill = GridBagConstraints.NONE; // No estirar, mantener tamaño natural
        gbc.anchor = GridBagConstraints.CENTER; // Centrar
        gbc.insets = new Insets(30, 0, 0, 0); // Margen extra arriba para separarlo

        Button btnRegistrar = new Button("Registrar");
        btnRegistrar.setFont(new Font("Arial", Font.BOLD, 14));
        btnRegistrar.setBackground(new Color(0, 160, 230)); // Azul similar a la imagen
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setPreferredSize(new Dimension(150, 40)); // Tamaño fijo más grande

        formPanel.add(btnRegistrar, gbc);

        // Agregar el panel del formulario a la ventana principal
        add(formPanel);

        // 3. Lógica para cerrar la ventana
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        RegistrationView view = new RegistrationView();
        view.setVisible(true);
    }
}
