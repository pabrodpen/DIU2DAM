package com.example.agenda_microservicio.service;

import com.example.agenda_microservicio.model.PersonDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    List<PersonDto> getAllPersons(); //funciona
    Optional<PersonDto> getPersonById(String id); //funciona
    List<PersonDto> findByNameContaining(String name);
    //List<TutorialsDto> findByPublished(); //funciona
    PersonDto save(PersonDto personDto); //funciona
    PersonDto updatePerson(PersonDto personDto); //funciona
    ResponseEntity deletePerson(String id); //funciona
    ResponseEntity deleteAllPersons(); //funciona
}
