package com.example.primerproy;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Ej extends Application {
    @Override
    public void start(Stage escenarioPrincipal) {
        try {
            BorderPane raiz = new BorderPane();

            Scene escena = new Scene(raiz, 800, 800);
            escenarioPrincipal.setTitle("TEXTO EJEMPLO");
            escenarioPrincipal.setScene(escena);
            escenarioPrincipal.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}