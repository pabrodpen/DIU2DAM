package com.example.agenda_microservicio.model;


import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonDto {

    private String id;
    private String nombre;
    private String apellido;
    private String direccion;
    private int localidad;
    private int codPostal;
    private LocalDate fechaNac;

}
