package com.example.hotel.model;

import com.example.hotel.model.repository.Repository;
import com.example.hotel.view.Persona;
import com.example.hotel.view.PersonaUtil;
import com.example.hotel.view.Reserva;
import com.example.hotel.view.ReservaUtil;

import java.util.ArrayList;

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

    public void addReservaVOtoBD(Reserva reserva){
        ReservaVO reservaVO=new ReservaVO(reserva.getCodigo(),reserva.getNumHabitaciones(),reserva.getTipoHabitacion(),reserva.isEsFumador(),reserva.getRegimenHabitacion(),reserva.getHoraLlegada(),reserva.getHoraSalida(),reserva.getDniCliente());
        repository.addReserva(reservaVO);
    }

    public void editPersonVOtoBD(Persona persona){
        //cogemos la persona de la interfaz y lo convertimos en VO para editarlo
        PersonaVO personaVO=new PersonaVO(persona.getDni(),persona.getNombre_completo(), persona.getDireccion(), persona.getLocalidad(), persona.getProvincia());
        repository.editPersona(personaVO);
    }

    public void editReservaVOtoBD(Reserva reserva){
        ReservaVO reservaVO=new ReservaVO(reserva.getCodigo(),reserva.getNumHabitaciones(),reserva.getTipoHabitacion(),reserva.isEsFumador(),reserva.getRegimenHabitacion(),reserva.getHoraLlegada(),reserva.getHoraSalida(),reserva.getDniCliente());
        repository.editReserva(reservaVO);
    }

    public void deletePersonVOtoBD(Persona persona){
        //cogemos la persona de la interfaz y la eliminamos mediante el codigo
        String dni=persona.getDni();
        repository.deletePersona(dni);
    }

    public void deleteReservaVOtoBD(Reserva reserva){
        String codigo=reserva.getCodigo();
        repository.deleteReserva(codigo);
    }

    //cogemos lista de PersonasVO y ReservasVO de repository y los volvemos Personas y Reservas
    //con los metodos de util para mandar estas listas al controller
    public ArrayList<Persona> getListaPersonas(){
        ArrayList<PersonaVO> personasVO=repository.ObtenerListaPersonas();
        ArrayList<Persona> personas=new ArrayList<>();
        for(PersonaVO personaVO:personasVO){
            Persona persona= personaUtil.personaVOtoPersona(personaVO);
            personas.add(persona);
        }
        System.out.println(personas);
        return personas;
    }

    public ArrayList<Reserva> getListaReservas(String dni){
        var reservasVO=repository.ObtenerListaReservas(dni);
        ArrayList<Reserva> reservas=new ArrayList<>();
        for(ReservaVO reservaVO:reservasVO){
            String dniCliente=reservaVO.getDniCliente();
            if(dniCliente.equals(dni)){
                Reserva reserva=reservaUtil.reservaVOtoReserva(reservaVO);
                reservas.add(reserva);
            }

        }
        return reservas;
    }


}
