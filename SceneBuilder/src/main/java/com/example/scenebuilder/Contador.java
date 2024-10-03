package com.example.scenebuilder;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Contador {
    // Definimos la propiedad numPulsaciones
    private IntegerProperty numPulsaciones = new SimpleIntegerProperty(0);

    // Método para actualizar el valor de numPulsaciones según el botón pulsado
    public void pulsado(int n) {
        if (n == 0) {
            numPulsaciones.set(0);
        } else {
            numPulsaciones.set(numPulsaciones.get() + n);
        }

    }

    // Método para obtener el número de pulsaciones
    public IntegerProperty getNumPulsaciones() {
        return numPulsaciones;
    }


}
