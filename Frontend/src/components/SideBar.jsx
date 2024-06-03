import React from 'react';
import '../css/Sidebar.css';
import { NavLink } from 'react-router-dom';
import { FaHome, FaUser, FaShoppingCart, FaBars } from 'react-icons/fa';
import logo from "../assets/logo.png"

const Sidebar = ({ children }) => {
    const menuItem = [
        { path: "/layout/Dashboard", name: "Dashboard", icon: <FaHome /> },
        { path: "/layout/Users", name: "Users", icon: <FaUser /> },
        { path: "/layout/Orders", name: "Orders", icon: <FaShoppingCart /> }
    ];

    return (
        <div className='container-sidebar'>
            <div className='body-sidebar'>
                <div className='top-section'>
                    <h1 className='logo_admin'>
                        <a href="/"><img src={logo} alt="Logo" /></a>
                    </h1>
                    <div className="bars"><FaBars/></div>
                </div>
                {menuItem.map((item, index) => (
                    <NavLink to={item.path} key={index} className="link" activeClassName="active">
                        <div className='icon'>{item.icon}</div>
                        <div className='link_text'>{item.name}</div>
                    </NavLink>
                ))}
            </div>
            <main>{children}</main>
        </div>
    );
};

export default Sidebar;
