package com.example.agenda.controller;
import com.example.agenda.MainApp;
import com.example.agenda.model.AgendaModelo;
import com.example.agenda.view.Contacto;
import com.example.agenda.util.DateUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class PersonOverviewController {
    @FXML
    public Button buttonAdd;
    @FXML
    public Button buttonEditar;
    @FXML
    public Button buttonEliminar;
    @FXML
    private TableView<Contacto> personTable;
    @FXML
    private TableColumn<Contacto, String> firstNameColumn;
    @FXML
    private TableColumn<Contacto, String> lastNameColumn;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;

    // Reference to the main application.
    private MainApp mainApp;
    private AgendaModelo agendaModelo;



    public PersonOverviewController() {
    }



    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().apellidoProperty());

        // Clear person details.
        showPersonDetails(null);

        //cuando se seleccione una persona del table, se muestran los detalles mediante
        //un listener

        personTable.getSelectionModel().selectedItemProperty().addListener
                ((observable,oldValue,newValue) ->showPersonDetails(newValue));
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        personTable.setItems(mainApp.getPersonData());
    }

    public void setAgendaModelo(AgendaModelo m){
        this.agendaModelo=m;
    }


    private void showPersonDetails(Contacto c) {
        if (c != null) {
            // Fill the labels with info from the person object.
            firstNameLabel.setText(c.getNombre());
            lastNameLabel.setText(c.getApellido());
            streetLabel.setText(c.getDireccion());
            postalCodeLabel.setText(Integer.toString(c.getCodPostal()));
            cityLabel.setText(c.getLocalidad());
            birthdayLabel.setText(DateUtil.format(c.getFechaNac()));

        } else {
            // Person is null, remove all the text.
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            streetLabel.setText("");
            postalCodeLabel.setText("");
            cityLabel.setText("");
            birthdayLabel.setText("");
        }

    }

 @FXML
    private void handleDeletePerson() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            personTable.getItems().remove(selectedIndex);
            //cogemos el Contacto con el indice que seleccionam os de
            // la interfz del table view

            Contacto contactoEliminar=personTable.getItems().get(selectedIndex);
            //cogemos el metodo de agnedaModelo para eliminar d ela bd
            agendaModelo.deletePersonVOtoBD(contactoEliminar);
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");
            alert.showAndWait();

        }
    }

    @FXML
    private void handleNewPerson() {
        Contacto tempPerson = new Contacto(); // Crea un nuevo Contacto
        boolean okClicked = mainApp.showPersonEditDialog(tempPerson); // Muestra el diálogo
        if (okClicked) {
            mainApp.getPersonData().add(tempPerson); // Agrega a la lista si se hizo clic en OK
            agendaModelo.addPersonVOtoBD(tempPerson);//agrega a la bd
        }


    }



    @FXML
    private void handleEditPerson() {
        Contacto selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
            if (okClicked) {
                showPersonDetails(selectedPerson);
                agendaModelo.editPersonVOtoBD(selectedPerson);
            }

        } else {
            // Nothing selected.
            // Nada seleccionado.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");
            alert.showAndWait();

        }
    }





        public boolean showPersonEditDialog(Contacto contacto) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainApp.class.getResource("/com/example/agenda/person-edit-dialog.fxml"));
                AnchorPane page = (AnchorPane) loader.load();

                Stage dialogStage = new Stage();
                dialogStage.setTitle("Edit Person");
                Scene scene = new Scene(page);
                dialogStage.setScene(scene);

                PersonEditDialogController controller = loader.getController();
                controller.setDialogStage(dialogStage);
                controller.setPerson(contacto);

                dialogStage.showAndWait();

                return controller.isOkClicked();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }


    }


