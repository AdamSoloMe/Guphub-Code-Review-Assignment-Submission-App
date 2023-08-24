import React from 'react';
import ReactDOM from 'react-dom';
import { Routes, Route,Link } from 'react-router-dom';
import Login from './Login/Login';
import Register from './Register/Register';
import Dashboard from './Dashboard';
import PrivateRoute from './PrivateRoute/PrivateRoute';

function App() {
  return (
    <div>
      <nav>
        <ul>
          <li><Link to="/">Home</Link></li>
          <li><Link to="/login">Login</Link></li>
          <li><Link to="/register">Register</Link></li>
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
