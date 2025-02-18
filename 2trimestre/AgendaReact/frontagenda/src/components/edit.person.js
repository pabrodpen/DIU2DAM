import React, { useState, useEffect } from "react";
import PersonDataService from "../services/person.service";
import { useParams, useNavigate } from "react-router-dom";

const EditPerson = () => {
  const { id } = useParams();
  const navigate = useNavigate(); // Para redirigir después de actualizar

  const [person, setPerson] = useState({
    nombre: '',
    apellidos: '',
    calle: '',
    ciudad: '',
    codigoPostal: '',
    fechaNacimiento: ''
  });

  useEffect(() => {
    PersonDataService.get(id)
      .then(response => {
        if (response.data) {
          setPerson(response.data);
        }
      })
      .catch(error => {
        console.error("Error al obtener contacto:", error);
      });
  }, [id]);

  const handleEditPerson = (e) => {
    e.preventDefault();
    PersonDataService.update(id, person).then(() => {
      console.log("Contacto actualizado correctamente");
      navigate("/persons"); // Redirigir a la lista de contactos
    });
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setPerson({ ...person, [name]: value });
  };

  return (
    <div className="list row">
      <div className="col-md-8">
        <div className="input-group mb-3">
          <input
            type="text"
            className="form-control"
            placeholder="Nombre"
            name="nombre"
            value={person.nombre}
            onChange={handleChange}
          />
          <input
            type="text"
            className="form-control"
            placeholder="Apellidos"
            name="apellidos"
            value={person.apellidos}
            onChange={handleChange}
          />
          <input
            type="text"
            className="form-control"
            placeholder="Dirección"
            name="calle"
            value={person.calle}
            onChange={handleChange}
          />
          <input
            type="text"
            className="form-control"
            placeholder="Localidad"
            name="ciudad"
            value={person.ciudad}
            onChange={handleChange}
          />
          <input
            type="text"
            className="form-control"
            placeholder="Codigo Postal"
            name="codigoPostal"
            value={person.codigoPostal}
            onChange={handleChange}
          />
          <input
            type="date"
            className="form-control"
            placeholder="Fecha de nacimiento"
            name="fechaNacimiento"
            value={person.fechaNacimiento}
            onChange={handleChange}
          />
          <div className="input-group-append">
            <button
              className="btn btn-outline-secondary"
              type="button"
              onClick={handleEditPerson}
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
