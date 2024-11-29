package com.example.hotel.controller;

import com.example.hotel.Main;
import com.example.hotel.model.HotelModelo;
import com.example.hotel.view.Persona;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;

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
            if(!(validarDNI(dniField.getText()))) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("DNI no valido");
                alert.setHeaderText("Error de datos");
                alert.setContentText("Lo siento, el DNI no es valido");
                alert.showAndWait();
            }else{
                if(dniExistente(dniField.getText())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("DNI ya existente");
                    alert.setHeaderText("Error de datos");
                    alert.setContentText("Lo siento, el DNI ya esta registrado");
                    alert.showAndWait();
                }else{
                    persona.setDni(dniField.getText());
                    persona.setNombre_completo(nombreField.getText());
                    persona.setDireccion(direccionField.getText());
                    persona.setLocalidad(localidadField.getText());
                    persona.setProvincia(provinciaField.getText());
                    //ponemos el booleano en true
                    isOkClicked = true;
                    //cerramos la ventana de dialogo
                    dialogoStage.close();
                }
            }

        }catch(Exception e){
            e.printStackTrace();

        }

    }

    private static final String LETRAS_DNI = "TRWAGMYFPDXBNJZSQVHLCKE";

    //metodo para cuando pulsemos el boton de Cancelar
    @FXML
    public void handleCancel(){
        dialogoStage.close();
    }

    public static boolean validarDNI(String dni) {
        // Verifica que el DNI tenga 9 caracteres
        if (dni == null || dni.length() != 9) {
            return false;
        }

        // Divide la parte numérica y la letra
        String numeroDNI = dni.substring(0, 8); // Los primeros 8 caracteres
        char letraDNI = Character.toUpperCase(dni.charAt(8)); // El último carácter

        // Verifica que los 8 primeros caracteres sean números
        if (!numeroDNI.matches("\\d+")) {
            return false;
        }

        // Calcula la letra esperada según los números
        int numero = Integer.parseInt(numeroDNI);
        char letraEsperada = LETRAS_DNI.charAt(numero % 23);

        // Compara la letra calculada con la proporcionada
        return letraDNI == letraEsperada;
    }

    public boolean dniExistente(String dni) {
        ArrayList<Persona> listaPersonas=hotelModelo.getListaPersonas();
        boolean existe=false;
        for (Persona persona : listaPersonas) {
            if(persona.getDni().equals(dni)) {
                existe=true;
                break;
            }
        }
        return existe;
    }


}
