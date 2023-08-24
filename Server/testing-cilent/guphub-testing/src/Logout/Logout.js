// components/Logout.js
import React from 'react';
import { useLocalState } from '../utils/useLocalStorage';

function Logout({ onLogout }) {
    const [, setAccessToken] = useLocalState('', 'access_token');
    const [, setRefreshToken] = useLocalState('', 'refresh_token');
    const [, setLoginPerformed] = useLocalState(false, 'login_performed');

    const handleLogout = () => {
        setAccessToken('');
        setRefreshToken('');
        setLoginPerformed(false);
        onLogout(); // Call the onLogout prop function
    };

    return (
        <button onClick={handleLogout}>Logout</button>
    );
}

export default Logout;
