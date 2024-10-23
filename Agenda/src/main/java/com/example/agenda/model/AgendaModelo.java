package com.example.agenda.model;

import com.example.agenda.model.repository.PersonaRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.List;

public class AgendaModelo {

    PersonaRepository personaRepository;
    public void setPersonaRepository(PersonaRepository p){
        this.personaRepository=p;
    }




}
