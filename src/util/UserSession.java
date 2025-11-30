package Util;

import model.domain.Usuario;

public class UserSession {
    private static Usuario usuarioActual;

    public static void iniciarSesion(Usuario u){
        usuarioActual = u;
    }

    public static void cerrarSesion(){
        usuarioActual = null;
    }
    
    public static Usuario getUsuarioActual(){
        return usuarioActual;
    }

    public static boolean haySesion(){
        return usuarioActual != null;
    }
}
