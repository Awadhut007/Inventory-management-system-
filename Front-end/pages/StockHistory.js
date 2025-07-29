import React, { useEffect, useState } from 'react';
import axios from 'axios';

function StockHistoryPage() {
  const [reason, setReason] = useState('');
  const [reasons] = useState(['Restocking', 'Damaged', 'Manual Deduction']); // Add more if needed
  const [stockHistory, setStockHistory] = useState([]);

  // Fetch stock history by selected reason
  useEffect(() => {
    if (reason) {
      axios.get(`http://localhost:8080/api/stock/by-reason/${reason}`)
        .then(response => {
          setStockHistory(response.data);
        })
        .catch(error => {
          console.error('Error fetching stock history:', error);
          setStockHistory([]);
        });
    }
  }, [reason]);

  return (
    <div className="container mt-4">
      <h2 className="mb-4">Stock History by Reason</h2>

      <div className="form-group mb-3">
        <label htmlFor="reasonSelect">Select Reason:</label>
        <select
          className="form-control"
          id="reasonSelect"
          value={reason}
          onChange={e => setReason(e.target.value)}
        >
          <option value="">-- Select Reason --</option>
          {reasons.map((r, index) => (
            <option key={index} value={r}>{r}</option>
          ))}
        </select>
      </div>

      {reason && stockHistory.length > 0 ? (
      <table className="table table-striped">
        <thead>
          <tr>
            <th>Product Name</th>
            <th>Quantity</th>
            <th>Action</th>
            <th>Reason</th>
            <th>Timestamp</th>
          </tr>
        </thead>
        <tbody>
          {stockHistory.map((item, index) => (
            <tr key={index}>
              <td>{item.product?.name || 'N/A'}</td>
              <td>{item.quantity}</td>
              <td>{item.action}</td>
              <td>{item.reason}</td>
              <td>{item.timestamp}</td>
            </tr>
          ))}
        </tbody>
      </table>
        
      ) : reason && stockHistory.length === 0 ? (
        <p>No stock history found for reason: <strong>{reason}</strong></p>
      ) : null}
    </div>
  );
}

export default StockHistoryPage;
