import React from 'react';
import { Routes, Route, Link } from 'react-router-dom';
import Login from './Login/Login';
import Register from './Register/Register';
import Dashboard from './Dashboard';
import PrivateRoute from './PrivateRoute/PrivateRoute';

function App() {
  return (
    <div>
      <nav style={{ backgroundColor: '#333', color: 'white', padding: '10px 0' }}>
        <ul style={{ listStyle: 'none', display: 'flex', justifyContent: 'center', alignItems: 'center', margin: 0, padding: 0 }}>
          <li style={{ margin: '0 10px' }}><Link to="/" style={{ color: 'white', textDecoration: 'none' }}>Home</Link></li>
          <li style={{ margin: '0 10px' }}><Link to="/login" style={{ color: 'white', textDecoration: 'none' }}>Login</Link></li>
          <li style={{ margin: '0 10px' }}><Link to="/register" style={{ color: 'white', textDecoration: 'none' }}>Register</Link></li>
          {/* Add more navigation links if needed */}
        </ul>
      </nav>

      <Routes>
        <Route path="/" element={<div>home</div>} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        {/*<!-- Runs up to -->*/}
        <Route path="/Dashboard" element={<PrivateRoute component={<Dashboard />} />} />
      </Routes>
    </div>
  );
}

export default App;
