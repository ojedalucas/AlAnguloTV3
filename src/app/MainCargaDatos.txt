package App;

import java.util.Scanner;
import java.time.LocalDateTime;
import java.util.Arrays; 

// Imports de Modelo
import Model.Domain.DatosPersonales;
import Model.Domain.Pelicula;
import Model.Domain.Resenia;
import Model.Domain.GeneroPelicula;
import Model.Domain.Usuario; // <-- Nuevo Import

// Imports de Datos y Conexión
import Data.ConnectionManager;
import Data.Dao.DatosPersonalesDAOjdbl;
import Data.Dao.PeliculaDAOjdbl;
import Data.Dao.ReseniaDAOjdbl;
import Data.Dao.UsuarioDAOjdbl; // <-- Asumo que este es el nombre de tu DAO

public class MainCargaDatos {

    public static void main(String[] args) {
        // 1. Iniciar Base de Datos
        try {
            ConnectionManager.iniciar();
            System.out.println("✅ Base de datos iniciada correctamente.");
        } catch (Exception e) {
            System.out.println("❌ Error crítico al iniciar la base de datos: " + e.getMessage());
            return;
        }

        Scanner scanner = new Scanner(System.in);
        int opcion = -1;

        // 2. Bucle del Menú Principal
        while (opcion != 0) {
            System.out.println("\n=== SISTEMA DE GESTIÓN ===");
            System.out.println("1. Agregar Persona");
            System.out.println("2. Agregar Película");
            System.out.println("3. Agregar Usuario");
            System.out.println("4. Agregar Reseña"); // <-- Nueva Opción
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                String input = scanner.nextLine();
                opcion = Integer.parseInt(input);

                switch (opcion) {
                    case 1: agregarDatosPersonales(scanner); break;
                    case 2: agregarPelicula(scanner); break;
                    case 3: agregarUsuario(scanner); break;
                    case 4: agregarResenia(scanner); break; // <-- Llamada al nuevo método
                    case 0: System.out.println("Saliendo..."); break;
                    default: System.out.println("⚠️ Opción no válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Por favor, ingrese un número válido.");
            }
        }
        scanner.close();
    }

    // --- MÉTODO PARA PERSONAS ---
    public static void agregarDatosPersonales(Scanner scanner) {
        System.out.println("\n--- Nueva Persona ---");
        try {
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Apellido: ");
            String apellido = scanner.nextLine();
            System.out.print("DNI (sin puntos): ");
            int dni = Integer.parseInt(scanner.nextLine());

            DatosPersonales persona = new DatosPersonales(0, nombre, apellido, dni);
            DatosPersonalesDAOjdbl dao = new DatosPersonalesDAOjdbl();
            
            dao.cargarDatos(persona);
            System.out.println("✅ Persona guardada exitosamente.");

        } catch (NumberFormatException e) {
            System.out.println("❌ Error: El DNI debe ser numérico.");
        } catch (Exception e) {
            System.out.println("❌ Error al guardar persona: " + e.getMessage());
        }
    }

    // --- MÉTODO PARA PELÍCULAS ---
    public static void agregarPelicula(Scanner scanner) {
        System.out.println("\n--- Nueva Película ---");
        try {
            System.out.print("Título: ");
            String titulo = scanner.nextLine();

            System.out.println("Géneros disponibles: " + Arrays.toString(GeneroPelicula.values()));
            System.out.print("Ingrese el Género: ");
            String generoInput = scanner.nextLine();
            GeneroPelicula genero = GeneroPelicula.desdeCsv(generoInput);
            
            System.out.print("Director: ");
            String director = scanner.nextLine();
            System.out.print("Resumen: ");
            String resumen = scanner.nextLine();
            System.out.print("Duración (minutos): ");
            double duracion = Double.parseDouble(scanner.nextLine());
            System.out.print("Rating: ");
            float rating = Float.parseFloat(scanner.nextLine());
            System.out.print("Año de estreno: ");
            int anio = Integer.parseInt(scanner.nextLine());
            System.out.print("URL del Poster (Enter para vacío): ");
            String posterInput = scanner.nextLine();
            String poster = posterInput.trim().isEmpty() ? "Sin poster" : posterInput;

            Pelicula nuevaPelicula = new Pelicula(0, genero, titulo, resumen, director, duracion, rating, anio, poster);
            PeliculaDAOjdbl dao = new PeliculaDAOjdbl();
            dao.cargarPelicula(nuevaPelicula);

            System.out.println("✅ Película guardada exitosamente.");

        } catch (Exception e) {
            System.out.println("❌ Error al guardar película: " + e.getMessage());
        }
    }

