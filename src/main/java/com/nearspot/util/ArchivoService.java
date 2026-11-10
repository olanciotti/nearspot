package com.nearspot.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ArchivoService {

    private static final String ARCHIVO =
            "historial_busquedas.txt";

    public void guardarBusqueda(
            String rubro,
            String ubicacion) {

        DateTimeFormatter formato =
                DateTimeFormatter.ofPattern(
                        "dd/MM/yyyy HH:mm"
                );

        String fecha =
                LocalDateTime.now()
                        .format(formato);

        try (
            BufferedWriter writer =
                    new BufferedWriter(
                            new FileWriter(
                                    ARCHIVO,
                                    true
                            )
                    )
        ) {

            writer.write(
                    fecha
                    + " | Rubro: "
                    + rubro
                    + " | Ubicación: "
                    + ubicacion
            );

            writer.newLine();

        } catch (IOException e) {

            System.err.println(
                    "Error guardando historial: "
                    + e.getMessage()
            );
        }
    }
}