package Model.Domain;

public enum GeneroPelicula {
    ACCION,
    AVENTURA,
    COMEDIA,
    DRAMA,
    CIENCIA_FICCION,
    TERROR,
    SUSPENSO,
    DOCUMENTAL,
    ROMANCE,
    ANIMACION,
    MUSICAL,
    FANTASIA,
    MISTERIO,
    WESTERN,
    BIOGRAFICA,
    CRIMEN,     // Agregado
    FAMILIAR,   // Agregado
    BELICA,     // Agregado
    HISTORIA,   // Agregado
    OTROS;

    // Método estático para convertir el String en Inglés del CSV al Enum en Español
    public static GeneroPelicula desdeCsv(String generoEnIngles) {
        if (generoEnIngles == null || generoEnIngles.isEmpty()) {
            return OTROS;
        }

        // Normalizamos el texto: mayúsculas y sin espacios extra
        String texto = generoEnIngles.toUpperCase().trim();

        switch (texto) {
            case "ACTION":          return ACCION;
            case "ADVENTURE":       return AVENTURA;
            case "COMEDY":          return COMEDIA;
            case "DRAMA":           return DRAMA;
            case "SCIENCE FICTION": 
            case "SCI-FI":          return CIENCIA_FICCION;
            case "HORROR":          return TERROR;
            case "THRILLER":        return SUSPENSO;
            case "DOCUMENTARY":     return DOCUMENTAL;
            case "ROMANCE":         return ROMANCE;
            case "ANIMATION":       return ANIMACION;
            case "MUSIC":           return MUSICAL;
            case "FANTASY":         return FANTASIA;
            case "MYSTERY":         return MISTERIO;
            case "WESTERN":         return WESTERN;
            case "HISTORY":         return HISTORIA;
            case "WAR":             return BELICA;
            case "CRIME":           return CRIMEN;
            case "FAMILY":          return FAMILIAR;
            // A veces Biográfica viene como "Biography" o no existe y es Drama/Historia
            case "BIOGRAPHY":       return BIOGRAFICA;
            
            default:
                // Intentamos buscar coincidencia exacta por si el CSV ya viene en español
                try {
                    return GeneroPelicula.valueOf(texto);
                } catch (IllegalArgumentException e) {
                    return OTROS;
                }
        }
    }
}
