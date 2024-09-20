package com.example.ejemplos;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ContadorPulsaciones extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        try{
            HBox botones=new HBox();

            botones.setPadding(new Insets(10));
            botones.setSpacing(10);
            botones.setAlignment(Pos.CENTER);


            Button bSumar,bRestar,bCero;

            bSumar=new Button();
            bRestar=new Button();
            bCero=new Button();

            bSumar.setText("+");
            bRestar.setText("-");
            bCero.setText("0");

            botones.getChildren().addAll(bSumar,bRestar,bCero);


            Label lbNumero=new Label();

            lbNumero.setText("1");
            lbNumero.setFont(Font.font("Ani", 40));
            lbNumero.setMinWidth(100);
            lbNumero.setAlignment(Pos.CENTER);


            VBox raiz=new VBox();

            raiz.setPadding(new Insets(10));
            raiz.setSpacing(10);
            raiz.setAlignment(Pos.CENTER);

            raiz.getChildren().addAll(botones,lbNumero);


            Scene escena = new Scene(raiz, 420, 150);
            stage.setTitle("Contador");
            stage.setScene(escena);
            stage.show();



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
