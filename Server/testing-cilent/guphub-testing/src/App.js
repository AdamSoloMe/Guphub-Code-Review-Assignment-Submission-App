import './App.css';
import ReactDOM from "react-dom/client";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { useState } from 'react';
import axios from 'axios';
import { useLocalState } from './utils/useLocalStorage';
import Dashboard from './Dashboard';

function App() {
  // const [username, setUsername] = useState('');
  // const [password, setPassword] = useState('');
  // const [accessToken, setAccessToken] = useLocalState('', 'access_token');
  // const [refreshToken, setRefreshToken] = useLocalState('', 'refresh_token'); // Add this line
  // const [loginPerformed, setLoginPerformed] = useState(false); // Track login state

  // const handleLogin = async (e) => {
  //   e.preventDefault();

  //   try {
  //     const response = await axios.post('/api/auth/login', {
  //       username: username,
  //       password: password,
  //     });

  //     setAccessToken(response.data.access_token);
  //     setRefreshToken(response.data.refresh_token); // Set the refresh token
  //     setLoginPerformed(true); // Mark login as performed

  //   } catch (error) {
  //     console.error('Login error:', error);
  //   }
  // };

  // const handleRegister = async (e) => {
  //   e.preventDefault();

  //   try {
  //     const response = await axios.post('/api/auth/register', {
  //       username: username,
  //       password: password,
  //     });

  //     console.log(response.data);
  //   } catch (error) {
  //     console.error('Registration error:', error);
  //   }
  // };

  // const handleRefresh = async () => {
  //   try {
  //     const response = await axios.post('/api/auth/refresh', {
  //       refresh_token: refreshToken, // Use the stored refresh token for refreshing
  //     });

  //     setAccessToken(response.data); // New access token
  //   } catch (error) {
  //     console.error('Token refresh error:', error);
  //   }
  // };

  // const handleLogout = () => {
  //   // Clear access token and refresh token from local storage
  //   setAccessToken('');
  //   setRefreshToken('');
  //   setLoginPerformed(false);
  // };


  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<div>home</div>} />
        <Route path="/dashboard" element={<Dashboard />} />
      </Routes>
    </BrowserRouter>

    // <div>
    //   <h2>Login</h2>
    //   <form onSubmit={handleLogin}>
    //     <input
    //       type="text"
    //       placeholder="Username"
    //       value={username}
    //       onChange={(e) => setUsername(e.target.value)}
    //     />
    //     <input
    //       type="password"
    //       placeholder="Password"
    //       value={password}
    //       onChange={(e) => setPassword(e.target.value)}
    //     />
    //     <button type="submit">Login</button>
    //   </form>
    //   <h2>Register</h2>
    //   <form onSubmit={handleRegister}>
    //     <input
    //       type="text"
    //       placeholder="Username"
    //       value={username}
    //       onChange={(e) => setUsername(e.target.value)}
    //     />
    //     <input
    //       type="password"
    //       placeholder="Password"
    //       value={password}
    //       onChange={(e) => setPassword(e.target.value)}
    //     />
    //     <button type="submit">Register</button>
    //   </form>
    //   <button onClick={handleRefresh} disabled={!accessToken}>
    //     Refresh Token
    //   </button>
    //   <button onClick={handleLogout} disabled={!loginPerformed}>
    //     Logout
    //   </button>
    //   <div>
    //     Access Token: {accessToken}
    //     <br />
    //     Refresh Token: {refreshToken}
    //   </div>
    // </div>
  );
}

export default App;
