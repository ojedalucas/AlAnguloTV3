package model.domain;

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
    CRIMEN,     
    FAMILIAR,   
    BELICA,     
    HISTORIA,   
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

    @Override
public String toString() {
    switch(this) {
        case ACCION: return "Acción";
        case AVENTURA: return "Aventura";
        case COMEDIA: return "Comedia";
        case DRAMA: return "Drama";
        case CIENCIA_FICCION: return "Ciencia Ficción";
        case TERROR: return "Terror";
        case SUSPENSO: return "Suspenso";
        case DOCUMENTAL: return "Documental";
        case ROMANCE: return "Romance";
        case ANIMACION: return "Animación";
        case MUSICAL: return "Musical";
        case FANTASIA: return "Fantasía";
        case MISTERIO: return "Misterio";
        case WESTERN: return "Western";
        case BIOGRAFICA: return "Biográfica";
        case CRIMEN: return "Crimen";
        case FAMILIAR: return "Familiar";
        case BELICA: return "Bélica";
        case HISTORIA: return "Historia";
        case OTROS: return "Otros";
        default: return this.name();
    }
}
}
