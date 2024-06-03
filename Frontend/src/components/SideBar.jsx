// src/components/Sidebar.jsx
import React from 'react';
import '../css/Sidebar.css';
import { NavLink } from 'react-router-dom';
import { FaHome, FaUser, FaShoppingCart, FaBars, FaBoxOpen, FaTags, FaBoxes, FaShippingFast, FaClipboardList, FaUsersCog, FaCog, FaPlusCircle } from 'react-icons/fa';
import logo from "../assets/logo.png";

const Sidebar = ({ children }) => {
    const menuItem = [
        { path: "/layout/create_new_products", name: "Create New Products", icon: <FaPlusCircle /> },
        { path: "/layout/dashboard", name: "Dashboard", icon: <FaHome /> },
        { path: "/layout/users", name: "Users", icon: <FaUser /> },
        { path: "/layout/categories", name: "Categories", icon: <FaTags /> },
        { path: "/layout/brands", name: "Brands", icon: <FaBoxes /> },
        { path: "/layout/products", name: "Products", icon: <FaShoppingCart /> },
        { path: "/layout/shipping", name: "Shipping", icon: <FaShippingFast /> },
        { path: "/layout/orders", name: "Orders", icon: <FaClipboardList /> },
        { path: "/layout/customers", name: "Customers", icon: <FaUsersCog /> },
        { path: "/layout/menu_setting", name: "Menu Setting", icon: <FaCog /> },
    ];

    return (
        <div className='sidebar-container'>
            <div className='sidebar'>
                <div className='top-section'>
                    <h1 className='logo_admin'>
                        <a href="/"><img src={logo} alt="Logo" /></a>
                    </h1>
                    <div className="bars-body">
                        <FaBars className="bars"/>
                    </div>
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
