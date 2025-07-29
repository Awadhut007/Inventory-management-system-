// src/App.js
import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Navbar from './Components/Navbar';
import Home from './Pages/Home';
import ProductList from './Pages/ProductList';
import AddProduct from './Pages/AddProduct';
import UpdateProduct from './Pages/UpdateProduct';
import StockManagementPage from './Pages/StockManagement';
import StockHistoryPage from './Pages/StockHistory';
import StockDetailsPage from './Pages/StockDetailsPage';
import LoginPage from './Pages/LoginPage';
import RegisterPage from './Pages/RegisterPage';
import Dashboard from './Pages/DashBoard';
import BillingPage from './Pages/BillingPage';


function App() {
  return (
    <Router>
      <Navbar />
      <div className="container mt-4">
        <Routes>
          {/* Public routes */}
          <Route path="/" element={<Home />} />
          <Route path="/stock-details" element={<StockDetailsPage />} />
          <Route path="/login" element={<LoginPage />} />
          <Route path="/register" element={<RegisterPage />} />
           <Route path="/dashBoard" element={<Dashboard />} />
          {/* Protected routes for owner */}
          
          <Route path="/billing" element={<BillingPage />} />
          <Route path="/products" element={<ProductList />} />
          <Route path="/add-product" element={<AddProduct />} />
          <Route path="/update/:id" element={<UpdateProduct />} />
          <Route path="/stock-management" element={<StockManagementPage />} />
          <Route path="/stock-history" element={<StockHistoryPage />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
