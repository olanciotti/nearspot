package com.nearspot.data;

import com.nearspot.model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DatosPrueba {

    public static List<Usuario> crearUsuarios() {

    List<Usuario> usuarios = new ArrayList<>();

    Rol administrador =
            new Rol(1, "ADMINISTRADOR");

    Rol usuarioGeneral =
            new Rol(2, "USUARIO_GENERAL");

    Rol usuarioPrestador =
            new Rol(3, "USUARIO_PRESTADOR");

    Usuario admin = new Usuario(
            1,
            "Ornella",
            "Lanciotti",
            "admin@nearspot.com",
            "admin123",
            "ACTIVO",
            1
    );

    Usuario sofia = new Usuario(
            2,
            "Sofía",
            "Ramírez",
            "sofia@nearspot.com",
            "1234",
            "ACTIVO",
            2
    );

    Usuario juan = new Usuario(
            3,
            "Juan",
            "López",
            "juan@nearspot.com",
            "1234",
            "ACTIVO",
            3
    );

    usuarios.add(admin);
    usuarios.add(sofia);
    usuarios.add(juan);

    return usuarios;
}

    public static List<Prestador> crearPrestadores() {

        List<Prestador> prestadores = new ArrayList<>();

        Rubro electricidad =
                new Rubro(
                        1,
                        "Electricidad",
                        "Servicios eléctricos",
                        "ACTIVO"
                );

        Rubro gastronomia =
                new Rubro(
                        2,
                        "Gastronomía",
                        "Alimentos y gastronomía",
                        "ACTIVO"
                );

        Rubro tecnologia =
                new Rubro(
                        3,
                        "Tecnología",
                        "Servicios tecnológicos",
                        "ACTIVO"
                );

         
        Zona centro = new Zona(
                 1,
                "Centro"
        );

        Zona norte = new Zona(
                2,
                "Zona Norte"
        );

        Zona sur = new Zona(
                3,
                "Zona Sur"
        );

        Zona oeste = new Zona(
                4,
                "Zona Oeste"
        );      

        Ubicacion ubicacion1 = new Ubicacion(
            1,
            "San Martín 520",
            "Rosario",
            "Santa Fe",
            -32.9468,
            -60.6393,
            centro  
        );

        Ubicacion ubicacion2 = new Ubicacion(
            2,
            "Av. Alberdi 1850",
            "Rosario",
            "Santa Fe",
            -32.9255,
            -60.6684,
            norte
        );

        Ubicacion ubicacion3 = new Ubicacion(
            3,
            "Av. San Martín 4200",
            "Rosario",
            "Santa Fe",
            -32.9881,
            -60.6467,
            sur
        );

        Ubicacion ubicacion4 = new Ubicacion(
            4,
            "San Juan 3100",
            "Rosario",
            "Santa Fe",
            -32.9561,
            -60.6723,
            oeste
        );

        PrestadorServicio prestadorServicio_1 = new PrestadorServicio(
        3,
        "Electricidad López",
        "Instalaciones y reparaciones eléctricas",
        "3415550101",
        "electricidad@nearspot.com",
        null,
        "@electricidadlopez",
        LocalDateTime.of(2026, 10, 19, 8, 0),
        "ACTIVO",
        LocalDateTime.of(2026, 1, 15, 10, 30),
        true,
        electricidad,
        ubicacion1,
        "Electricista",
        8,
        true,
        "MAT-12345"
);


Usuario sofia = new Usuario(
        10,
        "Sofía",
        "Ramírez",
        "sofia@nearspot.com",
        "1234",
        "ACTIVO",
        2
);

Usuario lucas = new Usuario(
        11,
        "Lucas",
        "Torres",
        "lucas@nearspot.com",
        "1234",
        "ACTIVO",
        2
);

Calificacion calificacion1 = new Calificacion(
        1,
        5,
        LocalDateTime.now(),
        "ACTIVO",
        sofia
);

Calificacion calificacion2 = new Calificacion(
        2,
        3,
        LocalDateTime.now(),
        "ACTIVO",
        lucas
);

Resena resena1 = new Resena(
        1,
        "Excelente atención, muy recomendable.",
        LocalDateTime.now(),
        "ACTIVO",
        sofia
);

Resena resena2 = new Resena(
        2,
        "Buen servicio, aunque demoró un poco.",
        LocalDateTime.now(),
        "ACTIVO",
        lucas
);

prestadorServicio_1.agregarCalificacion(calificacion1);
prestadorServicio_1.agregarCalificacion(calificacion2);

prestadorServicio_1.agregarResena(resena1);
prestadorServicio_1.agregarResena(resena2);


Emprendimiento emprendimiento_1 = new Emprendimiento(
        2,
        "Dulce Carolina",
        "Tortas y desayunos artesanales",
        "3415550202",
        "dulce@nearspot.com",
        null,
        "@dulcecarolina",
        LocalDateTime.of(2026, 10, 19, 9, 0),
        "ACTIVO",
        LocalDateTime.of(2026, 2, 10, 11, 0),
        true,
        gastronomia,
        ubicacion2,
        "Online",
        true,
        true
);
      Comercio comercio_1 = new Comercio(
        1,
        "TecnoPoint",
        "Venta y reparación de computadoras",
        "3415550303",
        "ventas@tecnopoint.com",
        "https://tecnopoint.com",
        "@tecnopoint",
        LocalDateTime.of(2026, 10, 19, 9, 0),
        "ACTIVO",
        LocalDateTime.of(2026, 3, 10, 10, 0),
        true,
        tecnologia,
        ubicacion2,
        "TecnoPoint SRL",
        "30-12345678-9",
        true
);

        prestadores.add(prestadorServicio_1); 
        prestadores.add(emprendimiento_1);
        prestadores.add(comercio_1);

        return prestadores;

        
}

}