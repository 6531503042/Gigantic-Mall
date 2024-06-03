import React from 'react';
import { Link } from 'react-router-dom';
import '../css/Sidebar.css';

const Sidebar = () => {
  return (
    <div>
      <ul>
        <li><Link to="/DashBoard">DashBoard</Link></li>
        <li><Link to="/Users">Users</Link></li>
        <li><Link to="/Orders">Orders</Link></li>
      </ul>
    </div>
  );
};

export default Sidebar;