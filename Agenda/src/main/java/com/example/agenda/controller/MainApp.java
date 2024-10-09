package com.example.agenda.controller;

import java.io.IOException;

import com.example.agenda.model.Contacto;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    public MainApp(){
        listaContactos.add(new Contacto("Hans", "Muster"));
        listaContactos.add(new Contacto("Ruth", "Mueller"));
        listaContactos.add(new Contacto("Heinz", "Kurz"));
        listaContactos.add(new Contacto("Cornelia", "Meier"));
        listaContactos.add(new Contacto("Werner", "Meyer"));
        listaContactos.add(new Contacto("Lydia", "Kunz"));
        listaContactos.add(new Contacto("Anna", "Best"));
        listaContactos.add(new Contacto("Stefan", "Meier"));
        listaContactos.add(new Contacto("Martin", "Mueller"));
    }


    private Stage primaryStage;
    private BorderPane rootLayout;

    private ObservableList<Contacto> listaContactos= FXCollections.observableArrayList();

    public ObservableList<Contacto> getPersonData() {
        return listaContactos;
    }



    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");

        initRootLayout();

        showPersonOverview();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/com/example/agenda/root-layout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showPersonOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/com/example/agenda/hello-view.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}