package com.example.scenebuilder;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {

    Contador contador;


    // El Label que actualizaremos con el valor de numPulsaciones
    @FXML
    private Label lbNumero;
    private TextField txt;

    public void initialize() {
        /*
        * initialize() es un método que JavaFX llama automáticamente cuando
        *se carga el archivo FXML. Sin embargo, en este caso no puede ser
        * usado directamente para inicializar el contador porque necesitamos
        * pasar una instancia compartida del contador desde la clase principal
        * a ambos controladores.
        * */
    }

    public void iniciarContador(Contador contador){
        this.contador = contador; // Asignar el contador recibido a la variable del controlador

        // Inicialmente mostrar el valor de numPulsaciones en el Label
        lbNumero.setText(String.valueOf(contador.getNumPulsaciones().get()));

        // Listener para actualizar la etiqueta cuando cambia
        // el valor del contador
        contador.getNumPulsaciones().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                contador.getNumPulsaciones().addListener((observable, oldValue, newValue) -> {
                    lbNumero.setText(String.valueOf(newValue));
                });

            }
        });
    }


    // Métodos que serán vinculados en SceneBuilder
    public void incrementar() {
        contador.pulsado(1);
    }

    public void decrementar() {
        contador.pulsado(-1);
    }

    public void resetear() {
        contador.pulsado(0);
    }
    public void cambiar(){
        int cantidad=Integer.parseInt(txt.getText());
        contador.cantidadIntroducida(cantidad);
    }

}
