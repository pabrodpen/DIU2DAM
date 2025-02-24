import http from "../http.common";

class MonedaDataService {
  getAllCurrencies() {
    return http.get("/products");
  }


  



  updateCurrency(id,data){
    return http.put(`/products/${id}`, data);
  }

  
}

export default new MonedaDataService();