package View;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class MainMenuView extends Frame {

    // Variables de componentes
    private Label lblTituloPrincipal;
    private Label lblSubtitulo;
    private Label lblNombreUsuario;
    
    private TextField txtBuscador;
    private Button btnBuscar;
    private Button btnCerrarSesion;

    // Estructura de la Tabla
    private Panel panelContenedorFilas;
    private ScrollPane scrollPaneTabla;
    private Panel panelCabeceraTabla;

    // Lista de botones
    private ArrayList<Button> listaBotonesCalificar;

    // --- CONFIGURACIÓN DE ANCHOS FIJOS (en Píxeles) ---
    // Hemos aumentado los anchos para llenar la pantalla de 1024px
    // Poster(80) + Titulo(250) + Genero(150) + Resumen(400) + Boton(100) = 980px aprox
    private final int[] COL_WIDTHS = {80, 250, 150, 400, 100};

    public MainMenuView() {
        super("Plataforma de Streaming - Bienvenida");
        
        // Tamaño inicial
        setSize(1024, 768);
        
        // Importante: BorderLayout en el Frame principal estira los componentes
        setLayout(new BorderLayout());
        setBackground(Color.white);
        
        // Centrar ventana
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        listaBotonesCalificar = new ArrayList<>();

        inicializarComponentes();
        construirLayoutSuperior();
        construirLayoutCentral();
        
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
                System.exit(0);
            }
        });
    }

    private void inicializarComponentes() {
        lblTituloPrincipal = new Label("Bienvenido a la Plataforma de Streaming");
        lblTituloPrincipal.setFont(new Font("Arial", Font.BOLD, 24));

        lblSubtitulo = new Label("Seguro viste alguna de estas peliculas, haznos saber que te parecio dejando una resena");
        lblSubtitulo.setFont(new Font("Arial", Font.PLAIN, 14));

        lblNombreUsuario = new Label("Usuario Invitado");
        lblNombreUsuario.setFont(new Font("Arial", Font.BOLD, 14));
        lblNombreUsuario.setAlignment(Label.RIGHT);

        txtBuscador = new TextField(20);
        btnBuscar = new Button("Buscar");
        btnBuscar.setBackground(new Color(173, 216, 230));

        btnCerrarSesion = new Button("Cerrar sesion");
        btnCerrarSesion.setBackground(new Color(33, 150, 243));
        btnCerrarSesion.setForeground(Color.white);
    }

    private void construirLayoutSuperior() {
        Panel panelSuperior = new Panel(new GridBagLayout());
        panelSuperior.setBackground(Color.white);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 0, 20); 

        // Título
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridheight = 2;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        panelSuperior.add(lblTituloPrincipal, gbc);

        // Buscador
        Panel panelBuscador = new Panel(new FlowLayout(FlowLayout.RIGHT));
        panelBuscador.add(txtBuscador);
        panelBuscador.add(btnBuscar);

        gbc.gridx = 1; gbc.gridy = 0; gbc.gridheight = 1;
        gbc.weightx = 0.0;
        panelSuperior.add(panelBuscador, gbc);

        // Usuario
        Panel panelUsuario = new Panel(new GridLayout(2, 1));
        panelUsuario.add(lblNombreUsuario);
        panelUsuario.add(btnCerrarSesion);

        gbc.gridx = 1; gbc.gridy = 1;
        panelSuperior.add(panelUsuario, gbc);

        add(panelSuperior, BorderLayout.NORTH);
    }

    private void construirLayoutCentral() {
        Panel panelCentral = new Panel(new BorderLayout());
        panelCentral.setBackground(Color.white);

        // Subtítulo
        Panel panelSub = new Panel(new FlowLayout(FlowLayout.LEFT));
        panelSub.add(lblSubtitulo);
        panelCentral.add(panelSub, BorderLayout.NORTH);

        // ==========================================
        // 1. CABECERA FIJA
        // ==========================================
        // Usamos FlowLayout con gap 0 alineado a la izquierda para pegar columnas
        panelCabeceraTabla = new Panel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelCabeceraTabla.setBackground(new Color(220, 220, 220));
        
        String[] titulos = {"Poster", "Titulo", "Genero", "Resumen", "Accion"};
        
        for(int i=0; i<titulos.length; i++) {
            Panel celda = new Panel(new BorderLayout());
            celda.setPreferredSize(new Dimension(COL_WIDTHS[i], 40));
            
            Label l = new Label(titulos[i], Label.CENTER);
            l.setFont(new Font("Arial", Font.BOLD, 13));
            
            celda.add(l, BorderLayout.CENTER);
            panelCabeceraTabla.add(celda);
        }

        // ==========================================
        // 2. CUERPO SCROLLEABLE (Ocupar todo el espacio)
        // ==========================================
        scrollPaneTabla = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
        
        // Contenedor vertical de filas
        panelContenedorFilas = new Panel();
        // GridBagLayout aquí permite apilar verticalmente sin comprimir el alto
        panelContenedorFilas.setLayout(new GridBagLayout());
        panelContenedorFilas.setBackground(Color.white);
        
        scrollPaneTabla.add(panelContenedorFilas);

        // PANEL TABLA: Une cabecera (Norte) y Scroll (Centro)
        Panel panelTabla = new Panel(new BorderLayout());
        panelTabla.add(panelCabeceraTabla, BorderLayout.NORTH);
        panelTabla.add(scrollPaneTabla, BorderLayout.CENTER);

        // AL AÑADIR AL CENTER DEL LAYOUT PRINCIPAL, SE ESTIRA AUTOMÁTICAMENTE
        // Eliminamos paneles intermedios que restringían el tamaño.
        panelCentral.add(panelTabla, BorderLayout.CENTER);
        
        // Añadimos el panel central al Frame
        add(panelCentral, BorderLayout.CENTER);
    }

    // ===========================================================================
    // SETTERS Y GETTERS
    // ===========================================================================
    public void setTituloPrincipal(String texto) { lblTituloPrincipal.setText(texto); }
    public void setSubtitulo(String texto) { lblSubtitulo.setText(texto); }
    public void setNombreUsuario(String nombre) { 
        lblNombreUsuario.setText(nombre); 
        validate();
    }
    
    public Button getBtnBuscar() { return btnBuscar; }
    public Button getBtnCerrarSesion() { return btnCerrarSesion; }
    public TextField getTxtBuscador() { return txtBuscador; }
    public ArrayList<Button> getListaBotonesCalificar() { return listaBotonesCalificar; }

    // ===========================================================================
    // ACTUALIZAR TABLA
    // ===========================================================================
    public void actualizarListaPeliculas(Object[][] datosPeliculas) {
        panelContenedorFilas.removeAll();
        listaBotonesCalificar.clear();

        // Configuración para apilar filas verticalmente
        GridBagConstraints gbcRow = new GridBagConstraints();
        gbcRow.gridx = 0;
        gbcRow.weightx = 1.0;
        gbcRow.fill = GridBagConstraints.HORIZONTAL; // Estirar horizontalmente
        gbcRow.anchor = GridBagConstraints.NORTH;    // Anclar arriba
        
        int filaIndex = 0;

        for (Object[] datos : datosPeliculas) {
            // Cada fila es un panel con FlowLayout ajustado a los anchos fijos
            Panel rowPanel = new Panel(new FlowLayout(FlowLayout.LEFT, 0, 0));
            rowPanel.setBackground(Color.white);
            
            // --- 0. POSTER ---
            Panel pnlPoster = new Panel(new FlowLayout(FlowLayout.CENTER));
            pnlPoster.setPreferredSize(new Dimension(COL_WIDTHS[0], 60));
            
            Panel posterBox = new Panel();
            posterBox.setBackground(Color.lightGray);
            posterBox.setPreferredSize(new Dimension(40, 50));
            pnlPoster.add(posterBox);
            rowPanel.add(pnlPoster);

            // --- 1. TITULO ---
            Label lblTitulo = new Label((String) datos[0], Label.LEFT);
            lblTitulo.setPreferredSize(new Dimension(COL_WIDTHS[1], 60));
            lblTitulo.setFont(new Font("Arial", Font.BOLD, 12));
            rowPanel.add(lblTitulo);

            // --- 2. GENERO ---
            Label lblGenero = new Label((String) datos[1], Label.LEFT);
            lblGenero.setPreferredSize(new Dimension(COL_WIDTHS[2], 60));
            rowPanel.add(lblGenero);

            // --- 3. RESUMEN ---
            Label lblResumen = new Label((String) datos[2], Label.LEFT);
            lblResumen.setPreferredSize(new Dimension(COL_WIDTHS[3], 60));
            rowPanel.add(lblResumen);

            // --- 4. BOTON ---
            Panel pnlBoton = new Panel(new FlowLayout(FlowLayout.CENTER));
            pnlBoton.setPreferredSize(new Dimension(COL_WIDTHS[4], 60));
            
            Button btnAccion = new Button("Calificar");
            btnAccion.setPreferredSize(new Dimension(80, 30));
            boolean activo = (Boolean) datos[3];
            
            if (activo) {
                btnAccion.setBackground(new Color(33, 150, 243));
                btnAccion.setForeground(Color.white);
            } else {
                btnAccion.setBackground(Color.gray);
                btnAccion.setEnabled(false);
            }
            
            pnlBoton.add(btnAccion);
            rowPanel.add(pnlBoton);
            listaBotonesCalificar.add(btnAccion);

            // Añadir fila al contenedor vertical
            gbcRow.gridy = filaIndex;
            panelContenedorFilas.add(rowPanel, gbcRow);
            
            filaIndex++;
        }

        // Espaciador final (Push) para que las filas se queden arriba si hay pocas
        GridBagConstraints gbcFiller = new GridBagConstraints();
        gbcFiller.gridx = 0; gbcFiller.gridy = filaIndex;
        gbcFiller.weighty = 1.0; // Este peso absorbe el espacio vertical sobrante
        panelContenedorFilas.add(new Panel(), gbcFiller);

        // Validar cambios para repintar
        panelContenedorFilas.validate();
        scrollPaneTabla.validate();
    }


    // MAIN DE PRUEBA
    public static void main(String[] args) {
        MainMenuView v = new MainMenuView();
        v.setVisible(true);
        v.setNombreUsuario("Juan Perez");
        
        Object[][] data = {
            {"Titanic", "Drama", "Un barco gigante se hunde...", true},
            {"Avatar", "Sci-Fi", "Aliens azules en Pandora...", true},
            {"Shrek 2", "Comedia", "Viaje a muy muy lejano...", true},
            {"Docu X", "Docu", "No disponible", false},
            {"Matrix", "Accion", "Hacker descubre la verdad...", true},
            {"Inception", "Sci-Fi", "Suenos compartidos...", true},
            {"El Padrino", "Crimen", "La familia Corleone...", true},
            {"Toy Story", "Animacion", "Juguetes vivos...", true},
            {"Joker", "Drama", "Origen del villano...", true},
            {"Coco", "Animacion", "Mundo de los muertos...", true},
            {"Avengers", "Accion", "Heroes unidos...", true},
            {"Parasite", "Suspenso", "Lucha de clases...", true},
            {"Gladiador", "Accion", "Roma antigua...", true},
            {"Cars", "Infantil", "Autos parlantes...", true},
            {"Up", "Animacion", "Casa con globos...", true},
            {"Interstellar", "Sci-Fi", "Viaje por agujero negro...", true},
            {"Batman", "Accion", "El caballero de la noche...", true},
            {"Frozen", "Animacion", "El reino de hielo...", true}
        };
        
        v.actualizarListaPeliculas(data);
    }
}