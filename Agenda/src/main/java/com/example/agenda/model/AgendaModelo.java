package com.example.agenda.model;

import com.example.agenda.model.repository.PersonaRepository;
import com.example.agenda.view.Contacto;
import com.example.agenda.view.PersonUtil;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.List;

public class AgendaModelo {

    PersonaRepository personaRepository;
    PersonUtil personUtil;

    //actulizar constantmente el nuemro de ocnatxctos para el caso de que abramos mas ventanas
    //lo inicicalismoa el el numero de contactops en el mkmento en que se abre la ventana
    private final IntegerProperty numContactos = new SimpleIntegerProperty();

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
        //actualizamos el numero de personas por si abrimos varias ventanas
        setNumContactos(getPersonas().size());
    }

    public void deletePersonVOtoBD(Contacto c){
        int codContacto=c.getCodigo();
        personaRepository.deletePersona(codContacto);
        //actualizamos el numero de personas por si abrimos varias ventanas
        setNumContactos(getPersonas().size());
    }

    public void editPersonVOtoBD(Contacto c){
        //mismo proceso que en añadir
        PersonaVO personaVO= new PersonaVO(c.getCodigo(),c.getNombre(),c.getApellido(),c.getDireccion(),c.getLocalidad(),c.getCodPostal(),c.getFechaNac());
        personaRepository.editPersona(personaVO);
        //actualizamos el numero de personas por si abrimos varias ventanas
        setNumContactos(getPersonas().size());
    }

    public int getNumContactos(){
        return numContactos.get();
    }

    public void setNumContactos(int numContactos) {
        this.numContactos.set(numContactos);
    }

    public IntegerProperty numContactosProperty() {
        return numContactos;
    }

}
