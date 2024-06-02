import { faBars, faTh } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import React, { useState } from 'react';
import { NavLink } from 'react-router-dom';
import '../css/Sidebar.css';
import logo from '../assets/logo.png';

const Sidebar = ({ children }) => {
    const [isOpen, setIsOpen] = useState(false);
    const toggle = () => setIsOpen(!isOpen);

    const menuItem = [
        { path: "/", name: "DashBoard", icon: <FontAwesomeIcon icon={faTh} /> },
        { path: "/Brands", name: "Brands", icon: <FontAwesomeIcon icon={faTh} /> },
        { path: "/Categories", name: "Categories", icon: <FontAwesomeIcon icon={faTh} /> },
        { path: "/Create_new_products", name: "Create New Products", icon: <FontAwesomeIcon icon={faTh} /> },
        { path: "/Customers", name: "Customers", icon: <FontAwesomeIcon icon={faTh} /> },
        { path: "/Menu_setting", name: "Menu Setting", icon: <FontAwesomeIcon icon={faTh} /> },
        { path: "/Orders", name: "Orders", icon: <FontAwesomeIcon icon={faTh} /> },
        { path: "/Products", name: "Products", icon: <FontAwesomeIcon icon={faTh} /> },
        { path: "/Shipping", name: "Shipping", icon: <FontAwesomeIcon icon={faTh} /> },
        { path: "/Users", name: "Users", icon: <FontAwesomeIcon icon={faTh} /> },
    ];

    return (
        <div className="sidebar-container">
            <div className={`sidebar ${isOpen ? 'open' : ''}`}>
                <div className="top-section">
                    <h1 className='logo-admin'>
                        <a href="/HomePage">
                            <img src={logo} alt="Logo" />
                        </a>
                    </h1>
                    <div className="bars">
                        <FontAwesomeIcon icon={faBars} onClick={toggle} />
                    </div>
                </div>
                {menuItem.map((item, index) => (
                    <NavLink 
                        to={item.path} 
                        key={index} 
                        className={({ isActive }) => isActive ? "link active" : "link"}
                    >
                        <div className='icon'>{item.icon}</div>
                        <div className='link_text'>{item.name}</div>
                    </NavLink>
                ))}
            </div>
            <main className="main-content">{children}</main>
        </div>
    );
};

export default Sidebar;
