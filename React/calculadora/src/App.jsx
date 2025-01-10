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
    this.setState({ texto: math.evaluate(this.state.texto)});
  };

  escribir = (valor) => {
    this.setState({ texto: this.state.texto + valor });
  };

  limpiar = () => {
    this.setState({ texto: "" });
  };

  negativo=()=>{
    this.setState({texto: Math.evaluate(this.state.texto*(-1))})
  }


  render() {
    return (
      <>
        <h1>Calculadora en React</h1>
        <CalculadoraComponent
          texto={this.state.texto} 
          escribir={this.incrementar} 
          realizarOperacion={this.realizarOperacion}
          limpiar={this.limpiar}
          negativo={this.negativo} 
        />
      </>
    );
  }
}

export default App;