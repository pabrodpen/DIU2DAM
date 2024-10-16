package com.example.appconversor;

import Modelo.ExcepcionMoneda;
import Modelo.MonedaVO;
import Modelo.repository.impl.ConexionJDBC;
import Modelo.repository.impl.MonedaRepositoryImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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


        //conexion a la bd con expecion personalizada

        ConexionJDBC conexion = new ConexionJDBC();
        Connection conn = null;

        try {
            // Intentar conectarse a la base de datos
            conn = conexion.conectarBD();
            System.out.println("Conexión exitosa a la base de datos.");

            // Aquí puedes realizar operaciones con la base de datos

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar la conexión
            if (conn != null) {
                conexion.desconectarBD(conn);
                System.out.println("Conexión cerrada.");
            }
        }

        try{
            MonedaRepositoryImpl monedaRepository=new MonedaRepositoryImpl();
            MonedaVO monedaPrueba=new MonedaVO("dólar",1.2F);
            monedaRepository.addMoneda(monedaPrueba);
            System.out.println("Moneda insertada");


        } catch (ExcepcionMoneda e) {
            e.imprimirMensaje();
        }
    }

    public static void main(String[] args) {
            launch(args);
    }

}