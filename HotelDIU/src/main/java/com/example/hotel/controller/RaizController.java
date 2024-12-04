package com.example.hotel.controller;

import com.example.hotel.Main;
import com.example.hotel.model.HotelModelo;
import com.example.hotel.view.Persona;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;


public class RaizController {
    Main main;
    HotelModelo hotelModelo;
    VentanaClientesController ventanaClientesController;
    @FXML
    TextField buscadorField;
    @FXML
    private WebView webView;
    public void setMain(Main main) {
        this.main = main;
    }

    public void setHotelModelo(HotelModelo hotelModelo) {
        this.hotelModelo = hotelModelo;
    }
    public void setVentanaClienteController(VentanaClientesController ventanaClientesController) {
        this.ventanaClientesController = ventanaClientesController;
    }

    @FXML
    public void initialize() {
        System.out.println("webView es: " + webView);  // Verifica si es null o no
    }


    @FXML
    private void handleBuscador() {
        // Obtener la persona a partir del DNI proporcionado en el campo de búsqueda
        Persona persona = hotelModelo.buscarPersonaVOBD(buscadorField.getText());

        // Si la persona es encontrada, pasar al controlador adecuado
        if (persona != null) {
            ventanaClientesController.abrirVentanaReservas(persona);
        } else {
            // Si la persona no es encontrada, mostrar un alert de advertencia
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Cliente no encontrado");
            alert.setHeaderText("No se ha encontrado al cliente");
            alert.setContentText("El cliente con el DNI proporcionado no existe en el sistema.");

            // Mostrar el alert
            alert.showAndWait();
        }
    }


    @FXML
    private void handleVerDocumentacion() {
        try {
            // Usar una ruta relativa dentro de resources
            String path = getClass().getResource("/docs/index.html").toExternalForm();

            // Verificar si la ruta es correcta
            if (path != null) {
                // Cargar el archivo HTML en el WebView
                webView.getEngine().load(path);
            } else {
                throw new Exception("No se pudo encontrar el archivo HTML.");
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al cargar la documentación");
            alert.setHeaderText(null);
            alert.setContentText("Hubo un problema al intentar cargar la documentación: " + e.getMessage());
            alert.showAndWait();
        }
    }


    @FXML
    private void handleEstadisticas(){
        main.mostrarEstadisticas();
    }

    @FXML
    private void handleGaleriaDobles() {
        try {
            // Load the FXML file for the gallery window
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/hotel/hab_dobles.fxml"));
            AnchorPane galeriaPage = loader.load();

            // Create a new stage for the gallery
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Galería de Tipos de Habitaciones");
            Scene scene = new Scene(galeriaPage);
            dialogStage.setScene(scene);

            // Get the controller for the gallery and pass the hotel model
            VentanaGaleriaController controller = loader.getController();
            controller.setHotelModelo(hotelModelo); // Set the hotel model

            // Show the gallery window
            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleGaleriaDoblesIndividuales() {
        try {
            // Load the FXML file for the gallery window
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/hotel/hab_dobles_individuales.fxml"));
            AnchorPane galeriaPage = loader.load();

            // Create a new stage for the gallery
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Galería de Tipos de Habitaciones");
            Scene scene = new Scene(galeriaPage);
            dialogStage.setScene(scene);

            // Get the controller for the gallery and pass the hotel model
            VentanaGaleriaController controller = loader.getController();
            controller.setHotelModelo(hotelModelo); // Set the hotel model

            // Show the gallery window
            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleGaleriaJunior() {
        try {
            // Load the FXML file for the gallery window
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/hotel/hab_junior_suite.fxml"));
            AnchorPane galeriaPage = loader.load();

            // Create a new stage for the gallery
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Galería de Tipos de Habitaciones");
            Scene scene = new Scene(galeriaPage);
            dialogStage.setScene(scene);

            // Get the controller for the gallery and pass the hotel model
            VentanaGaleriaController controller = loader.getController();
            controller.setHotelModelo(hotelModelo); // Set the hotel model

            // Show the gallery window
            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleGaleriaSuite() {
        try {
            // Load the FXML file for the gallery window
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/hotel/hab_suite.fxml"));
            AnchorPane galeriaPage = loader.load();

            // Create a new stage for the gallery
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Galería de Tipos de Habitaciones");
            Scene scene = new Scene(galeriaPage);
            dialogStage.setScene(scene);

            // Get the controller for the gallery and pass the hotel model
            VentanaGaleriaController controller = loader.getController();
            controller.setHotelModelo(hotelModelo); // Set the hotel model

            // Show the gallery window
            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
