package Util.Exceptions;

public class FormatoDniException extends Exception{
    public FormatoDniException(){
        super("El DNI debe contener solo números.");
    }
}
