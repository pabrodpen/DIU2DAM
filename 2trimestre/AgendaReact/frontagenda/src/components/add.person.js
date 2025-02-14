import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import PersonDataService from "../services/agenda.service";

const AddPerson = () => {
  const [person, setPerson] = useState({
    nombre: '',
    apellidos: '',
    calle: '',
    ciudad: '',
    codigoPostal: '',
    fechaNacimiento: ''
  });

  const navigate = useNavigate();

  const onChange = (e) => {
    const { name, value } = e.target;
    setPerson({ ...person, [name]: value });
  };

  const formatDate = (dateString) => {
    if (!dateString) return ""; // Evita errores si la fecha es nula
  
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, "0"); // Asegura 2 dígitos
    const day = String(date.getDate()).padStart(2, "0"); // Asegura 2 dígitos
  
    return `${year}-${month}-${day}`;
  };

  const addPerson = () => {
    const fechaFormateada = formatDate(person.fechaNacimiento);
  
    const data = {
      nombre: person.nombre,
      apellidos: person.apellidos,
      calle: person.calle,
      ciudad: person.ciudad,
      codigoPostal: parseInt(person.codigoPostal, 10),
      fechaNacimiento: fechaFormateada  // Fecha en formato YYYY-MM-DD
    };
  
    PersonDataService.create(data)
      .then(response => {
        console.log("Respuesta del backend:", response.data);
        setPerson({
          id: response.data.id,
          nombre: response.data.nombre,
          apellidos: response.data.apellidos,
          calle: response.data.calle,
          codigoPostal: response.data.codigoPostal,
          ciudad: response.data.ciudad,
          fechaNacimiento: response.data.fechaNacimiento
        });
        navigate("/persons"); // Redirigir a la lista de contactos
      })
      .catch(e => {
        console.error("Error al agregar contacto:", e);
      });
  };

  return (
    <div className="container mt-3">
      <h4>Añadir Contacto</h4>
      <div className="form-group">
        <label>Nombre</label>
        <input
          type="text"
          className="form-control"
          placeholder="Nombre"
          name="nombre"
          value={person.nombre}
          onChange={onChange}
        />
      </div>
      
      <div className="form-group">
        <label>Apellido</label>
        <input
          type="text"
          className="form-control"
          placeholder="Apellido"
          name="apellidos"
          value={person.apellidos}
          onChange={onChange}
        />
      </div>

      <div className="form-group">
        <label>Dirección</label>
        <input
          type="text"
          className="form-control"
          placeholder="Dirección"
          name="calle"
          value={person.calle}
          onChange={onChange}
        />
      </div>

      <div className="form-group">
        <label>Localidad</label>
        <input
          type="text"
          className="form-control"
          placeholder="Localidad"
          name="ciudad"
          value={person.ciudad}
          onChange={onChange}
        />
      </div>

      <div className="form-group">
        <label>Código Postal</label>
        <input
          type="text"
          className="form-control"
          placeholder="Código Postal"
          name="codigoPostal"
          value={person.codigoPostal}
          onChange={onChange}
        />
      </div>

      <div className="form-group">
        <label>Fecha de Nacimiento</label>
        <input
          type="date"
          className="form-control"
          name="fechaNacimiento"
          value={person.fechaNacimiento}
          onChange={onChange}
        />
      </div>

      <button className="btn btn-primary mt-3" onClick={addPerson}>
        Añadir Contacto
      </button>
    </div>
  );
};

export default AddPerson;
