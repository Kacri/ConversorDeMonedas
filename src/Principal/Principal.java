package Principal;

import Excepcion.ErrorEnConversionMonedasExeption;
import Modelos.ConversorMonedas;
import Modelos.ConversorMonedasExchange;
import Servicio.InteractuaConApi;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        InteractuaConApi interactuaConApi = new InteractuaConApi();
        double cantidad = 0;

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();


//        System.out.println("Ingresar valor a convertir");

        System.out.println("Bienvenido al Conversor de Monedas");
        System.out.println("----------------------------------");

        String menuOpciones = """
                ** Elija una opcion valida **
                *****************************
                        
                1) Dólar Estadounidense =>> Peso argentino
                2) Peso Argentino =>> Dólar Estadounidense
                3) Boliviano Boliviano =>> Dólar Estadounidense
                4) Dólar Estadounidense  =>> Boliviano Boliviano
                5) Real Brasileño =>> Dólar
                6) Dólar Estadounidense  =>> Real Brasileño
                7) Peso Chileno  =>> Dólar
                8) Dólar Estadounidense  =>> Peso Chileno
                9) Peso Colombiano  =>> Dólar
                10) Dólar Estadounidense  =>> Peso Colombiano
                11) Peso Colombiano  =>> Dólar
                12) Salir
                """;
        System.out.println(menuOpciones);
        System.out.println("Escriba el numero de la opcion deseada: ");
        System.out.println("*************************************************");

        int opcion = 0;

        while (opcion != 12) {
            opcion = teclado.nextInt();
            System.out.println(opcion);
            System.out.println("*****************************************************");
            System.out.println(menuOpciones);
            System.out.println("*****************************************************");

            System.out.println("Ingrese el valor que deseas convertir: ");
            cantidad = teclado.nextDouble();

            if (opcion==12){
                System.out.println("Saliendo del programa...");
                break;
            }

            try {

                ConversorMonedas conversion = SeleccionarOpcion.seleccionarMoneda(opcion);
                String monedaOrigen = conversion.getMonedaOrigen();
                String monedaDestino = conversion.getMonedaDestino();

                ConversorMonedas conversorMonedas = interactuaConApi.ObtenerConversorMonedas(monedaOrigen, monedaDestino, cantidad);

                System.out.println(" ");

                System.out.println("Escriba el numero de la opcion deseada: ");

//    System.out.println("Resultado: "+conversorMonedas);
//    switch (opcion){
//        case 1:
//            ConversorMonedas conversorMonedas = interactuaConApi.ObtenerConversorMonedas("USD","ARS",cantidad);
//    }


            } catch (ErrorEnConversionMonedasExeption e) {
                System.out.println("No se puede convertir moneda  Por que Esta vacio o tiene un N/A");
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }

        }


    }


    public class SeleccionarOpcion {
        public static ConversorMonedas seleccionarMoneda(int opcion) throws IllegalAccessException {

            switch (opcion) {
                case 1:
                    return new ConversorMonedas("USD", "ARS");
                case 2:
                    return new ConversorMonedas("ARS", "USD");
                case 3:
                    return new ConversorMonedas("BOB", "USD");
                case 4:
                    return new ConversorMonedas("USD", "BOB");
                case 5:
                    return new ConversorMonedas("BRL", "USD");
                case 6:
                    return new ConversorMonedas("USD", "BRL");
                case 7:
                    return new ConversorMonedas("CLP", "USD");
                case 8:
                    return new ConversorMonedas("USD", "CLP");
                case 9:
                    return new ConversorMonedas("COP", "USD");
                case 10:
                    return new ConversorMonedas("USD", "COP");
                default:
                    throw new IllegalAccessException("Opcion no valida");

            }

        }

    }


}
