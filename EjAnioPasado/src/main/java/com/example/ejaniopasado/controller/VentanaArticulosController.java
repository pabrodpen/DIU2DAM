package com.example.ejaniopasado.controller;

import com.example.ejaniopasado.MainApp;
import com.example.ejaniopasado.model.CatalogoModelo;
import com.example.ejaniopasado.view.Articulo;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class VentanaArticulosController {
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
        codigoArticulo.setCellValueFactory(cellData -> cellData.getValue().codigoProperty());
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
        }

    }

    @FXML
    private void handleTotal(){

        int unidades = Integer.parseInt(unidadesField.getText());
        float precio = Float.parseFloat(totalField.getText());

        float total = precio * unidades;
        totalField.setText(String.valueOf(total));
    }


    @FXML
    public void handleDeletePerson() {
        int indiceEliminar = articuloTable.getSelectionModel().getSelectedIndex();
        if (indiceEliminar >= 0) {
            //cogemos el Contacto con el indice que seleccionam os de
            // la interfaz del table view y le restamos uno, ya que la bd sigue unn indice mas
            //que la inmterfaz

            //cogemos el metodo de hotelModelo para eliminar de la bd
            Articulo articuloEliminar=articuloTable.getItems().get(indiceEliminar);
            catalogoModelo.deleteArticuloVOtoBD(articuloEliminar);

            //lo quitamos de la interfaz
            articuloTable.getItems().remove(indiceEliminar);

        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Cliente no seleccionado");
            alert.setContentText("Seleccione un persona que desea eliminar");
            alert.showAndWait();

        }
    }

    private void mostrarDetalles(Articulo p) {
        if (p != null) {
            // Fill the labels with info from the person object.
            nombreLabel.setText(p.getNombre());
            descripcionLabel.setText(p.getDescripcion());
            precioLabel.setText(String.valueOf(p.getPrecio()));
            stockLabel.setText(String.valueOf(p.getStock()));
        } else {
            // Person is null, remove all the text.
            nombreLabel.setText("");
            descripcionLabel.setText("");
            precioLabel.setText("");
            stockLabel.setText("");
        }

    }



}
