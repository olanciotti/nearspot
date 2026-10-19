package com.nearspot.controller;

import com.nearspot.data.DatosPrueba;
import com.nearspot.model.Prestador;
import com.nearspot.service.PrestadorService;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class InicioController {

    @FXML
    private ComboBox<String> cmbBusqueda;

    @FXML
    private TextField txtUbicacion;

    @FXML
    private ListView<Prestador> listaPrestadores;

    private List<Prestador> prestadores;

    @FXML
        private ComboBox<String> cmbOrdenamiento;

        private final PrestadorService prestadorService =
                new PrestadorService();

    @FXML
    public void initialize() {

        // Cargar datos de prueba
        prestadores = DatosPrueba.crearPrestadores();

        // Mostrar todos inicialmente
        listaPrestadores.setItems(
                FXCollections.observableArrayList(prestadores)
        );

        // Obtener los rubros existentes
        List<String> rubros = prestadores.stream()
                .map(prestador ->
                        prestador.getRubro().getNombre()
                )
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        // Agregar opción para mostrar todos
        rubros.add(0, "Todos");

        // Cargar ComboBox
        cmbBusqueda.setItems(
                FXCollections.observableArrayList(rubros)
        );

        // Valor inicial
        cmbBusqueda.setValue("Todos");
        
        // DOBLE CLICK SOBRE UN PRESTADOR
    listaPrestadores.setOnMouseClicked(event -> {

        if (event.getClickCount() == 2) {

            Prestador prestadorSeleccionado =
                    listaPrestadores
                            .getSelectionModel()
                            .getSelectedItem();

            if (prestadorSeleccionado != null) {
                abrirDetalle(prestadorSeleccionado);
            }
        }
    });

    listaPrestadores.setCellFactory(listView ->
        new ListCell<Prestador>() {

            @Override
            protected void updateItem(
                    Prestador prestador,
                    boolean empty) {

                super.updateItem(prestador, empty);

                if (empty || prestador == null) {
                    setText(null);
                    setGraphic(null);
                    return;
                }

                // =========================
                // NOMBRE
                // =========================

                Label lblNombre =
                        new Label(
                                prestador.getNombre()
                        );

                lblNombre.setStyle(
                        "-fx-font-size: 18px;" +
                        "-fx-font-weight: bold;"
                );


                // =========================
                // UBICACIÓN
                // =========================

                String ubicacion = "Ubicación no informada";

                if (prestador.getUbicacion() != null) {

                    ubicacion =
                            prestador
                                    .getUbicacion()
                                    .getDireccion()
                            + " - "
                            + prestador
                                    .getUbicacion()
                                    .getZona()
                                    .getNombre()
                            + ", "
                            + prestador
                                    .getUbicacion()
                                    .getLocalidad();
                }

                Label lblUbicacion =
                        new Label(
                                "📍 " + ubicacion
                        );

                lblUbicacion.setStyle(
                        "-fx-font-size: 13px;"
                );


                // =========================
                // CALIFICACIÓN
                // =========================

                double promedio =
                        prestador
                                .getPromedioCalificacion();

                Label lblCalificacion =
                        new Label(
                                generarEstrellas(
                                        (int) Math.round(promedio)
                                )
                                + "  "
                                + String.format(
                                        "%.1f",
                                        promedio
                                )
                        );

                lblCalificacion.setStyle(
                       "-fx-font-size: 16px;" +
                        "-fx-text-fill: #D4AF37;" +
                        "-fx-font-weight: bold;"
                );


                // =========================
                // TIPO / RUBRO
                // =========================

                Label lblRubro =
                        new Label(
                                prestador
                                        .getRubro()
                                        .getNombre()
                        );

                lblRubro.setStyle(
                        "-fx-font-size: 13px;" +
                        "-fx-font-style: italic;"
                );


                // =========================
                // CARD
                // =========================

                VBox card =
                        new VBox(
                                7,
                                lblNombre,
                                lblRubro,
                                lblUbicacion,
                                lblCalificacion
                        );

                card.setStyle(
                        "-fx-background-color: white;" +
                        "-fx-background-radius: 15;" +
                        "-fx-border-radius: 15;" +
                        "-fx-border-color: #dddddd;" +
                        "-fx-border-width: 1;" +
                        "-fx-padding: 18;"
                );

                card.setMaxWidth(
                        Double.MAX_VALUE
                );


                // Sacar aspecto tradicional del ListCell
                setText(null);
                setGraphic(card);

                setStyle(
                        "-fx-background-color: transparent;" +
                        "-fx-padding: 7;"
                );
            }
        }
);
                cmbOrdenamiento.getItems().addAll(
                        "Sin ordenar",
                        "Nombre A-Z",
                        "Mejor calificación"
                );

                cmbOrdenamiento.setValue("Sin ordenar");
    }

    @FXML
private void buscar() {

    String rubroSeleccionado =
            cmbBusqueda.getValue();

    String ubicacionBuscada =
            txtUbicacion.getText();

    // ==============================
    // BÚSQUEDA LINEAL
    // ==============================

    List<Prestador> resultados =
            prestadorService.buscarLineal(
                    prestadores,
                    rubroSeleccionado,
                    ubicacionBuscada
            );


    // ==============================
    // ORDENAMIENTO
    // ==============================

    String ordenSeleccionado =
            cmbOrdenamiento.getValue();

    if ("Nombre A-Z".equals(ordenSeleccionado)) {

        resultados =
                prestadorService
                        .ordenarPorNombre(resultados);

    } else if ("Mejor calificación"
            .equals(ordenSeleccionado)) {

        resultados =
                prestadorService
                        .ordenarPorCalificacion(
                                resultados
                        );
    }


    // ==============================
    // MOSTRAR RESULTADOS
    // ==============================

    listaPrestadores.setItems(
            FXCollections.observableArrayList(
                    resultados
            )
    );
}
/*
    @FXML
    private void buscar() {

        String rubroSeleccionado =
                cmbBusqueda.getValue();

        String ubicacionBuscada =
                txtUbicacion.getText()
                        .toLowerCase()
                        .trim();

        List<Prestador> resultados =
                prestadores.stream()

                        // FILTRO POR RUBRO
                        .filter(prestador -> {

                            if (rubroSeleccionado == null ||
                                rubroSeleccionado.equals("Todos")) {

                                return true;
                            }

                            return prestador
                                    .getRubro()
                                    .getNombre()
                                    .equalsIgnoreCase(
                                            rubroSeleccionado
                                    );
                        })

                        // FILTRO POR UBICACIÓN
                        .filter(prestador -> {

                            if (ubicacionBuscada.isEmpty()) {
                                return true;
                            }

                            return prestador
                                    .getUbicacion()
                                    .getLocalidad()
                                    .toLowerCase()
                                    .contains(ubicacionBuscada)

                                    ||

                                    prestador
                                            .getUbicacion()
                                            .getProvincia()
                                            .toLowerCase()
                                            .contains(ubicacionBuscada)

                                    ||

                                    prestador
                                            .getUbicacion()
                                            .getZona()
                                            .getNombre()
                                            .toLowerCase()
                                            .contains(ubicacionBuscada);
                        })

                        .collect(Collectors.toList());

        // Mostrar resultados
        listaPrestadores.setItems(
                FXCollections.observableArrayList(resultados)
        ); 
               
    }
 */
    private void abrirDetalle(Prestador prestador) {

    try {

        FXMLLoader loader =
                new FXMLLoader(
                        getClass().getResource(
                                "/views/detallePrestador.fxml"
                        )
                );

        Scene scene = new Scene(loader.load());

        // Obtener el controller de detallePrestador.fxml
        DetallePrestadorController controller =
                loader.getController();

        // Enviarle el prestador seleccionado
        controller.setPrestador(prestador);

        // Obtener la ventana actual
        Stage stage =
                (Stage) listaPrestadores
                        .getScene()
                        .getWindow();

        // Cambiar la pantalla
        stage.setScene(scene);

        stage.setTitle(
                "NearSpot - " + prestador.getNombre()
        );

        stage.setWidth(900);
        stage.setHeight(700);

        stage.centerOnScreen();

    } catch (IOException e) {

        System.err.println(
                "Error al abrir el detalle del prestador: "
                + e.getMessage()
        );

        e.printStackTrace();
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
}