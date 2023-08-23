
import './App.css';
import { useState } from 'react';
import axios from 'axios';
import { useLocalState } from './utils/useLocalStorage';

function App() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [accessToken, setAccessToken] = useLocalState('', 'access_token');
  const [refreshToken, setRefreshToken] = useLocalState('', 'refresh_token');

  const handleLogin = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post('/api/auth/login', {
        username: username,
        password: password,
      });

      setAccessToken(response.data.access_token);
      setRefreshToken(response.data.refresh_token);

    } catch (error) {
      console.error('Login error:', error);
    }
  };

  const handleRefresh = async () => {
    try {
      const response = await axios.post('/api/auth/refresh', {
        refresh_token: refreshToken,
      });

      setAccessToken(response.data);

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
      <button onClick={handleRefresh} disabled={!accessToken}>
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
