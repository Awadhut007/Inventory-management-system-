import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';

const ProductList = () => {
  const [products, setProducts] = useState([]);

  const loadProducts = async () => {
    try {
      const result = await axios.get('http://localhost:8080/api/products/all');
      setProducts(result.data);
    } catch (error) {
      console.error('Error loading products:', error);
    }
  };

  useEffect(() => {
    loadProducts();
  }, []);

  const deleteProduct = async (id) => {
    if (window.confirm('Are you sure you want to delete this product?')) {
      try {
        await axios.delete(`http://localhost:8080/api/products/${id}`);
        loadProducts(); // Refresh list
      } catch (error) {
        console.error('Error deleting product:', error);
      }
    }
  };

  return (
    <div className="container mt-4">
      <h3>Product List</h3>
      <table className="table table-bordered table-striped">
        <thead className="table-dark">
          <tr>
            {/* <th>ID</th> */}
            <th>Name</th>
            <th>Category</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {products.length > 0 ? (
            products.map((prod) => (
              <tr key={prod.id}>
                {/* <td>{prod.id}</td> */}
                <td>{prod.name}</td>
                <td>{prod.category}</td>
                <td>₹{prod.price}</td>
                <td>{prod.quantity}</td>
                <td>
                  <Link to={`/update/${prod.id}`} className="btn btn-warning btn-sm me-2">Update</Link>
                  <button
                    className="btn btn-danger btn-sm"
                    onClick={() => deleteProduct(prod.id)}
                  >
                    Delete
                  </button>
                </td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="6" className="text-center">No products found</td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
};

export default ProductList;
