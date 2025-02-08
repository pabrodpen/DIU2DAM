package com.example.agenda.view;

import com.example.agenda.model.PersonaVO;
import com.example.agenda.model.repository.PersonaRepository;

import java.util.ArrayList;

public class PersonUtil {
    PersonaRepository personaRepository;

    public ArrayList<PersonaVO> getListaPersonasVO(ArrayList<Contacto> contactos){
        ArrayList<PersonaVO> listaPersonasVO=new ArrayList<>();
        for(Contacto c:contactos){
                PersonaVO personaVO=new PersonaVO(c.getNombre(),c.getApellido(),c.getDireccion(),c.getLocalidad(),c.getCodPostal(),c.getFechaNac());

                listaPersonasVO.add(personaVO);
        }
        return listaPersonasVO;

    }

    public ArrayList<Contacto> getListaContactos(ArrayList<PersonaVO> personaVOS){
        ArrayList<Contacto> listaContactos=new ArrayList<>();
        for(PersonaVO p:personaVOS){
            Contacto contacto=new Contacto(p.getCod(), p.getNombre(),p.getApellido(),p.getDireccion(),p.getLocalidad(),p.getCodPostal(),String.valueOf(p.getFechaNac()));

            listaContactos.add(contacto);
        }
        return listaContactos;

    }
}
