import React, { Component, useState } from 'react';
import './App.css';
import CalculadoraComponent from './components/CalculadoraComponent';
import * as math from 'mathjs';

const App=()=>{
/*useState es un Hook de React que te permite agregar y manejar un estado en un componente funcional.
El argumento que pasas a useState (en este caso, "") es el valor inicial del estado.*/ 

  const[texto,setTexto]=useState("")

  const realizarOperacion=()=>{
    try{
      setTexto(math.evaluate(texto).toString())
    }catch{
      setTexto("Error")
    }
  }

  const escribir=(valor)=>{
    setTexto(texto+valor)
  }

  const limpiar=()=>{
    setTexto("")
  }

  const negativo=()=>{
    try{
      setTexto(-1 * parseFloat(texto).toString())
    }catch{
      setTexto("Error")
    }
  }




  return (
    <>
      <h1>Calculadora en React</h1>
      <CalculadoraComponent
        texto={texto}
        escribir={escribir}
        realizarOperacion={realizarOperacion}
        limpiar={limpiar}
        negativo={negativo}
      />
    </>
  );

}

export default App;

  
