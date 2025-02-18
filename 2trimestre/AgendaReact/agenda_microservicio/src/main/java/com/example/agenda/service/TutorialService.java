package com.example.agenda.service;

import com.example.agenda.model.PersonDto;

import java.util.List;
import java.util.Optional;

public interface TutorialService {
    List<PersonDto> getAllPersons(); //funciona
    Optional<PersonDto> getPersonById(String id); //funciona
    List<PersonDto> findByNombreContaining(String nombre);
}
