package com.example.ejemplos;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.scene.layout.Panel;

import static javafx.application.Application.launch;

public class ContadorProperties extends Application {
    ContadorEstilosInteraccion contador1=new ContadorEstilosInteraccion();
    ContadorEstilosInteraccion contador2=new ContadorEstilosInteraccion();

    public void start(Stage stage) throws Exception {

        try{

            //IMP
            /*Sincronizar numPulsaciones de ambos contadores*/

            contador1.getNumPulsaciones().bindBidirectional(contador2.getNumPulsaciones());

            //creamos la escena

            Stage stage2=new Stage();
            VBox raiz1=contador1.crearPantalla();
            VBox raiz2=contador2.crearPantalla();

            //2 escenas

            Scene escena1=new Scene(raiz1,420,150);
            escena1.getStylesheets().add(getClass().getResource("/styles/estilosContador.css").toExternalForm());
            stage.setTitle("Contador1");
            stage.setScene(escena1);
            stage.show();

            Scene escena2=new Scene(raiz2,420,150);
            escena2.getStylesheets().add(getClass().getResource("/styles/estilosContador.css").toExternalForm());
            stage2.setTitle("Contador2");
            stage2.setScene(escena2);
            stage2.show();




        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        launch(args);
    }


}
