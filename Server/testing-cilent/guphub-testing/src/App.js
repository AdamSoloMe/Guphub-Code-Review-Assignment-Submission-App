import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Login from './Login/Login';
import Register from './Register/Register';
import Dashboard from './Dashboard/Dashboard';
import PrivateRoute from './PrivateRoute/PrivateRoute';

function App() {
  return (
  

      <Routes>
        <Route path="/" element={<div>home</div>} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route
          path="/Dashboard"
          element={
            <PrivateRoute>
              <>
              <Dashboard />
              </>
            </PrivateRoute>
          }
        />
      </Routes>
  );
}

export default App;
