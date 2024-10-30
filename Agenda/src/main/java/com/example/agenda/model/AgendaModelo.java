package com.example.agenda.model;

import com.example.agenda.model.repository.PersonaRepository;
import com.example.agenda.view.Contacto;
import com.example.agenda.view.PersonUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.List;

public class AgendaModelo {

    PersonaRepository personaRepository;
    PersonUtil personUtil;

    public void setPersonaRepository(PersonaRepository p){
        this.personaRepository=p;
    }
    public void setPersonUtil(PersonUtil personUtil){
        this.personUtil=personUtil;
    }



    public ArrayList<Contacto> getPersonas(){
        ArrayList<PersonaVO> lista=personaRepository.ObtenerListaPersonas();
        ArrayList<Contacto> contactoArrayList=personUtil.getListaContactos(lista);
        return contactoArrayList;
    }

    public void addPersonVOtoBD(Contacto c){
        PersonaVO personaVO= new PersonaVO(c.getCodigo(),c.getNombre(),c.getApellido(),c.getDireccion(),c.getLocalidad(),c.getCodPostal(),c.getFechaNac());
        personaRepository.addPersona(personaVO);
    }

    public void deletePersonVOtoBD(Contacto c){
        int codContacto=c.getCodigo();
        personaRepository.deletePersona(codContacto);
    }

    public void editPersonVOtoBD(Contacto c){
        //mismo proceso que en añadir
        PersonaVO personaVO= new PersonaVO(c.getCodigo(),c.getNombre(),c.getApellido(),c.getDireccion(),c.getLocalidad(),c.getCodPostal(),c.getFechaNac());
        personaRepository.editPersona(personaVO);
    }

    public int getNumContactos(){
        ArrayList<Contacto> lista=getPersonas();
        return lista.size();
    }

}
