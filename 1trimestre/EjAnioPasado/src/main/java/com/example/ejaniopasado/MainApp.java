package com.example.ejaniopasado;

import com.example.ejaniopasado.controller.VentanaArticulosController;
import com.example.ejaniopasado.controller.VentanaCreacionArticulosController;
import com.example.ejaniopasado.model.CatalogoModelo;
import com.example.ejaniopasado.model.ExcepcionArticulo;
import com.example.ejaniopasado.model.repository.ArticuloRepository;
import com.example.ejaniopasado.model.repository.impl.ArticuloRepositoryImpl;
import com.example.ejaniopasado.util.ArticuloUtil;
import com.example.ejaniopasado.view.Articulo;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    public Stage primaryStage;
    //public BorderPane rootLayout; // Usar BorderPane como layout raíz

    public ObservableList<Articulo> listaArticulos = FXCollections.observableArrayList();
    CatalogoModelo catalogoModelo=new CatalogoModelo();

    //cogemos la lista observable(lista para mostrar a la interfaz y que se actualiza conntantemente
    //en la interfaz)
    public ObservableList<Articulo> getListaArticulos() {
        return listaArticulos;
    }

    public MainApp() {

        ArticuloRepository repository=new ArticuloRepositoryImpl();
        ArticuloUtil util=new Articulo  public Stage primaryStage;
    //public BorderPane rootLayout; // Usar BorderPane como layout raíz

    public ObservableList<Articulo> listaArticulos = FXCollections.observableArrayList();
    CatalogoModelo catalogoModelo=new CatalogoModelo();

    //cogemos la lista observable(lista para mostrar a la interfaz y que se actualiza conntantemente
    //en la interfaz)
    public ObservableList<Articulo> getListaArticulos() {
        return listaArticulos;
    }

    public MainApp() {

        ArticuloRepository repository=new ArticuloRepositoryImpl();
        ArticuloUtil util=new ArticuloUtil();

        catalogoModelo.setArticuloUtil(util);
        catalogoModelo.setArticuloRepository(repository);


        //el main recoge los datos del modelo
        listaArticulos.addAll(catalogoModelo.getArticulos());
    }

    @Override
    public void start(Stage stage) throws IOException {
        this.primaryStage = stage;Util();

        catalogoModelo.setArticuloUtil(util);
        catalogoModelo.setArticuloRepository(repository);


        //el main recoge los datos del modelo
        listaArticulos.addAll(catalogoModelo.getArticulos());
    }

    @Override
    public void start(Stage stage) throws IOException {
        this.primaryStage = stage;
        primaryStage.setTitle("Catalogo");

        cargarVentanaPrincipal();




    }

    public void cargarVentanaPrincipal(){
        try {
            // Cargamos la vista de los clientes
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/ejaniopasado/ventana-articulos.fxml"));
            AnchorPane ventana = null;
            try {
                ventana = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Mostrar la escena que contiene el layout raíz.
            Scene scene = new Scene(ventana);
            primaryStage.setScene(scene);
            primaryStage.show();

            // Obtener el controlador del root layout y establecer la referencia de MainApp.
            VentanaArticulosController controller = loader.getController();
            controller.setMain(this); // Pasar la referencia de MainApp
            controller.setCatalogoModelo(catalogoModelo);
        } catch (ExcepcionArticulo e) {
            throw new RuntimeException(e);
        }


    }

    public boolean mostrarVentanaCreacion(Articulo c) {
        try {

            // Cargar el archivo FXML y crear un nuevo escenario para el diálogo emergente.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/com/example/ejaniopasado/crear-articulo.fxml")); // Ruta corregida
            AnchorPane page = (AnchorPane) loader.load();

            // Crear el escenario del diálogo.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            //quitamos modal para que se puedan abrir varias ventanas
            //dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Establecer la persona en el controlador.
            VentanaCreacionArticulosController controller = loader.getController();
            controller.setDialogoStage(dialogStage);
            controller.cambiarDatosArticulo(c);
            controller.setCatalogoModelo(catalogoModelo);

            // Mostrar el diálogo y esperar hasta que el usuario lo cierre.
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }




    public static void main(String[] args) {
        launch();
    }
}