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

    public ArrayList<Reserva> getReservas() {
        ArrayList<ReservaVO> reservasVOS = repository.ObtenerListaReservas();
        ArrayList<Reserva> reservas = new ArrayList<>();
        for (ReservaVO reservaVO:reservasVOS){
            Reserva reserva = new Reserva(reservaVO.getCodigo(), reservaVO.getNumHabitaciones(),reservaVO.getTipoHabitacion(),reservaVO.isEsFumador(),reservaVO.getRegimenHabitacion());
            reservas.add(reserva);
        }
        return reservas;
    }

    public ArrayList<ReservaVO> getReservasVO(ArrayList<Reserva> reservas) {
        ArrayList<ReservaVO> reservasVOS=new ArrayList<>();
        for(Reserva reserva:reservas){
            ReservaVO reservaVO=new ReservaVO(reserva.getCodigo(),reserva.getNumHabitaciones(),reserva.getTipoHabitacion(),reserva.isEsFumador(),reserva.getRegimenHabitacion());
            reservasVOS.add(reservaVO);
        }
        return reservasVOS;
    }



}
