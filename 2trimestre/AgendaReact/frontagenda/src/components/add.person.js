import React, { Component } from "react";
import PersonDataService from "../services/agenda.service";

export default class AddPerson extends Component {
  constructor(props) {
    super(props);
    
    this.state = {
      //Id: null,
      Nombre: "", 
      Apellido: "",
      Direccion: "", 
      Localidad: "",  
      Codigo_postal: "",
      Fecha_nacimiento: ""
    };
  }

  onChangeName = (e) => {
    this.setState({ Nombre: e.target.value });
  };

  onChangeSurname = (e) => {
    this.setState({ Apellido: e.target.value });
  };

  onChangeAddress = (e) => {
    this.setState({ Direccion: e.target.value });
  };

  onChangeCity = (e) => {
    this.setState({ Localidad: e.target.value });
  };

  onChangePostalCode = (e) => {
    this.setState({ Codigo_postal: e.target.value });
  };

  onChangeBirthDate = (e) => {
    this.setState({ Fecha_nacimiento: e.target.value });
  };

  addPerson = () => {
    const data = {
      nombre: this.state.Nombre,
      apellido: this.state.Apellido,
      direccion: this.state.Direccion,
      localidad: this.state.Localidad,
      codigo_postal: this.state.Codigo_postal,
      fecha_nacimiento: this.state.Fecha_nacimiento
    };

    PersonDataService.create(data)
      .then(response => {
        this.setState({
            id: response.data.id,
            Apellido: response.data.apellido,
            Direccion: response.data.direccion,
            Localidad: response.data.localidad,
            Codigo_postal: response.data.codigo_postal,
            Fecha_nacimiento: response.data.fecha_nacimiento
        });
      })
      .catch(e => {
        console.error("Error al agregar contacto:", e);
      });
  };

  render() {
    return (
      <div className="list row">
        <div className="col-md-8">
          <div className="input-group mb-3">
            <input
              type="text"
              className="form-control"
              placeholder="Nombre"
              value={this.state.Nombre}
              onChange={this.onChangeName}
            />
            <input
              type="text"
              className="form-control"
              placeholder="Apellido"
              value={this.state.Apellido}
              onChange={this.onChangeSurname} 
            />
            <label>
              <input
                type="text"
                className="form-control"
                placeholder="Direccion"
                value={this.state.Direccion}
                onChange={this.onChangeAddress} 
              />
            </label>

            <label>
              <input
                type="text"
                className="form-control"
                placeholder="Localidad"
                value={this.state.Localidad}
                onChange={this.onChangeCity} 
              />
            </label>

            <label>
              <input
                type="text"
                checked={this.state.Codigo_postal}
                placeholder="Codigo Postal"
                value={this.state.Codigo_postal}
                onChange={this.onChangePostalCode} 
              />
            </label>

            <label>
              <input
                type="date"
                value={this.state.Fecha_nacimiento}
                onChange={this.onChangeBirthDate} 
              />
              Fecha de Nacimiento
            </label>
            <div className="input-group-append">
              <button
                className="btn btn-outline-secondary"
                type="button"
                onClick={this.addPerson} 
              >
                Añadir contacto
              </button>
            </div>
          </div>
        </div>
      </div>
    );
  }
}