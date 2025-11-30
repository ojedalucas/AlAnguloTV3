package App;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

// Imports necesarios
import Data.ConnectionManager;
import Data.Dao.PeliculaDAOjdbl;
import Model.Domain.Pelicula;
import Model.Domain.GeneroPelicula;

public class LectorCSV {

    private static final String RUTA_ARCHIVO = "src/CSV/movies_database.csv";

    // --- MAIN DE PRUEBA E INSERCIÓN ---
    public static void main(String[] args) {
        System.out.println("--- Inicio del proceso ---");

        // 1. Conectar a la BD
        try {
            ConnectionManager.iniciar();
            System.out.println("✅ Conexión establecida.");
        } catch (Exception e) {
            System.out.println("❌ Error de conexión: " + e.getMessage());
            return;
        }

        // 2. Leer CSV
        ArrayList<Pelicula> misPeliculas = leerPeliculas();

        if (misPeliculas.isEmpty()) {
            System.out.println("❌ La lista está vacía.");
        } else {
            // 3. Obtener la primera película
            Pelicula p = misPeliculas.get(0);
            
            // --- NUEVO: IMPRIMIR TODOS LOS DATOS ANTES DE GUARDAR ---
            System.out.println("\n------------------------------------------------");
            System.out.println("       DATOS A CARGAR EN BASE DE DATOS          ");
            System.out.println("------------------------------------------------");
            System.out.println("Título:    " + p.getTitulo());
            System.out.println("Año:       " + p.getAnio());
            System.out.println("Género:    " + p.getGenero());
            System.out.println("Director:  " + p.getDirector());
            System.out.println("Duración:  " + p.getDuracion());
            System.out.println("Rating:    " + p.getRatingPromedio());
            System.out.println("Resumen:   " + p.getResumen());
            System.out.println("Poster URL:" + p.getPoster());
            System.out.println("------------------------------------------------\n");

            // 4. Guardar en BD
            try {
                System.out.println("Guardando en SQL...");
                PeliculaDAOjdbl dao = new PeliculaDAOjdbl();
                dao.cargarPelicula(p);
                
                System.out.println("✅ ¡ÉXITO! Película guardada correctamente.");

            } catch (Exception e) {
                System.out.println("❌ Error al guardar en SQL: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    // --- MÉTODO DE LECTURA (Sin cambios) ---
    public static ArrayList<Pelicula> leerPeliculas() {
        ArrayList<Pelicula> listaPeliculas = new ArrayList<>();
        String linea = "";
        String separador = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"; 

        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_ARCHIVO))) {
            br.readLine(); 

            while ((linea = br.readLine()) != null) {
                try {
                    String[] datos = linea.split(separador, -1); 
                    if (datos.length < 9) continue; 

                    String fechaStr = datos[0].replace("\"", ""); 
                    int anio = 0;
                    if (fechaStr.length() >= 4) {
                        try {
                            anio = Integer.parseInt(fechaStr.substring(0, 4));
                        } catch (Exception e) { anio = 0; }
                    }

                    String titulo = datos[1].replace("\"", "").trim();
                    String resumen = datos[2].replace("\"", "").trim();

                    float rating = 0;
                    try {
                        rating = Float.parseFloat(datos[5]);
                    } catch (Exception e) { rating = 0; }

                    String generoRaw = datos[7].replace("\"", "").trim();
                    if (generoRaw.contains(",")) {
                        generoRaw = generoRaw.split(",")[0]; 
                    }
                    GeneroPelicula genero = GeneroPelicula.desdeCsv(generoRaw);

                    String poster = datos[8].replace("\"", "").trim();
                    if (poster.isEmpty()) poster = "Sin poster";

                    String director = "Desconocido";
                    double duracion = 0.0; 

                    Pelicula p = new Pelicula();
                    p.setTitulo(titulo);
                    p.setAnio(anio);
                    p.setResumen(resumen);
                    p.setRatingPromedio(rating);
                    p.setGenero(genero);
                    p.setPoster(poster);
                    p.setDirector(director);
                    p.setDuracion(duracion);

                    listaPeliculas.add(p);

                } catch (Exception eLine) {
                    continue;
                }
            }
        } catch (IOException e) {
            System.out.println("❌ Error de lectura: " + e.getMessage());
        }
        return listaPeliculas;
    }
}
