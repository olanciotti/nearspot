package com.nearspot.service;

import com.nearspot.model.Prestador;

import java.util.ArrayList;
import java.util.List;

public class PrestadorService {

    /*private final PrestadorRepository repository;

    public PrestadorService(
            PrestadorRepository repository) {

        this.repository = repository;
    }

    public List<Prestador> listarTodos() {

        return repository.listarTodos();
    }

    public List<Prestador> buscar(
            String criterio) {

        if (criterio == null ||
            criterio.isBlank()) {

            return repository.listarTodos();
        }

        return repository.buscarPorNombre(
                criterio.trim()
        );
    }*/
   /*
     * BÚSQUEDA LINEAL
     *
     * Recorre uno por uno todos los prestadores
     * y agrega a resultados aquellos que coinciden
     * con el rubro y la ubicación.
     */
    public List<Prestador> buscarLineal(
            List<Prestador> prestadores,
            String rubroSeleccionado,
            String ubicacionBuscada) {

        List<Prestador> resultados = new ArrayList<>();

        String ubicacion = "";

        if (ubicacionBuscada != null) {
            ubicacion = ubicacionBuscada
                    .toLowerCase()
                    .trim();
        }

        for (Prestador prestador : prestadores) {

            // -------------------------
            // VALIDAR RUBRO
            // -------------------------

            boolean coincideRubro =
                    rubroSeleccionado == null
                    || rubroSeleccionado.equalsIgnoreCase("Todos")
                    || prestador.getRubro()
                            .getNombre()
                            .equalsIgnoreCase(rubroSeleccionado);


            // -------------------------
            // VALIDAR UBICACIÓN
            // -------------------------

            boolean coincideUbicacion =
                    ubicacion.isEmpty();

            if (!coincideUbicacion &&
                    prestador.getUbicacion() != null) {

                String direccion =
                        prestador.getUbicacion()
                                .getDireccion()
                                .toLowerCase();

                String localidad =
                        prestador.getUbicacion()
                                .getLocalidad()
                                .toLowerCase();

                String provincia =
                        prestador.getUbicacion()
                                .getProvincia()
                                .toLowerCase();

                String zona =
                        prestador.getUbicacion()
                                .getZona()
                                .getNombre()
                                .toLowerCase();

                coincideUbicacion =
                        direccion.contains(ubicacion)
                        || localidad.contains(ubicacion)
                        || provincia.contains(ubicacion)
                        || zona.contains(ubicacion);
            }


            // -------------------------
            // AGREGAR RESULTADO
            // -------------------------

            if (coincideRubro && coincideUbicacion) {

                resultados.add(prestador);
            }
        }

        return resultados;
    }


    /*
     * BUBBLE SORT
     * Ordena alfabéticamente por nombre.
     */
    public List<Prestador> ordenarPorNombre(
            List<Prestador> prestadores) {

        List<Prestador> ordenados =
                new ArrayList<>(prestadores);

        for (int i = 0;
             i < ordenados.size() - 1;
             i++) {

            for (int j = 0;
                 j < ordenados.size() - 1 - i;
                 j++) {

                Prestador actual =
                        ordenados.get(j);

                Prestador siguiente =
                        ordenados.get(j + 1);

                if (actual.getNombre()
                        .compareToIgnoreCase(
                                siguiente.getNombre()
                        ) > 0) {

                    // Intercambio
                    ordenados.set(j, siguiente);
                    ordenados.set(j + 1, actual);
                }
            }
        }

        return ordenados;
    }


    /*
     * BUBBLE SORT
     * Ordena de mayor a menor calificación.
     */
    public List<Prestador> ordenarPorCalificacion(
            List<Prestador> prestadores) {

        List<Prestador> ordenados =
                new ArrayList<>(prestadores);

        for (int i = 0;
             i < ordenados.size() - 1;
             i++) {

            for (int j = 0;
                 j < ordenados.size() - 1 - i;
                 j++) {

                Prestador actual =
                        ordenados.get(j);

                Prestador siguiente =
                        ordenados.get(j + 1);

                if (actual.getPromedioCalificacion()
                        <
                    siguiente.getPromedioCalificacion()) {

                    // Intercambio
                    ordenados.set(j, siguiente);
                    ordenados.set(j + 1, actual);
                }
            }
        }

        return ordenados;
    }
} 
