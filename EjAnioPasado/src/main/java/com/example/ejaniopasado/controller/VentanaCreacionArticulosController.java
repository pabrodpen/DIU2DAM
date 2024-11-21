package com.example.ejaniopasado.controller;

import com.example.ejaniopasado.MainApp;
import com.example.ejaniopasado.model.CatalogoModelo;
import com.example.ejaniopasado.view.Articulo;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class VentanaCreacionArticulosController {
    boolean isOkClicked = false;
    MainApp main;
    CatalogoModelo catalogoModelo;
    Stage dialogoStage;
    Articulo articulo;
    @FXML
    TextField codigoField,nombreField,descripcionField,precioField,stockField;

    @FXML
    Label numArticulosLabel;

    @FXML
    public void initialize() {

    }

    public void setCatalogoModelo(CatalogoModelo c) {
        this.catalogoModelo = c;
        numArticulosLabel.textProperty().bind(catalogoModelo.getNumContactosProperty().asString());

    }



    public boolean isOkClicked() {
        return isOkClicked;
    }

    public void setOkClicked(boolean isOkClicked) {
        this.isOkClicked = isOkClicked;
    }


    public void setMain(MainApp main) {
        this.main = main;
    }
    public void setDialogoStage(Stage dialogoStage) {
        this.dialogoStage = dialogoStage;
    }

    public void cambiarDatosArticulo(Articulo a) {
        if (a == null) {
            // Initialize a new Persona if null
            this.articulo = new Articulo();
        } else {
            this.articulo = a;
        }


        codigoField.setText(a.getCodigo());
        nombreField.setText(a.getNombre());
        descripcionField.setText(a.getDescripcion());
        precioField.setText(String.valueOf(a.getPrecio()));
        stockField.setText(String.valueOf(a.getStock()));


    }

    //metodo para cuando pulsemos el boton OK
    @FXML
    public void handleOkClicked() {
        if(articulo == null) {
            articulo = new Articulo();
        }
        articulo.setCodigo(codigoField.getText());
        articulo.setNombre(nombreField.getText());
        articulo.setDescripcion(descripcionField.getText());
        articulo.setPrecio(Double.parseDouble(precioField.getText()));
        articulo.setStock(Integer.parseInt(stockField.getText()));

        //ponemos el booleano en true
        isOkClicked = true;
        //cerramos la ventana de dialogo
        dialogoStage.close();
    }

    //metodo para cuando pulsemos el boton de Cancelar
    @FXML
    public void handleCancel(){
        dialogoStage.close();
    }

}
