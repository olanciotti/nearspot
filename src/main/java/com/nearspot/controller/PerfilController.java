package com.nearspot.controller;

import com.nearspot.dao.UsuarioDAO;
import com.nearspot.model.Usuario;
import com.nearspot.session.Sesion;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class PerfilController {

    @FXML
    private Label lblNombre;

    @FXML
    private Label lblApellido;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblEstado;

    @FXML
    private Label lblRol;


    @FXML
        public void initialize() {

            Usuario usuarioSesion =
                    Sesion.getUsuarioActual();

            if (usuarioSesion == null) {

                System.out.println(
                        "No hay usuario en sesión."
                );

                return;
            }

            System.out.println(
                    "Usuario en sesión: "
                    + usuarioSesion.getEmail()
            );

            UsuarioDAO usuarioDAO =
                    new UsuarioDAO();

            try {

                Usuario usuario =
                        usuarioDAO.buscarPorEmail(
                                usuarioSesion.getEmail()
                        );

                if (usuario == null) {

                    System.out.println(
                            "No se encontró el usuario en la base."
                    );

                    return;
                }

                lblNombre.setText(
                        usuario.getNombre()
                );

                lblApellido.setText(
                        usuario.getApellido()
                );

                lblEmail.setText(
                        usuario.getEmail()
                );

                lblEstado.setText(
                        usuario.getEstado()
                );

                if (usuario.getRol() != null) {

                    lblRol.setText(
                            usuario
                                    .getRol()
                                    .getNombre()
                    );

                } else {

                    lblRol.setText(
                            "Sin rol"
                    );
                }

                System.out.println(
                        "Perfil cargado correctamente."
                );

                System.out.println(
                        "Nombre: "
                        + usuario.getNombre()
                );

                System.out.println(
                        "Apellido: "
                        + usuario.getApellido()
                );

                System.out.println(
                        "Email: "
                        + usuario.getEmail()
                );

                System.out.println(
                        "Estado: "
                        + usuario.getEstado()
                );

            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
    @FXML
    private void volver() {

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    "/views/inicio.fxml"
                            )
                    );

            Scene scene =
                    new Scene(loader.load());

            Stage stage =
                    (Stage) lblNombre
                            .getScene()
                            .getWindow();

            stage.setScene(scene);

            stage.setTitle(
                    "NearSpot"
            );

            stage.setWidth(900);
            stage.setHeight(700);

            stage.centerOnScreen();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}