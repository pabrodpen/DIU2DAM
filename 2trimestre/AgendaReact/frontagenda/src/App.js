import React from "react";
import { Routes, Route } from "react-router-dom";
import Navbar from "./components/navbar";
import PersonsList from "./components/persons.list";
import AddPerson from "./components/add.person";
import EditPerson from "./components/edit.person";
import TutorialsList from "./components/tutorials.from.person";
import SignIn from "./components/sign.in";
import SignUp from "./components/sign.up";
import "./App.css";
import { AuthProvider } from "./components/auth.context";

const App = () => {
  return (
    <AuthProvider>
    <div className="app-container">
      <Navbar />
      <div className="content">
        <Routes>
          <Route path="/" element={<PersonsList />} />
          <Route path="/persons" element={<PersonsList />} />
          <Route path="/add" element={<AddPerson />} />
          <Route path="/edit/:id" element={<EditPerson />} />
          <Route path="/tutorials/:id" element={<TutorialsList />} />
          <Route path="/signIn" element={<SignIn />} />
          <Route path="/signUp" element={<SignUp />} />
        </Routes>
      </div>
    </div>
    </AuthProvider>
  );
};

export default App;
