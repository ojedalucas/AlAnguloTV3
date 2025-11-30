package App;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import Model.Domain.Pelicula;
import Model.Domain.GeneroPelicula;

public class LectorCSV {

    // Ruta fija al archivo
    private static final String RUTA_ARCHIVO = "src/CSV/movies_database.csv";

    // --- MËTODO PRINCIPAL DE PRUEBA ---
    public static void main(String[] args) {
        System.out.println("--- Probando LectorCSV ---");
        
        // 1. Llamamos al método
        ArrayList<Pelicula> misPeliculas = leerPeliculas();

        // 2. Verificamos si trajo algo
        if (misPeliculas.isEmpty()) {
            System.out.println("❌ La lista está vacía. Verifica la ruta o el contenido del archivo.");
        } else {
            System.out.println("✅ Se leyeron un total de: " + misPeliculas.size() + " películas.\n");

            System.out.println("--- Mostrando las primeras 3 ---");
            
            // 3. Imprimimos las primeras 3 (o menos si el archivo es muy chico)
            int limite = Math.min(3, misPeliculas.size());
            
            for (int i = 0; i < limite; i++) {
                Pelicula p = misPeliculas.get(i);
                System.out.println("Pelicula #" + (i + 1));
                System.out.println("Titulo: " + p.getTitulo());
                System.out.println("Año: " + p.getAnio());
                System.out.println("Género: " + p.getGenero());
                System.out.println("---------------------------");
            }
        }
    }
    // ----------------------------------


    public static ArrayList<Pelicula> leerPeliculas() {
        ArrayList<Pelicula> listaPeliculas = new ArrayList<>();
        String linea = "";
        String separador = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"; 

        // System.out.println("Leyendo archivo desde: " + RUTA_ARCHIVO);

        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_ARCHIVO))) {
            
            br.readLine(); // Saltar encabezado

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

                    // Valores por defecto
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
            System.out.println("❌ Error al leer el archivo: " + e.getMessage());
        }

        return listaPeliculas;
    }
}
