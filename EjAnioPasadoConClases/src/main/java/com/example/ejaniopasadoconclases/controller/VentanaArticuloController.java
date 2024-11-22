package com.example.ejaniopasadoconclases.controller;

import com.example.ejaniopasadoconclases.MainApp;
import com.example.ejaniopasadoconclases.model.CatalogoModelo;
import com.example.ejaniopasadoconclases.view.Articulo;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class VentanaArticuloController {
    @FXML
    public TableView<Articulo> articuloTable;
    @FXML
    public TableColumn<Articulo, String> codigoArticulo;
    @FXML
    public TableColumn<Articulo, Double> precioArticulo;

    @FXML
    Label nombreLabel;
    @FXML
    Label descripcionLabel;
    @FXML
    Label precioLabel;
    @FXML
    Label stockLabel;
    @FXML
    TextField unidadesField;
    @FXML
    TextField totalField;

    CatalogoModelo catalogoModelo;


    public void setCatalogoModelo(CatalogoModelo catalogoModelo) {
        this.catalogoModelo = catalogoModelo;
    }

    MainApp main;
    public void setMain(MainApp main) {
        this.main = main;

        //como el metodo get de la lista observable lo tenemos en el main,
        //lo ponemos en el setMain para que se actulice al pasarle la referencia del main
        articuloTable.setItems(main.getListaArticulos());
    }


    @FXML
    public void initialize(){

        // Se inciializa la lista de Personas mostrando solo el nombre(atributo nombre_completo)
        //        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        codigoArticulo.setCellValueFactory(cellData -> cellData.getValue().codigoProperty().asString());
        precioArticulo.setCellValueFactory(cellData -> cellData.getValue().precioProperty().asObject());



        //cuando se seleccione una persona del table, se muestran los detalles mediante
        //un listener

        articuloTable.getSelectionModel().selectedItemProperty().addListener
                ((observable,oldValue,newValue) ->mostrarDetalles(newValue));

    }

    //los metodos onAction de nuevocliente, editar cliente, eliminar clienye y ver reservas
    //del cliente
    @FXML
    private void handleNewArticulo() {
        Articulo nuevoArticuloCreado = new Articulo();
        boolean okClicked = main.mostrarVentanaCreacion(nuevoArticuloCreado); // Muestra el diálogo
        if (okClicked) {//si se pulsa el boton de OK
            main.listaArticulos.add(nuevoArticuloCreado); // Agrega a la lista observable
            //  si se hizo clic en OK
            catalogoModelo.addArticuloVOtoBD(nuevoArticuloCreado);//agrega a la bd
            catalogoModelo.setNumContactosProperty(articuloTable.getItems().size());
        }

    }

    @FXML
    private void handleTotal(){

        int unidades = Integer.parseInt(unidadesField.getText());
        float precio = Float.parseFloat(precioLabel.getText());
        System.out.println("Unidades: " + unidades);
        System.out.println("Precio: " + precio);

        float total =catalogoModelo.getTotal(unidades,precio);
        System.out.println("Total: " + total);
        totalField.setText(String.valueOf(total));
        System.out.println(total);
    }

    private void mostrarDetalles(Articulo p) {
        if (p != null) {
            // Fill the labels with info from the person object.
            nombreLabel.setText(p.getNombre());
            descripcionLabel.setText(p.getDescripcion());
            precioLabel.setText(String.valueOf(p.getPrecio()));
            stockLabel.setText(String.valueOf(p.getCantidad()));
        } else {
            // Person is null, remove all the text.
            nombreLabel.setText("");
            descripcionLabel.setText("");
            precioLabel.setText("");
            stockLabel.setText("");
        }

    }
}