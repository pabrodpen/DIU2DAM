package com.example.hotel.controller;

import com.example.hotel.Main;
import com.example.hotel.model.HotelModelo;
import com.example.hotel.view.Persona;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class VentanaClientesController {



    public TableView <Persona> personaTable;
    public TableColumn <Persona, String> nombrePersona;
    public ObservableList<Persona> listaPersonas= FXCollections.observableArrayList();

    HotelModelo hotelModelo;

    public void setHotelModelo(HotelModelo hotelModelo) {
        this.hotelModelo = hotelModelo;
    }

    Main main;
    public void setMain(Main main) {
        this.main = main;
    }
    private Label dniLabel,direccionLabel,localidadLabel,provinciaLabel;

    public void initialize(){
        // Initialize the person table with the two columns.
        nombrePersona.setCellValueFactory(new PropertyValueFactory<Persona, String>("nombrePersona"));

        //cuando se seleccione una persona del table, se muestran los detalles mediante
        //un listener

        personaTable.getSelectionModel().selectedItemProperty().addListener
                ((observable,oldValue,newValue) ->mostrarDetalles(newValue));

    }

    private void mostrarDetalles(Persona p) {
        if (p != null) {
            // Fill the labels with info from the person object.
            dniLabel.setText(p.getDni());
            direccionLabel.setText(p.getDireccion());
            localidadLabel.setText(p.getLocalidad());
            provinciaLabel.setText(p.getProvincia());
        } else {
            // Person is null, remove all the text.
            dniLabel.setText("");
            direccionLabel.setText("");
            localidadLabel.setText("");
            provinciaLabel.setText("");
        }

    }

    @FXML
    private void handleNewPerson() {
        Persona tempPerson = new Persona(); // Crea un nuevo Contacto
        boolean okClicked = mainApp.showPersonEditDialog(tempPerson); // Muestra el diálogo
        if (okClicked) {
            listaPersonas.add(tempPerson); // Agrega a la lista de  si se hizo clic en OK
            hotelModelo.addPersonVOtoBD(tempPerson);//agrega a la bd
        }


    }


    @FXML
    private void handleDeletePerson() {
        int selectedIndex = personaTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            //cogemos el Contacto con el indice que seleccionam os de
            // la interfaz del table view y le restamos uno, ya que la bd sigue unn indice mas
            //que la inmterfaz
            //cogemos el metodo de agnedaModelo para eliminar de la bd
            Persona personaEliminar=personaTable.getItems().get(selectedIndex);
            //lo quitamos de la interfaz
            personaTable.getItems().remove(selectedIndex);

            hotelModelo.deletePersonVOtoBD(personaEliminar);
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");
            alert.showAndWait();

        }
    }


    @FXML
    private void handleEditPerson() {
        Persona selectedPerson = personaTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
            if (okClicked) {
                mostrarDetalles(selectedPerson);
                hotelModelo.editPersonVOtoBD(selectedPerson);
            }

        } else {
            // Nothing selected.
            // Nada seleccionado.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");
            alert.showAndWait();

        }
    }



}
