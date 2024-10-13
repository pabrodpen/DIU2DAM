package com.example.agenda.controller;

import javafx.fxml.FXML;

public class RootLayoutController {

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    /**
     * Opens the birthday statistics.
     */
    MainApp mainApp;
    @FXML
    private void handleShowBirthdayStatistics() {
        mainApp.showBirthdayStatistics();
    }
}
