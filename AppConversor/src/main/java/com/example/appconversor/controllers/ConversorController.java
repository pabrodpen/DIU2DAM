package com.example.appconversor.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import com.example.appconversor.models.*;

public class ConversorController {

    @FXML
    TextField textEuros,textDolares;
    ConversorModelo conversorModelo;

    public void setConversorModelo(ConversorModelo c){
        this.conversorModelo=c;
    }

    @FXML
    public void initialize(){

        textEuros.setText("0");

    }

    public void mostrarConversion(){
        double cantidadEuros=Double.parseDouble(textEuros.getText());
        double conv=conversorModelo.getConversion();
        double res=cantidadEuros*conv;
        textDolares.setText(String.valueOf(res));
    }




}
