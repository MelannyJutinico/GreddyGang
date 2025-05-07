package co.edu.unbosque.PayrollAPI.exception.exception;

public class DataBaseException extends RuntimeException{

    private String mensaje;

    public DataBaseException(String mensaje) {
         super(mensaje);
    }
}
