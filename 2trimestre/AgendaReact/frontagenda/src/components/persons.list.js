import React, { Component } from "react";
import PersonDataService from "../services/person.service";
import { Link } from "react-router-dom";
import ProgressBar from "./progress.bar";
import "../App.css";

export default class PersonsList extends Component {
  constructor(props) {
    super(props);

    this.state = {
      persons: [],
      currentPerson: null,
      currentIndex: -1,
      searchByName: "",
      maxPersons: 50,
    };
  }

  componentDidMount = () => {
    this.retrievePersons();
  };

  onChangeSearchByName = (e) => {
    this.setState({ searchByName: e.target.value });
  };

  retrievePersons = () => {
    PersonDataService.getAll()
      .then(response => {
        this.setState({ persons: response.data });
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  };


  refreshList = () => {
    this.retrievePersons();
    this.setState({
      currentPerson: null,
      currentIndex: -1
    });
  };

  setPerson = (person, index) => {
    this.setState({
      currentPerson: person,
      currentIndex: index
    });
  };

  removeAllPersons = () => {
    PersonDataService.deleteAll()
      .then(response => {
        console.log("Contactos eliminados:", response.data);
        this.refreshList(); // Recargar la lista después de eliminar todos los contactos
      })
      .catch(e => {
        console.error("Error al eliminar contactos:", e);
      });
  };
  

  removePerson = (id) => {
    PersonDataService.delete(id)
      .then(response => {
        console.log(response.data);
        this.refreshList();
      }
      )
    };

  searchByName = () => {
    PersonDataService.findByName(this.state.searchByName)
      .then(response => {
        this.setState({ persons: response.data });
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  };



  render() {
    console.log(this.state.persons);
    const { searchByName, persons, currentPerson, currentIndex } = this.state;

    return (
      <div className="contacts-container">
        {/* 🔹 Lista de Contactos */}
        <div className="list-container">
          <h4>Lista de contactos</h4>
          <ul className="list-group">
            {persons &&
              persons.map((person, index) => (
                <li
                  className={
                    "list-group-item " + (index === currentIndex ? "active" : "")
                  }
                  onClick={() => this.setPerson(person, index)}
                  key={index}
                >
                  {person.nombre} {person.apellidos}
                </li>
              ))}
          </ul>
          {/* ✅ Barra de progreso debajo de la lista */}
          <ProgressBar totalPersons={persons.length} maxPersons={this.state.maxPersons} />
        </div>
    
        {/* 🔹 Detalles del Contacto (A la derecha) */}
        <div className="details-container">
          {currentPerson ? (
            <div>
              <h4>Contacto</h4>
              <div>
                <label><strong>Nombre:</strong></label> {currentPerson.nombre}
              </div>
              <div>
                <label><strong>Apellido:</strong></label> {currentPerson.apellidos}
              </div>
              <div>
                <label><strong>Dirección:</strong></label> {currentPerson.calle}
              </div>
              <div>
                <label><strong>Localidad:</strong></label> {currentPerson.ciudad}
              </div>
              <div>
                <label><strong>Código Postal:</strong></label> {currentPerson.codigoPostal}
              </div>
              <div>
                <label><strong>Fecha de Nacimiento:</strong></label> {currentPerson.fechaNacimiento}
              </div>
              <Link to={"/edit/" + currentPerson.id}>
                <button className="btn btn-primary">Editar Contacto</button>
              </Link>
              <button className="btn btn-delete" onClick={() => this.removePerson(currentPerson.id)}>
                Eliminar Contacto
              </button>

              <Link to={"/tutorials/" + currentPerson.id} className="nav-link">
                <button className="btn btn-primary mt-3" onClick={this.addPerson}>
                Tutoriales
                </button>
              </Link>
            </div>
          ) : (
            <p>Selecciona un contacto</p>
          )}
        </div>
      </div>
    );
    
  }
}
