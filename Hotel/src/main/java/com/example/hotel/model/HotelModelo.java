package com.example.hotel.model;

import com.example.hotel.model.repository.Repository;
import com.example.hotel.view.Persona;
import com.example.hotel.view.PersonaUtil;
import com.example.hotel.view.ReservaUtil;

public class HotelModelo {
    Repository repository;
    PersonaUtil personaUtil;
    ReservaUtil reservaUtil;

    public void setRepository(Repository repository){
        this.repository=repository;
    }

    public void setPersonaUtil(PersonaUtil personaUtil){
        this.personaUtil=personaUtil;
    }

    public void setReservaUtil(ReservaUtil reservaUtil){
        this.reservaUtil=reservaUtil;
    }

    //actualizar los cambios de la interfaz a la bd-->añadir,editar y eliminar
    public void addPersonVOtoBD(Persona persona){
        PersonaVO personaVO=new PersonaVO(persona.getDni(),persona.getNombre_completo(),persona.getDireccion(), persona.getLocalidad(), persona.getProvincia());
        repository.addPersona(personaVO);
    }

    public void editPersonVOtoBD(Persona persona){
        //cogemos la persona de la interfaz y lo convertimos en VO para editarlo
        PersonaVO personaVO=new PersonaVO(persona.getDni(),persona.getNombre_completo(), persona.getDireccion(), persona.getLocalidad(), persona.getProvincia());
        repository.editPersona(personaVO);
    }

    public void removePersonVOtoBD(Persona persona){
        //cogemos la persona de la interfaz y la eliminamos mediante el codigo
        String dni=persona.getDni();
        repository.deletePersona(dni);
    }


}
