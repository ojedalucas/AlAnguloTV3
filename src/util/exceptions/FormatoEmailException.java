package util.exceptions;

public class FormatoEmailException extends Exception{
    public FormatoEmailException(){
        super("El email debe cumplir el formato básico 'xxx@yyy.zz'.");
    }
}