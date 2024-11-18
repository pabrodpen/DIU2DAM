package com.example.hotel.controller;

import com.example.hotel.Main;
import com.example.hotel.model.HotelModelo;
import com.example.hotel.view.Persona;
import com.example.hotel.view.Reserva;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class VentanaReservasController {


    @FXML
    public TableView<Reserva> reservaTable;
    @FXML
    public TableColumn<Reserva, String> codigoReserva;
    @FXML
    Label codigoReservaLabel;
    @FXML
    Label fechaLLegadaLabel;
    @FXML
    Label fechaSalidaLabel;
    @FXML
    Label numHabitacionesLabel;
    @FXML
    Label tipoHabitacionLabel;
    @FXML
    Label regimenHabitacionLabel;
    //@FXML
    //Label fumadorLabel;
    HotelModelo hotelModelo;
    Persona persona;

    //pasamos el cliente seleccionado para mostrar las reservas
    public void setClienteSeleccionado(Persona persona) {
        this.persona = persona;
    }

    public void setHotelModelo(HotelModelo hotelModelo) {
        this.hotelModelo = hotelModelo;
    }

    Main main;
    public void setMain(Main main) {
        this.main = main;

        //como el metodo get de la lista observable lo tenemos en el main,
        //lo ponemos en el setMain para que se actulice al pasarle la referencia del main
        reservaTable.setItems(main.getListaReservas());
    }

    @FXML
    public void initialize(){

        // Se inciializa la lista de Reservas mostrando solo el codigo
        //        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        codigoReserva.setCellValueFactory(cellData -> cellData.getValue().codigoProperty());

        //cuando se seleccione una persona del table, se muestran los detalles mediante
        //un listener

        reservaTable.getSelectionModel().selectedItemProperty().addListener
                ((observable,oldValue,newValue) ->mostrarDetalles(newValue));

    }


    private void mostrarDetalles(Reserva r) {
        if (r != null) {
            // Fill the labels with info from the person object.
            codigoReservaLabel.setText(r.getCodigo());
            numHabitacionesLabel.setText(String.valueOf(r.getNumHabitaciones()));
            tipoHabitacionLabel.setText(r.getTipoHabitacion());
            regimenHabitacionLabel.setText(r.getRegimenHabitacion());
            fechaLLegadaLabel.setText(String.valueOf(r.getHoraLlegada()));
            fechaSalidaLabel.setText(String.valueOf(r.getHoraSalida()));
        } else {
            // Reserva is null, remove all the text.
            codigoReservaLabel.setText("");
            numHabitacionesLabel.setText("");
            tipoHabitacionLabel.setText("");
            regimenHabitacionLabel.setText("");
            fechaLLegadaLabel.setText("");
            fechaSalidaLabel.setText("");
        }

    }

    @FXML
    private void handleNewReserva() {
        Reserva nuevaReservaCreada = new Reserva(); // Crea un nuevo Cliente
        //asociar el dni del cliente seleccionado
        nuevaReservaCreada.setDniCliente(persona.getDni());
        boolean okClicked = main.cargarVentanaCreacionReserva(nuevaReservaCreada); // Muestra el diálogo
        if (okClicked) {//si se pulsa el boton de OK
            main.listaReservas.add(nuevaReservaCreada); // Agrega a la lista observable
            //  si se hizo clic en OK
            hotelModelo.addReservaVOtoBD(nuevaReservaCreada);//agrega a la bd
        }

    }


    @FXML
    public void handleDeleteReserva() {
        int indiceEliminar = reservaTable.getSelectionModel().getSelectedIndex();
        if (indiceEliminar >= 0) {
            //cogemos el Contacto con el indice que seleccionam os de
            // la interfaz del table view y le restamos uno, ya que la bd sigue unn indice mas
            //que la inmterfaz

            //cogemos el metodo de hotelModelo para eliminar de la bd
            Reserva reservaEliminar=reservaTable.getItems().get(indiceEliminar);
            hotelModelo.deleteReservaVOtoBD(reservaEliminar);

            //lo quitamos de la interfaz
            reservaTable.getItems().remove(indiceEliminar);

        } else {
            // Nothing selected.
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
            boolean okClicked = main.cargarVentanaCreacionReserva(reservaEditar);
            if (okClicked) {

                mostrarDetalles(reservaEditar);
                hotelModelo.editReservaVOtoBD(reservaEditar);
            }

        } else {
            // Nada seleccionado.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Reserva no seleccionada");
            alert.setContentText("Seleccione una reserva que desea editar");
            alert.showAndWait();

        }
    }



}
