import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import PersonDataService from "../services/person.service";
import TutorialDataService from "../services/tutorial.service"; // 🔥 Importamos servicio de tutoriales
import MultiSelectDropdown from "./button.multiple.selection";
import "../App.css";

const AddPerson = () => {
  const [person, setPerson] = useState({
    nombre: "",
    apellidos: "",
    calle: "",
    ciudad: "",
    codigoPostal: "",
    fechaNacimiento: "",
    tutoriales: [],
  });

  const [tutorials, setTutorials] = useState([]);
  const [selectedTutorials, setSelectedTutorials] = useState([]);
  const navigate = useNavigate();

  // 🔹 Obtener tutoriales publicados al cargar el componente
  useEffect(() => {
    const fetchTutorials = async () => {
      try {
        const response = await TutorialDataService.getAll();
        const publishedTutorials = response.data.filter(tutorial => tutorial.published);
        setTutorials(publishedTutorials);
      } catch (error) {
        console.error("❌ Error al obtener tutoriales:", error);
      }
    };

    fetchTutorials();
  }, []);

  const onChange = (e) => {
    const { name, value } = e.target;
    setPerson({ ...person, [name]: value });
  };

  const formatDate = (dateString) => {
    if (!dateString) return "";
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, "0");
    const day = String(date.getDate()).padStart(2, "0");
    return `${year}-${month}-${day}`;
  };

  const addPerson = () => {
    const fechaFormateada = formatDate(person.fechaNacimiento);

    // 🔹 Validaciones antes de agregar contacto
    if (isNaN(person.codigoPostal) || person.codigoPostal.trim() === "") {
      alert("❌ El código postal debe contener solo números.");
      return;
    }

    const fechaNacimiento = new Date(person.fechaNacimiento);
    const fechaHoy = new Date();
    fechaHoy.setHours(0, 0, 0, 0);

    if (fechaNacimiento >= fechaHoy) {
      alert("❌ La fecha de nacimiento debe ser anterior a hoy.");
      return;
    }

    const data = {
      ...person,
      codigoPostal: parseInt(person.codigoPostal, 10),
      fechaNacimiento: fechaFormateada,
      tutoriales: selectedTutorials.map(tutorial => tutorial.value), // 🔥 Guardamos solo los IDs
    };

    PersonDataService.create(data)
      .then((response) => {
        console.log("✅ Contacto agregado:", response.data);
        alert("✅ Contacto agregado correctamente!");
        navigate("/persons");
      })
      .catch((e) => {
        console.error("❌ Error al agregar contacto:", e);
      });
  };

  return (
    <div className="form-container">
      <h4>Añadir Contacto</h4>

      <div className="form-group">
        <label>Nombre</label>
        <input type="text" name="nombre" value={person.nombre} onChange={onChange} />
      </div>

      <div className="form-group">
        <label>Apellido</label>
        <input type="text" name="apellidos" value={person.apellidos} onChange={onChange} />
      </div>

      <div className="form-group">
        <label>Dirección</label>
        <input type="text" name="calle" value={person.calle} onChange={onChange} />
      </div>

      <div className="form-group">
        <label>Localidad</label>
        <input type="text" name="ciudad" value={person.ciudad} onChange={onChange} />
      </div>

      <div className="form-group">
        <label>Código Postal</label>
        <input type="text" name="codigoPostal" value={person.codigoPostal} onChange={onChange} />
      </div>

      <div className="form-group">
        <label>Fecha de Nacimiento</label>
        <input type="date" name="fechaNacimiento" value={person.fechaNacimiento} onChange={onChange} />
      </div>

      {/* 🔥 Selección de Tutoriales Publicados */}
      <div className="form-group">
        <label>Seleccionar Tutoriales</label>
        <MultiSelectDropdown
          selectedTutorials={selectedTutorials}
          setSelectedTutorials={setSelectedTutorials}
          tutorials={tutorials.map(tutorial => ({ value: tutorial.id, label: tutorial.title }))}
        />
      </div>

      <button className="btn btn-primary mt-3" onClick={addPerson}>
        Añadir Contacto
      </button>
    </div>
  );
};

export default AddPerson;
