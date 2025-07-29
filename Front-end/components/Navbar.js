import React from 'react';
import { Link, useNavigate } from 'react-router-dom';

function Navbar() {
  const isLoggedIn = localStorage.getItem("isLoggedIn") === "true";
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.clear();
    navigate("/");
  };

  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-dark px-3">
      <Link className="navbar-brand" to="/">Inventory System</Link>
      <div className="collapse navbar-collapse">
        <ul className="navbar-nav ms-auto">
          {!isLoggedIn && (
            <>
              <li className="nav-item">
                <Link className="nav-link" to="/stock-details">Stock Details</Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link" to="/login">User Login</Link>
              </li>
            </>
          )}
          {isLoggedIn && (
            <>

                <li className="nav-item">
                    <Link className="nav-link" to="/billing">Add Product</Link>
                </li>
              <li className="nav-item">
                <Link className="nav-link" to="/add-product">Add Product</Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link" to="/products">Product List</Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link" to="/stock-management">Stock Management</Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link" to="/stock-history">Stock History</Link>
              </li>
              <li className="nav-item">
                <button className="btn btn-link nav-link" onClick={handleLogout}>Logout</button>
              </li>
            </>
          )}
        </ul>
      </div>
    </nav>
  );
}

export default Navbar;
