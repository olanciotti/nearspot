package com.nearspot.controller;

import com.nearspot.model.Comercio;
import com.nearspot.model.Emprendimiento;
import com.nearspot.model.Prestador;
import com.nearspot.model.PrestadorServicio;
import com.nearspot.model.Resena;
import com.nearspot.model.Calificacion;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class DetallePrestadorController {

    @FXML
    private Label lblNombre;

    @FXML
    private Label lblVerificado;

    @FXML
    private Label lblTipo;

    @FXML
    private Label lblRubro;

    @FXML
    private Label lblDescripcion;

    @FXML
    private Label lblTelefono;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblSitioWeb;

    @FXML
    private Label lblInstagram;

    @FXML
    private Label lblDireccion;

    @FXML
    private Label lblLocalidad;

    @FXML
    private Label lblProvincia;

    @FXML
    private Label lblZona;

    @FXML
    private Label lblHorario;

    @FXML
    private Label lblEstado;

    @FXML
    private Label lblDatosEspecificos;

    @FXML
    private Label lblEstrellasPromedio;

    @FXML
    private Label lblPromedio;

    @FXML
    private Label lblCantidadCalificaciones;

    @FXML
    private VBox contenedorResenas;

    private Prestador prestador;


    public void setPrestador(Prestador prestador) {

        this.prestador = prestador;

        mostrarDatos();
    }


    private void mostrarDatos() {

        if (prestador == null) {
            return;
        }

        lblNombre.setText(
                prestador.getNombre()
        );

        lblDescripcion.setText(
                prestador.getDescripcion()
        );

        lblRubro.setText(
                "Rubro: " +
                prestador.getRubro().getNombre()
        );

        lblTelefono.setText(
                "Teléfono: " +
                valorSeguro(prestador.getTelefono())
        );

        lblEmail.setText(
                "Email: " +
                valorSeguro(prestador.getEmail())
        );

        lblSitioWeb.setText(
                "Sitio web: " +
                valorSeguro(prestador.getSitioWeb())
        );

        lblInstagram.setText(
                "Instagram: " +
                valorSeguro(prestador.getInstagram())
        );

        lblEstado.setText(
                "Estado: " +
                prestador.getEstado()
        );

        // Verificado
        if (prestador.isEstaVerificado()) {

            lblVerificado.setText(
                    "✓ Verificado"
            );

        } else {

            lblVerificado.setText(
                    "No verificado"
            );
        }


        // UBICACIÓN
        if (prestador.getUbicacion() != null) {

            lblDireccion.setText(
                    "Dirección: " +
                    valorSeguro(
                            prestador
                                    .getUbicacion()
                                    .getDireccion()
                    )
            );

            lblLocalidad.setText(
                    "Localidad: " +
                    valorSeguro(
                            prestador
                                    .getUbicacion()
                                    .getLocalidad()
                    )
            );

            lblProvincia.setText(
                    "Provincia: " +
                    valorSeguro(
                            prestador
                                    .getUbicacion()
                                    .getProvincia()
                    )
            );

            if (prestador
                    .getUbicacion()
                    .getZona() != null) {

                lblZona.setText(
                        "Zona: " +
                        prestador
                                .getUbicacion()
                                .getZona()
                                .getNombre()
                );
            }
        }


        // HORARIO
        if (prestador.getHorarioAtencion() != null) {

            DateTimeFormatter formato =
                    DateTimeFormatter.ofPattern(
                            "HH:mm"
                    );

            lblHorario.setText(
                    "Horario de atención: " +
                    prestador
                            .getHorarioAtencion()
                            .format(formato)
            );

        } else {

            lblHorario.setText(
                    "Horario de atención: No informado"
            );
        }

        mostrarResenasYCalificaciones();
        mostrarDatosEspecificos();
    }


    private void mostrarResenasYCalificaciones() {

    double promedio =
            prestador.getPromedioCalificacion();

    lblPromedio.setText(
            String.format("%.1f", promedio)
    );

    lblEstrellasPromedio.setText(
            generarEstrellas(
                    (int) Math.round(promedio)
            )
    );

    int cantidad =
            prestador
                    .getCalificaciones()
                    .size();

    lblCantidadCalificaciones.setText(
            cantidad + " calificaciones"
    );

    contenedorResenas
            .getChildren()
            .clear();

    for (Resena resena :
            prestador.getResenas()) {

        VBox tarjeta =
                crearTarjetaResena(resena);

        contenedorResenas
                .getChildren()
                .add(tarjeta);
    }

    if (prestador.getResenas().isEmpty()) {

        Label sinResenas =
                new Label(
                        "Este prestador todavía no tiene reseñas."
                );

        contenedorResenas
                .getChildren()
                .add(sinResenas);
    }
}

private String generarEstrellas(int valor) {

    StringBuilder estrellas =
            new StringBuilder();

    for (int i = 1; i <= 5; i++) {

        if (i <= valor) {
            estrellas.append("★");
        } else {
            estrellas.append("☆");
        }
    }

    return estrellas.toString();
}

private VBox crearTarjetaResena(
        Resena resena) {

    Label lblUsuario =
            new Label(
                    resena.getUsuario().getNombre()
                    + " "
                    + resena.getUsuario().getApellido()
            );

    lblUsuario.setStyle(
            "-fx-font-weight: bold;"
    );


    int valorCalificacion =
            buscarCalificacionUsuario(
                    resena.getUsuario().getId()
            );


    Label lblEstrellas =
            new Label(
                    generarEstrellas(
                            valorCalificacion
                    )
            );

    lblEstrellas.setStyle(
            "-fx-font-size: 18px;"+
        "-fx-text-fill: #D4AF37;" +
        "-fx-font-weight: bold;"
    );


    Label lblContenido =
            new Label(
                    resena.getContenido()
            );

    lblContenido.setWrapText(true);


    VBox tarjeta =
            new VBox(
                    5,
                    lblUsuario,
                    lblEstrellas,
                    lblContenido
            );

    tarjeta.setStyle(
            "-fx-background-color: #f7f7f7;" +
            "-fx-background-radius: 10;" +
            "-fx-border-radius: 10;" +
            "-fx-padding: 12;"
    );

    return tarjeta;
}

private int buscarCalificacionUsuario(
        int usuarioId) {

    return prestador
            .getCalificaciones()
            .stream()
            .filter(calificacion ->
                    calificacion
                            .getUsuario()
                            .getId()
                            == usuarioId
            )
            .mapToInt(
                    Calificacion::getValor
            )
            .findFirst()
            .orElse(0);
}
    private void mostrarDatosEspecificos() {

        if (prestador instanceof PrestadorServicio servicio) {

            lblTipo.setText(
                    "Prestador de servicios"
            );

            lblDatosEspecificos.setText(
                    "Especialidad: "
                    + servicio.getEspecialidad()
                    + "\nAños de experiencia: "
                    + servicio.getAniosExperiencia()
                    + "\nTrabaja a domicilio: "
                    + (servicio.isTrabajaADomicilio()
                        ? "Sí"
                        : "No")
            );

        } else if (prestador instanceof Comercio comercio) {

            lblTipo.setText(
                    "Comercio"
            );

            lblDatosEspecificos.setText(
                    "Razón social: "
                    + valorSeguro(
                        comercio.getRazonSocial()
                    )
                    + "\nCUIT: "
                    + valorSeguro(
                        comercio.getCuit()
                    )
                    + "\nRealiza envíos: "
                    + (comercio.isRealizaEnvios()
                        ? "Sí"
                        : "No")
            );

        } else if (prestador instanceof Emprendimiento emprendimiento) {

            lblTipo.setText(
                    "Emprendimiento"
            );

            lblDatosEspecificos.setText(
                    "Modalidad de venta: "
                    + valorSeguro(
                        emprendimiento
                            .getModalidadVenta()
                    )
                    + "\nTrabaja por pedido: "
                    + (emprendimiento
                            .isTrabajaPorPedido()
                        ? "Sí"
                        : "No")
                    + "\nRealiza entregas: "
                    + (emprendimiento
                            .isRealizaEntregas()
                        ? "Sí"
                        : "No")
            );

        } else {

            lblTipo.setText(
                    "Prestador"
            );

            lblDatosEspecificos.setText("");
        }
    }


    private String valorSeguro(String valor) {

        if (valor == null ||
            valor.isBlank()) {

            return "No informado";
        }

        return valor;
    }


    @FXML
    private void volver() {

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass()
                                    .getResource(
                                            "/views/inicio.fxml"
                                    )
                    );

            Scene scene =
                    new Scene(
                            loader.load()
                    );

            Stage stage =
                    (Stage) lblNombre
                            .getScene()
                            .getWindow();

            stage.setScene(scene);
            stage.setTitle("NearSpot");

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}