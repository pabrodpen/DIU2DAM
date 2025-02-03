import React, { Component } from "react";
import TutorialDataService from "../services/tutorial.service";

export default class EditTutorial extends Component {
  constructor(props) {
    super(props);
    
    this.state = {
      Id: null, // 🔹 Inicializamos con valores predeterminados
      Title: "", 
      Description: "", 
      Status: false
    };
  }

  componentDidMount() {
    this.getTutorial(this.props.match.params.id);
  }

  getTutorial(id) {
    TutorialDataService.get(id)
      .then(response => {
        this.setState({
          Id: response.data.id,
          Title: response.data.title,
          Description: response.data.description,
          Status: response.data.published
        });
      })
      .catch(e => {
        console.error("Error al obtener tutorial:", e);
      });
  }

  onChangeTitle = (e) => {
    this.setState({ Title: e.target.value });
  };

  onChangeDescription = (e) => {
    this.setState({ Description: e.target.value });
  };

  onChangeStatus = (e) => {
    this.setState({ Status: e.target.checked });
  };

  updateTutorial = () => {
    const data = {
      title: this.state.Title,
      description: this.state.Description,
      published: this.state.Status
    };

    TutorialDataService.update(this.state.Id, data)
      .then(response => {
        console.log("Tutorial actualizado:", response.data);
      })
      .catch(e => {
        console.error("Error al actualizar tutorial:", e);
      });
  };

  render() {
    return (
      <div className="list row">
        <div className="col-md-8">
          <div className="input-group mb-3">
            <input
              type="text"
              className="form-control"
              placeholder="Title"
              value={this.state.Title}
              onChange={this.onChangeTitle}
            />
            <input
              type="text"
              className="form-control"
              placeholder="Description"
              value={this.state.Description}
              onChange={this.onChangeDescription} 
            />
            <label>
              <input
                type="checkbox"
                checked={this.state.Status}
                onChange={this.onChangeStatus} 
              />
              Published
            </label>
            <div className="input-group-append">
              <button
                className="btn btn-outline-secondary"
                type="button"
                onClick={this.updateTutorial} 
              >
                Update Tutorial
              </button>
            </div>
          </div>
        </div>
      </div>
    );
  }
}
