package com.example.hotel.view;

import com.example.hotel.model.PersonaVO;
import com.example.hotel.model.ReservaVO;
import com.example.hotel.model.repository.Repository;

import java.util.ArrayList;

public class PersonaUtil {

    Repository repository;
    public void setRepository(Repository repository) {
        this.repository = repository;
    }


    public Persona personaVOtoPersona(PersonaVO personaVO) {
        return new Persona(personaVO.getDni(),personaVO.getNombre_completo(),personaVO.getDireccion(),personaVO.getLocalidad(),personaVO.getProvincia());
    }

    public PersonaVO personatoPersonaVO(Persona persona) {
        return new PersonaVO(persona.getDni(), persona.getNombre_completo(), persona.getDireccion(), persona.getLocalidad(), persona.getProvincia());
    }

}
