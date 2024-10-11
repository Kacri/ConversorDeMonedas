package Excepcion;

public class ErrorEnConversionMonedasExeption extends RuntimeException{
    private String mensaje;

    public ErrorEnConversionMonedasExeption(String mensaje){
        this.mensaje = mensaje;
    }

    @Override
    public String getMessage() {
        return this.mensaje;
    }
}
