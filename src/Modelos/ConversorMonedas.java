package Modelos;

import Excepcion.ErrorEnConversionMonedasExeption;

public class ConversorMonedas {

String monedaOrigen;
String monedaDestino;
double cantidad;
double tasaDeCambio;
double valorFinal;

    public ConversorMonedas(String monedaOrigen, String monedaDestino) {
        this.monedaOrigen = monedaOrigen;
        this.monedaDestino = monedaDestino;

    }

    public String getMonedaOrigen() {
        return monedaOrigen;
    }

    public String getMonedaDestino() {
        return monedaDestino;
    }

    public ConversorMonedas(ConversorMonedasExchange conversorMonedasExchange) {
        this.monedaOrigen = conversorMonedasExchange.base_code();
        this.monedaDestino = conversorMonedasExchange.target_code();
        this.tasaDeCambio = conversorMonedasExchange.conversion_rate();
        this.valorFinal = conversorMonedasExchange.conversion_result();

        // Validar base_code (moneda de origen)
        if (monedaOrigen == null || monedaOrigen.isEmpty() || monedaOrigen.equals("N/A")) {
            throw new ErrorEnConversionMonedasExeption("No se puede convertir moneda porque la moneda de origen está vacía o tiene 'N/A'");
        }

        // Validar target_code (moneda de destino)
        if (monedaDestino == null || monedaDestino.isEmpty() || monedaDestino.equals("N/A")) {
            throw new ErrorEnConversionMonedasExeption("No se puede convertir moneda porque la moneda de destino está vacía o tiene 'N/A'");
        }
    }

    @Override
    public String toString() {
        return
                "["+monedaOrigen+"]" + " " + "Corresponde al valor final de =>>> "+
                valorFinal + " " + "["+monedaDestino+"]";
    }


}
