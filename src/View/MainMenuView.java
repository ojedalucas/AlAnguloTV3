package View;

import java.awt.*;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

public class MainMenuView extends Panel {

    // Componentes Generales
    private Label lblTituloPrincipal;
    private Label lblSubtitulo;
    private Label lblNombreUsuario;
    
    private TextField txtBuscador;
    private Button btnBuscar;
    private Button btnCerrarSesion;

    // Componentes de la Tabla
    private Panel panelContenedorFilas;
    private ScrollPane scrollPaneTabla;
    private Panel panelCabeceraTabla;

    // --- LISTA DE BOTONES PARA EL CONTROLADOR ---
    private ArrayList<Button> listaBotonesCalificar;

    private final int[] COL_WIDTHS = {60, 200, 120, 380, 90};

    // Colores y Fuentes Estandarizadas
    private final Color brandBlue = new Color(30, 144, 255);
    private final Color bgHeader = new Color(240, 240, 240);
    private final Font fontTitle = new Font("Arial", Font.BOLD, 28);
    private final Font fontSubtitle = new Font("Arial", Font.PLAIN, 16);
    private final Font fontHeaderTable = new Font("Arial", Font.BOLD, 14);
    private final Font fontRowText = new Font("Arial", Font.PLAIN, 14);
    private final Font fontBtn = new Font("Arial", Font.BOLD, 14);

    public MainMenuView() {
        setSize(1024, 768);
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        
        // Centrar ventana
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        // Inicializamos la lista vacía
        listaBotonesCalificar = new ArrayList<>();

        inicializarComponentes();
        construirLayoutSuperior();
        construirLayoutCentral();
    }

    private void inicializarComponentes() {
        lblTituloPrincipal = new Label("Bienvenido a la Plataforma de Streaming");
        lblTituloPrincipal.setFont(fontTitle);

        lblSubtitulo = new Label("Seguro viste alguna de estas películas, haznos saber qué te parecieron dejando una reseña.");
        lblSubtitulo.setFont(fontSubtitle);
        lblSubtitulo.setForeground(Color.DARK_GRAY);

        lblNombreUsuario = new Label("Usuario Invitado");
        lblNombreUsuario.setFont(new Font("Arial", Font.BOLD, 16));
        lblNombreUsuario.setAlignment(Label.RIGHT);

        // Buscador Estilizado
        txtBuscador = new TextField(20);
        txtBuscador.setFont(new Font("Arial", Font.PLAIN, 14));
        txtBuscador.setPreferredSize(new Dimension(200, 30));

        // Botón Buscar
        btnBuscar = new Button("Buscar");
        btnBuscar.setBackground(brandBlue);
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setFont(fontBtn);
        btnBuscar.setPreferredSize(new Dimension(80, 30));

        // Botón Cerrar Sesión
        btnCerrarSesion = new Button("Cerrar Sesión");
        btnCerrarSesion.setBackground(Color.DARK_GRAY); 
        btnCerrarSesion.setForeground(Color.WHITE);
        btnCerrarSesion.setFont(fontBtn);
        btnCerrarSesion.setPreferredSize(new Dimension(120, 30));
    }

    private void construirLayoutSuperior() {
        Panel panelSuperior = new Panel(new GridBagLayout());
        panelSuperior.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 30, 10, 30); 

        // Título
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridheight = 2;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        panelSuperior.add(lblTituloPrincipal, gbc);

        // Buscador
        Panel panelBuscador = new Panel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        panelBuscador.add(txtBuscador);
        panelBuscador.add(btnBuscar);

        gbc.gridx = 1; gbc.gridy = 0; gbc.gridheight = 1;
        gbc.weightx = 0.0;
        panelSuperior.add(panelBuscador, gbc);

        // Usuario
        Panel panelUsuario = new Panel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        panelUsuario.add(lblNombreUsuario);
        panelUsuario.add(btnCerrarSesion);

        gbc.gridx = 1; gbc.gridy = 1;
        panelSuperior.add(panelUsuario, gbc);

