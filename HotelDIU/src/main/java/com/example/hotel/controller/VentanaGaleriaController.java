package com.example.hotel.controller;

import com.example.hotel.model.HotelModelo;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressIndicator;

import javax.swing.text.html.ImageView;
import java.awt.*;

public class VentanaGaleriaController {

    private HotelModelo hotelModelo;
    @FXML
    ImageView dobleImg,dobleIndividualImg,suiteImg,juniorImg;

    @FXML
    ProgressIndicator progresoDoble, progresoDobleInidividual, progresoJunior, progresoSuite;

    public void setHotelModelo(HotelModelo hotelModelo) {
        this.hotelModelo = hotelModelo;
        hotelModelo.actualizarNumReservas();
        bindDatos();
    }

    private void bindDatos() {
        if (hotelModelo != null) {
            // Bind the progress of the indicators to the model's properties
            progresoDoble.progressProperty().bind(hotelModelo.numReservasDobleProperty().divide(80.0));
            progresoDobleInidividual.progressProperty().bind(hotelModelo.numReservasIndividualProperty().divide(20.0));
            progresoJunior.progressProperty().bind(hotelModelo.numReservasJuniorProperty().divide(15.0));
            progresoSuite.progressProperty().bind(hotelModelo.numReservasSuiteProperty().divide(5.0));
        }
    }



    @FXML
    public void actualizarProgresos() {
        if (hotelModelo == null) {
            return; // Avoid NullPointerException if hotelModelo is not set
        }

        int totalReservas = hotelModelo.getListaTodasReservas().size();

        if (totalReservas > 0) {
            progresoDoble.setProgress((double) hotelModelo.numReservasDobleProperty().get() / 80);
            progresoDobleInidividual.setProgress((double) hotelModelo.numReservasIndividualProperty().get() / 20);
            progresoJunior.setProgress((double) hotelModelo.numReservasJuniorProperty().get() / 15);
            progresoSuite.setProgress((double) hotelModelo.numReservasSuiteProperty().get() / 5);
        } else {
            // Reset all progress indicators if there are no reservations
            progresoDoble.setProgress(0);
            progresoDobleInidividual.setProgress(0);
            progresoJunior.setProgress(0);
            progresoSuite.setProgress(0);
        }
    }

}
