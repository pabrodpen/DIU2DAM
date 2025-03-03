import React, { useState, useEffect } from "react";
import PersonDataService from "../services/person.service";
import { useParams, useNavigate } from "react-router-dom";
import MultiSelectDropdown from "./button.multiple.selection";

const EditPerson = () => {
  const { id } = useParams();
  const navigate = useNavigate();

  const [person, setPerson] = useState({
    nombre: "",
    apellidos: "",
    calle: "",
    ciudad: "",
    codigoPostal: "",
    fechaNacimiento: "",
    tutoriales: [] // Guardaremos los IDs seleccionados
  });

  const [selectedTutorials, setSelectedTutorials] = useState([]); 

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await PersonDataService.get(id);
        if (response.data) {
          setPerson(response.data);
          setSelectedTutorials(response.data.tutoriales || []);
        }
      } catch (error) {
        console.error("❌ Error al obtener contacto:", error);
      }
    };

    fetchData();

    // 🔥 Escuchar el evento cuando se actualicen los tutoriales desde `TutorialsSelector.js`
    const handleTutorialsUpdated = () => {
      fetchData(); // Recarga los tutoriales automáticamente
    };

    window.addEventListener("tutorialsUpdated", handleTutorialsUpdated);

    return () => {
      window.removeEventListener("tutorialsUpdated", handleTutorialsUpdated);
    };
  }, [id]);

  const handleEditPerson = async (e) => {
    e.preventDefault();
  
    // 🔹 Validar que el código postal sea un número
    if (isNaN(person.codigoPostal) || String(person.codigoPostal).trim() === "") {
      alert("❌ El código postal debe contener solo números.");
      return;
    }
  
    // 🔹 Validar que la fecha de nacimiento no sea futura
    const fechaNacimiento = new Date(person.fechaNacimiento);
    const fechaHoy = new Date();
    fechaHoy.setHours(0, 0, 0, 0); // Eliminar horas para comparación exacta
  
    if (fechaNacimiento >= fechaHoy) {
      alert("❌ La fecha de nacimiento debe ser anterior a hoy.");
      return;
    }
  
    try {
      const updatedPerson = {
        ...person,
        tutoriales: selectedTutorials.map(tutorial => tutorial?.value || tutorial)
      };
  
      console.log("🚀 Enviando datos actualizados:", updatedPerson);
  
      await PersonDataService.update(id, updatedPerson);
  
      // 🔄 Obtener la persona actualizada
      const updatedPersonResponse = await PersonDataService.get(id);
      setPerson(updatedPersonResponse.data);
      setSelectedTutorials(updatedPersonResponse.data.tutoriales || []);
  
      alert("✅ Contacto actualizado correctamente!");
      navigate("/persons");
    } catch (error) {
      console.error("❌ Error al actualizar el contacto:", error);
    }
  };
  

  const handleChange = (e) => {
    const { name, value } = e.target;
    setPerson(prevPerson => ({ ...prevPerson, [name]: value }));
  };

  return (
    <div className="form-container">
      <h4>Editar Contacto</h4>

      <div className="form-group">
        <label>Nombre</label>
        <input type="text" className="form-control" name="nombre" value={person.nombre} onChange={handleChange} />
      </div>

      <div className="form-group">
        <label>Apellidos</label>
        <input type="text" className="form-control" name="apellidos" value={person.apellidos} onChange={handleChange} />
      </div>

      <div className="form-group">
        <label>Dirección</label>
        <input type="text" className="form-control" name="calle" value={person.calle} onChange={handleChange} />
      </div>

      <div className="form-group">
        <label>Localidad</label>
        <input type="text" className="form-control" name="ciudad" value={person.ciudad} onChange={handleChange} />
      </div>

      <div className="form-group">
        <label>Código Postal</label>
        <input type="text" className="form-control" name="codigoPostal" value={person.codigoPostal} onChange={handleChange} />
      </div>

      <div className="form-group">
        <label>Fecha de Nacimiento</label>
        <input type="date" className="form-control" name="fechaNacimiento" value={person.fechaNacimiento} onChange={handleChange} />
      </div>

      {/* 🔥 Selección de Tutoriales */}
      <div className="form-group">
        <label>Seleccionar Tutoriales</label>
        <MultiSelectDropdown
          selectedTutorials={selectedTutorials}
          setSelectedTutorials={setSelectedTutorials} 
        />
      </div>

      <button className="btn btn-primary mt-3" onClick={handleEditPerson}>
        Actualizar Contacto
      </button>
    </div>
  );
};

export default EditPerson;
