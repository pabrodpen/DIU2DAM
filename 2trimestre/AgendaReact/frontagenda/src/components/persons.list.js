import React, { useState, useEffect, forwardRef, useImperativeHandle } from "react";
import PersonDataService from "../services/agenda.service";
import { Link } from "react-router-dom";

const PersonsList = forwardRef((props, ref) => {
  const [persons, setPersons] = useState([]);
  const [currentPerson, setCurrentPerson] = useState(null);
  const [currentIndex, setCurrentIndex] = useState(-1);
  const [searchByName, setSearchByName] = useState("");

  useEffect(() => {
    retrievePersons();
  }, []);

  const retrievePersons = () => {
    PersonDataService.getAll()
      .then((response) => {
        setPersons(response.data);
        console.log(response.data);
      })
      .catch((e) => {
        console.log(e);
      });
  };

  const refreshList = () => {
    retrievePersons();
    setCurrentPerson(null);
    setCurrentIndex(-1);
  };

  const removeAllPersons = () => {
    PersonDataService.deleteAll()
      .then((response) => {
        console.log("Contactos eliminados:", response.data);
        refreshList(); // Recargar la lista después de eliminar todos los contactos
      })
      .catch((e) => {
        console.error("Error al eliminar contactos:", e);
      });
  };

  useImperativeHandle(ref, () => ({
    removeAllPersons, // Expone la función removeAllPersons() a App.js
  }));

  return (
    <div className="list row">
      <div className="col-md-8">
        <div className="input-group mb-3">
          {/*
          <input
            type="text"
            className="form-control"
            placeholder="Buscar por nombre"
            value={searchByName}
            onChange={(e) => setSearchByName(e.target.value)}
          />
          <div className="input-group-append">
            <button
              className="btn btn-outline-secondary"
              type="button"
              onClick={() => PersonDataService.findByName(searchByName)}
            >
              Buscar
            </button>
          </div>*/}
        </div>
      </div>
      <div className="col-md-6">
        <h4>Lista de contactos</h4>
        <ul className="list-group">
          {persons &&
            persons.map((person, index) => (
              <li
                className={"list-group-item " + (index === currentIndex ? "active" : "")}
                onClick={() => {
                  setCurrentPerson(person);
                  setCurrentIndex(index);
                }}
                key={index}
              >
                {person.nombre} {person.apellidos}
              </li>
            ))}
        </ul>
      </div>
      <div className="col-md-6">
        {currentPerson ? (
          <div>
            <h4>Contacto</h4>
            <div>
              <label>
                <strong>Nombre:</strong>
              </label>{" "}
              {currentPerson.nombre}
            </div>
            <div>
              <label>
                <strong>Apellido:</strong>
              </label>{" "}
              {currentPerson.apellidos}
            </div>

            <Link to={"/edit/" + currentPerson.id} className="nav-link">
              <button className="btn btn-primary mt-3">Editar Contacto</button>
            </Link>

            <button
              className="btn btn-danger mt-3"
              onClick={() => PersonDataService.delete(currentPerson.id).then(refreshList)}
            >
              Eliminar Contacto
            </button>
          </div>
        ) : (
          <div>
            <br />
            <p>Selecciona un contacto</p>
          </div>
        )}
      </div>
    </div>
  );
});

export default PersonsList;
