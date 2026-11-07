package com.nearspot.dao;

import com.nearspot.config.DatabaseConnection;
import com.nearspot.model.Rol;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RolDAO {

    public Rol buscarPorId(int id) throws SQLException {

        String sql = """
                SELECT id, nombre
                FROM rol
                WHERE id = ?
                """;

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, id);

            try (ResultSet rs = statement.executeQuery()) {

                if (rs.next()) {

                    return new Rol(
                            rs.getInt("id"),
                            rs.getString("nombre")
                    );
                }
            }
        }

        return null;
    }
}