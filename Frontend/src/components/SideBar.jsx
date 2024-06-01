import React, { useState } from 'react';
import '../css/UserManagementPage.css';
import logo from '../assets/logo.png';

const SideBar = () => {
    const [activeSubmenu, setActiveSubmenu] = useState(null);

    const toggleSubmenu = (menu) => {
        setActiveSubmenu(activeSubmenu === menu ? null : menu);
    };

    return (
        <div className="sidebar-1">
            <div className="sidebar-header">
                <a href="/"><img src={logo} alt="Logo" className="logo" /></a>
            </div>
            <div className="">
                <button className="">
                    <i className="fas fa-plus mr-2"></i> Create New Products
                </button>
            </div>
            <div className="sidebar-menu">
                <div className="menu-item toggle-submenu" onClick={() => toggleSubmenu('dashboard')}>
                    <a href="#" className="menu-link"><i className="fas fa-home mr-3"></i> Dashboard</a>
                </div>
                <div className={`menu-item toggle-submenu ${activeSubmenu === 'users' ? 'active' : ''}`} onClick={() => toggleSubmenu('users')}>
                    <a href="#" className="menu-link"><i className="fas fa-users mr-3"></i> Users</a>
                    {activeSubmenu === 'users' && (
                        <div className="submenu">
                            <a href="#" className="submenu-link">All Users</a>
                            <a href="#" className="submenu-link">Add User</a>
                        </div>
                    )}
                </div>
                <div className={`menu-item toggle-submenu ${activeSubmenu === 'categories' ? 'active' : ''}`} onClick={() => toggleSubmenu('categories')}>
                    <a href="#" className="menu-link"><i className="fas fa-tags mr-3"></i> Categories</a>
                    {activeSubmenu === 'categories' && (
                        <div className="submenu">
                            <a href="#" className="submenu-link">All Categories</a>
                            <a href="#" className="submenu-link">Add Category</a>
                        </div>
                    )}
                </div>
                <div className={`menu-item toggle-submenu ${activeSubmenu === 'brands' ? 'active' : ''}`} onClick={() => toggleSubmenu('brands')}>
                    <a href="#" className="menu-link"><i className="fas fa-store mr-3"></i> Brands</a>
                    {activeSubmenu === 'brands' && (
                        <div className="submenu">
                            <a href="#" className="submenu-link">All Brands</a>
                            <a href="#" className="submenu-link">Add Brand</a>
                        </div>
                    )}
                </div>
                <div className={`menu-item toggle-submenu ${activeSubmenu === 'products' ? 'active' : ''}`} onClick={() => toggleSubmenu('products')}>
                    <a href="#" className="menu-link"><i className="fas fa-cubes mr-3"></i> Products</a>
                    {activeSubmenu === 'products' && (
                        <div className="submenu">
                            <a href="#" className="submenu-link">All Products</a>
                            <a href="#" className="submenu-link">Add Product</a>
                        </div>
                    )}
                </div>
                <div className={`menu-item toggle-submenu ${activeSubmenu === 'shipping' ? 'active' : ''}`} onClick={() => toggleSubmenu('shipping')}>
                    <a href="#" className="menu-link"><i className="fas fa-shipping-fast mr-3"></i> Shipping</a>
                    {activeSubmenu === 'shipping' && (
                        <div className="submenu">
                            <a href="#" className="submenu-link">All Shipments</a>
                            <a href="#" className="submenu-link">New Shipment</a>
                        </div>
                    )}
                </div>
                <div className={`menu-item toggle-submenu ${activeSubmenu === 'orders' ? 'active' : ''}`} onClick={() => toggleSubmenu('orders')}>
                    <a href="#" className="menu-link"><i className="fas fa-shopping-cart mr-3"></i> Orders</a>
                    {activeSubmenu === 'orders' && (
                        <div className="submenu">
                            <a href="#" className="submenu-link">All Orders</a>
                            <a href="#" className="submenu-link">New Order</a>
                        </div>
                    )}
                </div>
                <div className={`menu-item toggle-submenu ${activeSubmenu === 'customers' ? 'active' : ''}`} onClick={() => toggleSubmenu('customers')}>
                    <a href="#" className="menu-link"><i className="fas fa-users-cog mr-3"></i> Customers</a>
                    {activeSubmenu === 'customers' && (
                        <div className="submenu">
                            <a href="#" className="submenu-link">All Customers</a>
                            <a href="#" className="submenu-link">Add Customer</a>
                        </div>
                    )}
                </div>
                <div className={`menu-item toggle-submenu ${activeSubmenu === 'articles' ? 'active' : ''}`} onClick={() => toggleSubmenu('articles')}>
                    <a href="#" className="menu-link"><i className="fas fa-newspaper mr-3"></i> Articles</a>
                    {activeSubmenu === 'articles' && (
                        <div className="submenu">
                            <a href="#" className="submenu-link">All Articles</a>
                            <a href="#" className="submenu-link">Add Article</a>
                        </div>
                    )}
                </div>
                <div className={`menu-item toggle-submenu ${activeSubmenu === 'settings' ? 'active' : ''}`} onClick={() => toggleSubmenu('settings')}>
                    <a href="#" className="menu-link"><i className="fas fa-cog mr-3"></i> Settings</a>
                    {activeSubmenu === 'settings' && (
                        <div className="submenu">
                            <a href="#" className="submenu-link">General</a>
                            <a href="#" className="submenu-link">Appearance</a>
                            <a href="#" className="submenu-link">Security</a>
                        </div>
                    )}
                </div>
            </div>
            <div className="px-6 py-4">
                <a href="#" className="menu-link"><i className="fas fa-user-cog mr-3"></i> Profile</a>
                <div className="submenu">
                    <a href="#" className="submenu-link">Edit Profile</a>
                    <a href="#" className="submenu-link">Change Password</a>
                </div>
            </div>
        </div>
    );
};

export default SideBar;
