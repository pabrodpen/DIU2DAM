import React, { useState, useEffect } from "react";
import PersonDataService from "../services/agenda.service";

const EditPerson = () => {
  const id = window.location.pathname.split('/')[2];

  const [person, setPerson] = useState({
    id: id,
    nombre: '',
    apellidos: '',
    calle: '',
    ciudad: '',
    codigoPostal: '',
    fechaNacimiento: ''  // Fecha en formato YYYY-MM-DD
  });

  useEffect(() => {
    PersonDataService.get(id)
      .then(response => {
        setPerson(response.data);
      });
  }, [id]);

  const EditPerson = (e) => {
    e.preventDefault();
    PersonDataService.update(id, person).then(() => {
      console.log("Contacto actualizado correctamente");
    });
  };

  const setName = (e) => {
    setPerson({ ...person, nombre: e.target.value });
  };

  const setSurname = (e) => {
    setPerson({ ...person, apellidos: e.target.value });
  };

  const setAddress = (e) => {
    setPerson({ ...person, calle: e.target.value });
  };

  const setCity = (e) => {
    setPerson({ ...person, ciudad: e.target.value });
  };

  const setPostalCode = (e) => {
    setPerson({ ...person, codigoPostal: e.target.value });
  };

  const setBirthDate = (e) => {
    setPerson({ ...person, fechaNacimiento: e.target.value });
  };
  return (
    <div className="list row">
      <div className="col-md-8">
        <div className="input-group mb-3">
          <input
            type="text"
            className="form-control"
            placeholder="Nombre"
            value={person.nombre}
            onChange={setName}
          />
           <input
            type="text"
            className="form-control"
            placeholder="Apellidos"
            value={person.apellidos}
            onChange={setSurname}
          />
           <input
            type="text"
            className="form-control"
            placeholder="Dirección"
            value={person.calle}
            onChange={setAddress}
          />
           <input
            type="text"
            className="form-control"
            placeholder="Localidad"
            value={person.ciudad}
            onChange={setCity}
          />
           <input
            type="text"
            className="form-control"
            placeholder="Codigo Postal"
            value={person.codigoPostal}
            onChange={setPostalCode}
          />
           <input
            type="date"
            className="form-control"
            placeholder="Fecha de nacimiento"
            value={person.fechaNacimiento}
            onChange={setBirthDate}
          />
          <div className="input-group-append">
            <button
              className="btn btn-outline-secondary"
              type="button"
              onClick={EditPerson}
            >
              Actualizar contacto
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default EditPerson;