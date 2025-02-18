/*
package com.example.agenda.controller.impl;

import com.example.agenda.model.PersonDto;
import com.example.agenda.repository.PersonRepository;
import com.example.agenda.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class TutorialController {
    @Autowired
    private PersonService personService;
    @Autowired
    private PersonRepository personRepository;
    @Override
    @GetMapping("/agenda")
    public List<PersonDto> getAllPersons(){return personService.getAllPersons();}

    @Override
    @GetMapping("/agenda/{id}")
    public Optional<PersonDto> getPersonById(@PathVariable String id) {
        return personService.getPersonById(id);
    }

    @Override
    @GetMapping("/agenda/title/{title}")
    public List<PersonDto> findByNombreContaining(@PathVariable String nombre) {
        return personService.findByNombreContaining(nombre);
    }
}
*/