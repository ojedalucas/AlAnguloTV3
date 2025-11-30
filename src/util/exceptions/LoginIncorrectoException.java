package util.exceptions;

public class LoginIncorrectoException extends Exception{
    public LoginIncorrectoException(){
        super("Email o contraseña incorrectos.");
    }
}
