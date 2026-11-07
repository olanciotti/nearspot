package com.nearspot;

import com.nearspot.dao.UsuarioDAO;
import com.nearspot.model.Usuario;

public class TestUsuarioDAO {

    public static void main(String[] args) {

        UsuarioDAO usuarioDAO =
                new UsuarioDAO();

        try {

            Usuario usuario =
                    usuarioDAO.buscarPorEmail(
                            "ornella.admin@nearspot.com"
                    );

            if (usuario != null) {

                System.out.println(
                        "Usuario encontrado"
                );

                System.out.println(
                        "Nombre: "
                        + usuario.getNombre()
                );

                System.out.println(
                        "Email: "
                        + usuario.getEmail()
                );

                System.out.println(
                        "Estado: "
                        + usuario.getEstado()
                );

                System.out.println(
                        "Rol: "
                        + usuario
                            .getRol()
                            .getNombre()
                );

            } else {

                System.out.println(
                        "Usuario no encontrado"
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}