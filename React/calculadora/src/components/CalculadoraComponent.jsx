import React, { Component } from 'react';
import '../styles/style.css'; 

class CalculadoraComponent extends Component {
  render() {
    const { texto, realizarOperacion, limpiar, escribir, negativo } = this.props;

    return (
      <div className="calculadora-container">
        <div className="pantalla">
          <input className="pantalla-input" value={texto} readOnly />
        </div>

        <div className="botones-grid">
          <button className="boton gris" onClick={limpiar}>AC</button>
          <button className="boton gris" onClick={negativo}>+/-</button>
          <button className="boton gris" onClick={() => escribir('%')}>%</button>
          <button className="boton naranja" onClick={() => escribir('/')}>/</button>

          <button className="boton" onClick={() => escribir('7')}>7</button>
          <button className="boton" onClick={() => escribir('8')}>8</button>
          <button className="boton" onClick={() => escribir('9')}>9</button>
          <button className="boton naranja" onClick={() => escribir('*')}>*</button>

          <button className="boton" onClick={() => escribir('4')}>4</button>
          <button className="boton" onClick={() => escribir('5')}>5</button>
          <button className="boton" onClick={() => escribir('6')}>6</button>
          <button className="boton naranja" onClick={() => escribir('-')}>-</button>

          <button className="boton" onClick={() => escribir('1')}>1</button>
          <button className="boton" onClick={() => escribir('2')}>2</button>
          <button className="boton" onClick={() => escribir('3')}>3</button>
          <button className="boton naranja" onClick={() => escribir('+')}>+</button>

          <button className="boton grande" onClick={() => escribir('0')}>0</button>
          <button className="boton" onClick={() => escribir('.')}>.</button>
          <button className="boton naranja" onClick={realizarOperacion}>=</button>
        </div>
      </div>
    );
  }
}

export default CalculadoraComponent;
