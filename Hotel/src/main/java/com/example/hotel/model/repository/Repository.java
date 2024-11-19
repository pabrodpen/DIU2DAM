package com.example.hotel.model.repository;


import com.example.hotel.model.ExcepcionPersona;
import com.example.hotel.model.ExcepcionReserva;
import com.example.hotel.model.PersonaVO;
import com.example.hotel.model.ReservaVO;

import java.util.ArrayList;

public interface Repository {


    ArrayList<PersonaVO> ObtenerListaPersonas() throws ExcepcionPersona;

    void addPersona(PersonaVO var1) throws ExcepcionPersona;

    void deletePersona(String var1) throws ExcepcionPersona;

    void editPersona(PersonaVO var1) throws ExcepcionPersona;


    ArrayList<ReservaVO> ObtenerListaReservas(String dni) throws ExcepcionReserva;

    void addReserva(ReservaVO var1) throws ExcepcionReserva;

    void deleteReserva(String var1) throws ExcepcionReserva;

    void editReserva(ReservaVO var1) throws ExcepcionReserva;
}


