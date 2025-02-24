import http from "../http.common";

class ProductDataService {
  getAll() {
    return http.get("/products");
  }

  get(id) {
    return http.get(`/products/${id}`);
  }


  update(id, data) {
    return http.put(`/products/${id}`, data);
  }


}

export default new ProductDataService();