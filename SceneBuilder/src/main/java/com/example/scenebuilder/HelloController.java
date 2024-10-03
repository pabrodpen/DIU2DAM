package com.example.scenebuilder;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {

    Contador contador1 = new Contador();
    Contador contador2 = new Contador();


    // El Label que actualizaremos con el valor de numPulsaciones
    @FXML
    private Label lbNumero;

    public void initialize() {
        //nada mas arrnacar sincronizamos los contadores y lo vinculamos al label
        contador1.getNumPulsaciones().bindBidirectional(contador2.getNumPulsaciones());

        // Inicialmente mostrar el valor de numPulsaciones en el Label
        lbNumero.setText(String.valueOf(contador1));//da igual si es cont2, ya que son iguales

        // Listener para actualizar la etiqueta cuando cambia
        // el valor del contador
        contador1.getNumPulsaciones().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                contador1.getNumPulsaciones().addListener((observable, oldValue, newValue) -> {
                    lbNumero.setText(String.valueOf(newValue));
                });

            }
        });

    }


    // Métodos que serán vinculados en SceneBuilder
    public void incrementar() {
        contador1.pulsado(1);
    }

    public void decrementar() {
        contador1.pulsado(-1);
    }

    public void resetear() {
        contador1.pulsado(0);
    }

    // Método para inicializar el contador desde la aplicación principal
    public void initializeContador(Contador contador) {
        this.contador1 = contador; // Asignar la instancia de contador al controlador

    }
}
