package com.nearspot;
import com.nearspot.data.DatosPrueba;
import com.nearspot.model.Prestador;
import java.util.List;

public class TestNearSpot {

    public static void main(String[] args) {

        List<Prestador> prestadores =
                DatosPrueba.crearPrestadores();

        System.out.println("=== PRESTADORES NEARSPOT ===");

        for (Prestador prestador : prestadores) {

            System.out.println("--------------------");

            System.out.println(
                    "Nombre: " +
                    prestador.getNombre()
            );

            System.out.println(
                    "Rubro: " +
                    prestador.getRubro().getNombre()
            );

            System.out.println(
                    "Dirección: " +
                    prestador.getUbicacion().getDireccion()
            );

            System.out.println(
                    "Zona: " +
                    prestador
                            .getUbicacion()
                            .getZona()
                            .getNombre()
            );

            System.out.println(
                    "Tipo: " +
                    prestador.getClass().getSimpleName()
            );
        }
    }
}