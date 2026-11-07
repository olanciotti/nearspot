package com.nearspot;

import com.nearspot.dao.CalificacionesDAO;
import com.nearspot.dao.ResenaDAO;

import com.nearspot.model.Calificacion;
import com.nearspot.model.Resena;

import java.util.List;

public class TestResenasCalificacionesDAO {

    public static void main(String[] args) {

        int prestadorId = 3;

        ResenaDAO resenaDAO =
                new ResenaDAO();

        CalificacionesDAO calificacionDAO =
                new CalificacionesDAO();

        try {

            List<Resena> resenas =
                    resenaDAO.listarPorPrestador(
                            prestadorId
                    );

            List<Calificacion> calificaciones =
                    calificacionDAO.listarPorPrestador(
                            prestadorId
                    );


            System.out.println(
                    "=== RESEÑAS ==="
            );

            for (Resena resena : resenas) {

                System.out.println(
                        resena.getUsuario()
                                .getNombre()
                        + ": "
                        + resena.getContenido()
                );
            }


            System.out.println(
                    "\n=== CALIFICACIONES ==="
            );

            for (Calificacion calificacion :
                    calificaciones) {

                System.out.println(
                        calificacion
                            .getUsuario()
                            .getNombre()
                        + ": "
                        + calificacion
                            .getValor()
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}