import React, { useState, useEffect, forwardRef, useImperativeHandle } from "react";
import TutorialDataService from "../services/tutorial.service";
import { Link } from "react-router-dom";
import { useParams, useNavigate } from "react-router-dom";
import PersonDataService from "../services/person.service";


const TutorialsList = forwardRef((props, ref) => {
const { id } = useParams();
  const [tutorials, setTutorials] = useState([]);
  const [currentTutorial, setCurrentTutorial] = useState(null);
  const [currentIndex, setCurrentIndex] = useState(-1);


  useEffect(() => {
    console.log("ID recibido en TutorialsList:", id); 
    retrieveTutorialsFromPerson();
  }, []);

  const retrieveTutorialsFromPerson = async () => {
    const tutorialsresponse = await TutorialDataService.getAll();
    const alltutorials = tutorialsresponse.data || [];
    const publishedTutorials = alltutorials.filter(tutorial => tutorial.published)|| [];
    const personResponse = await PersonDataService.get(id);
    const person = personResponse.data

    
      

      // Filtrar tutoriales que coincidan con los del contacto
      const tutorialsFromPerson = publishedTutorials.filter(tutorial =>
          person.tutoriales.includes(tutorial.id) || []
      );

      setTutorials(tutorialsFromPerson);
    

    
  };

  const refreshList = () => {
    retrieveTutorialsFromPerson();
    setCurrentTutorial(null);
    setCurrentIndex(-1);
  };

 

  return (
    <div className="list row">
      <div className="col-md-8">
        <div className="input-group mb-3">
        </div>
      </div>
      <div className="col-md-6">
        <h4>Lista de tutoriales</h4>
        <ul className="list-group">
          {tutorials &&
            tutorials.map((tutorial, index) => (
              <li
                className={"list-group-item " + (index === currentIndex ? "active" : "")}
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
      <div className="col-md-6">
        {currentTutorial ? (
          <div>
            <h4>Tutorial</h4>
            <div>
              <label>
                <strong>Titulo:</strong>
              </label>{" "}
              {currentTutorial.title}
            </div>
            <div>
              <label>
                <strong>Descripcion:</strong>
              </label>{" "}
              {currentTutorial.description}
            </div>
            <div>
                <label>
                    <strong>Publicado:</strong>
                </label>{" "}
                {currentTutorial.published ? "Published" : "Pending"}
            </div>
            
            <div>
            <img src={currentTutorial.url_image} alt="Tutorial" width="200" />
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
