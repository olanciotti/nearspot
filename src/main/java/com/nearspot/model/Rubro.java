package com.nearspot.model;

public class Rubro {

    private int id;
    private String nombre;
    private String descripcion;
    private String estado;

    public Rubro() {
    }

    public Rubro(
            int id,
            String nombre,
            String descripcion,
            String estado) {

        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getEstado() {
        return estado;
    }
}