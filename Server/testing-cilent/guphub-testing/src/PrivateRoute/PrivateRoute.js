import React, { useEffect, useState } from 'react';
import { Route,Routes, Navigate } from 'react-router-dom';
import { useLocalState } from '../utils/useLocalStorage';
import axios from 'axios';

function PrivateRoute({ element: Element, ...rest }) {
    const [accessToken] = useLocalState('', 'access_token');
    const [isRegistered, setIsRegistered] = useState(false);

    useEffect(() => {
        checkUserRegistration();
    }, []);

    const checkUserRegistration = async () => {
        try {
            const response = await axios.get('/api/auth/check-registration');
            setIsRegistered(response.data);
        } catch (error) {
            console.error('Registration status check error:', error);
        }
    };

    return (
        <Routes>
        <Route
            {...rest}
            element={
                accessToken ? (
                    isRegistered ? (
                        <Element /> // Rendering the Dashboard component directly here
                    ) : (
                        <Navigate to="/register" />
                    )
                ) : (
                    <Navigate to="/login" />
                )
            }
        />
        </Routes>
    );
}

export default PrivateRoute;
