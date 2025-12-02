package service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

import Model.Domain.GeneroPelicula;
import Model.Domain.Pelicula;

public class ConsultaPeliculasOMDb {

    private static final String API_KEY = "TU_API";

    public static void main(String[] args) {
        String titulo = "Jurassic Park"; // Reemplazar por el título a buscar
        consultarPelicula(titulo);
    }

    public static Pelicula consultarPelicula(String titulo) {
        try {
            String url = "https://www.omdbapi.com/?t=" + titulo.replace(" ", "+") + "&apikey=" + API_KEY;

            // Crear cliente y solicitud
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            // Enviar solicitud        
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Procesar respuesta
            JSONObject json = new JSONObject(response.body());
            if (json.has("Response") && json.getString("Response").equals("True")) {
                // System.out.println("🎬 Título: " + json.getString("Title"));
                // System.out.println("📅 Año: " + json.getString("Year"));
                // System.out.println("📝 Sinopsis: " + json.getString("Plot"));
                
                String runtimeStr = json.getString("Runtime"); // Ej: "127 min"
                double duracion = 0;
                if (!runtimeStr.equals("N/A")) { 
                   String duracionStr = runtimeStr.replace(" min", ""); // quitamos " min"
                   duracion = Double.parseDouble(duracionStr);
}
                Pelicula p = new Pelicula();
                p.setTitulo(json.getString("Title"));
                p.setResumen(json.getString("Plot"));
                p.setDuracion(duracion);
                p.setDirector(json.getString("Director"));
                p.setAnio(Integer.parseInt(json.getString("Year")));
                p.setPoster(json.getString("Poster"));
                p.setRatingPromedio(Float.parseFloat(json.getString("imdbRating")));

                // OMDb no manda género como caracter → ponés OTROS o lo que quieras
                p.setGenero(GeneroPelicula.OTROS);

                return p;
            } else {System.out.println("❌ Película no encontrada o error en la consulta.");
            }              
        } catch (Exception e) {
            System.out.println("Error al consultar la API: " + e.getMessage());
        }

        return null; // si falla
    }
}