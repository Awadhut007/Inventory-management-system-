// src/services/productService.js
import axios from 'axios';

const BASE_URL = "http://localhost:8080/api/products";

const getAllProducts = () => axios.get(BASE_URL);

const productService = {
  getAllProducts,
};

export default productService;
