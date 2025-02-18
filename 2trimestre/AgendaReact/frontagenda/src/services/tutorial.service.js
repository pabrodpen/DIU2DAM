import http from "../http-common.tutorials";

class TutorialDataService {
  getAll(id) {
    return http.get(`/tutorials/${id}`);
  }

  /*get(id) {
    return http.get(`/tutorials/${id}`);
  }*/


 findByTitle(title) {
     return http.get(`/tutorials/title/${title}`);
   }
}

export default new TutorialDataService();