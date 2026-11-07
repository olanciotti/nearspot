package com.nearspot.dao;

import com.nearspot.config.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.nearspot.model.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PrestadorDAO {

public List<Prestador> listarTodos()
        throws SQLException {

    List<Prestador> prestadores =
            new ArrayList<>();

    String sql = """
        SELECT
            p.id,
            p.nombre,
            p.descripcion,
            p.telefono,
            p.email,
            p.sitio_web,
            p.instagram,
            p.horario_atencion,
            p.estado,
            p.fecha_alta,

            r.id AS rubro_id,
            r.nombre AS rubro_nombre,
            r.descripcion AS rubro_descripcion,
            r.estado AS rubro_estado,

            u.id AS ubicacion_id,
            u.direccion,
            u.localidad,
            u.provincia,
            u.latitud,
            u.longitud,

            z.id AS zona_id,
            z.nombre AS zona_nombre,

            ta.nombre AS tipo_actor

        FROM prestador p

        JOIN rubro r
            ON r.id = p.rubro_id

        JOIN ubicacion u
            ON u.id = p.ubicacion_id

        LEFT JOIN zona z
            ON z.id = u.zona_id

        JOIN tipo_actor ta
            ON ta.id = p.tipo_actor_id

        WHERE p.estado = 'ACTIVO'

        ORDER BY p.nombre
        """;

    try (
        Connection conn =
                DatabaseConnection.getConnection();

        PreparedStatement stmt =
                conn.prepareStatement(sql);

        ResultSet rs =
                stmt.executeQuery()
    ) {

        while (rs.next()) {

            Prestador prestador =
                    crearPrestador(rs);

            prestadores.add(prestador);
        }
    }

    return prestadores;
}

private Prestador crearPrestador(
        ResultSet rs)
        throws SQLException {

    Zona zona = new Zona(
            rs.getInt("zona_id"),
            rs.getString("zona_nombre")
    );

    Ubicacion ubicacion =
            new Ubicacion(
                    rs.getInt("ubicacion_id"),
                    rs.getString("direccion"),
                    rs.getString("localidad"),
                    rs.getString("provincia"),
                    rs.getDouble("latitud"),
                    rs.getDouble("longitud"),
                    zona
            );

    Rubro rubro =
            new Rubro(
                    rs.getInt("rubro_id"),
                    rs.getString("rubro_nombre"),
                    rs.getString("rubro_descripcion"),
                    rs.getString("rubro_estado")
            );

    LocalDateTime horario =
            convertirFecha(
                    rs.getTimestamp(
                            "horario_atencion"
                    )
            );

    LocalDateTime fechaAlta =
            convertirFecha(
                    rs.getTimestamp(
                            "fecha_alta"
                    )
            );

    String tipo =
            rs.getString("tipo_actor");


    if ("COMERCIO".equalsIgnoreCase(tipo)) {

        return new Comercio(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("descripcion"),
                rs.getString("telefono"),
                rs.getString("email"),
                rs.getString("sitio_web"),
                rs.getString("instagram"),
                horario,
                rs.getString("estado"),
                fechaAlta,

                // Temporalmente:
                false,

                rubro,
                ubicacion,

                // Datos específicos todavía
                // no están en tu tabla
                null,
                null,
                false
        );
    }


    if ("EMPRENDIMIENTO"
            .equalsIgnoreCase(tipo)) {

        return new Emprendimiento(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("descripcion"),
                rs.getString("telefono"),
                rs.getString("email"),
                rs.getString("sitio_web"),
                rs.getString("instagram"),
                horario,
                rs.getString("estado"),
                fechaAlta,
                false,
                rubro,
                ubicacion,

                null,
                false,
                false
        );
    }


    return new PrestadorServicio(
            rs.getInt("id"),
            rs.getString("nombre"),
            rs.getString("descripcion"),
            rs.getString("telefono"),
            rs.getString("email"),
            rs.getString("sitio_web"),
            rs.getString("instagram"),
            horario,
            rs.getString("estado"),
            fechaAlta,
            false,
            rubro,
            ubicacion,

            null,
            0,
            false,
            null
    );
}

private LocalDateTime convertirFecha(
        Timestamp timestamp) {

    if (timestamp == null) {
        return null;
    }

    return timestamp.toLocalDateTime();
}
}

