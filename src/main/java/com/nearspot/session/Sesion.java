package com.nearspot.session;

import com.nearspot.model.Usuario;

public class Sesion {

    private static Usuario usuarioActual;

    private Sesion() {
    }

    public static void iniciar(Usuario usuario) {
        usuarioActual = usuario;
    }

    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public static void cerrar() {
        usuarioActual = null;
    }

    public static boolean hayUsuarioLogueado() {
        return usuarioActual != null;
    }
}