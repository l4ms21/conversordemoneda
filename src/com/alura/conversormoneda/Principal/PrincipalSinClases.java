package com.alura.conversormoneda.Principal;

//import com.alura.conversormoneda.cambio.cambio;
import com.google.gson.*;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;
import java.util.Scanner;

public class PrincipalSinClases {
    public static void main(String[] args) {
        int salida = 0;

        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        while (salida != 7)
        {
            System.out.println("""
            *********************************************************
            Sea bienvenido/a al Conversor de Moneda :-)
            
            Opciones: 
            1) Peso Mexicano >>> Dolar
            2) Dolar >>> Peso Mexicano
            3) Peso Mexicano >>> Real Brasileño
            4) Real Brasileño >>> Peso Mexicano
            5) Peso Mexicano >>> Peso Colombiano
            6) Peso Colombiano >>> Peso Mexicano
            7) Salir
           
            Elige una opción válida:
            ********************************************************** 
            """);
            Scanner entrada = new Scanner(System.in);
            Scanner valorACambiar = new Scanner(System.in);
            int opcion = Integer.valueOf(entrada.nextLine());
            String monedaOrigen = "";
            String monedaDestino ="";
            double tipoDeCambio = 0.0;
            double valorCambiado =0.0;
            String miKey = "323c2b7a121072060625eea6";
            String url_str = "";

            switch (opcion){
                case 1: //MXN >>> USD
                    //cambio canje = new cambio("MXN");
                    monedaOrigen = "MXN";
                    monedaDestino = "USD";
                    break;
                case 2: //USD >>> MXN
                    monedaOrigen = "USD";
                    monedaDestino = "MXN";
                    break;
                case 3: //MXN >>> EUR
                    monedaOrigen = "MXN";
                    monedaDestino = "BRL";
                    break;
                case 4: //EUR >>> MXN
                    monedaOrigen = "BRL";
                    monedaDestino = "MXN";
                    break;
                case 5: //MXN >>> COP
                    monedaOrigen = "MXN";
                    monedaDestino = "COP";
                    break;
                case 6: //COP >>> MXN
                    monedaOrigen = "COP";
                    monedaDestino = "MXN";
                    break;
                case 7: //Salida del programa
                    salida = 7; break;
                default:
                    System.out.println("Opcion invalida, intenta de nuevo");
                    break;
            }

            if (opcion != 7 && opcion >= 1 && opcion < 8) {
                url_str = "https://v6.exchangerate-api.com/v6/" +
                        miKey+
                        "/latest/"+monedaOrigen+"/";
                //System.out.println(url_str);
                try{
                    URL url = new URL(url_str);
                    HttpURLConnection request = (HttpURLConnection) url.openConnection();
                    request.connect();

                    // Convert to JSON
                    JsonParser jp = new JsonParser();
                    JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
                    JsonObject jsonobj = root.getAsJsonObject();

                    // Accessing object
                    JsonObject conversionRates = jsonobj.getAsJsonObject("conversion_rates");

                    //Obtengo el tipo de cambio
                    for (String key : conversionRates.keySet()) {
                        if (Objects.equals(key, monedaDestino)){
                            tipoDeCambio = conversionRates.get(key).getAsDouble();
                            break;
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Error al recuperar tipo de cambio: "+e);
                }

                //Solicitar monto a cambiar y Realizar cambio de moneda
                System.out.println("Escribe el monto en " + monedaOrigen + " que quieres cambiar a "+monedaDestino+" :");
                valorCambiado = Double.valueOf(valorACambiar.nextLine())*tipoDeCambio;

                //Imprimir resultado
               System.out.printf("El monto cambiado es de: "+String.valueOf(valorCambiado)+" "+monedaDestino+"\n\n");
            }
        }
        System.out.println("Gracias por usar el convertidor de monedas de Luis Montero ");
    }
}
