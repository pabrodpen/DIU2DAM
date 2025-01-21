import React, { Component } from 'react';
import './App.css';
import CalculadoraComponent from './components/FormularioComponent';
import FormularioComponent from './components/FormularioComponent';

class App extends Component {
  constructor() {
    super();
    this.state = {
      artista: "", 
      cancion:""
    };
  }

  // Función para realizar la operación matemática usando la API de mathjs
  buscar = () => {
    fetch(`http://api.mathjs.org/v4/?expr=${encodeURIComponent(this.state.texto)}`)
      .then((response) => {
        if (response.ok) {
          return response.text(); // Obtenemos el resultado como texto
        } else {
          throw new Error(response.statusText);
        }
      })
      .then((resultado) => {
        this.setState({ texto: resultado, resultadoMostrado: true }); // Actualizamos el estado con el resultado
      })
      .catch(() => {
        this.setState({ texto: "Error", resultadoMostrado: true }); // Mostramos error si algo falla
      });
  };




  render() {
    return (
      <>
        <h1>Buscador de canciones</h1>
        <FormularioComponent
          texto={this.state.texto}
          escribir={this.escribir}
          realizarOperacion={this.realizarOperacion}
          limpiar={this.limpiar}
          negativo={this.negativo}
        />
      </>
    );
  }
}

export default App;