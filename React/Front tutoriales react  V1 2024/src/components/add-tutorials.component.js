import React, { Component } from "react";
import TutorialDataService from "../services/tutorial.service";
import { Link } from "react-router-dom";

export default class AddTutorial extends Component {
  constructor(props) {
    super(props);
    
    this.state = {
      Id:null,
      Title: "", 
      Description: "", 
      Status: false
    };
  }
  //Cuando se carga el componente, se realiza la petición de tutoriales a la API
  //El método retrieveTutorials provoca la actualización del estado, y por tanto la re-renderización del componente
  componentDidMount() {
    this.retrieveTutorials();
  }

  onChangeSearchTitle(e) {
    const searchTitle = e.target.value;

    this.setState({
      searchTitle: searchTitle
    });
  }

  retrieveTutorials() {
    TutorialDataService.getAll()
      .then(response => {
        this.setState({
          tutorials: response.data
        });
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }

  
  searchTitle() {
    TutorialDataService.findByTitle(this.state.searchTitle)
      .then(response => {
        this.setState({
          tutorials: response.data
        });
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }

  addTutorial() {
    TutorialDataService.create(data)
  }

  render() {
    const { searchTitle, tutorials, currentTutorial, currentIndex } = this.state;
    //ponemos los distintos elementos del estado en variables para simplificar su acceso dentro del método
    return (
      <div className="list row">
        <div className="col-md-8">
          <div className="input-group mb-3">
            <input
              type="text"
              className="form-control"
              placeholder="Title"
              value={this.addTutorial.Title}
              onChange={this.setState({title: e.target.value})}
            />
            <input
              type="text"
              className="form-control"
              placeholder="Description"
              value={this.addTutorial.Description}
              onChange={this.setState({description: e.target.value})}
            />
            <input
              type="text"
              className="form-control"
              placeholder="Status"
              value={this.addTutorial.Status}
              onChange={this.setState({status: e.target.value})}
            />
            <div className="input-group-append">
              <button
                className="btn btn-outline-secondary"
                type="button"
                onClick={this.create}
              >
                Add Tutorial
              </button>
            </div>
          </div>
        </div>
       
        
      </div>
    );
  }
}
