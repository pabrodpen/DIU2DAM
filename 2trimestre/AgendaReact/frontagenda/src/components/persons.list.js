import React, { Component } from "react";
import PersonDataService from "../services/agenda.service";
import { Link } from "react-router-dom";

export default class PersonsList extends Component {
  constructor(props) {
    super(props);
    this.onChangeSearchName = this.onChangeSearchName.bind(this);
    this.retrievePersons = this.retrievePersons.bind(this);
    this.refreshList = this.refreshList.bind(this);
    this.setPerson = this.setPerson.bind(this);
    this.removeAllPersons = this.removeAllPersons.bind(this);
    this.searchByName = this.searchByName.bind(this);
    //Hacemos el bind de los métodos porque al usar estos métodos en gestores de eventos los componentes basados
    //en clases pierden el ámbito.
    this.state = {
      persons: [], //lista de personas
      currentPerson: null, //tutorial seleccionado de la lista
      currentIndex: -1,
      searchByName: ""
    };
  }
  //Cuando se carga el componente, se realiza la petición de tutoriales a la API
  //El método retrieveTutorials provoca la actualización del estado, y por tanto la re-renderización del componente
  componentDidMount() {
    this.retrievePersons();
  }

  onChangeSearchByName(e) {
    const searchByName = e.target.value;

    this.setState({
      searchByName: searchByName
    });
  }

  retrievePersons() {
    PersonDataService.getAll()
      .then(response => {
        this.setState({
          persons: response.data
        });
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }

  refreshList() {
    this.retrievePersons();
    this.setState({
      currentPerson: null,
      currentIndex: -1
    });
  }

  setPerson(person, index) {
    this.setState({
      currentPerson: person,
      currentIndex: index
    });
  }

  removeAllPersons() {
    PersonDataService.deleteAll()
      .then(response => {
        console.log(response.data);
        this.refreshList();
      })
      .catch(e => {
        console.log(e);
      });
  }

  searchByName() {
    PersonDataService.findByName(this.state.searchByName)
      .then(response => {
        this.setState({
          persons: response.data
        });
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }

  render() {
    const { searchByName, persons, currentPerson, currentIndex } = this.state;
    //ponemos los distintos elementos del estado en variables para simplificar su acceso dentro del método
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
            
            {/*<div className="input-group-append">
              <button
                className="btn btn-outline-secondary"
                type="button"
                onClick={this.searchByName}
              >
                Search
              </button>
            </div>*/}
          </div>
        </div>
        <div className="col-md-6">
          <h4>Lista de contactos</h4>

          <ul className="list-group">
            
            {/*El operedor && lógico. Los dos elementos tienen que ser true, en este caso no vacio, para que se ejecute la sentencia */}
            {/*si tutorials está vacio , no se ejecuta el map*/}
            {/*Aquí .map() está recorriendo la lista de personas y generando dinámicamente un <li> para cada persona en la lista.*/}

            {persons &&
              persons.map((person, index) => (
                <li
              /* Cambiamos la clase del elemento de la lista seleccionado. Ponemos fondo azul*/
                  className={
                    "list-group-item " +
                    (index === currentIndex ? "active" : "")
                  }
                  onClick={() => this.setPerson(person, index)}
                  key={index}
                >
                  {person.name}
                  {person.surname}
                </li>
              ))}
          </ul>

          {/*<button
            className="m-3 btn btn-sm btn-danger"
            onClick={this.removeAllPersons}
          >
            Remove All
          </button>*/}
        </div>
        <div className="col-md-6">
          {/*Renderizado condicional. Si current person es null se dibuja lo de abajo. Si no,*/}
          {/*se dibuja "Por favor pulse un contacto" ver más abajo.*/}
          {currentPerson ? (
            <div>
              <h4>Contacto</h4>
              <div>
                <label>
                  <strong>Nombre:</strong>
                </label>{" "}
                {currentPerson.name}
              </div>
              <div>
                <label>
                  <strong>Apellido:</strong>
                </label>{" "}
                {currentPerson.surname}
              </div>

              <div>
                <label>
                  <strong>Direccion:</strong>
                </label>{" "}
                {currentPerson.address}
              </div>

              <div>
                <label>
                  <strong>Localidad:</strong>
                </label>{" "}
                {currentPerson.locality}
              </div>

              <div>
                <label>
                  <strong>Codigo postal:</strong>
                </label>{" "}
                {currentPerson.postalCode}
              </div>

              <div>
                <label>
                  <strong>Fecha de nacimiento:</strong>
                </label>{" "}
                {currentPerson.birthDate}
              </div>

              {/*<div>
                <label>
                  <strong>Status:</strong>
                </label>{" "}*/}
                {/* renderizado condicional */}
                {/*{currentTutorial.published ? "Published" : "Pending"}
              </div>*/}

              <Link
                //Como hemos incluido en el switch esta ruta, /tutorials/+id se renderizará el componente
                // agenda cuando se pulse el enlace.
                to={"/person/" + currentPerson.id}
                className="badge badge-warning"
              >
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