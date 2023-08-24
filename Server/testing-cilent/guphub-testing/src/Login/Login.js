import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useLocalState } from '../utils/useLocalStorage';
import Logout from '../Logout/Logout';

function Login() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [accessToken, setAccessToken] = useLocalState('', 'access_token');
    const [refreshToken, setRefreshToken] = useLocalState('', 'refresh_token');
    const [loginPerformed, setLoginPerformed] = useState(false);
    const [isRegistered, setIsRegistered] = useState(false);

    const handleLogin = async (e) => {
        e.preventDefault();

        try {
            const response = await axios.post('/api/auth/login', {
                username: username,
                password: password,
            });

            setAccessToken(response.data.access_token);
            setRefreshToken(response.data.refresh_token);
            setLoginPerformed(true);
            handleCheckRegistration(); // Check user registration after login

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
            console.error('Token refresh error:', error);
        }
    };

    const handleLogout = () => {
        // Clear access token and refresh token from local storage
        setAccessToken('');
        setRefreshToken('');
        setLoginPerformed(false);
        setIsRegistered(false); // Reset registration status on logout
    };

    const handleCheckRegistration = async () => {
        try {
            const response = await axios.get(`/api/auth/check-registration/${username}`);
            setIsRegistered(response.data);
        } catch (error) {
            console.error('Registration status check error:', error);
        }
    };

    return (


                <div>
                    <h2>Login</h2>
                    <form onSubmit={handleLogin}>
                        {/* ... (input fields and login button) */}
                    </form>
                    <div>
                        Access Token: {accessToken}
                        <br />
                        Refresh Token: {refreshToken}
                        <br />
                        {loginPerformed ? <Logout onLogout={handleLogout} /> : 'No users logged in'}
                        <button onClick={handleRefresh} disabled={!accessToken}>
                            Generate Refresh Token
                        </button>
                    </div>
                    <div>
                        {loginPerformed ? (isRegistered ? 'User is registered' : 'User is not registered') : ''}
                    </div>
                    <div>
                        <button onClick={handleCheckRegistration}>
                            Check Registration
                        </button>
                    </div>
                </div>
    
    );
}

export default Login;
