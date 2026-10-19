package com.nearspot.model;
import java.util.ArrayList;
import java.util.List;

import java.time.LocalDateTime;

public abstract class Prestador {

    private int id;
    private String nombre;
    private String descripcion;
    private String telefono;
    private String email;
    private String sitioWeb;
    private String instagram;

    private LocalDateTime horarioAtencion;
    private String estado;
    private LocalDateTime fechaAlta;

    private boolean estaVerificado;

    private Rubro rubro;
    private Ubicacion ubicacion;

    private List<Resena> resenas;
    private List<Calificacion> calificaciones;


    // CONSTRUCTOR
    public Prestador(
            int id,
            String nombre,
            String descripcion,
            String telefono,
            String email,
            String sitioWeb,
            String instagram,
            LocalDateTime horarioAtencion,
            String estado,
            LocalDateTime fechaAlta,
            boolean estaVerificado,
            Rubro rubro,
            Ubicacion ubicacion) {

        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.telefono = telefono;
        this.email = email;
        this.sitioWeb = sitioWeb;
        this.instagram = instagram;
        this.horarioAtencion = horarioAtencion;
        this.estado = estado;
        this.fechaAlta = fechaAlta;
        this.estaVerificado = estaVerificado;
        this.rubro = rubro;
        this.ubicacion = ubicacion;
        this.resenas = new ArrayList<>();
        this.calificaciones = new ArrayList<>();        
    }


    // GETTERS

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public String getSitioWeb() {
        return sitioWeb;
    }

    public String getInstagram() {
        return instagram;
    }

    public LocalDateTime getHorarioAtencion() {
        return horarioAtencion;
    }

    public String getEstado() {
        return estado;
    }

    public LocalDateTime getFechaAlta() {
        return fechaAlta;
    }

    public List<Resena> getResenas() {
        return resenas;
    }

    public List<Calificacion> getCalificaciones() {
        return calificaciones;
    }

    public Rubro getRubro() {
        return rubro;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

     // GETTER PARA EL ESTADO DE VERIFICACIÓN
    public boolean isEstaVerificado() {
        return estaVerificado;
    }
    
    // SETTERS

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public void setHorarioAtencion(LocalDateTime horarioAtencion) {
        this.horarioAtencion = horarioAtencion;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setFechaAlta(LocalDateTime fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public void setEstaVerificado(boolean estaVerificado) {
        this.estaVerificado = estaVerificado;
    }

    public void setRubro(Rubro rubro) {
        this.rubro = rubro;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    // Agregar reseña y calificación
    public void agregarResena(Resena resena) {
    resenas.add(resena);
    }

    public void agregarCalificacion(Calificacion calificacion) {
        calificaciones.add(calificacion);
    }

    public double getPromedioCalificacion() {

    if (calificaciones.isEmpty()) {
        return 0;
    }

    return calificaciones.stream()
            .mapToInt(Calificacion::getValor)
            .average()
            .orElse(0);
    }

    @Override
    public String toString() {
        return nombre;
    }
}