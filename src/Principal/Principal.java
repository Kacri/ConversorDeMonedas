package Principal;

import Controller.GeneradorDeArchivo;
import Excepcion.ErrorEnConversionMonedasExeption;
import Modelos.ConversorMonedas;
import Servicio.InteractuaConApi;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        InteractuaConApi interactuaConApi = new InteractuaConApi();
        double cantidad = 0;

        List<ConversorMonedas> convertiendomonedas = new ArrayList<>();
        //List<ConversorMonedas> convertiendomonedas = new ArrayList<>();
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

//        System.out.println("Ingresar valor a convertir");
        System.out.println("*******************************************");
        System.out.println("     Bienvenido al Conversor de Monedas    ");
        System.out.println("*******************************************");

        String menuOpciones = """
                         ** Menu Conversor de Monedas **
                -----------------------------------------------                       
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
                11) Sol Peruano =>> Dólar
                12) Salir
                """;
        int opcion = 0;

        while (opcion != 12) {
            System.out.println(menuOpciones);
            System.out.println("Escriba el numero de la opcion deseada: ");
            System.out.println("-------------------------------------------");


            //CONTROLANDO ERROR DE INGRESO DE DATO Q NO CORRESPONDE
            try {
                opcion = teclado.nextInt();
            }catch (InputMismatchException e){
                System.out.println("Error debe ingresar una opcion valida");
                //limpia buffer
                teclado.next();
                //regresa al inicio del ciclo
                continue;
            }










            if (opcion == 12) {
                System.out.println("Saliendo del programa...");
            }else {
                try {
                    ConversorMonedas conversion = ReselectionOption.reselectionMoaned(opcion);
                    String monedaOrigen = conversion.getMonedaOrigen();
                    String monedaDestino = conversion.getMonedaDestino();

                    boolean cantidadValida = false;
                    while (!cantidadValida){
                        System.out.println("Ingrese el valor que deseas convertir: ");
                        try {
                            cantidad = teclado.nextDouble();
                            //salir del bucle
                            cantidadValida = true;
                        }catch (InputMismatchException e){
                            System.out.println("Debe ingresar solo Numeros enteros o decimales. ");
                            teclado.next();
                        }

                    }




                    conversion = interactuaConApi.ObtenerConversorMonedas(monedaOrigen, monedaDestino, cantidad);
                    conversion.setCantidad(cantidad);
                    System.out.println(" ");
                    convertiendomonedas.add(conversion);

//                System.out.println("Escriba el numero de la opcion deseada: ");
                } catch (ErrorEnConversionMonedasExeption e) {
                    System.out.println("No se puede convertir moneda  Por que Esta vacio o tiene un N/A");
                } catch (IllegalAccessException e) {
//                System.out.println("Elija una opcion valida");
                }
            }
        }
                    GeneradorDeArchivo generadorDeArchivo = new GeneradorDeArchivo();
        try {
            generadorDeArchivo.guardarJson(convertiendomonedas);
            System.out.println("Se guardo el archivo JSON con las conversiones");
        }catch (IOException e){
            System.out.println("Error al guardar el archivo JSON.");
        }





    }

    public static class ReselectionOption {
        public static ConversorMonedas reselectionMoaned(int option) throws IllegalAccessException {
            return switch (option) {
                case 1 -> new ConversorMonedas("USD", "ARS");
                case 2 -> new ConversorMonedas("ARS", "USD");
                case 3 -> new ConversorMonedas("BOB", "USD");
                case 4 -> new ConversorMonedas("USD", "BOB");
                case 5 -> new ConversorMonedas("BRL", "USD");
                case 6 -> new ConversorMonedas("USD", "BRL");
                case 7 -> new ConversorMonedas("CLP", "USD");
                case 8 -> new ConversorMonedas("USD", "CLP");
                case 9 -> new ConversorMonedas("COP", "USD");
                case 10 -> new ConversorMonedas("USD", "COP");
                case 11 -> new ConversorMonedas("PEN", "USD");
                default -> throw new IllegalAccessException("Opcion no valida");
            };

        }

    }


}
