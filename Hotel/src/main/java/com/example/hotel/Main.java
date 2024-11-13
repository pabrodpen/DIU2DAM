package com.example.hotel;

import com.example.hotel.controller.RaizController;
import com.example.hotel.controller.VentanaCreacionClientesController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    //es neceaario tener un setMainApp en raizcontroller y ventanacontroller
    //ya que son los 2 fmxl que van a a ocupar la ventana principal
    private Stage primaryStage;
    @Override
    public void start(Stage stage) throws IOException {
        this.primaryStage = stage;
        primaryStage.setTitle("Hotel");


    }


    public void cargarRaiz(){
        try {
            // Cargar el layout raíz desde el archivo FXML.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.getClass().getResource("/com/example/agenda/raiz.fxml"));
            BorderPane raiz = (BorderPane) loader.load();

            // Mostrar la escena que contiene el layout raíz.
            Scene scene = new Scene(raiz);
            primaryStage.setScene(scene);
            primaryStage.show();

            // Obtener el controlador del root layout y establecer la referencia de MainApp.
            RaizController controller = loader.getController();
            controller.setMain(this); // Pasar la referencia de MainApp
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cargarVentanaPrincipal(){
        try {
            // Cargar el layout raíz desde el archivo FXML.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.getClass().getResource("/com/example/agenda/ventana-clientes.fxml"));
            AnchorPane ventana = null;
            ventana = (AnchorPane) loader.load();

            // Mostrar la escena que contiene el layout raíz.
            Scene scene = new Scene(ventana);
            primaryStage.setScene(scene);
            primaryStage.show();

            // Obtener el controlador del root layout y establecer la referencia de MainApp.
            RaizController controller = loader.getController();
            controller.setMain(this); // Pasar la referencia de MainApp
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public void cargarVentanaCreacionPersona(){
        try {
            // Cargar el archivo FXML y crear un nuevo escenario para el diálogo emergente.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/com/example/agenda/ventana-creacion-persona.fxml")); // Ruta corregida
            AnchorPane page = (AnchorPane) loader.load();

            // Crear el escenario del diálogo.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Editar Persona");
            //quitamos modal para que se puedan abrir varias ventanas
            //dialogStage.initModality(Modality.WINDOW_MODAL);
            //dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Establecer la persona en el controlador.
            VentanaCreacionClientesController controller = loader.getController();
            /*controller.setDialogStage(dialogStage);
            controller.setPerson(c);
            controller.setAgendaModelo(agendaModelo);*/

            // Mostrar el diálogo y esperar hasta que el usuario lo cierre.
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void cargarVentanaCreacionReserva(){

    }

    public static void main(String[] args) {
        launch();
    }
}