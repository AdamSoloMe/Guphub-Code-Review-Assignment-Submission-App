import React, { useEffect, useState } from 'react';
import { Navigate } from 'react-router-dom';
import { useLocalState } from '../utils/useLocalStorage';
import axios from 'axios';

function PrivateRoute({ component }) {
    
    const [accessToken] = useLocalState('jiuvv', 'access_token');
    const [isRegistered, setIsRegistered] = useState(false);

   useEffect(() => {
    //checkUserRegistration();
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
        <>
               {accessToken ? (
                    isRegistered ? (
                       {...component }// Rendering the Dashboard component directly here
                    ) : (
                        <Navigate to="/register" />
                    )
                ) : (
                    <Navigate to="/login" />
                )
               }
        </>
        
    );
}

export default PrivateRoute;
