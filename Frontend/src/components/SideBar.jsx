// src/components/Sidebar.jsx
import React, { useState } from 'react';
import '../css/Sidebar.css';
import { NavLink } from 'react-router-dom';
import { FaHome, FaUser, FaShoppingCart, FaBars, FaBoxOpen, FaTags, FaBoxes, FaShippingFast, FaClipboardList, FaUsersCog, FaCog, FaPlusCircle, FaLess, FaAngleRight, FaAngleLeft } from 'react-icons/fa';
import logo from "../assets/logo.png";
import { motion } from 'framer-motion';

const Sidebar = ({ children }) => {
    const [isOpen, setIsOpen] = useState(true);
    const toggle = () => setIsOpen (!isOpen);

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
            <div className='sidebar' style={{width: isOpen ? "290px" : "130px"}}>
                <div className='top-section' >
                    <h1 className='logo_admin' style={{display: isOpen ? "block" : "none"}}>
                        <a href="/"><img src={logo} alt="Logo" /></a>
                    </h1>
                    <div className="bars-body">
                    {isOpen ? (
                            <FaAngleLeft className="bars" onClick={toggle} /> 
                        ) : (
                            <FaAngleRight className="bars" onClick={toggle} /> 
                        )}
                    </div>
                </div>
                {menuItem.map((item, index) => (
                    <NavLink to={item.path} key={index} className="link" activeClassName="active">
                        <a className='link-line'>
                            <div className='icon' >{item.icon}</div>
                            <div className='link_text' style={{display: isOpen ? "block" : "none"}}>{item.name}</div>
                        </a>
                    </NavLink>
                ))}
            </div>
            <main>{children}</main>
        </div>
    );
};

export default Sidebar;
