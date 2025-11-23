package View;

import java.awt.*;
import java.awt.event.*;

public class InfoView extends Frame{
    private Button btnContinuar;
    private Label titleLabel;
    private Label yearLabel;
    private TextArea resumenArea;

    public InfoView() {
        // Configuro ventana
        super("Plataforma de Streaming  -   Información");
        setSize(700, 450);
        setBackground(Color.WHITE);
        setLocationRelativeTo(null);

        // Lógica para cerrar la ventana
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        // Layout de la ventana
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20,20,20,20);
        gbc.anchor = GridBagConstraints.NORTHWEST;

        // Contenido principal: dos columnas (texto a la izquierda, nota a la derecha)
        // Título
        titleLabel = new Label("Titulo de la Pelicula");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setAlignment(Label.LEFT);
        gbc.weightx = 1;
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 1; gbc.anchor = GridBagConstraints.WEST;
        add(titleLabel, gbc);

        // Año
        yearLabel = new Label("Año: ");
        yearLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.WEST; gbc.insets = new Insets(8,20,8,20);
        add(yearLabel, gbc);

        // Resumen (etiqueta y área)
        Label resumenLabel = new Label("Resumen:");
        resumenLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 0; gbc.gridy = 2; gbc.anchor = GridBagConstraints.WEST; gbc.insets = new Insets(8,20,8,20);
        add(resumenLabel, gbc);

        resumenArea = new TextArea();
        resumenArea.setFont(new Font("Arial", Font.PLAIN, 14));
        resumenArea.setEditable(false);
        resumenArea.setBackground(Color.WHITE);
        resumenArea.setPreferredSize(new Dimension(640, 160));
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2; gbc.fill = GridBagConstraints.BOTH; gbc.weightx = 1; gbc.weighty = 1.0;
        add(resumenArea, gbc);

        // --- Botón "Continuar" centrado en la parte inferior ---
        btnContinuar = new Button("Continuar");
        btnContinuar.setBackground(new Color(30, 144, 255)); // gris claro
        btnContinuar.setForeground(Color.WHITE);
        btnContinuar.setFont(new Font("Arial", Font.BOLD, 16));
        btnContinuar.setPreferredSize(new Dimension(160, 40));

        gbc.gridy = 4;
        gbc.weighty = 0; // no expandir
        gbc.anchor = GridBagConstraints.PAGE_END;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        add(btnContinuar, gbc);


    }

    // Método público para obtener el botón "Continuar"
    public Button getContinuarButton() {
        return btnContinuar;
    }

    // Métodos para actualizar el contenido dinámico
    public void setTitleText(String title) {
        if (titleLabel != null) titleLabel.setText(title);
    }

    public void setYearText(String year) {
        if (yearLabel != null) yearLabel.setText(year);
    }

    public void setResumenText(String resumen) {
        if (resumenArea != null) resumenArea.setText(resumen);
    }

    public static void main(String[] args) {
        InfoView view = new InfoView();
        view.setVisible(true);
    }

}
