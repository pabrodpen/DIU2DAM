package com.example.hotel.view;

import com.example.hotel.model.PersonaVO;
import com.example.hotel.model.ReservaVO;
import com.example.hotel.model.repository.Repository;

import java.util.ArrayList;

public class ReservaUtil {

    Repository repository;
    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public Reserva reservaVOtoReserva(ReservaVO reservaVO) {
        return new Reserva(reservaVO.getCodigo(),reservaVO.getNumHabitaciones(),reservaVO.getTipoHabitacion(), reservaVO.isEsFumador(), reservaVO.getRegimenHabitacion(), reservaVO.getHoraLLegada(),reservaVO.getHoraSalida(), reservaVO.getDniCliente());
    }

    public ReservaVO reservatoReservaVO(Reserva reserva) {
        return new ReservaVO(reserva.getCodigo(), reserva.getNumHabitaciones(), reserva.getTipoHabitacion(),reserva.isEsFumador(),reserva.getRegimenHabitacion(),reserva.getHoraLlegada(),reserva.getHoraLlegada(),reserva.getDniCliente());
    }



}
