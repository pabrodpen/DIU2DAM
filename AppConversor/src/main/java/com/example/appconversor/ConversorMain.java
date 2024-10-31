package com.example.appconversor;

import Modelo.ExcepcionMoneda;
import Modelo.MonedaVO;
import Modelo.repository.MonedaRepository;
import Modelo.repository.impl.ConexionJDBC;
import Modelo.repository.impl.MonedaRepositoryImpl;
import com.example.appconversor.controllers.ConversorController;
import com.example.appconversor.models.ConversorModelo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class ConversorMain extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(ConversorMain.class.getResource("conversor-main.fxml"));
        Scene  escena1 = new Scene(fxmlLoader.load(), 350, 200);


        Stage stage1 = new Stage(); // Crear nuevo Stage para la primera ventana
        stage1.setTitle("Conversor de monedas");
        stage1.setScene(escena1);
        stage1.show();

        // Instanciar el repositorio y el modelo
        MonedaRepositoryImpl monedaRepository = new MonedaRepositoryImpl();
        ConversorModelo conversorModelo = new ConversorModelo();
        // Establecer el repositorio en el modelo
        conversorModelo.setMonedaRepository(monedaRepository);
        // Obtener el controlador
        ConversorController controller = fxmlLoader.getController();



        // Establecer el modelo en el controlador
        controller.setConversorModelo(conversorModelo);


        try{
            MonedaRepositoryImpl monedaRepositoryImpl=new MonedaRepositoryImpl();
            MonedaVO monedaPrueba=new MonedaVO("dólar",1.2F);
            monedaRepositoryImpl.addMoneda(monedaPrueba);
            System.out.println("Moneda insertada");


        } catch (ExcepcionMoneda e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null); // No encabezado
            alert.setContentText("No se ha podido recuperar la conversion a la moneda");
            alert.showAndWait();
        }
    }

    public static void main(String[] args) {
            launch(args);
    }

}