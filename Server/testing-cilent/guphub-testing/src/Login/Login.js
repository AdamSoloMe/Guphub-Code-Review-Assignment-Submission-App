import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useLocalState } from '../utils/useLocalStorage';
import { useNavigate } from 'react-router-dom';
import jwtDecode from 'jwt-decode';
function Login() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const [accessToken, setAccessToken] = useLocalState('', 'access_token');
    const [refreshToken, setRefreshToken] = useLocalState('', 'refresh_token');
    const [loginPerformed, setLoginPerformed] = useState(false);
    const navigate = useNavigate();
    

    const navigateToDashboard = () => {
        navigate('/Dashboard');
    };

    useEffect(() => {
        // Update the loginPerformed state when access token is updated
        setLoginPerformed(Boolean(accessToken));
        if (loginPerformed && accessToken !== '') {
            navigateToDashboard();
        }
    }, [accessToken, loginPerformed]);

    useEffect(() => {
        // Update the display when tokens change
        console.log('Access Token:', accessToken);
        console.log('Refresh Token:', refreshToken);
    }, [accessToken, refreshToken]);

    const isAccessTokenExpired = (token) => {
        if (!token) {
            return true; // If no token is provided, consider it expired
        }

        try {
            const decodedToken = jwtDecode(token);
            const currentTime = Date.now() / 1000; // Current time in seconds

            return decodedToken.exp < currentTime; // Compare expiration time with current time
        } catch (error) {
            console.error('Token decoding error:', error);
            return true; // If token decoding fails, consider it expired
        }
    };

    const handleLogin = async (e) => {
        e.preventDefault();

        try {
            const response = await axios.post('/api/auth/login', {
                username: username,
                password: password,
            });
            if (username !== "" && password !== "") {
            setAccessToken(response.data.access_token);
            setRefreshToken(response.data.refresh_token);
            setLoginPerformed(true);
         if (isAccessTokenExpired(accessToken)) {
                    handleRefresh();
                }
    
           
            }

        } catch (error) {
            console.error('Login error:', error);
        }
       
    };

    const handleRefresh = async () => {
        try {
            const response = await axios.post('/api/auth/refresh', {
                refresh_token: refreshToken,
            });

            setAccessToken(response.data); // New access token
        } catch (error) {
             if (error.response && error.response.status === 401) {
                // Handle expired refresh token here
                alert("Your session has expired. Please log in again.");
                localStorage.removeItem('access_token');
                localStorage.removeItem('refresh_token');
                setAccessToken('');
                setRefreshToken('');
                setLoginPerformed(false);
            } else {
                console.error('Token refresh error:', error);
            }
        }
    };

    const handleLogout = () => {
        // Clear access token and refresh token from local storage
        localStorage.removeItem('access_token');
        localStorage.removeItem('refresh_token');
        setAccessToken();
        setRefreshToken();
        setLoginPerformed(false);
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
                    <div>
                        Access Token: {accessToken}
                        <br />
                        Refresh Token: {refreshToken}
                        <br />
                {loginPerformed ? <button onClick={handleLogout}>Logout</button> : 'No users logged in'}
                        <button onClick={handleRefresh} disabled={!accessToken}>
                            Generate Refresh Token
                        </button>
                    </div>
                </div>
    
    );
}

export default Login;
