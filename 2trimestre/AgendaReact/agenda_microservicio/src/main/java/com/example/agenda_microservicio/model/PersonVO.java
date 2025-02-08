package com.example.agenda_microservicio.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@Document
public class PersonVO {

    @Id
    private String id;
    private String nombre;
    private String apellido;
    private String direccion;
    private int localidad;
    private int codPostal;
    private LocalDate fechaNac;

}

