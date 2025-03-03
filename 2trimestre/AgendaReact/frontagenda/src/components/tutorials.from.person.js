import React, { useState, useEffect, forwardRef } from "react";
import TutorialDataService from "../services/tutorial.service";
import { useParams } from "react-router-dom";
import PersonDataService from "../services/person.service";
import defaultImage from "../assets/icon_imagen.png"; // Importamos la imagen por defecto

const TutorialsList = forwardRef((props, ref) => {
  const { id } = useParams();
  const [tutorials, setTutorials] = useState([]);
  const [currentTutorial, setCurrentTutorial] = useState(null);
  const [currentIndex, setCurrentIndex] = useState(-1);

  useEffect(() => {
    console.log("🔄 Recargando tutoriales para ID:", id);
    retrieveTutorialsFromPerson();
  
    // 🔥 Escuchar el evento de actualización de tutoriales
    const updateTutorialsListener = (event) => {
      if (event.detail === id) {
        console.log("🔁 Recargando tutoriales después de edición");
        retrieveTutorialsFromPerson();
      }
    };
  
    window.addEventListener("updateTutorials", updateTutorialsListener);
  
    return () => {
      window.removeEventListener("updateTutorials", updateTutorialsListener);
    };
  }, [id]);
  
  


  const retrieveTutorialsFromPerson = async () => {
    try {
      const tutorialsResponse = await TutorialDataService.getAll();
      const allTutorials = tutorialsResponse.data || [];
      const publishedTutorials = allTutorials.filter((tutorial) => tutorial.published) || [];
      const personResponse = await PersonDataService.get(id);
      const person = personResponse.data;
  
      // 🔥 Asegurarse de que person.tutoriales es un array antes de filtrar
      const personTutorials = Array.isArray(person.tutoriales) ? person.tutoriales : [];
  
      // 🔹 Filtrar tutoriales que coincidan con los del contacto
      const tutorialsFromPerson = publishedTutorials.filter((tutorial) =>
        personTutorials.includes(tutorial.id)
      );
  
      setTutorials(tutorialsFromPerson);
    } catch (error) {
      console.error("❌ Error al recuperar tutoriales:", error);
      setTutorials([]); // 🔥 Si hay un error, se asigna un array vacío
    }
  };
  

  return (
    <div className="contacts-container">
      {/* 🔹 Lista de tutoriales */}
      <div className="list-container">
        <h4>Lista de tutoriales</h4>
        <ul className="list-group">
          {tutorials &&
            tutorials.map((tutorial, index) => (
              <li
                className={`list-group-item ${index === currentIndex ? "active" : ""}`}
                onClick={() => {
                  setCurrentTutorial(tutorial);
                  setCurrentIndex(index);
                }}
                key={index}
              >
                {tutorial.title}
              </li>
            ))}
        </ul>
      </div>

      {/* 🔹 Detalles del tutorial */}
      <div className="details-container">
        {currentTutorial ? (
          <div>
            <h4>Tutorial</h4>
            <div>
              <label>
                <strong>Título:</strong>
              </label>{" "}
              {currentTutorial.title}
            </div>
            <div>
              <label>
                <strong>Descripción:</strong>
              </label>{" "}
              {currentTutorial.description}
            </div>
            <div>
              <label>
                <strong>Publicado:</strong>
              </label>{" "}
              {currentTutorial.published ? "Published" : "Pending"}
            </div>

            {/* Imagen del tutorial o imagen por defecto */}
            <div>
              <img
                src={currentTutorial.url_image ? currentTutorial.url_image : defaultImage}
                alt="Tutorial"
                width="200"
              />
            </div>
          </div>
        ) : (
          <div>
            <br />
            <p>Selecciona un tutorial</p>
          </div>
        )}
      </div>
    </div>
  );
});

export default TutorialsList;
