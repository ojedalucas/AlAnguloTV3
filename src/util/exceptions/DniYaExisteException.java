package util.exceptions;

public class DniYaExisteException extends Exception {
    public DniYaExisteException(){
        super("El DNI ingresado ya está asociado a una cuenta.");
    }
}