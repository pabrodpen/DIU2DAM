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



    @FXML
    public TableView <Persona> personaTable;
    @FXML
    public TableColumn <Persona, String> nombrePersona;

    @FXML
    Label dniLabel;
    @FXML
    Label direccionLabel;
    @FXML
    Label localidadLabel;
    @FXML
    Label provinciaLabel;
    HotelModelo hotelModelo;


    public void setHotelModelo(HotelModelo hotelModelo) {
        this.hotelModelo = hotelModelo;
    }

    Main main;
    public void setMain(Main main) {
        this.main = main;

        //como el metodo get de la lista observable lo tenemos en el main,
        //lo ponemos en el setMain para que se actulice al pasarle la referencia del main
        personaTable.setItems(main.getListaPersonas());
    }

    @FXML
    public void initialize(){

        // Se inciializa la lista de Personas mostrando solo el nombre(atributo nombre_completo)
        //        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        nombrePersona.setCellValueFactory(cellData -> cellData.getValue().nombre_completoProperty());

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
        Persona nuevaPersonaCreada = new Persona(); // Crea un nuevo Cliente
        boolean okClicked = main.cargarVentanaCreacionPersona(nuevaPersonaCreada); // Muestra el diálogo
        if (okClicked) {//si se pulsa el boton de OK
            main.listaPersonas.add(nuevaPersonaCreada); // Agrega a la lista observable
            //  si se hizo clic en OK
            hotelModelo.addPersonVOtoBD(nuevaPersonaCreada);//agrega a la bd
        }

    }


    @FXML
    public void handleDeletePerson() {
        int indiceEliminar = personaTable.getSelectionModel().getSelectedIndex();
        if (indiceEliminar >= 0) {
            //cogemos el Contacto con el indice que seleccionam os de
            // la interfaz del table view y le restamos uno, ya que la bd sigue unn indice mas
            //que la inmterfaz

            //lo quitamos de la interfaz
            personaTable.getItems().remove(indiceEliminar);

            //cogemos el metodo de hotelModelo para eliminar de la bd
            Persona personaEliminar=personaTable.getItems().get(indiceEliminar);
            hotelModelo.deletePersonVOtoBD(personaEliminar);
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Cliente no seleccionado");
            alert.setContentText("Seleccione un persona que desea eliminar");
            alert.showAndWait();

        }
    }


    @FXML
    private void handleEditPerson() {
        Persona personaEditar = personaTable.getSelectionModel().getSelectedItem();
        if (personaEditar != null) {
            boolean okClicked = main.cargarVentanaCreacionPersona(personaEditar);
            if (okClicked) {
                mostrarDetalles(personaEditar);
                hotelModelo.editPersonVOtoBD(personaEditar);
            }

        } else {
            // Nada seleccionado.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Cliente no seleccionado");
            alert.setContentText("Seleccione un persona que desea editar");
            alert.showAndWait();

        }
    }





}
