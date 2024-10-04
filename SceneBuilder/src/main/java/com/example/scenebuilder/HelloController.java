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

    Contador contador;


    // El Label que actualizaremos con el valor de numPulsaciones
    @FXML
    private Label lbNumero;
    @FXML
    private TextField textCantidad;
    @FXML
    ProgressBar barraProgreso;

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

        //inicializamos la barra de progreso
        barraProgreso.setProgress(contador.getNumPulsaciones().get()/50.0);

        // Listener para actualizar la etiqueta y la barra progeso cuando cambia
        // el valor del contador
        contador.getNumPulsaciones().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                contador.getNumPulsaciones().addListener((observable, oldValue, newValue) -> {
                    lbNumero.setText(String.valueOf(newValue));
                    cambiarProgreso(Integer.parseInt(String.valueOf(newValue)));
                });

            }
        });
    }


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
        int cantidad=Integer.parseInt(textCantidad.getText());
        contador.cantidadIntroducida(cantidad);
    }

    public void cambiarProgreso(int n){
        double progreso=(double) n/50.0;
        barraProgreso.setProgress(progreso);
    }


}
