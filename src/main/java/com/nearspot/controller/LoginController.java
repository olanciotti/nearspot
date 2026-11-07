package com.nearspot.controller;

import com.nearspot.exception.LoginException;
import com.nearspot.model.Usuario;
import com.nearspot.service.LoginService;
import com.nearspot.session.Sesion;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Label lblError;

    private final LoginService loginService =
            new LoginService();


    @FXML
    private void iniciarSesion() {

        try {

            Usuario usuario =
                    loginService.autenticar(
                            txtEmail.getText(),
                            txtPassword.getText()
                    );

            // Guardamos el usuario logueado
            Sesion.iniciar(usuario);

            System.out.println(
                    "Login correcto: "
                    + usuario.getNombre()
            );

            System.out.println(
                    "Rol: "
                    + usuario.getRol().getNombre()
            );

            abrirInicio();

        } catch (LoginException e) {

            lblError.setText(
                    e.getMessage()
            );
        }
    }


    private void abrirInicio() {

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    "/views/inicio.fxml"
                            )
                    );

            Scene scene =
                    new Scene(
                            loader.load()
                    );

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