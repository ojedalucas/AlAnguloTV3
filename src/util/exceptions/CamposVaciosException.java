package Util.Exceptions;

public class CamposVaciosException extends Exception{
    public CamposVaciosException(){
        super("Debe completar todas las casillas.");
    }

}