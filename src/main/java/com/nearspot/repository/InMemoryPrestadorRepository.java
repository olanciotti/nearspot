package com.nearspot.repository;

import com.nearspot.data.DatosPrueba;
import com.nearspot.model.Prestador;

import java.util.List;
import java.util.Optional;

public class InMemoryPrestadorRepository
        implements PrestadorRepository {

    private final List<Prestador> prestadores;

    public InMemoryPrestadorRepository() {

        this.prestadores =
                DatosPrueba.crearPrestadores();
    }

    @Override
    public List<Prestador> listarTodos() {

        return prestadores;
    }

    @Override
    public List<Prestador> buscarPorNombre(
            String nombre) {

        return prestadores
                .stream()
                .filter(p ->
                    p.getNombre()
                     .toLowerCase()
                     .contains(
                        nombre.toLowerCase()
                     )
                )
                .toList();
    }

    @Override
    public Optional<Prestador> buscarPorId(
            int id) {

        return prestadores
                .stream()
                .filter(p -> p.getId() == id)
                .findFirst();
    }
}