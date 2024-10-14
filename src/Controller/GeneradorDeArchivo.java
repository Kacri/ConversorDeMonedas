package Controller;

import Modelos.ConversorMonedas;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.FileWriter;
import java.io.IOException;

public class GeneradorDeArchivo {
    public void guardarJson(ConversorMonedas conversorMonedas) throws IOException{
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter escritura = new FileWriter("ConversorMonedas.json");
        escritura.write(gson.toJson(conversorMonedas));
        escritura.close();

    }
}
