import React, { Component } from 'react';
import '../styles/counterApp.css';


class CalculadoraComponent extends Component {
  render() {
    //const { num, incrementar, decrementar, limpiar } = this.props;

    return (
      <div className="container-counter">
        <div className="counter">
          <h1>{num}</h1>
        </div>
        <div className="buttons">
          <button className='incButton' onClick={incrementar}>Incrementar</button>
          <button onClick={decrementar}>Decrementar</button>
          <button onClick={limpiar}>Resetear</button>
          <button onClick={limpiar}>Resetear</button>

          <button className='incButton' onClick={incrementar}>Incrementar</button>
          <button onClick={decrementar}>Decrementar</button>
          <button onClick={limpiar}>Resetear</button>
          <button onClick={limpiar}>Resetear</button>

          <button className='incButton' onClick={incrementar}>Incrementar</button>
          <button onClick={decrementar}>Decrementar</button>
          <button onClick={limpiar}>Resetear</button>
          <button onClick={limpiar}>Resetear</button>

          <button className='incButton' onClick={incrementar}>Incrementar</button>
          <button onClick={decrementar}>Decrementar</button>
          <button onClick={limpiar}>Resetear</button>
        </div>
      </div>
    );
  }
}

export default IncContador;