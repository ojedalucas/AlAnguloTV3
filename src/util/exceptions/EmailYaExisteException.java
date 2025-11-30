package util.exceptions;

public class EmailYaExisteException extends Exception{
    public EmailYaExisteException(){
        super("El email ingresado ya está asociado a una cuenta.");
    }

}
