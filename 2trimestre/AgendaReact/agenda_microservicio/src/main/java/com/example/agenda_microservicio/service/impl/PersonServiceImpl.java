package com.example.agenda_microservicio.service.impl;

import com.example.agenda_microservicio.model.PersonDto;
import com.example.agenda_microservicio.model.PersonVO;
import com.example.agenda_microservicio.repository.PersonRepository;
import com.example.agenda_microservicio.service.PersonService;
import com.example.agenda_microservicio.util.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<PersonDto> getAllPersons() {
        List<PersonVO> tutorialsList = personRepository.findAll();
        return tutorialsList.stream()
                .map(PersonMapper::tutorialsMapperEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PersonDto> getPersonById(String id) {
        Optional<PersonVO> tutorialOptional = personRepository.findById(id);

        return tutorialOptional.map(PersonMapper::tutorialsMapperEntityToDto);
    }

    @Override
    public List<PersonDto> findByNameContaining(String name) {
        List<PersonVO> personOptional = personRepository.findByTitleContaining(title);

        return PersonMapper.tutorialsListMapperEntityToDto(personOptional);
    }

    /*@Override
    public List<TutorialsDto> findByPublished() {
        List<Tutorials> publishedTutorials = tutorialsRepository.findByPublished(true);

        return TutorialsMapper.tutorialsListMapperEntityToDto(publishedTutorials);
    }*/

    @Override
    public PersonDto save(PersonDto personDto) {
        PersonVO personVO = PersonMapper.tutorialsMapperDtoToEntity(personDto);
        PersonVO savedPersonEntity = personRepository.save(personVO);
        return PersonMapper.tutorialsMapperEntityToDto(savedPersonEntity);
    }

    @Override
    public PersonDto updatePerson(PersonDto personDto) {
        Optional<PersonVO> existingPersonOptional = personRepository.findById(personDto.getId());

        if (existingPersonOptional.isPresent()) {
            PersonVO existingPersonVO = existingPersonOptional.get();
            existingPersonVO.setNombre(personDto.getNombre());
            existingPersonVO.setApellido(personDto.getApellido());
            existingPersonVO.setDireccion(personDto.getDireccion());
            existingPersonVO.setLocalidad(personDto.getLocalidad());
            existingPersonVO.setCodPostal(personDto.getCodPostal());
            existingPersonVO.setFechaNac(personDto.getFechaNac());

            PersonVO updatedPerson = personRepository.save(existingPersonVO);

            return PersonMapper.tutorialsMapperEntityToDto(updatedPerson);
        } else {
            return null;
        }
    }

    @Override
    public ResponseEntity deletePersonVO(String id) {
        try {
            Optional<PersonVO> existingPersonOptional = personRepository.findById(id);
            if (existingPersonOptional.isPresent()) {
                personRepository.deleteById(id);
                return ResponseEntity.ok("Persona eliminada exitosamente");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Persona no encontrada con ID: " + id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la persona");
        }
    }

    @Override
    public ResponseEntity deleteAllPersons() {
        personRepository.deleteAll();
        ResponseEntity.ok("Persona eliminada exitosamente");
        return ResponseEntity.ok().build();
    }



}
