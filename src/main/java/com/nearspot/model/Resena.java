package com.nearspot.model;

import java.time.LocalDateTime;

public class Resena {

    private int id;
    private String contenido;
    private LocalDateTime fecha;
    private String estado;
    private Usuario usuario;

    public Resena(
            int id,
            String contenido,
            LocalDateTime fecha,
            String estado,
            Usuario usuario) {

        this.id = id;
        this.contenido = contenido;
        this.fecha = fecha;
        this.estado = estado;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public String getContenido() {
        return contenido;
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