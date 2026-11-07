package com.nearspot.dao;

import com.nearspot.config.DatabaseConnection;
import com.nearspot.model.Rol;
import com.nearspot.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public Usuario buscarPorEmail(String email)
            throws SQLException {

        String sql = """
                SELECT
                    u.id,
                    u.nombre,
                    u.apellido,
                    u.email,
                    u.password,
                    u.estado,

                    r.id AS rol_id,
                    r.nombre AS rol_nombre

                FROM usuario u

                JOIN rol r
                    ON r.id = u.rol_id

                WHERE LOWER(u.email) = LOWER(?)
                """;

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, email);

            try (
                    ResultSet rs =
                            statement.executeQuery()
            ) {

                if (rs.next()) {

                    Rol rol = new Rol(
                            rs.getInt("rol_id"),
                            rs.getString("rol_nombre")
                    );

                    return new Usuario(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("estado"),
                            rol
                    );
                }
            }
        }

        return null;
    }
}