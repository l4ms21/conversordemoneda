package com.alura.conversormoneda.clases;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

public class ConversorMoneda {
    private static final String API_KEY = "323c2b7a121072060625eea6";

    public double obtenerTipoDeCambio(String monedaOrigen, String monedaDestino) throws Exception {
        String urlStr = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/" + monedaOrigen + "/";
        URL url = new URL(urlStr);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();

        // Convertir a JSON
        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonObject jsonobj = root.getAsJsonObject();

        // Acceder al objeto de tasas de conversión
        JsonObject conversionRates = jsonobj.getAsJsonObject("conversion_rates");

        // Obtener el tipo de cambio
        for (String key : conversionRates.keySet()) {
            if (Objects.equals(key, monedaDestino)) {
                return conversionRates.get(key).getAsDouble();
            }
        }

        throw new Exception("No se pudo encontrar el tipo de cambio para " + monedaDestino);
    }

    public double convertir(Transaccion transaccion) throws Exception {
        double tipoDeCambio = obtenerTipoDeCambio(transaccion.getMonedaOrigen(), transaccion.getMonedaDestino());
        double valorCambiado = transaccion.getValorACambiar() * tipoDeCambio;
        transaccion.setValorCambiado(valorCambiado);
        return valorCambiado;
    }
}
