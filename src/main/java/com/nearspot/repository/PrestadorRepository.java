package com.nearspot.repository;

import com.nearspot.model.Prestador;

import java.util.List;
import java.util.Optional;

public interface PrestadorRepository {

    List<Prestador> listarTodos();

    List<Prestador> buscarPorNombre(String nombre);

    Optional<Prestador> buscarPorId(int id);
}