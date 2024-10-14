package Servicio;

import Config.ApiConfig;
import Modelos.ConversorMonedas;
import Modelos.ConversorMonedasExchange;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class InteractuaConApi {

    List<ConversorMonedas> conversorMonedasList = new ArrayList<>();

    public ConversorMonedas ObtenerConversorMonedas(String monedaOrigen, String monedaDestino, double cantidad) {

        URI direccion = URI.create(ApiConfig.getBaseUrl() + "/" + monedaOrigen + "/" + monedaDestino + "/" + cantidad);

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(direccion)
                    .build();

            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();
//            System.out.println("Este ok "+json);

            ConversorMonedasExchange conversorMonedasExchange = gson.fromJson(json, ConversorMonedasExchange.class);
            ConversorMonedas conversorMonedas = new ConversorMonedas(conversorMonedasExchange);
            System.out.println("El valor de  " + cantidad + " " + conversorMonedas);

            conversorMonedasList.add(conversorMonedas);
            return conversorMonedas;

        } catch (Exception e) {
            throw new RuntimeException("No se encontro Conversor de Moneda de ese tipo.");
        }

    }

}