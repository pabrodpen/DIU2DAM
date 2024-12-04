package com.example.hotel;

import com.example.hotel.controller.*;
import com.example.hotel.model.HotelModelo;
import com.example.hotel.model.ReservaVO;
import com.example.hotel.model.repository.impl.RepositoryImpl;
import com.example.hotel.view.Persona;
import com.example.hotel.view.PersonaUtil;
import com.example.hotel.view.Reserva;
import com.example.hotel.view.ReservaUtil;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {

    //es neceaario tener un setMainApp en raizcontroller y ventanacontroller
    //ya que son los 2 fmxl que van a a ocupar la ventana principal
    private Stage primaryStage;
    BorderPane raiz;
    HotelModelo hotelModelo=new HotelModelo();
    public ObservableList<Persona> listaPersonas= FXCollections.observableArrayList();
    RepositoryImpl repository;
    PersonaUtil personaUtil;
    ReservaUtil reservaUtil;
    VentanaClientesController ventanaClientesController=new VentanaClientesController();

    Persona clienteSeleccionado;

    public Main() throws IOException {
        repository=new RepositoryImpl();
        personaUtil=new PersonaUtil();
        reservaUtil=new ReservaUtil();

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



    // Método para seleccionar un cliente
    public void setClienteSeleccionado(Persona cliente) {
        this.clienteSeleccionado = cliente;
    }

    public void cargarRaiz() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/com/example/hotel/raiz.fxml"));
            raiz = loader.load();

            Scene scene = new Scene(raiz);
            primaryStage.setScene(scene);
            primaryStage.show();

            RaizController raizController = loader.getController();
            raizController.setMain(this);
            raizController.setHotelModelo(hotelModelo);

            // Cargar VentanaClientesController y pasarselo a RaizController
            FXMLLoader clienteLoader = new FXMLLoader();
            clienteLoader.setLocation(Main.class.getResource("/com/example/hotel/ventana-clientes.fxml"));
            AnchorPane ventanaClientes = clienteLoader.load();

            VentanaClientesController ventanaClientesController = clienteLoader.getController();
            ventanaClientesController.setMain(this);
            ventanaClientesController.setHotelModelo(hotelModelo);

            // Pasaer el VentanaClientesController a RaizController
            raizController.setVentanaClienteController(ventanaClientesController);

            // Set VentanaClientesController in the root layout
            raiz.setCenter(ventanaClientes);

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

    public boolean cargarVentanaCreacionPersona(Persona persona){
        try {
            // Cargar el archivo FXML y crear un nuevo escenario para el diálogo emergente.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/hotel/ventana-creacion-clientes.fxml")); // Ruta corregida
            AnchorPane page = loader.load();

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
            controller.setDialogoStage(dialogStage);
            //controller.setPerson(c);
            controller.setMain(this);
            controller.setHotelModelo(hotelModelo);
            controller.cambiarDatosClienteCrear(persona);

            // Mostrar el diálogo y esperar hasta que el usuario lo cierre.
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean cargarVentanaEdicionPersona(Persona persona){
        try {
            // Cargar el archivo FXML y crear un nuevo escenario para el diálogo emergente.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/hotel/ventana-creacion-clientes.fxml")); // Ruta corregida
            AnchorPane page = loader.load();

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
            controller.setDialogoStage(dialogStage);
            //controller.setPerson(c);
            controller.setMain(this);
            controller.setHotelModelo(hotelModelo);
            controller.cambiarDatosClienteEditar(persona);

            // Mostrar el diálogo y esperar hasta que el usuario lo cierre.
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public ArrayList<Reserva> getListaReservas(String dni){
        ArrayList<ReservaVO> reservasVO=repository.ObtenerListaReservas(dni);
        ArrayList<Reserva> reservas=new ArrayList<>();
        for(ReservaVO reservaVO:reservasVO){
            String dniCliente=reservaVO.getDniCliente();
            if(dniCliente.equals(dni)){
                Reserva reserva=reservaUtil.reservaVOtoReserva(reservaVO);
                reservas.add(reserva);
            }

        }
        return reservas;
    }

    public boolean cargarVentanaCreacionReserva(Reserva reserva){
        try {
            // Cargar el archivo FXML y crear un nuevo escenario para el diálogo emergente.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/hotel/ventana-creacion-reservas.fxml")); // Ruta corregida
            AnchorPane page = loader.load();

            // Crear el escenario del diálogo.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Editar Reserva");
            //quitamos modal para que se puedan abrir varias ventanas
            //dialogStage.initModality(Modality.WINDOW_MODAL);
            //dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Establecer la persona en el controlador.
            VentanaCreacionReservasController controller = loader.getController();
            controller.setDialogoStage(dialogStage);
            //controller.setPerson(c);
            controller.setMain(this);
            controller.setHotelModelo(hotelModelo);
            controller.cambiarDatosReservaCreacion(reserva);

            // Mostrar el diálogo y esperar hasta que el usuario lo cierre.
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean cargarVentanaEdicionReserva(Reserva reserva){
        try {
            // Cargar el archivo FXML y crear un nuevo escenario para el diálogo emergente.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/hotel/ventana-creacion-reservas.fxml")); // Ruta corregida
            AnchorPane page = loader.load();

            // Crear el escenario del diálogo.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Editar Reserva");
            //quitamos modal para que se puedan abrir varias ventanas
            //dialogStage.initModality(Modality.WINDOW_MODAL);
            //dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Establecer la persona en el controlador.
            VentanaCreacionReservasController controller = loader.getController();
            controller.setDialogoStage(dialogStage);
            //controller.setPerson(c);
            controller.setMain(this);
            controller.setHotelModelo(hotelModelo);
            controller.cambiarDatosReservaEdicion(reserva);

            // Mostrar el diálogo y esperar hasta que el usuario lo cierre.
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void mostrarEstadisticas() {
        try {
            // Load the FXML file for the statistics view
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/hotel/ventana-reservas-por-mes.fxml"));
            AnchorPane page = loader.load();

            // Create a new stage for the statistics popup
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Estadísticas de Reservas");
            dialogStage.initOwner(primaryStage); // Link the dialog to the main stage
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the controller for the statistics view
            VentanaEstadisticasReservasController controller = loader.getController();
            controller.setPersonData(hotelModelo.getListaTodasReservas()); // Pass the reservation data

            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public static void main(String[] args) {
        launch();
    }
}