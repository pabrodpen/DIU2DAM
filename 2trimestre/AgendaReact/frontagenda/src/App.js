import React, { useRef } from "react";
import { Routes, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";

import PersonsList from "./components/persons.list";
import AddPerson from "./components/add.person";
import EditPerson from "./components/edit.person";

function App() {
  const personsListRef = useRef(null); // Creamos la referencia

  const handleRemoveAllPersons = () => {
    if (personsListRef.current) {
      personsListRef.current.removeAllPersons(); // Llamamos correctamente la función
    }
  };

  return (
    <div>
      <nav className="navbar navbar-expand navbar-dark bg-dark">
        <div className="navbar-nav mr-auto">
          <li className="nav-item">
            <Link to={"/persons"} className="nav-link">
              Personas
            </Link>
          </li>
          <li className="nav-item">
            <Link to={"/add"} className="nav-link">
              Add
            </Link>
          </li>
          <li className="nav-item">
            <button className="nav-link btn btn-link" onClick={handleRemoveAllPersons}>
              Limpiar contactos
            </button>
          </li>
        </div>
      </nav>

      <div className="container mt-3">
        <Routes>
          <Route path="/" element={<PersonsList ref={personsListRef} />} />
          <Route path="/persons" element={<PersonsList ref={personsListRef} />} />
          <Route path="/add" element={<AddPerson />} />
          <Route path="/edit/:id" element={<EditPerson />} />
        </Routes>
      </div>
    </div>
  );
}

export default App;
