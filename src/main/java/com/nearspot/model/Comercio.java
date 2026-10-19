package com.nearspot.model;

import java.time.LocalDateTime;

public class Comercio extends Prestador {

    private String razonSocial;
    private String cuit;
    private boolean realizaEnvios;

    public Comercio(
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
            Ubicacion ubicacion,
            String razonSocial,
            String cuit,
            boolean realizaEnvios) {

        super(
                id,
                nombre,
                descripcion,
                telefono,
                email,
                sitioWeb,
                instagram,
                horarioAtencion,
                estado,
                fechaAlta,
                estaVerificado,
                rubro,
                ubicacion
        );

        this.razonSocial = razonSocial;
        this.cuit = cuit;
        this.realizaEnvios = realizaEnvios;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public boolean isRealizaEnvios() {
        return realizaEnvios;
    }

    public void setRealizaEnvios(boolean realizaEnvios) {
        this.realizaEnvios = realizaEnvios;
    }
}