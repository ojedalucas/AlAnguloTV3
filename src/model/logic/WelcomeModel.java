package Model.Logic;

import java.sql.SQLException;

import Data.Dao.UsuarioDAO;
import Data.Dao.UsuarioDAOjdbl;
import Model.Domain.Usuario;
import Util.Exceptions.CamposVaciosException;
import Util.Exceptions.FormatoEmailException;
import Util.Exceptions.LoginIncorrectoException;

public class WelcomeModel {
  private UsuarioDAO verificador;

    public WelcomeModel (){
        verificador= new UsuarioDAOjdbl();
    }

    public Usuario iniciarSesion (String email, String contrasenia) throws SQLException, CamposVaciosException, FormatoEmailException, LoginIncorrectoException {
        if (email.isBlank() || contrasenia.isBlank())
            throw new CamposVaciosException();
        if (!formatoEmail(email))
            throw new FormatoEmailException();
        Usuario user = verificador.iniciarSesion(email, contrasenia);
        if (user == null)
            throw new LoginIncorrectoException();
        return user;
    }

    public boolean formatoEmail(String email){
        boolean formatoValido = false;
        if (email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            formatoValido = true;
        }
        return formatoValido; 
    }
}
