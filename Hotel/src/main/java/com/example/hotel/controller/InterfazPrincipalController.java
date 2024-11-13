package com.example.hotel.controller;

import com.example.hotel.view.Persona;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class InterfazPrincipalController {

    public TableView <Persona> personaTable;
    public TableColumn <Persona, String> nombrePersona;

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
            mainApp.getPersonData().add(tempPerson); // Agrega a la lista si se hizo clic en OK
            agendaModelo.addPersonVOtoBD(tempPerson);//agrega a la bd
        }


    }


    @FXML
    private void handleDeletePerson() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            //cogemos el Contacto con el indice que seleccionam os de
            // la interfz del table view y le restamos uno, ya que la bd sigue unn indice mas
            //que la inmterfaz
            //cogemos el metodo de agnedaModelo para eliminar de la bd
            Persona personaEliminar=personTable.getItems().get(selectedIndex);
            //lo quitamos de la interfaz
            personTable.getItems().remove(selectedIndex);

            agendaModelo.deletePersonVOtoBD(personaEliminar);
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
        Persona selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
            if (okClicked) {
                showPersonDetails(selectedPerson);
                agendaModelo.editPersonVOtoBD(selectedPerson);
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
