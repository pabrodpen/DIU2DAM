package com.example.scenebuilder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        // Crear la primera ventana
        FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("contador.fxml"));
        Scene escena1 = new Scene(fxmlLoader1.load(), 350, 200);
        escena1.getStylesheets().add(getClass().getResource("/styles/estilos.css").toExternalForm());


        Stage stage1 = new Stage(); // Crear nuevo Stage para la primera ventana
        stage1.setTitle("Contador 1");
        stage1.setScene(escena1);
        stage1.show();

        // Crear la segunda ventana
        FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("contador.fxml"));
        Scene escena2 = new Scene(fxmlLoader2.load(), 350, 200);
        escena2.getStylesheets().add(getClass().getResource("/styles/estilos.css").toExternalForm());


        Stage stage2 = new Stage(); // Crear nuevo Stage para la segunda ventana
        stage2.setTitle("Contador 2");
        stage2.setScene(escena2);
        stage2.show();

        //cargamos los controladores
        HelloController controlador1=fxmlLoader1.getController();
        HelloController controlador2=fxmlLoader2.getController();


        controlador1.getNumPulsaciones().bindBidirectional(controlador2.getNumPulsaciones());


    }

    public static void main(String[] args) {
        launch();
    }
}