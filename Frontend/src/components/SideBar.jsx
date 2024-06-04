import React, { useState } from 'react';
import '../css/Sidebar.css';
import { NavLink } from 'react-router-dom';
import { FaHome, FaUser, FaShoppingCart, FaBars, FaTags, FaBoxes, FaShippingFast, FaClipboardList, FaUsersCog, FaCog, FaPlus, FaAngleRight, FaAngleLeft } from 'react-icons/fa';
import logo from "../assets/logo.png";

const Sidebar = ({ children }) => {
    const [isOpen, setIsOpen] = useState(true);
    const toggle = () => setIsOpen(!isOpen);

    const menuItem = [
        { path: "/layout/create_new_products", name: "Create New Products", icon: <FaPlus className="create-new-icon" />, className: 'create-new'},
        { path: "/layout/dashboard", name: "Dashboard", icon: <FaHome className='normally-menu-bar'/>},
        { path: "/layout/users", name: "Users", icon: <FaUser className='normally-menu-bar'/> },
        { path: "/layout/categories", name: "Categories", icon: <FaTags className='normally-menu-bar'/> },
        { path: "/layout/brands", name: "Brands", icon: <FaBoxes className='normally-menu-bar'/> },
        { path: "/layout/products", name: "Products", icon: <FaShoppingCart className='normally-menu-bar'/> },
        { path: "/layout/shipping", name: "Shipping", icon: <FaShippingFast className='normally-menu-bar'/> },
        { path: "/layout/orders", name: "Orders", icon: <FaClipboardList className='normally-menu-bar'/> },
        { path: "/layout/customers", name: "Customers", icon: <FaUsersCog className='normally-menu-bar'/> },
        { path: "/layout/menu_setting", name: "Menu Setting", icon: <FaCog className='normally-menu-bar'/> },
    ];

    return (
        <div className='sidebar-container'>
            <div className='sidebar' style={{ width: isOpen ? "290px" : "140px" }}>
                <div className='top-section'>
                    <h1 className='logo_admin' style={{ display: isOpen ? "block" : "none" }}>
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
                    <NavLink to={item.path} key={index} className={`link ${item.className || ''}`} activeClassName="active">
                        <a className='link-line'>
                            <div className='icon'>{item.icon}</div>
                            <div className='link_text' style={{ display: isOpen ? "block" : "none" }}>{item.name}</div>
                        </a>
                    </NavLink>
                ))}
            </div>
            <main>{children}</main>
        </div>
    );
};

export default Sidebar;
