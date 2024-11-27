package com.example.hotel.controller;

import com.example.hotel.Main;
import com.example.hotel.model.HotelModelo;
import com.example.hotel.view.Reserva;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalTime;

public class VentanaCreacionReservasController {

    boolean isOkClicked = false;
    Main main;
    HotelModelo hotelModelo;
    Stage dialogoStage;
    Reserva reserva;

    @FXML
    TextField codigoField;
    @FXML
    private Spinner<Integer> spinnerNumHabitaciones;

    @FXML
    SplitMenuButton tipoHabitacionMenu, regimenHabitacionMenu;
    @FXML
    DatePicker fechaLlegadaPicker, fechaSalidaPicker;
    @FXML
    TextField horaLlegadaField, horaSalidaField;
    @FXML
    CheckBox fumadorCheckBox;

    public void initialize() {
        // Configurar el Spinner con valores de 1 a 10 y valor inicial de 1
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1);
        spinnerNumHabitaciones.setValueFactory(valueFactory);
        spinnerNumHabitaciones.setEditable(true); // Permitir edición manual

        // Opciones para tipo de habitación
        tipoHabitacionMenu.getItems().addAll(
                new MenuItem("doble_individual"),
                new MenuItem("doble"),
                new MenuItem("junior_suite"),
                new MenuItem("suite")
        );

        for (MenuItem item : tipoHabitacionMenu.getItems()) {
            item.setOnAction(event -> tipoHabitacionMenu.setText(item.getText())); // Establece el texto al seleccionarlo
        }

        // Opciones para régimen de habitación
        regimenHabitacionMenu.getItems().addAll(
                new MenuItem("alojamiento_desayuno"),
                new MenuItem("media_pension"),
                new MenuItem("pension_completa")
        );

        for (MenuItem item : regimenHabitacionMenu.getItems()) {
            item.setOnAction(event -> regimenHabitacionMenu.setText(item.getText()));
        }
    }

    public boolean isOkClicked() {
        return isOkClicked;
    }

    public void setOkClicked(boolean isOkClicked) {
        this.isOkClicked = isOkClicked;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void setHotelModelo(HotelModelo hotelModelo) {
        this.hotelModelo = hotelModelo;
    }

    public void setDialogoStage(Stage dialogoStage) {
        this.dialogoStage = dialogoStage;
    }

    public void cambiarDatosReservaCreacion(Reserva reserva) {
        if (reserva == null) {
            // Initialize a new Reserva if null
            this.reserva = new Reserva();
        } else {
            this.reserva = reserva;
        }

        codigoField.setText(reserva.getCodigo());
        spinnerNumHabitaciones.getValueFactory().setValue(reserva.getNumHabitaciones()); // Cargar número de habitaciones
        tipoHabitacionMenu.setText(reserva.getTipoHabitacion());
        regimenHabitacionMenu.setText(reserva.getRegimenHabitacion());

        // Convertimos las fechas y horas para mostrarlas correctamente
        if (reserva.getHoraLlegada() != null) {
            fechaLlegadaPicker.setValue(reserva.getHoraLlegada().toLocalDate());
            horaLlegadaField.setText(reserva.getHoraLlegada().toLocalTime().toString());
        }else{
            horaLlegadaField.setPromptText("HH:mm:ss");
        }

        if (reserva.getHoraSalida() != null) {
            fechaSalidaPicker.setValue(reserva.getHoraSalida().toLocalDate());
            horaSalidaField.setText(reserva.getHoraSalida().toLocalTime().toString());
        }else{
            horaSalidaField.setPromptText("HH:mm:ss");
        }

        // Establecemos el valor del CheckBox según el valor de "fumador"
        fumadorCheckBox.setSelected(reserva.isEsFumador());
    }

    public void cambiarDatosReservaEdicion(Reserva reserva) {
        if (reserva == null) {
            this.reserva = new Reserva();
        } else {
            this.reserva = reserva;
        }

        codigoField.setText(reserva.getCodigo());
        codigoField.setDisable(true);
        spinnerNumHabitaciones.getValueFactory().setValue(reserva.getNumHabitaciones()); // Cargar número de habitaciones
        tipoHabitacionMenu.setText(reserva.getTipoHabitacion());
        regimenHabitacionMenu.setText(reserva.getRegimenHabitacion());

        if (reserva.getHoraLlegada() != null) {
            fechaLlegadaPicker.setValue(reserva.getHoraLlegada().toLocalDate());
            horaLlegadaField.setText(reserva.getHoraLlegada().toLocalTime().toString());
        }

        if (reserva.getHoraSalida() != null) {
            fechaSalidaPicker.setValue(reserva.getHoraSalida().toLocalDate());
            horaSalidaField.setText(reserva.getHoraSalida().toLocalTime().toString());
        }

        fumadorCheckBox.setSelected(reserva.isEsFumador());
    }


    @FXML
    public void handleOkClicked() {
        if (reserva == null) {
            reserva = new Reserva();
        }

        // Obtenemos los datos ingresados por el usuario
        reserva.setCodigo(codigoField.getText());
        reserva.setNumHabitaciones(spinnerNumHabitaciones.getValue()); // Usar el Spinner
        reserva.setTipoHabitacion(tipoHabitacionMenu.getText());
        reserva.setRegimenHabitacion(regimenHabitacionMenu.getText());

        // Convertir las fechas y horas de los campos de texto
        LocalDate fechaLlegada = fechaLlegadaPicker.getValue();
        LocalDate fechaSalida = fechaSalidaPicker.getValue();

        // Parseamos las horas
        LocalTime horaLlegada = LocalTime.parse(horaLlegadaField.getText());
        LocalTime horaSalida = LocalTime.parse(horaSalidaField.getText());

        // Establecemos las fechas y horas en el objeto reserva
        reserva.setHoraLlegada(fechaLlegada.atTime(horaLlegada));
        reserva.setHoraSalida(fechaSalida.atTime(horaSalida));

        reserva.setEsFumador(fumadorCheckBox.isSelected());

        // Establecemos el booleano en true para indicar que se hizo clic en OK
        isOkClicked = true;

        // Cerramos la ventana de diálogo
        dialogoStage.close();
    }

    // Método para cuando pulsemos el botón de Cancelar
    @FXML
    public void handleCancel() {
        dialogoStage.close();
    }
}
