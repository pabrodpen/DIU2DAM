import axios from "axios";

export default axios.create({
  baseURL: "http://tutorials/api/v1/",
  headers: {
    "Content-type": "application/json"
  }
});
