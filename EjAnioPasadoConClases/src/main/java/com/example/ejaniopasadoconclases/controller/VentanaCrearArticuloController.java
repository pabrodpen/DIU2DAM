package com.example.ejaniopasadoconclases.controller;

import com.example.ejaniopasadoconclases.MainApp;
import com.example.ejaniopasadoconclases.model.CatalogoModelo;
import com.example.ejaniopasadoconclases.view.Articulo;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class VentanaCrearArticuloController {
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


        codigoField.setText(String.valueOf(a.getCodigo()));
        nombreField.setText(a.getNombre());
        descripcionField.setText(a.getDescripcion());
        precioField.setText(String.valueOf(a.getPrecio()));
        stockField.setText(String.valueOf(a.getCantidad()));


    }

    //metodo para cuando pulsemos el boton OK
    @FXML
    public void handleOkClicked() {
        if(articulo == null) {
            articulo = new Articulo();
        }
        articulo.setCodigo(Integer.parseInt(codigoField.getText()));
        articulo.setNombre(nombreField.getText());
        articulo.setDescripcion(descripcionField.getText());
        articulo.setPrecio(Double.parseDouble(precioField.getText()));
        articulo.setCantidad(Integer.parseInt(stockField.getText()));

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
