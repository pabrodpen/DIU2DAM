import React, { Component } from 'react';
//import '../styles/counterApp.css';

class CalculadoraComponent extends Component {
  render() {
    const { texto, realizarOperacion, limpiar, escribir, negativo } = this.props;

    return (
      <div className="container">
        {/* Pantalla de la calculadora */}
        <div className="row">
          <div className="col-12">
            <input value={texto} readOnly />
          </div>
        </div>

        {/* Botones organizados en una cuadrícula */}
        <div className="row text-center">
          <div className="col-3">
            <button  onClick={limpiar}>AC</button>
          </div>
          <div className="col-3">
            <button  onClick={negativo}>+/-</button>
          </div>
          <div className="col-3">
            <button  onClick={escribir}>%</button>
          </div>
          <div className="col-3">
            <button  onClick={escribir}>/</button>
          </div>
        </div>

        <div className="row">
          <div className="col-3">
            <button  onClick={escribir}>7</button>
          </div>
          <div className="col-3">
            <button  onClick={escribir}>8</button>
          </div>
          <div className="col-3">
            <button onClick={escribir}>9</button>
          </div>
          <div className="col-3">
            <button onClick={escribir}>*</button>
          </div>
        </div>

        <div className="row">
          <div className="col-3">
            <button onClick={escribir}>4</button>
          </div>
          <div className="col-3">
            <button onClick={escribir}>5</button>
          </div>
          <div className="col-3">
            <button onClick={escribir}>6</button>
          </div>
          <div className="col-3">
            <button onClick={escribir}>-</button>
          </div>
        </div>

        <div className="row">
          <div className="col-3">
            <button onClick={escribir}>1</button>
          </div>
          <div className="col-3">
            <button onClick={escribir}>2</button>
          </div>
          <div className="col-3">
            <button onClick={escribir}>3</button>
          </div>
          <div className="col-3">
            <button onClick={escribir}>+</button>
          </div>
        </div>

        <div className="row">
          <div className="col-6">
            <button onClick={escribir}>0</button>
          </div>
          <div className="col-3">
            <button onClick={escribir}>.</button>
          </div>
          <div className="col-3">
            <button onClick={realizarOperacion}>=</button>
          </div>
        </div>
      </div>
    );
  }
}

export default CalculadoraComponent;
