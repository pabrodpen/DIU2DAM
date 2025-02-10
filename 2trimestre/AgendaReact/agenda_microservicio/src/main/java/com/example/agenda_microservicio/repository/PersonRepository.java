package com.example.agenda_microservicio.repository;

import com.example.agenda_microservicio.model.PersonVO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends MongoRepository<PersonVO,String> {
    //List<PersonVO> findByPublishedTrue();
    List<PersonVO>  findAll();
    Optional<PersonVO> getPersonById();
    List<PersonVO> findByTitleContaining(String title);
    //List<PersonVO> findByPublished(boolean published);
}
