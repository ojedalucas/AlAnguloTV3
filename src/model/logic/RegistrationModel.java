package model.logic;

import java.sql.SQLException;

import Util.Exceptions.*;
import data.dao.*;
import model.domain.*;

public class RegistrationModel {
    private DatosPersonalesDAO verificadorDatos;
    private UsuarioDAO verificadorUsuario;

    public RegistrationModel (){
        verificadorDatos= new DatosPersonalesDAOjdbl ();
        verificadorUsuario= new UsuarioDAOjdbl ();
    }


    public boolean existeDNI (int dni) throws SQLException{
        boolean existe= verificadorDatos.existeDNI(dni);
        return existe;
    }

    public boolean formatoEmail(String email){
        boolean formatoValido = false;
        if (email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            formatoValido = true;
        }
        return formatoValido; 
    }

    public boolean existeEmail(String email) throws SQLException{
        boolean existe=verificadorUsuario.verificarMail(email);
        return existe;
    }

    public Usuario registrarUsuario(String username, String nombre, String apellido, String DNI, String email, String contrasenia) throws SQLException, CamposVaciosException,
    FormatoEmailException, FormatoDniException, DniYaExisteException, EmailYaExisteException{
        int dni;
        if (nombre.isBlank() || apellido.isBlank() || DNI.isBlank() || email.isBlank() || contrasenia.isBlank())
            throw new CamposVaciosException();
        if (!formatoEmail(email))
            throw new FormatoEmailException();
        try {
            dni = Integer.parseInt(DNI.trim());
        } catch (NumberFormatException e) {
            throw new FormatoDniException();
        }
        if (existeDNI(dni))
            throw new DniYaExisteException();
        if (existeEmail(email))
            throw new EmailYaExisteException();
        DatosPersonales datosP = new DatosPersonales(0, nombre, apellido, dni);
        Usuario usuario= new Usuario(username, contrasenia, email, 0, datosP);
        verificadorUsuario.cargarUsuario(usuario);
        return usuario;
    }
}
