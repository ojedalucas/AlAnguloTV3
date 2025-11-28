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
        super("Plataforma de Streaming - Información");
        setSize(700, 480); // Un poco más alto para acomodar mejor los elementos
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
        gbc.insets = new Insets(15, 30, 15, 30); // Márgenes laterales más amplios
        gbc.anchor = GridBagConstraints.NORTHWEST;

        // --- Título ---
        titleLabel = new Label("Título de la Película");
        // Aumenté un poco el tamaño para que sea imponente
        titleLabel.setFont(new Font("Arial", Font.BOLD, 34)); 
        gbc.gridx = 0; 
        gbc.gridy = 0; 
        gbc.gridwidth = GridBagConstraints.REMAINDER; // Ocupa todo el ancho
        gbc.weightx = 1.0;
        add(titleLabel, gbc);

        // --- Año ---
        yearLabel = new Label("Año: 2024");
        yearLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        yearLabel.setForeground(Color.DARK_GRAY); // Color gris para metadatos
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 30, 15, 30); // Menos espacio arriba
        add(yearLabel, gbc);

        // --- Etiqueta Resumen ---
        Label resumenLabel = new Label("Resumen:");
        resumenLabel.setFont(new Font("Arial", Font.BOLD, 18)); // Negrita para encabezado de sección
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 30, 5, 30);
        add(resumenLabel, gbc);

        // --- Área de Texto ---
        resumenArea = new TextArea("", 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
        resumenArea.setFont(new Font("Arial", Font.PLAIN, 15)); // Tamaño legible
        resumenArea.setEditable(false);
        resumenArea.setBackground(new Color(250, 250, 250)); // Gris muy claro de fondo para diferenciar
        
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0; // Que ocupe el espacio vertical sobrante
        gbc.insets = new Insets(0, 30, 20, 30);
        add(resumenArea, gbc);

        // --- Botón "Continuar" ---
        btnContinuar = new Button("Continuar");
        btnContinuar.setBackground(new Color(30, 144, 255));
        btnContinuar.setForeground(Color.WHITE);
        btnContinuar.setFont(new Font("Arial", Font.BOLD, 16));
        // Misma dimensión exacta que en ExitoView
        btnContinuar.setPreferredSize(new Dimension(160, 40));

        gbc.gridy = 4;
        gbc.weighty = 0; 
        gbc.anchor = GridBagConstraints.CENTER; // Centrado al fondo
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(10, 30, 30, 30); // Margen inferior generoso
        add(btnContinuar, gbc);
    }

    // Método público para obtener el botón
    public Button getContinuarButton() {
        return btnContinuar;
    }
    public void addContinuarListener(ActionListener listener){
        btnContinuar.addActionListener(listener);
    }

    // Métodos para actualizar contenido
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
