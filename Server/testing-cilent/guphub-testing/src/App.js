import logo from './logo.svg';
import './App.css';
import { useState, useEffect } from 'react';
import axios from 'axios';

function App() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [refreshToken, setRefreshToken] = useState('');
  const [accessToken, setAccessToken] = useState('');

  useEffect(() => {
    fetchTokens(); // Fetch tokens on component mount
  }, [accessToken]);

  const fetchTokens = async () => {
    try {
      const response = await axios.post('/api/auth/login', {
        username: 'your_username',
        password: 'your_password',
      });

      //setRefreshToken(response.data.refresh_token);
      setAccessToken(response.data.access_token);
    } catch (error) {
      console.error('Error fetching tokens:', error);
    }
  };

  const handleLogin = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post('/api/auth/login', {
        username: username,
        password: password,
      });

      setRefreshToken(response.data.refresh_token);
      setAccessToken(response.data.access_token);
    } catch (error) {
      console.error('Login error:', error);
    }
  };

  const handleRegister = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post('/api/auth/register', {
        username: username,
        password: password,
      });

      console.log(response.data);
    } catch (error) {
      console.error('Registration error:', error);
    }
  };

  const handleRefresh = async () => {
    try {
      const response = await axios.post('/api/auth/refresh', {
        refresh_token: refreshToken,
      });

      setAccessToken(response.data); // New access token
    } catch (error) {
      console.error('Token refresh error:', error);
    }
  };

  return (
    <div>
      <h2>Login</h2>
      <form onSubmit={handleLogin}>
        <input
          type="text"
          placeholder="Username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />
        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <button type="submit">Login</button>
      </form>
      <h2>Register</h2>
      <form onSubmit={handleRegister}>
        <input
          type="text"
          placeholder="Username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />
        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <button type="submit">Register</button>
      </form>
      <button onClick={handleRefresh} disabled={!refreshToken}>
        Refresh Token
      </button>
      <div>
        Access Token: {accessToken}
        <br />
        Refresh Token: {refreshToken}
      </div>
    </div>
  );
}

export default App;