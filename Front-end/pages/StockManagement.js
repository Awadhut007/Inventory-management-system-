import React, { useState, useEffect } from 'react';
import axios from 'axios';

function StockManagementPage() {
  const [products, setProducts] = useState([]);
  const [productId, setProductId] = useState('');
  const [quantity, setQuantity] = useState('');
  const [reason, setReason] = useState('');
  const [actionType, setActionType] = useState('add'); // 'add' or 'remove'
  const [message, setMessage] = useState('');

  const reasonOptions = {
    restock: "Restocking",
    damaged: "Damaged",
    manual: "Manual Deduction"
  };

  // Load products on mount
  useEffect(() => {
    axios.get('http://localhost:8080/api/products/all')
      .then(response => setProducts(response.data))
      .catch(error => console.error('Error fetching products:', error));
  }, []);

  // Handle stock update (add/remove)
  const handleStockAction = async (e) => {
    e.preventDefault();
    try {
      const url = `http://localhost:8080/api/stock/${actionType}`;
      const response = await axios.post(url, null, {
        params: {
          productId,
          quantity,
          reason
        }
      });
      alert(`Stock ${actionType}ed successfully.`);
    } catch (error) {
      console.error(error);
      alert(`Failed to ${actionType} stock.`);
    }
  };

  return (
    <div className="container mt-5">
      <h2>Stock Management</h2>

      <form onSubmit={handleStockAction} className="mb-4">
        <div className="form-group mb-2">
          <label>Product:</label>
          <select
            className="form-control"
            value={productId}
            onChange={(e) => setProductId(e.target.value)}
            required
          >
            <option value="">Select Product</option>
            {products.map(product => (
              <option key={product.id} value={product.id}>
                {product.name} (Stock: {product.quantity})
              </option>
            ))}
          </select>
        </div>

        <div className="form-group mb-2">
          <label>Quantity:</label>
          <input
            type="number"
            className="form-control"
            value={quantity}
            onChange={(e) => setQuantity(e.target.value)}
            required
          />
        </div>

        <div className="form-group mb-2">
          <label>Reason</label>
        <select
          className="form-control"
          value={reason}
          onChange={(e) => setReason(e.target.value)}
          required
        >
          <option value="">-- Select Reason --</option>
          {Object.entries(reasonOptions).map(([key, label]) => (
            <option key={key} value={label}>
              {label}
            </option>
          ))}
        </select>
        </div>

        <div className="form-group mb-3">
          <label>Action:</label>
          <select
            className="form-control"
            value={actionType}
            onChange={(e) => setActionType(e.target.value)}
          >
            <option value="add">Add Stock</option>
            <option value="remove">Remove Stock</option>
          </select>
        </div>

        <button type="submit" className="btn btn-primary">
          {actionType === 'add' ? 'Add Stock' : 'Remove Stock'}
        </button>
      </form>

      {message && <div className="alert alert-info">{message}</div>}
    </div>
  );
}

export default StockManagementPage;
