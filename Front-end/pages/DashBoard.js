// src/pages/Dashboard.js
import React from 'react';

function Dashboard() {
  // Assuming the username is stored in localStorage after login
  const username = localStorage.getItem('username') || 'User';

  return (
    <div className="container text-center mt-5">
      <h2>Welcome {username} to Inventory Management System</h2>
    </div>
  );
}

export default Dashboard;
