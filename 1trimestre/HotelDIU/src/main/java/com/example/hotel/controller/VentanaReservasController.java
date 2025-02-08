package com.example.hotel.controller;

import com.example.hotel.Main;
import com.example.hotel.model.HotelModelo;
import com.example.hotel.view.Persona;
import com.example.hotel.view.Reserva;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class VentanaReservasController {

    @FXML
    public TableView<Reserva> reservaTable;

    // Estas variables ya están definidas, no es necesario redefinirlas aquí
    @FXML
    public TableColumn<Reserva, String> codigoReserva;
    @FXML
    public TableColumn<Reserva, String> fechaLlegadaReserva;

    @FXML
    Label dniClienteLabel;
    @FXML
    Label fechaLlegadaLabel;
    @FXML
    Label fechaSalidaLabel;
    @FXML
    Label numHabitacionesLabel;
    @FXML
    Label tipoHabitacionLabel;
    @FXML
    Label regimenHabitacionLabel;
    @FXML
    CheckBox checkFumador;
    @FXML
    Label fumadorLabel;

    HotelModelo hotelModelo;
    Persona persona;
    public ObservableList<Reserva> listaReservas = FXCollections.observableArrayList();

    //pasamos el cliente seleccionado para mostrar las reservas
    public void setClienteSeleccionado(Persona cliente) {
        this.persona = cliente; // Seteamos la persona seleccionada
        // Obtener las reservas del cliente seleccionado
        listaReservas.setAll(hotelModelo.getListaReservas(cliente.getDni()));
        reservaTable.setItems(listaReservas);  // Mostrar las reservas en la tabla
        dniClienteLabel.setText(persona.getNombre_completo());
    }

    public void setHotelModelo(HotelModelo hotelModelo) {
        this.hotelModelo = hotelModelo;
    }

    Main main;
    public void setMain(Main main) {
        this.main = main;
    }

    @FXML
    public void initialize() {
        // Se inicializa la lista de Reservas mostrando el código de la reserva y la fecha de llegada
        codigoReserva.setCellValueFactory(cellData -> cellData.getValue().codigoProperty());

        // Nueva columna para la fecha de llegada
        fechaLlegadaReserva.setCellValueFactory(cellData -> cellData.getValue().horaLlegadaProperty().asString());

        // Cuando se seleccione una reserva en el table, se muestran los detalles mediante un listener
        reservaTable.getSelectionModel().selectedItemProperty().addListener
                ((observable, oldValue, newValue) -> mostrarDetalles(newValue));
    }

    private void mostrarDetalles(Reserva r) {
        if (r != null) {
            numHabitacionesLabel.setText(String.valueOf(r.getNumHabitaciones()));
            tipoHabitacionLabel.setText(r.getTipoHabitacion());
            regimenHabitacionLabel.setText(r.getRegimenHabitacion());
            fechaLlegadaLabel.setText(String.valueOf(r.getHoraLlegada()));
            fechaSalidaLabel.setText(String.valueOf(r.getHoraSalida()));
            checkFumador.setSelected(r.isEsFumador());
            if (r.isEsFumador()) {
                fumadorLabel.setText("\"En virtud de la ley de\n sanidad se informa a los clientes de\n que solo podrán\n fumar en las habitaciones\n reservadas para tal fin.\"");
                fumadorLabel.setStyle("-fx-font-size: 7pt;");
            }
            checkFumador.setDisable(true);
        } else {
            numHabitacionesLabel.setText("");
            tipoHabitacionLabel.setText("");
            regimenHabitacionLabel.setText("");
            fechaLlegadaLabel.setText("");
            fechaSalidaLabel.setText("");
        }
    }

    @FXML
    private void handleNewReserva() {
        Reserva nuevaReservaCreada = new Reserva(); // Crea una nueva reserva
        nuevaReservaCreada.setDniCliente(persona.getDni()); // Asocia el DNI del cliente seleccionado

        boolean okClicked = main.cargarVentanaCreacionReserva(nuevaReservaCreada);
        if (okClicked) {
            listaReservas.add(nuevaReservaCreada); // Agrega la nueva reserva a la lista observable
            hotelModelo.addReservaVOtoBD(nuevaReservaCreada); // Agrega la nueva reserva a la base de datos
        }
    }

    @FXML
    public void handleDeleteReserva() {
        int indiceEliminar = reservaTable.getSelectionModel().getSelectedIndex();
        if (indiceEliminar >= 0) {
            Reserva reservaEliminar = reservaTable.getItems().get(indiceEliminar);
            hotelModelo.deleteReservaVOtoBD(reservaEliminar);
            reservaTable.getItems().remove(indiceEliminar);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Reserva no seleccionada");
            alert.setContentText("Seleccione una reserva que desea eliminar");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleEditReserva() {
        Reserva reservaEditar = reservaTable.getSelectionModel().getSelectedItem();
        if (reservaEditar != null) {
            boolean okClicked = main.cargarVentanaEdicionReserva(reservaEditar);
            if (okClicked) {
                mostrarDetalles(reservaEditar);
                hotelModelo.editReservaVOtoBD(reservaEditar);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Reserva no seleccionada");
            alert.setContentText("Seleccione una reserva que desea editar");
            alert.showAndWait();
        }
    }
}
