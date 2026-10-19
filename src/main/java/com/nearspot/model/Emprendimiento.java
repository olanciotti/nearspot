package com.nearspot.model;

import java.time.LocalDateTime;

public class Emprendimiento extends Prestador {

    private String modalidadVenta;
    private boolean trabajaPorPedido;
    private boolean realizaEntregas;

    public Emprendimiento(
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
            String modalidadVenta,
            boolean trabajaPorPedido,
            boolean realizaEntregas) {

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

        this.modalidadVenta = modalidadVenta;
        this.trabajaPorPedido = trabajaPorPedido;
        this.realizaEntregas = realizaEntregas;
    }

    public String getModalidadVenta() {
        return modalidadVenta;
    }

    public void setModalidadVenta(String modalidadVenta) {
        this.modalidadVenta = modalidadVenta;
    }

    public boolean isTrabajaPorPedido() {
        return trabajaPorPedido;
    }

    public void setTrabajaPorPedido(boolean trabajaPorPedido) {
        this.trabajaPorPedido = trabajaPorPedido;
    }

    public boolean isRealizaEntregas() {
        return realizaEntregas;
    }

    public void setRealizaEntregas(boolean realizaEntregas) {
        this.realizaEntregas = realizaEntregas;
    }
}