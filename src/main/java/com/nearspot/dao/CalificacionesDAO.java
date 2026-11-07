package com.nearspot.dao;

import com.nearspot.config.DatabaseConnection;
import com.nearspot.model.Calificacion;
import com.nearspot.model.Rol;
import com.nearspot.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class CalificacionesDAO {

    public List<Calificacion> listarPorPrestador(
            int prestadorId) throws SQLException {

        List<Calificacion> calificaciones =
                new ArrayList<>();

        String sql = """
            SELECT
                c.id,
                c.valor,
                c.fecha,
                c.estado,

                u.id AS usuario_id,
                u.nombre AS usuario_nombre,
                u.apellido AS usuario_apellido,
                u.email AS usuario_email,
                u.password AS usuario_password,
                u.estado AS usuario_estado,

                r.id AS rol_id,
                r.nombre AS rol_nombre

            FROM calificacion c

            JOIN usuario u
                ON u.id = c.usuario_id

            JOIN rol r
                ON r.id = u.rol_id

            WHERE c.prestador_id = ?
              AND c.estado = 'ACTIVA'

            ORDER BY c.fecha DESC
            """;

        try (
            Connection conn =
                    DatabaseConnection.getConnection();

            PreparedStatement stmt =
                    conn.prepareStatement(sql)
        ) {

            stmt.setInt(
                    1,
                    prestadorId
            );

            try (
                ResultSet rs =
                        stmt.executeQuery()
            ) {

                while (rs.next()) {

                    Rol rol =
                            new Rol(
                                    rs.getInt("rol_id"),
                                    rs.getString("rol_nombre")
                            );

                    Usuario usuario =
                            new Usuario(
                                    rs.getInt("usuario_id"),
                                    rs.getString("usuario_nombre"),
                                    rs.getString("usuario_apellido"),
                                    rs.getString("usuario_email"),
                                    rs.getString("usuario_password"),
                                    rs.getString("usuario_estado"),
                                    rol
                            );

                    Calificacion calificacion =
                            new Calificacion(
                                    rs.getInt("id"),
                                    rs.getInt("valor"),
                                    rs.getTimestamp("fecha")
                                            .toLocalDateTime(),
                                    rs.getString("estado"),
                                    usuario
                            );

                    calificaciones.add(
                            calificacion
                    );
                }
            }
        }

        return calificaciones;
    }
}