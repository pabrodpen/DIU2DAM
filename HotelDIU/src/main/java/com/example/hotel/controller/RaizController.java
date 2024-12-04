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
import javafx.stage.Stage;

import java.io.IOException;

public class RaizController {
    Main main;
    HotelModelo hotelModelo;
    VentanaClientesController ventanaClientesController;
    @FXML
    TextField buscadorField;
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
    private void handleBuscador(){
        Persona persona= hotelModelo.buscarPersonaVOBD(buscadorField.getText());
        if(persona!=null) {
            ventanaClientesController.abrirVentanaReservas(persona);
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Cliente no valido");
            alert.setContentText("El cliente no existe");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleEstadisticas(){
        main.mostrarEstadisticas();
    }

    @FXML
    private void handleGaleria() {
        try {
            // Load the FXML file for the gallery window
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/hotel/galeria-imagenes.fxml"));
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
