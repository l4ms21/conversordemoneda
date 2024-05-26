package com.alura.conversormoneda.clases;

public class Transaccion {
    private String monedaOrigen;
    private String monedaDestino;
    private double valorACambiar;
    private double valorCambiado;

    public Transaccion(String monedaOrigen, String monedaDestino, double valorACambiar) {
        this.monedaOrigen = monedaOrigen;
        this.monedaDestino = monedaDestino;
        this.valorACambiar = valorACambiar;
    }

    public String getMonedaOrigen() {
        return monedaOrigen;
    }

    public String getMonedaDestino() {
        return monedaDestino;
    }

    public double getValorACambiar() {
        return valorACambiar;
    }

    public double getValorCambiado() {
        return valorCambiado;
    }

    public void setValorCambiado(double valorCambiado) {
        this.valorCambiado = valorCambiado;
    }
}
