package View;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class MainMenuView extends Frame {

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
    // Esta lista almacenará las referencias a los botones creados dinámicamente
    private ArrayList<Button> listaBotonesCalificar;

    // Configuración de anchos fijos de columnas
    private final int[] COL_WIDTHS = {80, 250, 150, 400, 100};

    public MainMenuView() {
        super("Plataforma de Streaming - Bienvenida");
        
        setSize(1024, 768);
        setLayout(new BorderLayout());
        setBackground(Color.white);
        
        // Centrar ventana
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        // Inicializamos la lista vacía
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

        // Cabecera Fija
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

        // Cuerpo Scrolleable
        scrollPaneTabla = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
        panelContenedorFilas = new Panel();
        panelContenedorFilas.setLayout(new GridBagLayout());
        panelContenedorFilas.setBackground(Color.white);
        
        scrollPaneTabla.add(panelContenedorFilas);

        Panel panelTabla = new Panel(new BorderLayout());
        panelTabla.add(panelCabeceraTabla, BorderLayout.NORTH);
        panelTabla.add(scrollPaneTabla, BorderLayout.CENTER);

        panelCentral.add(panelTabla, BorderLayout.CENTER);
        add(panelCentral, BorderLayout.CENTER);
    }

    // ===========================================================================
    // MÉTODO PARA OBTENER LA LISTA DE BOTONES (CRUCIAL PARA EL CONTROLADOR)
    // ===========================================================================
    
    /**
     * Retorna la lista de botones generados dinámicamente en la tabla.
     * El Controlador usará esta lista para añadir ActionListener a cada botón.
     */
    public ArrayList<Button> getListaBotonesCalificar() {
        return listaBotonesCalificar;
    }

    // ===========================================================================
    // ACTUALIZACIÓN DE DATOS
    // ===========================================================================

    public void actualizarListaPeliculas(Object[][] datosPeliculas) {
        panelContenedorFilas.removeAll();
        
        // 1. Limpiamos la lista anterior para no guardar referencias a botones viejos
        listaBotonesCalificar.clear();

        GridBagConstraints gbcRow = new GridBagConstraints();
        gbcRow.gridx = 0; gbcRow.weightx = 1.0;
        gbcRow.fill = GridBagConstraints.HORIZONTAL; gbcRow.anchor = GridBagConstraints.NORTH;
        
        int filaIndex = 0;

        for (Object[] datos : datosPeliculas) {
            Panel rowPanel = new Panel(new FlowLayout(FlowLayout.LEFT, 0, 0));
            rowPanel.setBackground(Color.white);
            
            String titulo = (String) datos[0];
            String genero = (String) datos[1];
            String resumen = (String) datos[2];
            boolean activo = (Boolean) datos[3];

            // Poster, Título, Género, Resumen (Mismo código de layout anterior...)
            agregarCelda(rowPanel, new Panel(), COL_WIDTHS[0], true); // Placeholder poster
            agregarCelda(rowPanel, new Label(titulo), COL_WIDTHS[1], false);
            agregarCelda(rowPanel, new Label(genero), COL_WIDTHS[2], false);
            agregarCelda(rowPanel, new Label(resumen), COL_WIDTHS[3], false);

            // --- CREACIÓN DEL BOTÓN ---
            Panel pnlBoton = new Panel(new FlowLayout(FlowLayout.CENTER));
            pnlBoton.setPreferredSize(new Dimension(COL_WIDTHS[4], 60));
            
            Button btnAccion = new Button("Calificar");
            btnAccion.setPreferredSize(new Dimension(80, 30));
            
            if (activo) {
                btnAccion.setBackground(new Color(33, 150, 243));
                btnAccion.setForeground(Color.white);
                // IMPORTANTE: Seteamos el comando con el Título o ID único de la película
                btnAccion.setActionCommand(titulo); 
            } else {
                btnAccion.setBackground(Color.gray);
                btnAccion.setEnabled(false);
            }
            
            pnlBoton.add(btnAccion);
            rowPanel.add(pnlBoton);

            // 2. AGREGAMOS EL BOTÓN A LA LISTA QUE DEVOLVEREMOS AL CONTROLADOR
            listaBotonesCalificar.add(btnAccion);

            gbcRow.gridy = filaIndex;
            panelContenedorFilas.add(rowPanel, gbcRow);
            filaIndex++;
        }
        
        GridBagConstraints gbcFiller = new GridBagConstraints();
        gbcFiller.gridx = 0; gbcFiller.gridy = filaIndex; gbcFiller.weighty = 1.0; 
        panelContenedorFilas.add(new Panel(), gbcFiller);

        panelContenedorFilas.validate();
        scrollPaneTabla.validate();
    }

    // Método auxiliar para limpiar código de añadir celdas (Labels o Paneles)
    private void agregarCelda(Panel row, Component comp, int ancho, boolean esPoster) {
        if(esPoster) {
            Panel p = new Panel(new FlowLayout(FlowLayout.CENTER));
            p.setPreferredSize(new Dimension(ancho, 60));
            Panel box = new Panel(); box.setBackground(Color.lightGray); box.setPreferredSize(new Dimension(40, 50));
            p.add(box);
            row.add(p);
        } else {
            // Asumimos que es Label
            if(comp instanceof Label) {
                ((Label)comp).setAlignment(Label.LEFT);
                comp.setFont(new Font("Arial", Font.BOLD, 12));
            }
            comp.setPreferredSize(new Dimension(ancho, 60));
            row.add(comp);
        }
    }
    
    // Setters
    public void setNombreUsuario(String nombre) { lblNombreUsuario.setText(nombre); validate(); }
    public Button getBtnBuscar() { return btnBuscar; }
    public Button getBtnCerrarSesion() { return btnCerrarSesion; }
    public TextField getTxtBuscador() { return txtBuscador; }

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