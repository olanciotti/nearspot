package com.nearspot.service;

import com.nearspot.dao.UsuarioDAO;
import com.nearspot.exception.LoginException;
import com.nearspot.model.Usuario;

import java.sql.SQLException;

public class LoginService {

    private final UsuarioDAO usuarioDAO;

    public LoginService() {
        this.usuarioDAO =
                new UsuarioDAO();
    }

    public Usuario autenticar(
            String email,
            String password)
            throws LoginException {

        if (email == null ||
            email.isBlank() ||
            password == null ||
            password.isBlank()) {

            throw new LoginException(
                    "Debe completar email y contraseña."
            );
        }

        try {

            Usuario usuario =
                    usuarioDAO.buscarPorEmail(
                            email.trim()
                    );

            if (usuario == null) {

                throw new LoginException(
                        "Email o contraseña incorrectos."
                );
            }

            if (!usuario
                    .getPassword()
                    .equals(password)) {

                throw new LoginException(
                        "Email o contraseña incorrectos."
                );
            }

            if (!usuario
                    .getEstado()
                    .equalsIgnoreCase("ACTIVO")) {

                throw new LoginException(
                        "El usuario se encuentra inactivo."
                );
            }

            return usuario;

        } catch (SQLException e) {

            e.printStackTrace();

            throw new LoginException(
                    "No fue posible conectarse con la base de datos."
            );
        }
    }
}