import React, { useState, useEffect } from "react";
import TutorialDataService from "../services/tutorial.service";

const EditTutorial = () => {
  const id = window.location.pathname.split('/')[2];

  const [tutorial, setTutorial] = useState({
    id: id,
    title: '',
    description: '',
    published: false,
  });

  useEffect(() => {
    TutorialDataService.get(id)
      .then(response => {
        setTutorial(response.data);
      });
  }, [id]);

  const editTutorial = (e) => {
    e.preventDefault();
    TutorialDataService.update(id, tutorial).then(() => {
      console.log("Tutorial actualizado correctamente");
    });
  };

  const setTitle = (e) => {
    setTutorial({ ...tutorial, title: e.target.value });
  };

  const setDescription = (e) => {
    setTutorial({ ...tutorial, description: e.target.value });
  };

  const setPublished = (e) => {
    setTutorial({ ...tutorial, published: e.target.checked });
  };

  return (
    <div className="list row">
      <div className="col-md-8">
        <div className="input-group mb-3">
          <input
            type="text"
            className="form-control"
            placeholder="Title"
            value={tutorial.title}
            onChange={setTitle}
          />
          <input
            type="text"
            className="form-control"
            placeholder="Description"
            value={tutorial.description}
            onChange={setDescription}
          />
          <label>
            <input
              type="checkbox"
              checked={tutorial.published}
              onChange={setPublished}
            />
            Published
          </label>
          <div className="input-group-append">
            <button
              className="btn btn-outline-secondary"
              type="button"
              onClick={editTutorial}
            >
              Update Tutorial
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default EditTutorial;
