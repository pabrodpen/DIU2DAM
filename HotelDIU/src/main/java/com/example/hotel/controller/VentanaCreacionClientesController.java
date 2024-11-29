package com.example.hotel.controller;

import com.example.hotel.Main;
import com.example.hotel.model.HotelModelo;
import com.example.hotel.view.Persona;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class VentanaCreacionClientesController {
    boolean isOkClicked = false;
    Main main;
    HotelModelo hotelModelo;
    Stage dialogoStage;
    Persona persona;
    @FXML
    TextField dniField, nombreField,direccionField,localidadField,provinciaField;


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

    public void cambiarDatosClienteCrear(Persona persona) {
        if (persona == null) {
            // Initialize a new Persona if null
            this.persona = new Persona();
        } else {
            this.persona = persona;
        }


        dniField.setText(persona.getDni());
        nombreField.setText(persona.getNombre_completo());
        direccionField.setText(persona.getDireccion());
        localidadField.setText(persona.getLocalidad());
        provinciaField.setText(persona.getProvincia());


    }

    public void cambiarDatosClienteEditar(Persona persona) {
        if (persona == null) {
            // Initialize a new Persona if null
            this.persona = new Persona();
        } else {
            this.persona = persona;
        }


        dniField.setText(persona.getDni());
        dniField.setDisable(true);
        nombreField.setText(persona.getNombre_completo());
        direccionField.setText(persona.getDireccion());
        localidadField.setText(persona.getLocalidad());
        provinciaField.setText(persona.getProvincia());


    }
    //metodo para cuando pulsemos el boton OK
    @FXML
    public void handleOkClicked() {

        try{
        if(persona == null) {
            persona = new Persona();
        }
        persona.setDni(dniField.getText());
        persona.setNombre_completo(nombreField.getText());
        persona.setDireccion(direccionField.getText());
        persona.setLocalidad(localidadField.getText());
        persona.setProvincia(provinciaField.getText());

        //ponemos el booleano en true
        isOkClicked = true;
        //cerramos la ventana de dialogo
        dialogoStage.close();
        }catch(Exception e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("DNI ya existente");
            alert.setHeaderText("Error de datos");
            alert.setContentText("Lo siento, el DNI introducido ya existe");
            alert.showAndWait();
        }

    }

    //metodo para cuando pulsemos el boton de Cancelar
    @FXML
    public void handleCancel(){
        dialogoStage.close();
    }


}
