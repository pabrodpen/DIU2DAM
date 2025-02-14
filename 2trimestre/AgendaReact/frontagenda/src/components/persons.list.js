import React, { Component } from "react";
import PersonDataService from "../services/agenda.service";
import { Link } from "react-router-dom";

export default class PersonsList extends Component {
  constructor(props) {
    super(props);

    this.state = {
      persons: [],
      currentPerson: null,
      currentIndex: -1,
      searchByName: ""
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
        console.log(response.data);
        this.refreshList();
      })
      .catch(e => {
        console.log(e);
      });
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
      <div className="list row">
        <div className="col-md-8">
          <div className="input-group mb-3">
            <input
              type="text"
              className="form-control"
              placeholder="Buscar por nombre"
              value={searchByName}
              onChange={this.onChangeSearchByName}
            />
            <div className="input-group-append">
              <button
                className="btn btn-outline-secondary"
                type="button"
                onClick={this.searchByName}
              >
                Search
              </button>
            </div>
          </div>
        </div>
        <div className="col-md-6">
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
              <div>
                <label>
                  <strong>Direccion:</strong>
                </label>{" "}
                {currentPerson.calle}
              </div>
              <div>
                <label>
                  <strong>Localidad:</strong>
                </label>{" "}
                {currentPerson.ciudad}
              </div>
              <div>
                <label>
                  <strong>Codigo postal:</strong>
                </label>{" "}
                {currentPerson.codigoPostal}
              </div>
              <div>
                <label>
                  <strong>Fecha de nacimiento:</strong>
                </label>{" "}
                {currentPerson.fechaNacimiento}
              </div>

              <Link to={"/person/" + currentPerson.id} className="badge badge-warning">
                Editar
              </Link>
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
  }
}
