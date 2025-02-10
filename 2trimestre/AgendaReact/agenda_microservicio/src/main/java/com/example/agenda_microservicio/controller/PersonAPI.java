package com.example.agenda_microservicio.controller;

import com.example.agenda_microservicio.model.PersonDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface PersonAPI {
    List<PersonDto> getAllPersons();
    Optional<PersonDto> getPersonById(String id);
    List<PersonDto> findByTitleContaining(String id);
    //List<PersonDto>findByPublished();
    PersonDto save(PersonDto personDto);
    PersonDto updateTutorial(PersonDto personDto, String id);
    ResponseEntity deletePerson(String id);
    ResponseEntity deleteAllPersons();
}
