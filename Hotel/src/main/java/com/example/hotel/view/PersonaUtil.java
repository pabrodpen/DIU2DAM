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


    public ArrayList<Persona> getPersonas() {
        ArrayList<PersonaVO> personasVO = repository.ObtenerListaPersonas();
        ArrayList<Persona> personas = new ArrayList<>();
        for (PersonaVO personaVO:personasVO){
            Persona persona = new Persona(personaVO.getDni(), personaVO.getNombre_completo(), personaVO.getDireccion(), personaVO.getLocalidad(), personaVO.getProvincia());
            personas.add(persona);
        }
        return personas;
    }

    public ArrayList<PersonaVO> getPersonasVO(ArrayList<Persona> personas) {
        ArrayList<PersonaVO> personasVO=new ArrayList<>();
        for(Persona persona:personas){
            PersonaVO personaVO=new PersonaVO(persona.getDni(),persona.getNombre_completo(), persona.getDireccion(), persona.getLocalidad(), persona.getProvincia());
            personasVO.add(personaVO);
        }
        return personasVO;
    }

}