    // --- NUEVO MÉTODO PARA USUARIOS ---
    public static void agregarUsuario(Scanner scanner) {
        System.out.println("\n--- Nuevo Usuario ---");
        try {
            System.out.print("Nombre de Usuario (Nick): ");
            String nick = scanner.nextLine();

            System.out.print("Contraseña: ");
            String pass = scanner.nextLine();

            System.out.print("Email: ");
            String email = scanner.nextLine();

            // Aquí está la clave: Un Usuario NECESITA una Persona vinculada.
            System.out.println("IMPORTANTE: Para crear un usuario, debe asociarlo a una Persona existente.");
            System.out.print("Ingrese el ID de la Persona (DatosPersonales): ");
            int idPersona = Integer.parseInt(scanner.nextLine());

            // 1. Creamos un objeto DatosPersonales "dummy" solo con el ID.
            // Esto es para cumplir con la estructura de tu clase Usuario.
            DatosPersonales personaVinculada = new DatosPersonales();
            personaVinculada.setId(idPersona);

            // 2. Creamos el Usuario
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setNombreUsuario(nick);
            nuevoUsuario.setContrasenia(pass);
            nuevoUsuario.setEmail(email);
            nuevoUsuario.setDatosPersonales(personaVinculada);

            // 3. Llamamos al DAO
            UsuarioDAOjdbl dao = new UsuarioDAOjdbl();
            System.out.println("Guardando usuario...");
            
            dao.cargarUsuario(nuevoUsuario);

            System.out.println("✅ Usuario '" + nick + "' creado exitosamente vinculado a la Persona ID: " + idPersona);

        } catch (NumberFormatException e) {
            System.out.println("❌ Error: El ID de la persona debe ser un número entero.");
        } catch (Exception e) {
            System.out.println("❌ Error al guardar usuario: " + e.getMessage());
            // Tip extra para el programador
            System.out.println("(Verifique si el ID de la persona realmente existe en la base de datos)"); 
            e.printStackTrace();
        }
    }
    // --- NUEVO MÉTODO PARA RESEÑAS ---
    public static void agregarResenia(Scanner scanner) {
        System.out.println("\n--- Nueva Reseña ---");
        try {
            // 1. Vincular Usuario
            System.out.print("Ingrese el ID del Usuario que opina: ");
            int idUsuario = Integer.parseInt(scanner.nextLine());

            // 2. Vincular Película
            System.out.print("Ingrese el ID de la Película a calificar: ");
            int idPelicula = Integer.parseInt(scanner.nextLine());

            // 3. Datos de la reseña
            System.out.print("Calificación (1 a 5, o 1 a 10): ");
            int calificacion = Integer.parseInt(scanner.nextLine());

            System.out.print("Comentario: ");
            String comentario = scanner.nextLine();

            // 4. Configuración automática
            // Usamos la fecha y hora actual del sistema
            LocalDateTime fechaActual = LocalDateTime.now();
            
            // Por defecto la reseña no está aprobada hasta que un admin la revise
            boolean aprobado = false; 

            // 5. Crear Objeto
            Resenia nuevaResenia = new Resenia();
            nuevaResenia.setIdUsuario(idUsuario);
            nuevaResenia.setIdPelicula(idPelicula);
            nuevaResenia.setCalificacion(calificacion);
            nuevaResenia.setComentario(comentario);
            nuevaResenia.setFechaHora(fechaActual);
            nuevaResenia.setAprobado(aprobado);

            // 6. Guardar en BD
            ReseniaDAOjdbl dao = new ReseniaDAOjdbl();
            dao.cargarResenia(nuevaResenia);

            System.out.println("✅ Reseña agregada correctamente.");
            System.out.println(nuevaResenia.toStringSinID());

        } catch (NumberFormatException e) {
            System.out.println("❌ Error: Los IDs y la calificación deben ser números enteros.");
        } catch (Exception e) {
            System.out.println("❌ Error al guardar reseña: " + e.getMessage());
            e.printStackTrace();
        }
    }
}