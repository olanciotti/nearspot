package com.nearspot.controller;

import com.nearspot.data.DatosPrueba;
import com.nearspot.model.Usuario;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class LoginController {

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Label lblError;

    @FXML
    private void iniciarSesion() {

        String email =
                txtEmail.getText().trim();

        String password =
                txtPassword.getText();

        if (email.isEmpty() || password.isEmpty()) {

            lblError.setText(
                    "Debe completar email y contraseña."
            );

            return;
        }

        List<Usuario> usuarios =
                DatosPrueba.crearUsuarios();

        Usuario usuarioEncontrado =
                usuarios.stream()
                        .filter(usuario ->
                                usuario.getEmail()
                                        .equalsIgnoreCase(email)
                        )
                        .filter(usuario ->
                                usuario.getPassword()
                                        .equals(password)
                        )
                        .filter(usuario ->
                                usuario.getEstado()
                                        .equalsIgnoreCase("ACTIVO")
                        )
                        .findFirst()
                        .orElse(null);

        if (usuarioEncontrado == null) {

            lblError.setText(
                    "Email o contraseña incorrectos."
            );

            return;
        }

        System.out.println(
                "Login correcto: "
                + usuarioEncontrado.getNombre()
        );

        System.out.println(
                "Rol: "
                + usuarioEncontrado
                    .getRolId()
        );

        abrirInicio();
    }

    private void abrirInicio() {

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass()
                                    .getResource(
                                            "/views/inicio.fxml"
                                    )
                    );

            Scene scene =
                    new Scene(loader.load());

            Stage stage =
                    (Stage) txtEmail
                            .getScene()
                            .getWindow();

            stage.setScene(scene);
            stage.setTitle("NearSpot");
            stage.setWidth(900);
            stage.setHeight(600);
            stage.centerOnScreen();

        } catch (IOException e) {

            e.printStackTrace();

            lblError.setText(
                    "No se pudo abrir la pantalla principal."
            );
        }
    }
}