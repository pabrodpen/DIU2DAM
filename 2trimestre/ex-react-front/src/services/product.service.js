import http from "../http-common";

class TutorialDataService {
  getAll() {
    return http.get("/products");
  }

  get(id) {
    return http.get(`/products/${id}`);
  }

  create(data) {
    return http.post("/products", data);
  }

  update(id, data) {
    return http.put(`/producta/${id}`, data);
  }

  delete(id) {
    return http.delete(`/products/${id}`);
  }


 findByName(name) {
     return http.get(`/products/name/${title}`);
   }
}

export default new TutorialDataService();