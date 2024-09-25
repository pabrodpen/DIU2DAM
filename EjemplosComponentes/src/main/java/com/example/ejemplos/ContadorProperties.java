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
    ContadorEstilosInteraccion contador1;
    ContadorEstilosInteraccion contador2;
    Label lbNumero;
    Button bSumar,bRestar,bCero;
    private IntegerProperty numPulsaciones = new SimpleIntegerProperty(1);

    private void pulsado(int n){
        if(n==0){
            numPulsaciones.set(0);
        }
        lbNumero.setText(String.valueOf(numPulsaciones));

    }

    public VBox crearPantalla(){


        HBox botones=new HBox();

        botones.setPadding(new Insets(10));
        botones.setSpacing(10);
        botones.setAlignment(Pos.CENTER);



        bSumar=new Button();
        bRestar=new Button();
        bCero=new Button();
        bSumar.setId("bSumar");
        bRestar.setId("bRestar");
        bCero.setId("bCero");


        bSumar.setText("+");
        bRestar.setText("-");
        bCero.setText("0");

        bSumar.setOnAction(event->pulsado(1));

        bRestar.setOnAction(event->pulsado(-1));

        bCero.setOnAction(event->pulsado(0));

        botones.getChildren().addAll(bSumar,bRestar,bCero);


        lbNumero=new Label();

        lbNumero.setText("1");
        lbNumero.setFont(Font.font("Ani", 40));
        lbNumero.setMinWidth(100);
        lbNumero.setAlignment(Pos.CENTER);
        // Asignar ID a la etiqueta para el CSS
        lbNumero.setId("lbNumero");



        VBox raiz=new VBox();

        raiz.setPadding(new Insets(10));
        raiz.setSpacing(10);
        raiz.setAlignment(Pos.CENTER);

        //coger la clase para el css
        raiz.getStyleClass().add("raiz");



        raiz.getChildren().addAll(botones,lbNumero);


        /*solo es crear los hbox y vbox, no hace falta vvcrear la escena
        Scene escena = new Scene(raiz, 420, 150);
        escena.getStylesheets().add(getClass().getResource("/styles/estilosContador.css").toExternalForm());
        stage.setTitle("Contador");
        stage.setScene(escena);
        stage.show();*/

        return raiz;

    }



    public void start(Stage stage) throws Exception {

        try{


            Stage stage2=new Stage();
            VBox raiz1=crearPantalla();
            VBox raiz2=crearPantalla();

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
