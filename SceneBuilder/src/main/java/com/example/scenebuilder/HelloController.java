package com.example.scenebuilder;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

public class HelloController {



    // El Label que actualizaremos con el valor de numPulsaciones
    @FXML
    private Label lbNumero;
    @FXML
    private TextField textCantidad;
    @FXML
    ProgressBar barraProgreso;

    // Definimos la propiedad numPulsaciones
    private IntegerProperty numPulsaciones = new SimpleIntegerProperty(0);

    public void initialize() {
        // Inicialmente mostrar el valor de numPulsaciones en el Label
        lbNumero.setText(String.valueOf(numPulsaciones.get()));

        //inicializamos la barra de progreso
        barraProgreso.setProgress(numPulsaciones.get()/50.0);

        // Listener para actualizar la etiqueta y la barra progeso cuando cambia
        // el valor del contador
        numPulsaciones.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                numPulsaciones.addListener((observable, oldValue, newValue) -> {
                    lbNumero.setText(String.valueOf(newValue));
                    cambiarProgreso(Integer.parseInt(String.valueOf(newValue)));
                });

            }
        });
    }


    public void incrementar() {
        numPulsaciones.set(numPulsaciones.get()+1);
    }

    public void decrementar() {
        numPulsaciones.set(numPulsaciones.get()-1);
    }

    public void resetear() {
        numPulsaciones.set(0);
    }
    public void cambiar(){
        int cantidad=Integer.parseInt(String.valueOf(textCantidad.getText()));
        numPulsaciones.set(cantidad);
    }

    public void cambiarProgreso(int n){
        double progreso=(double) n/50.0;
        barraProgreso.setProgress(progreso);
    }

    public IntegerProperty getNumPulsaciones(){
        return numPulsaciones;
    }


}
