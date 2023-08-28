import React from "react";
import axios from 'axios';

const Dashboard = () => {
    const createAssignment = async () => {
        try {
            const accessTokenObj = JSON.parse(localStorage.getItem('access_token'));
            const response = await axios.post('/api/auth/assignments', null, {
                headers: {
                    'Authorization': `Bearer ${accessTokenObj}`,
                    'Content-Type': 'application/json'
                }
            });

    
                const responseData = response.data;
                console.log('Assignment created:', responseData);
                // Handle success or update the UI as needed
                window.location.href=`/assignments/${response.data.id}`
            
        } catch (error) {
            console.error('Assignment creation error:', error);
            // Handle error or display an error message
        }
    };

    return (
        <div>
            <button onClick={createAssignment}>Submit New Assignment</button>
        </div>
    );
}

export default Dashboard;
