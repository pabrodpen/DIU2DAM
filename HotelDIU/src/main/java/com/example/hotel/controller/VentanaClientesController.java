package com.example.hotel.controller;

import com.example.hotel.Main;
import com.example.hotel.model.HotelModelo;
import com.example.hotel.view.Persona;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

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

    public void abrirVentanaReservas(Persona p) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/hotel/ventana-reservas.fxml"));
            AnchorPane page = loader.load();

            // Crear el escenario del diálogo.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Reservas del cliente");
            //quitamos modal para que se puedan abrir varias ventanas
            //dialogStage.initModality(Modality.WINDOW_MODAL);
            //dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());
            dialogStage.setScene(scene);

            VentanaReservasController controller = loader.getController();
            controller.setHotelModelo(hotelModelo);
            controller.setMain(main);
            controller.setClienteSeleccionado(p);
            dialogStage.showAndWait();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //los metodos onAction de nuevocliente, editar cliente, eliminar clienye y ver reservas
    //del cliente
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

            //cogemos el metodo de hotelModelo para eliminar de la bd
            Persona personaEliminar=personaTable.getItems().get(indiceEliminar);
            hotelModelo.deletePersonVOtoBD(personaEliminar);

            //lo quitamos de la interfaz
            personaTable.getItems().remove(indiceEliminar);

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
            boolean okClicked = main.cargarVentanaEdicionPersona(personaEditar);
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

    @FXML
    private void handleMostrarReservas() {
        Persona persona = personaTable.getSelectionModel().getSelectedItem();
        if(persona!=null) {
            abrirVentanaReservas(persona);
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Cliente no seleccionado");
            alert.setContentText("Seleccione un persona que desea eliminar");
            alert.showAndWait();
        }
    }







}
