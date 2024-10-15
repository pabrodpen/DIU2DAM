package com.example.appconversor;

import Modelo.MonedaVO;
import Modelo.repository.impl.MonedaRepositoryImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
         try{
             MonedaRepositoryImpl monedaRepository=new MonedaRepositoryImpl();
             MonedaVO monedaPrueba=new MonedaVO("prueba",1.2F);
             monedaRepository.addMoneda(monedaPrueba);
         } catch (Exception e) {
             e.printStackTrace();
         }
    }
}