package com.nearspot.model;

import java.time.LocalDateTime;

public class Calificacion {

    private int id;
    private int valor;
    private LocalDateTime fecha;
    private String estado;
    private Usuario usuario;

    public Calificacion(
            int id,
            int valor,
            LocalDateTime fecha,
            String estado,
            Usuario usuario) {

        this.id = id;
        this.valor = valor;
        this.fecha = fecha;
        this.estado = estado;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public int getValor() {
        return valor;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public String getEstado() {
        return estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}