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

    // ✅ Guardar cambios en el contacto
    const handleSave = async () => {
        try {
            if (!person) return;
            const updatedPerson = {
                ...person,
                tutoriales: selectedTutorials.map(tutorial => tutorial.value), // Guardamos solo los IDs
            };

            await PersonDataService.update(person.id, updatedPerson);
            alert("Tutoriales actualizados correctamente!");
        } catch (error) {
            console.error("Error al actualizar tutoriales:", error);
        }
    };

    if (!person) {
        return <p>Cargando datos...</p>;
    }

    return (
        <div className="container mt-3">
            <h4>Seleccionar Tutoriales</h4>

            <Select
                isMulti
                options={tutorials.map(tutorial => ({ value: tutorial.id, label: tutorial.title }))}
                value={selectedTutorials}
                onChange={handleChange}
            />

            <button className="btn btn-primary mt-3" onClick={handleSave}>
                Guardar Selección
            </button>
        </div>
    );
};

export default TutorialsSelector;