        add(panelSuperior, BorderLayout.NORTH);
    }

    private void construirLayoutCentral() {
        Panel panelCentral = new Panel(new BorderLayout());
        panelCentral.setBackground(Color.WHITE);

        // Subtítulo
        Panel panelSub = new Panel(new FlowLayout(FlowLayout.LEFT, 30, 10));
        panelSub.add(lblSubtitulo);
        panelCentral.add(panelSub, BorderLayout.NORTH);

        // Cabecera Fija
        panelCabeceraTabla = new Panel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelCabeceraTabla.setBackground(bgHeader);
        
        String[] titulos = {"Póster", "Título", "Género", "Resumen", "Acción"};
        for(int i=0; i<titulos.length; i++) {
            Panel celda = new Panel(new BorderLayout());
            celda.setPreferredSize(new Dimension(COL_WIDTHS[i], 45));
            Label l = new Label(titulos[i], Label.CENTER);
            l.setFont(fontHeaderTable);
            celda.add(l, BorderLayout.CENTER);
            panelCabeceraTabla.add(celda);
        }

        // Cuerpo Scrolleable
        scrollPaneTabla = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
        panelContenedorFilas = new Panel();
        panelContenedorFilas.setLayout(new GridBagLayout());
        panelContenedorFilas.setBackground(Color.WHITE);
        
        scrollPaneTabla.add(panelContenedorFilas);

        Panel panelTabla = new Panel(new BorderLayout());
        panelTabla.add(panelCabeceraTabla, BorderLayout.NORTH);
        panelTabla.add(scrollPaneTabla, BorderLayout.CENTER);

        // Margen lateral para la tabla
        Panel wrapperTabla = new Panel(new BorderLayout());
        wrapperTabla.add(panelTabla, BorderLayout.CENTER);
        
        // Espaciadores laterales
        Panel margenIzq = new Panel(); margenIzq.setPreferredSize(new Dimension(30, 0));
        Panel margenDer = new Panel(); margenDer.setPreferredSize(new Dimension(30, 0));
        
        panelCentral.add(margenIzq, BorderLayout.WEST);
        panelCentral.add(margenDer, BorderLayout.EAST);
        panelCentral.add(wrapperTabla, BorderLayout.CENTER);
        
        add(panelCentral, BorderLayout.CENTER);
    }

    public void actualizarListaPeliculas(Object[][] datosPeliculas) {
        panelContenedorFilas.removeAll();
        listaBotonesCalificar.clear();

        // 1. Calculamos el ancho total de la tabla dinámicamente
        int totalTableWidth = 0;
        for (int w : COL_WIDTHS) totalTableWidth += w;

        GridBagConstraints gbcRow = new GridBagConstraints();
        gbcRow.gridx = 0; 
        gbcRow.weightx = 0.0; 
        gbcRow.fill = GridBagConstraints.NONE; 
        gbcRow.anchor = GridBagConstraints.NORTHWEST; 
        
        int filaIndex = 0;

        for (Object[] datos : datosPeliculas) {
            Panel rowPanel = new Panel(new FlowLayout(FlowLayout.LEFT, 0, 0));
            rowPanel.setBackground(Color.WHITE);
            rowPanel.setPreferredSize(new Dimension(totalTableWidth, 60));
            
            // --- CAMBIO: Extracción de datos con URL ---
            // Se asume que datos[0] es la URL de la imagen
            String urlPoster = (String) datos[0]; 
            String titulo = (String) datos[1];
            String genero = (String) datos[2];
            String resumen = (String) datos[3];
            boolean activo = (Boolean) datos[4];
            int id = (Integer) datos[5];

            // Celdas de datos
            // Pasamos la URL al método de la celda
            agregarCelda(rowPanel, null, COL_WIDTHS[0], true, urlPoster); 
            agregarCelda(rowPanel, new Label(titulo), COL_WIDTHS[1], false, null);
            agregarCelda(rowPanel, new Label(genero), COL_WIDTHS[2], false, null);
            agregarCelda(rowPanel, new Label(resumen), COL_WIDTHS[3], false, null);

            // Botón
            Panel pnlBoton = new Panel(new FlowLayout(FlowLayout.CENTER));
            pnlBoton.setPreferredSize(new Dimension(COL_WIDTHS[4], 60)); 
            
            Button btnAccion = new Button("Calificar");
            btnAccion.setPreferredSize(new Dimension(80, 30));
            btnAccion.setFont(new Font("Arial", Font.BOLD, 12));
            
            if (activo) {
                btnAccion.setBackground(new Color(30, 144, 255)); 
                btnAccion.setForeground(Color.WHITE);
                btnAccion.setActionCommand(String.valueOf(id)); 
                btnAccion.setCursor(new Cursor(Cursor.HAND_CURSOR));
            } else {
                btnAccion.setBackground(Color.LIGHT_GRAY);
                btnAccion.setForeground(Color.DARK_GRAY);
                btnAccion.setEnabled(false);
            }
            
            pnlBoton.add(btnAccion);
            rowPanel.add(pnlBoton);

            listaBotonesCalificar.add(btnAccion);

            Panel separador = new Panel();
            separador.setBackground(new Color(230,230,230));
            separador.setPreferredSize(new Dimension(totalTableWidth, 1));
            
            gbcRow.gridy = filaIndex * 2; 
            panelContenedorFilas.add(rowPanel, gbcRow);
            
            gbcRow.gridy = (filaIndex * 2) + 1;
            panelContenedorFilas.add(separador, gbcRow);
            
            filaIndex++;
        }
        
        GridBagConstraints gbcFiller = new GridBagConstraints();
        gbcFiller.gridx = 0; gbcFiller.gridy = filaIndex * 2; gbcFiller.weighty = 1.0; 
        panelContenedorFilas.add(new Panel(), gbcFiller);

        panelContenedorFilas.validate();
        scrollPaneTabla.validate();
    }

    // --- CAMBIO: Firma del método actualizada para recibir la URL ---
    private void agregarCelda(Panel row, Component comp, int ancho, boolean esPoster, String urlPoster) {
        if(esPoster) {
            Panel p = new Panel(new FlowLayout(FlowLayout.CENTER));
            p.setPreferredSize(new Dimension(ancho, 60));
            
            // --- CAMBIO: Uso de PosterPanel personalizado ---
            PosterPanel poster = new PosterPanel(urlPoster);
            p.add(poster);
            
            row.add(p);
        } else {
            if(comp instanceof Label) {
                ((Label)comp).setAlignment(Label.LEFT);
                ((Label)comp).setAlignment(Label.LEFT);
                comp.setFont(fontRowText);
            }
            comp.setPreferredSize(new Dimension(ancho, 60));
            row.add(comp);
        }
    }
    
    // --- NUEVA CLASE INTERNA: Para manejar la imagen ---
    class PosterPanel extends Panel {
        private Image image;

        public PosterPanel(String urlStr) {
            this.setBackground(new Color(220, 220, 220)); // Fondo de carga
            this.setPreferredSize(new Dimension(40, 50)); // Dimensiones exactas requeridas
            if (urlStr != null && !urlStr.isEmpty()) {
                try {
                    // Carga básica de AWT desde URL
                    URI uri = new URI(urlStr);
                    URL url = uri.toURL(); // Esto ya no está deprecated
                    image = Toolkit.getDefaultToolkit().createImage(url);
                } catch (Exception e) {
                    System.err.println("Error cargando imagen: " + urlStr);
                }
            }
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g); // Pinta el fondo gris primero
            if (image != null) {
                // Dibujamos la imagen escalada para llenar el panel (40x50)
                g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        }
    }
    
    // Setters
    public void setNombreUsuario(String nombre) { lblNombreUsuario.setText(nombre); validate(); }
    public Button getBtnBuscar() { return btnBuscar; }
    public Button getBtnCerrarSesion() { return btnCerrarSesion; }
    public void addBuscarListener(ActionListener listener){
        btnBuscar.addActionListener(listener);
    }
    public void addCerrarSesionListener(ActionListener listener){
        btnCerrarSesion.addActionListener(listener);
    }
    public String getTxtBuscador() { 
        return txtBuscador.getText(); }
    public ArrayList<Button> getListaBotonesCalificar() {
        return listaBotonesCalificar;
    }

}