package com.example.agenda;

import java.io.IOException;

import com.example.agenda.controller.BirthdayStatisticsController;
import com.example.agenda.controller.PersonEditDialogController;
import com.example.agenda.controller.PersonOverviewController;
import com.example.agenda.controller.RootLayoutController;
import com.example.agenda.model.AgendaModelo;
import com.example.agenda.model.repository.impl.PersonaRepositoryImpl;
import com.example.agenda.view.Contacto;
import com.example.agenda.view.PersonUtil;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {

    public Stage primaryStage;
    public BorderPane rootLayout; // Usar BorderPane como layout raíz

    public ObservableList<Contacto> listaContactos = FXCollections.observableArrayList();
    AgendaModelo agendaModelo=new AgendaModelo();

    public MainApp() {
        PersonaRepositoryImpl personaRepository=new PersonaRepositoryImpl();

        PersonUtil personUtil=new PersonUtil();
        agendaModelo.setPersonaRepository(personaRepository);
        agendaModelo.setPersonUtil(personUtil);

        listaContactos.addAll(agendaModelo.getPersonas());

        // Llamar a getPersonas() solo después de la configuración
        agendaModelo.setNumContactos(agendaModelo.getPersonas().size());


    }



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
            // Cargar el layout raíz desde el archivo FXML.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/com/example/agenda/root-layout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Mostrar la escena que contiene el layout raíz.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

            // Obtener el controlador del root layout y establecer la referencia de MainApp.
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this); // Pasar la referencia de MainApp
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
            loader.setLocation(MainApp.class.getResource("/com/example/agenda/person-overview.fxml")); // Ruta corregida
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Establecer el centro del layout raíz.
            rootLayout.setCenter(personOverview);

            // Obtener el controlador y pasar la referencia de MainApp.
            PersonOverviewController controller = loader.getController();
            //INYECTAMOS EL MODELO EN EL CONTROLLER
            controller.setAgendaModelo(agendaModelo);
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

            // Cargar el archivo FXML y crear un nuevo escenario para el diálogo emergente.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/com/example/agenda/person-edit-dialog.fxml")); // Ruta corregida
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
            PersonEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(c);
            controller.setAgendaModelo(agendaModelo);

            // Mostrar el diálogo y esperar hasta que el usuario lo cierre.
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void showBirthdayStatistics() {
        try {
            // Cargar el archivo FXML y crear un nuevo escenario para el diálogo emergente.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/com/example/agenda/birthday-statistics.fxml")); // Ruta corregida
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Birthday Statistics");
            //dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Establecer los contactos en el controlador.
            BirthdayStatisticsController controller = loader.getController();
            controller.setPersonData(listaContactos);

            dialogStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
