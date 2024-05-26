package com.alura.conversormoneda.Principal;

import com.alura.conversormoneda.clases.ConversorMoneda;
import com.alura.conversormoneda.clases.Transaccion;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.FieldNamingPolicy;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        int salida = 0;

        ConversorMoneda conversor = new ConversorMoneda();

        while (salida != 7) {
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
            int opcion = Integer.parseInt(entrada.nextLine());
            String monedaOrigen = "";
            String monedaDestino = "";

            switch (opcion) {
                case 1 -> {
                    monedaOrigen = "MXN";
                    monedaDestino = "USD";
                }
                case 2 -> {
                    monedaOrigen = "USD";
                    monedaDestino = "MXN";
                }
                case 3 -> {
                    monedaOrigen = "MXN";
                    monedaDestino = "BRL";
                }
                case 4 -> {
                    monedaOrigen = "BRL";
                    monedaDestino = "MXN";
                }
                case 5 -> {
                    monedaOrigen = "MXN";
                    monedaDestino = "COP";
                }
                case 6 -> {
                    monedaOrigen = "COP";
                    monedaDestino = "MXN";
                }
                case 7 -> salida = 7;
                default -> System.out.println("Opción inválida, intenta de nuevo");
            }

            if (opcion != 7 && opcion >= 1 && opcion < 8) {
                System.out.println("Escribe el monto en " + monedaOrigen + " que quieres cambiar a " + monedaDestino + " :");
                double valorACambiar = Double.parseDouble(entrada.nextLine());

                Transaccion transaccion = new Transaccion(monedaOrigen, monedaDestino, valorACambiar);

                try {
                    conversor.convertir(transaccion);
                    System.out.printf("El monto cambiado es de: %.2f %s%n", transaccion.getValorCambiado(), monedaDestino);
                } catch (Exception e) {
                    System.out.println("Error al realizar la conversión: " + e.getMessage());
                }
            }
        }
        System.out.println("Gracias por usar el convertidor de monedas de Luis Montero");
    }
}
