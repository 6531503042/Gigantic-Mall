import { Link } from 'react-router-dom';
import React from 'react';
import '../css/Sidebar.css';

const Sidebar = () => {
    return (
        <div className="sidebar">
            <ul>
                <li><Link to="/DashBoard">DashBoard</Link></li>
                <li><Link to="/Categories">Categories</Link></li>
                <li><Link to="/Shipping">Shipping</Link></li>
                <li><Link to="/Users">Users</Link></li>
            </ul>
        </div>
    );
};

export default Sidebar;
