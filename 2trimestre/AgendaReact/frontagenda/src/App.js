import React from "react";
import { BrowserRouter as Router, Routes, Route, Link, useLocation } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
import PersonsList from "./components/persons.list";
import AddPerson from "./components/add.person";
import EditPerson from "./components/edit.person";
import TutorialsList from "./components/tutorials.from.person";
import SignIn from "./components/sign.in";
import SignUp from "./components/sign.up";

const menuItems = [
  { path: "/", icon: "☰", label: "Home", color: "#ff8c00" },
  { path: "/persons", icon: "📂", label: "Personas", color: "#f54888" },
  { path: "/add", icon: "📚", label: "Añadir", color: "#4343f5" },
  { path: "/edit", icon: "📋", label: "Editar", color: "#e0b115" },
  { path: "/tutorials", icon: "🖼️", label: "Tutoriales", color: "#65ddb7" }
];

const Navbar = () => {
  const location = useLocation();
  return (
    <menu className="menu">
      {menuItems.map((item, index) => (
        <Link key={index} to={item.path} className={`menu__item ${location.pathname === item.path ? "active" : ""}`} style={{ "--bgColorItem": item.color }}>
          <span className="icon">{item.icon}</span>
        </Link>
      ))}
      <div className="menu__border"></div>
    </menu>
  );
};

const App = () => {
  return (
    <Router>
      <Navbar />
      <div className="container mt-3">
        <Routes>
        <Route path="/add" element={<AddPerson />} />
          <Route path="/edit/:id" element={<EditPerson />} />
          <Route path="/tutorials/:id" element={<TutorialsList />} />
          <Route path="/signIn" element={<SignIn />} />
          <Route path="/signUp" element={<SignUp />} />
        </Routes>
      </div>
    </Router>
  );
};

export default App;


/**   <Route path="/persons" element={<PersonsList />} />
           */