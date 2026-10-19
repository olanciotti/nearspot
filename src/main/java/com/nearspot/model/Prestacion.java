package com.nearspot.model;

public class Prestacion {

    private int id;
    private int calificacion;
    private boolean estaCalificada;

    public Prestacion() {
    }

    public Prestacion(
            int id,
            int calificacion,
            boolean estaCalificada) {

        this.id = id;
        this.calificacion = calificacion;
        this.estaCalificada = estaCalificada;
    }

    public int getId() {
        return id;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public boolean isEstaCalificada() {
        return estaCalificada;
    }
}