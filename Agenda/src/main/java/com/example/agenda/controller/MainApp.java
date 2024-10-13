package com.example.agenda.controller;

import java.io.IOException;

import com.example.agenda.model.Contacto;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane; // Cambiado a AnchorPane
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {

    public MainApp() {
        // Datos de ejemplo para la lista de contactos
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

    public Stage primaryStage;
    public AnchorPane rootLayout; // Cambiado a AnchorPane

    public ObservableList<Contacto> listaContactos = FXCollections.observableArrayList();

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
     * Inicializa el layout raíz.
     */
    public void initRootLayout() {
        try {
            // Cargar el layout raíz desde el archivo fxml.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/com/example/agenda/root-layout.fxml"));
            rootLayout = (AnchorPane) loader.load();

            // Obtener el controlador y asignarle la instancia de MainApp
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);

            // Mostrar la escena que contiene el layout raíz.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Muestra la vista de personas dentro del layout raíz.
     */
    public void showPersonOverview() {
        try {
            // Cargar la vista de personas.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/com/example/agenda/hello-view.fxml")); // Asegúrate de que este es el correcto
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Agregar la vista de personas al layout raíz
            rootLayout.getChildren().add(personOverview); // Añadir el AnchorPane al AnchorPane raíz

            // Obtener el controlador y pasar la referencia de MainApp
            PersonOverviewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retorna la escena principal.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public boolean showPersonEditDialog(Contacto c) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/com/example/agenda/person-edit-dialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            PersonEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(c);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void showBirthdayStatistics() {
        try {
            // Load the fxml file and create a new stage for the popup.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/com.example/agenda/controller/birthday-statistics.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Birthday Statistics");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the persons into the controller.
            BirthdayStatisticsController controller = loader.getController();
            controller.setPersonData(listaContactos);

            dialogStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
