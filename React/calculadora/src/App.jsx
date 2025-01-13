import React, { Component } from 'react';
import './App.css';
import CalculadoraComponent from './components/CalculadoraComponent';
import * as math from 'mathjs';

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      texto: "",
    };
  }

  realizarOperacion = () => {
    try {
      this.setState({ texto: math.evaluate(this.state.texto).toString() });
    } catch (e) {
      this.setState({ texto: "Error" });
    }
  };

  escribir = (valor) => {
    this.setState({ texto: this.state.texto + valor });
  };

  limpiar = () => {
    this.setState({ texto: "" });
  };

  negativo = () => {
      this.setState({ texto: (-1 * parseFloat(this.state.texto)).toString() });
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
