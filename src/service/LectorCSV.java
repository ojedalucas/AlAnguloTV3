package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

// Imports necesarios
import Model.Domain.Pelicula;
import Model.Domain.GeneroPelicula;

public class LectorCSV {
    // --- MÉTODO DE LECTURA (Sin cambios) ---
    public static ArrayList<Pelicula> leerPeliculas(String rutaArchivo) {
        ArrayList<Pelicula> listaPeliculas = new ArrayList<>();
        String linea = "";
        String separador = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"; 
        InputStream is = LectorCSV.class.getResourceAsStream(rutaArchivo);
        if (is == null) {
            System.err.println("❌ No se encontró el archivo CSV en el classpath: " + rutaArchivo);
            return listaPeliculas; // devolvemos vacío para evitar errores
        }
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
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
                    System.err.println("Error procesando línea: " + linea);
                }
            }
        } catch (IOException e) {
            System.out.println("❌ Error de lectura: " + e.getMessage());
        }
        return listaPeliculas;
    }
}
