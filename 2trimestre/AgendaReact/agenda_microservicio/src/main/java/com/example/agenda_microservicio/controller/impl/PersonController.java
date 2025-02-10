package com.example.agenda_microservicio.controller.impl;

import com.example.agenda_microservicio.controller.PersonAPI;
import com.example.agenda_microservicio.model.PersonDto;
import com.example.agenda_microservicio.repository.PersonRepository;
import com.example.agenda_microservicio.service.PersonService;
import com.example.tutorials.Tutorials.controller.TutorialsAPI;
import com.example.tutorials.Tutorials.model.TutorialsDto;
import com.example.tutorials.Tutorials.repository.TutorialsRepository;
import com.example.tutorials.Tutorials.service.TutorialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class PersonController implements PersonAPI {
    @Autowired
    private PersonService agendaService;
    @Autowired
    private PersonRepository personRepository;
    @Override
    @GetMapping("/tutorials")
    public List<PersonDto> getAllPersons(){return agendaService.getAllTutorials();}

    @Override
    @GetMapping("/tutorials/{id}")
    public Optional<PersonDto> getPersonById(@PathVariable String id) {
        return agendaService.getTutorialById(id);
    }

    @Override
    @GetMapping("/tutorials/title/{title}")
    public List<PersonDto> findByTitleContaining(@PathVariable String title) {
        return agendaService.findByTitleContaining(title);
    }

    @Override
    @GetMapping("/tutorials/published")
    public List<PersonDto> findByPublished() {
        return agendaService.findByPublished();
    }

    @Override
    @PostMapping("/tutorials")
    public PersonDto save(@RequestBody PersonDto personDto) {
        return agendaService.save(personDto);
    }

    @Override
    @PutMapping("/tutorials/{id}")
    public PersonDto updatePerson(@RequestBody PersonDto tutorial,@PathVariable String id) {
        return agendaService.updateTutorial(tutorial);
    }

    @Override
    @DeleteMapping("/tutorials/{id}")
    public ResponseEntity deletePerson(@PathVariable String id) {
        return agendaService.deleteTutorial(id);
    }

    @Override
    @DeleteMapping("/tutorials")
    public ResponseEntity deleteAllPersons() {
        return agendaService.deleteAllPersons();
    }
}
