import React, { useState, useEffect } from "react";
import Select from "react-select";
import TutorialDataService from "../services/tutorial.service";
import PersonDataService from "../services/person.service";
import { useParams } from "react-router-dom";

const TutorialsSelector = () => {
    const { id } = useParams();
    const [tutorials, setTutorials] = useState([]);
    const [selectedTutorials, setSelectedTutorials] = useState([]);
    const [person, setPerson] = useState(null);

    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = async () => {
        try {
            // ✅ Obtener todos los tutoriales
            const tutorialsResponse = await TutorialDataService.getAll();
            const allTutorials = tutorialsResponse.data || [];

            // ✅ Obtener datos del contacto
            const personResponse = await PersonDataService.get(id);
            const personData = personResponse.data || {};

            // 🚀 Asegurarse de que siempre sea un array antes de usar includes
            const tutorialesArray = Array.isArray(personData.tutoriales) ? personData.tutoriales : [];

            setPerson(personData);
            setTutorials(allTutorials);

            // ✅ Filtrar tutoriales seleccionados con una verificación extra
            const selected = allTutorials
                .filter(tutorial => tutorialesArray.includes(tutorial.id)) // 🔥 YA NO FALLARÁ
                .map(tutorial => ({ value: tutorial.id, label: tutorial.title }));

            setSelectedTutorials(selected);
        } catch (error) {
            console.error("Error al obtener los datos:", error);
        }
    };

    // ✅ Manejar cambio en selección de tutoriales
    const handleChange = (selectedOptions) => {
        setSelectedTutorials(selectedOptions);
    };

    const handleSave = async () => {
        try {
            if (!person) return;
    
            const updatedPerson = {
                ...person,
                tutoriales: selectedTutorials.map(tutorial => tutorial.value), // 🔥 Guardamos solo los IDs
            };
    
            console.log("🚀 Enviando datos actualizados:", updatedPerson);
    
            await PersonDataService.update(person.id, updatedPerson);
    
            // 🔄 Volver a obtener los datos actualizados después de guardar
            const updatedPersonResponse = await PersonDataService.get(person.id);
            setPerson(updatedPersonResponse.data);
    
            alert("✅ Tutoriales actualizados correctamente!");
    
            // 🔥 Emitir evento global para notificar a `EditPerson.js`
            window.dispatchEvent(new Event("tutorialsUpdated"));
    
        } catch (error) {
            console.error("❌ Error al actualizar tutoriales:", error);
        }
    };
    
      

    if (!person) {
        return <p>Cargando datos...</p>;
    }

    return (
        <div className="container mt-3">

            <Select
  isMulti
  options={tutorials.map(tutorial => ({ value: tutorial.id, label: tutorial.title }))}
  value={selectedTutorials}
  onChange={handleChange}
  styles={{
    control: (base) => ({
      ...base,
      background: "#333", 
      color: "white",
      border: "1px solid #777",
      borderRadius: "5px",
      padding: "5px"
    }),
    menu: (base) => ({
      ...base,
      background: "#222",
      color: "white",
    }),
    multiValue: (base) => ({
      ...base,
      background: "#169c78", 
      color: "white",
      borderRadius: "15px",
      padding: "4px 8px"
    }),
    multiValueLabel: (base) => ({
      ...base,
      color: "white"
    }),
    multiValueRemove: (base) => ({
      ...base,
      color: "#ff6b6b",
      ":hover": { background: "#ff4d4d", color: "white" }
    })
  }}
/>

            <button className="btn btn-primary mt-3" onClick={handleSave}>
                Guardar Selección
            </button>
        </div>
    );
};

export default TutorialsSelector;
