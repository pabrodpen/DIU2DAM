import React, { useRef, useState, useEffect } from "react";
import { Routes, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
import { getCurrentUser, auth } from "./firebase";
import { signOut } from "firebase/auth";

import PersonsList from "./components/persons.list";
import AddPerson from "./components/add.person";
import EditPerson from "./components/edit.person";
import TutorialsList from "./components/tutorials.from.person";
import SignUp from "./components/sign.up";
import SignIn from "./components/sign.in";

function App() {
  const personsListRef = useRef(null);
  const [user, setUser] = useState(null);

  useEffect(() => {
    getCurrentUser(setUser);
  }, []);

  const handleLogout = async () => {
    await signOut(auth);
    setUser(null);
  };

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
        <div className="navbar-nav ml-auto">
          {user ? (
            <>
              <li className="nav-item">
                <span className="nav-link">👤 {user.displayName}</span>
              </li>
              <li className="nav-item">
                <button className="btn btn-danger btn-sm" onClick={handleLogout}>
                  Logout
                </button>
              </li>
            </>
          ) : (
            <>
              <li className="nav-item">
                <Link to={"/signUp"} className="nav-link">
                  Registro
                </Link>
              </li>
              
            </>
          )}
        </div>
      </nav>

      <div className="container mt-3">
        <Routes>
          <Route path="/" element={<PersonsList ref={personsListRef} />} />
          <Route path="/persons" element={<PersonsList ref={personsListRef} />} />
          <Route path="/add" element={<AddPerson />} />
          <Route path="/edit/:id" element={<EditPerson />} />
          <Route path="/tutorials/:id" element={<TutorialsList />} />
          <Route path="/signUp" element={<SignUp />} />
          <Route path="/signIn" element={<SignIn />} />
        </Routes>
      </div>
    </div>
  );
}

export default App;
