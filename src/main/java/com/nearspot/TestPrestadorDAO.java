package com.nearspot;

import com.nearspot.dao.PrestadorDAO;
import com.nearspot.model.Prestador;

import java.util.List;

public class TestPrestadorDAO {

    public static void main(String[] args) {

        PrestadorDAO dao =
                new PrestadorDAO();

        try {

            List<Prestador> prestadores =
                    dao.listarTodos();

            for (Prestador prestador :
                    prestadores) {

                System.out.println(
                        "Nombre: "
                        + prestador.getNombre()
                );

                System.out.println(
                        "Tipo: "
                        + prestador
                            .getClass()
                            .getSimpleName()
                );

                System.out.println(
                        "Rubro: "
                        + prestador
                            .getRubro()
                            .getNombre()
                );

                System.out.println(
                        "Zona: "
                        + prestador
                            .getUbicacion()
                            .getZona()
                            .getNombre()
                );

                System.out.println("------------");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}