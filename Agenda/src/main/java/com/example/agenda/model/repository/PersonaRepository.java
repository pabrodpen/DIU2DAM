package com.example.agenda.model.repository;

import com.example.agenda.model.ExcepcionPersona;
import com.example.agenda.model.PersonaVO;

import java.util.ArrayList;

public interface PersonaRepository {


        ArrayList<PersonaVO> ObtenerListaPersonas() throws ExcepcionPersona;

        void addPersona(PersonaVO var1) throws ExcepcionPersona;

        void deletePersona(Integer var1) throws ExcepcionPersona;

        void editPersona(PersonaVO var1) throws ExcepcionPersona;

        //PersonaVO getPersona(int c) throws ExcepcionPersona;

        int lastId() throws ExcepcionPersona;
    }


