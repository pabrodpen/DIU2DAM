import React, { Component } from 'react';
import './App.css';
import CalculadoraComponent from './components/CalculadoraComponent';

class App extends Component {
  constructor() {
    super();
    this.state = {
      texto: "", // Estado para la pantalla
      resultadoMostrado: false, // Indica si el resultado está en pantalla
    };
  }

  // Función para realizar la operación matemática usando la API de mathjs
  realizarOperacion = () => {
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

  // Función para manejar la entrada del usuario
  escribir = (valor) => {
    if (this.state.resultadoMostrado) {
      this.setState({ texto: valor, resultadoMostrado: false }); // Limpia la pantalla si se muestra un resultado
    } else {
      this.setState({ texto: this.state.texto + valor }); // Agrega al texto existente
    }
  };

  // Función para limpiar la pantalla
  limpiar = () => {
    this.setState({ texto: "", resultadoMostrado: false });
  };

  // Función para cambiar el signo del número en pantalla
  negativo = () => {
    const texto = this.state.texto;
    if (texto) {
      this.setState({ texto: (-1 * parseFloat(texto)).toString() });
    }
  };

  render() {
    return (
      <>
        <h1>Calculadora en React</h1>
        <CalculadoraComponent
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
