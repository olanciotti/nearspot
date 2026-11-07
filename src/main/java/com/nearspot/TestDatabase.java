package com.nearspot;

import com.nearspot.config.DatabaseConnection;

import java.sql.Connection;

public class TestDatabase {

    public static void main(String[] args) {

        try (Connection connection =
                     DatabaseConnection.getConnection()) {

            System.out.println("Conexión exitosa");

            System.out.println(
                    "Base: " +
                    connection
                            .getMetaData()
                            .getDatabaseProductName()
            );

        } catch (Exception e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );

            e.printStackTrace();
        }
    }
}