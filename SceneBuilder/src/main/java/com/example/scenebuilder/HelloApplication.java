package com.example.scenebuilder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Contador contadorSincronizado=new Contador();

        // Crear la primera ventana
        FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("contador.fxml"));
        Scene scene1 = new Scene(fxmlLoader1.load(), 350, 200);

        Stage stage1 = new Stage(); // Crear nuevo Stage para la primera ventana
        stage1.setTitle("Contador 1");
        stage1.setScene(scene1);
        stage1.show();

        // Crear la segunda ventana
        FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("contador.fxml"));
        Scene scene2 = new Scene(fxmlLoader2.load(), 350, 200);

        Stage stage2 = new Stage(); // Crear nuevo Stage para la segunda ventana
        stage2.setTitle("Contador 2");
        stage2.setScene(scene2);
        stage2.show();

        //cargamos los controladores
        HelloController controlador1=fxmlLoader1.getController();
        HelloController controlador2=fxmlLoader2.getController();

        //Asignamos la instancia del contador sincronizado cada controlador
        controlador1.iniciarContador(contadorSincronizado);
        controlador2.iniciarContador(contadorSincronizado);

    }

    public static void main(String[] args) {
        launch();
    }
}