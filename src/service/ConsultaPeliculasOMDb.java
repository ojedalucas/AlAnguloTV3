package service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

import Model.Domain.GeneroPelicula;
import Model.Domain.Pelicula;

public class ConsultaPeliculasOMDb {

    // Recuerda poner tu API KEY real aquí para que funcione
    private static final String API_KEY = "470d833f";

    public static Pelicula consultarPelicula(String titulo) {
        try {
            String url = "https://www.omdbapi.com/?t=" + titulo.replace(" ", "+") + "&apikey=" + API_KEY;

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JSONObject json = new JSONObject(response.body());

            // CASO 1: ÉXITO (Response = True)
            if (json.has("Response") && json.getString("Response").equals("True")) {
                
                String runtimeStr = json.getString("Runtime"); // Ej: "127 min"
                double duracion = 0;
                if (!runtimeStr.equals("N/A")) { 
                    String duracionStr = runtimeStr.replace(" min", ""); 
                    try {
                        duracion = Double.parseDouble(duracionStr);
                    } catch (NumberFormatException e) {
                        duracion = 0; // Por seguridad si falla el parseo
                    }
                }

                Pelicula p = new Pelicula();
                p.setTitulo(json.getString("Title"));
                p.setResumen(json.getString("Plot"));
                p.setDuracion(duracion);
                p.setDirector(json.getString("Director"));
                
                // Validación extra por si el año viene con rangos (ej: "2010-2014")
                String anioStr = json.getString("Year").substring(0, 4); 
                try {
                    p.setAnio(Integer.parseInt(anioStr));
                } catch (NumberFormatException e) {
                     p.setAnio(0);
                }
                
                p.setPoster(json.getString("Poster"));
                
                // Validación para rating (a veces viene "N/A")
                String rating = json.getString("imdbRating");
                if(!rating.equals("N/A")){
                     p.setRatingPromedio(Float.parseFloat(rating));
                } else {
                     p.setRatingPromedio(0);
                }

                p.setGenero(GeneroPelicula.OTROS);

                return p;

            } else {
                // CASO 2: LA API RESPONDE PERO CON ERROR (Ej: "Movie not found" o "Invalid API Key")
                Pelicula pError = new Pelicula();
                pError.setTitulo(titulo);
                pError.setAnio(0);
                // Obtenemos el mensaje exacto que dio la web (ej: "Movie not found!")
                String mensajeError = json.has("Error") ? json.getString("Error") : "Error desconocido en la respuesta API";
                pError.setResumen("Error: " + mensajeError);
                return pError;
            }

        } catch (Exception e) {
            // CASO 3: ERROR DE CONEXIÓN O EXCEPCIÓN DE JAVA
            Pelicula pError = new Pelicula();
            pError.setTitulo(titulo);
            pError.setAnio(0);
            pError.setResumen("Error al acceder a la web: " + e.getMessage());
            return pError;
        }
    }
}