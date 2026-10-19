package com.nearspot.model;

import java.time.LocalDateTime;

public class PrestadorServicio extends Prestador {

    private String especialidad;
    private int aniosExperiencia;
    private boolean trabajaADomicilio;
    private String matricula;
    private PrestadorServicio prestadorServicio;


    public PrestadorServicio(
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
            String especialidad,
            int aniosExperiencia,
            boolean trabajaADomicilio,
            String matricula) {

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

        this.especialidad = especialidad;
        this.aniosExperiencia = aniosExperiencia;
        this.trabajaADomicilio = trabajaADomicilio;
        this.matricula = matricula;
    }


    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }


    public int getAniosExperiencia() {
        return aniosExperiencia;
    }

    public void setAniosExperiencia(int aniosExperiencia) {
        this.aniosExperiencia = aniosExperiencia;
    }


    public boolean isTrabajaADomicilio() {
        return trabajaADomicilio;
    }

    public void setTrabajaADomicilio(boolean trabajaADomicilio) {
        this.trabajaADomicilio = trabajaADomicilio;
    }


    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}