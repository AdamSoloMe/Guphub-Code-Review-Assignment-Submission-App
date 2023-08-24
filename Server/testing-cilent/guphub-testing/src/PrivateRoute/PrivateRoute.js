import React, { useEffect, useState } from 'react';
import { Navigate } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import { useLocalState } from '../utils/useLocalStorage';
import axios from 'axios';

function PrivateRoute({ component }) {
    const [accessToken] = useLocalState('', 'access_token');
    const [isRegistered, setIsRegistered] = useState(false);
    const [isLoading, setIsLoading] = useState(true);
    const navigate = useNavigate();

    useEffect(() => {
        if (accessToken) {
            checkUserRegistration();
        } else {
            // If there's no access token, navigate to the login page
            setIsLoading(false);
        }
    }, []);


    const checkUserRegistration = async () => {
        try {
            const response = await axios.get('/api/auth/check-registration', {
                headers: { Authorization: `Bearer ${accessToken}` }
            });

            // If the response is 401 (Unauthorized), alert the user and navigate to the login page
            if (response.status === 401) {
                alert("Your session has expired. Please log in again.");
                localStorage.removeItem('access_token');
                localStorage.removeItem('refresh_token');
                navigate("/login");
                return;
            }

            setIsRegistered(response.data);
        } catch (error) {
            console.log('Registration status check error:', error);
            alert("Invalid Login attempt");
            localStorage.removeItem('access_token');
            localStorage.removeItem('refresh_token');
            navigate("/login");
            return;
            // Handle other errors, e.g., navigate to an error page
        }
        setIsLoading(false);
    };
    const handleLogout = () => {
        // Clear access token and refresh token from local storage
        localStorage.removeItem('access_token');
        localStorage.removeItem('refresh_token');
        navigate("/login")
    };

    return (
        <div>
        <>
            {isLoading ? (
                // Render a loading indicator or placeholder
                <div>Loading...</div>
            ) : accessToken && isRegistered ? (
                // Render the protected component
                { ...component }
            ) : (
                // Navigate to the login page
                <Navigate to="/login" />
            )}
        </>
            <button onClick={handleLogout}>Logout</button>
        </div>
    );
}

export default PrivateRoute;
