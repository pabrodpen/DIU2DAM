package com.example.hotel;

import com.example.hotel.controller.RaizController;
import com.example.hotel.controller.VentanaClientesController;
import com.example.hotel.controller.VentanaCreacionClientesController;
import com.example.hotel.model.HotelModelo;
import com.example.hotel.model.repository.impl.RepositoryImpl;
import com.example.hotel.view.Persona;
import com.example.hotel.view.PersonaUtil;
import com.example.hotel.view.ReservaUtil;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    BorderPane raiz;
    HotelModelo hotelModelo=new HotelModelo();
    public ObservableList<Persona> listaPersonas= FXCollections.observableArrayList();


    public Main() throws IOException {
        RepositoryImpl repository=new RepositoryImpl();
        PersonaUtil personaUtil=new PersonaUtil();
        ReservaUtil reservaUtil=new ReservaUtil();

        hotelModelo.setPersonaUtil(personaUtil);
        hotelModelo.setReservaUtil(reservaUtil);
        hotelModelo.setRepository(repository);

        //el main recoge los datos del modelo
        listaPersonas.addAll(hotelModelo.getListaPersonas());

    }
    @Override
    public void start(Stage stage) throws IOException {
        this.primaryStage = stage;
        primaryStage.setTitle("Hotel");

        cargarRaiz();
        cargarVentanaPrincipal();




    }

    //cogemos la lista observable(lista para mostrar a la interfaz y que se actualiza conntantemente
    //en la interfaz)
    public ObservableList<Persona> getListaPersonas() {
        return listaPersonas;
    }


    public void cargarRaiz(){
        try {
            // Cargar el layout raíz desde el archivo FXML.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/com/example/hotel/raiz.fxml"));
            raiz = (BorderPane) loader.load();

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
            // Cargamos la vista de los clientes
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/hotel/ventana-clientes.fxml"));
            AnchorPane ventana;
            ventana = loader.load();

            //lo centramos en el layout raiz
            raiz.setCenter(ventana);

            // Obtener el controlador del root layout y establecer la referencia de MainApp.
            VentanaClientesController controller = loader.getController();
            controller.setMain(this); // Pasar la referencia de MainApp
            controller.setHotelModelo(hotelModelo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public boolean cargarVentanaCreacionPersona(){
        try {
            // Cargar el archivo FXML y crear un nuevo escenario para el diálogo emergente.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/com/example/hotel/ventana-creacion-persona.fxml")); // Ruta corregida
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