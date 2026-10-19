package com.nearspot.model;

public class Ubicacion {
    private int id;
    private String direccion;
    private String localidad;
    private String provincia;
    private double latitud;
    private double longitud;

    private Zona zona;

    public Ubicacion() {
    }

    public Ubicacion(int id, String direccion, String localidad, String provincia, double latitud, double longitud, Zona zona) {
        this.id = id;
        this.direccion = direccion;
        this.localidad = localidad;
        this.provincia = provincia;
        this.latitud = latitud;
        this.longitud = longitud;
        this.zona = zona;
    }

    public int getId() {
        return id;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public Zona getZona() {
        return zona;
    }
}
