package com.nearspot.dao;

import com.nearspot.config.DatabaseConnection;
import com.nearspot.model.Resena;
import com.nearspot.model.Rol;
import com.nearspot.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class ResenaDAO {

    public List<Resena> listarPorPrestador(
            int prestadorId) throws SQLException {

        List<Resena> resenas =
                new ArrayList<>();

        String sql = """
            SELECT
                re.id,
                re.contenido,
                re.fecha,
                re.estado,

                u.id AS usuario_id,
                u.nombre AS usuario_nombre,
                u.apellido AS usuario_apellido,
                u.email AS usuario_email,
                u.password AS usuario_password,
                u.estado AS usuario_estado,

                r.id AS rol_id,
                r.nombre AS rol_nombre

            FROM reseña re

            JOIN usuario u
                ON u.id = re.usuario_id

            JOIN rol r
                ON r.id = u.rol_id

            WHERE re.prestador_id = ?
              AND re.estado = 'ACTIVA'

            ORDER BY re.fecha DESC
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

                    Resena resena =
                            new Resena(
                                    rs.getInt("id"),
                                    rs.getString("contenido"),
                                    rs.getTimestamp("fecha")
                                        .toLocalDateTime(),
                                    rs.getString("estado"),
                                    usuario
                            );

                    resenas.add(
                            resena
                    );
                }
            }
        }

        return resenas;
    }
}