import React, { Component } from "react";
import PersonDataService from "../services/agenda.service";

export default class AddPerson extends Component {
  constructor(props) {
    super(props);

    this.state = {
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

  formatDate = (dateString) => {
    if (!dateString) return ""; // Evita errores si la fecha es nula
  
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, "0"); // Asegura 2 dígitos
    const day = String(date.getDate()).padStart(2, "0"); // Asegura 2 dígitos
  
    return `${year}-${month}-${day}`;
  };
  

  addPerson = () => {
    const fechaFormateada = this.formatDate(this.state.Fecha_nacimiento);
  
    const data = {
      nombre: this.state.Nombre,
      apellidos: this.state.Apellido,
      calle: this.state.Direccion,
      ciudad: this.state.Localidad,
      codigoPostal: parseInt(this.state.Codigo_postal, 10),
      fechaNacimiento: fechaFormateada  // Fecha en formato YYYY-MM-DD
    };
  
    PersonDataService.create(data)
      .then(response => {
        console.log("Respuesta del backend:", response.data);
        this.setState({
          id: response.data.id,
          Nombre: response.data.nombre,
          Apellido: response.data.apellidos,
          Direccion: response.data.calle,
          Codigo_postal: response.data.codigoPostal,
          Localidad: response.data.ciudad,
          Fecha_nacimiento: response.data.fechaNacimiento
        });
      })
      .catch(e => {
        console.error("❌ Error al agregar contacto:", e);
      });
  };
  

  render() {
    return (
      <div className="container mt-3">
        <h4>Añadir Contacto</h4>
        <div className="form-group">
          <label>Nombre</label>
          <input
            type="text"
            className="form-control"
            placeholder="Nombre"
            value={this.state.Nombre}
            onChange={this.onChangeName}
          />
        </div>
        
        <div className="form-group">
          <label>Apellido</label>
          <input
            type="text"
            className="form-control"
            placeholder="Apellido"
            value={this.state.Apellido}
            onChange={this.onChangeSurname}
          />
        </div>

        <div className="form-group">
          <label>Dirección</label>
          <input
            type="text"
            className="form-control"
            placeholder="Dirección"
            value={this.state.Direccion}
            onChange={this.onChangeAddress}
          />
        </div>

        <div className="form-group">
          <label>Localidad</label>
          <input
            type="text"
            className="form-control"
            placeholder="Localidad"
            value={this.state.Localidad}
            onChange={this.onChangeCity}
          />
        </div>

        <div className="form-group">
          <label>Código Postal</label>
          <input
            type="text"
            className="form-control"
            placeholder="Código Postal"
            value={this.state.Codigo_postal}
            onChange={this.onChangePostalCode}
          />
        </div>

        <div className="form-group">
          <label>Fecha de Nacimiento</label>
          <input
            type="date"
            className="form-control"
            value={this.state.Fecha_nacimiento}
            onChange={this.onChangeBirthDate}
          />
        </div>

        <button className="btn btn-primary mt-3" onClick={this.addPerson}>
          Añadir Contacto
        </button>
      </div>
    );
  }
}
