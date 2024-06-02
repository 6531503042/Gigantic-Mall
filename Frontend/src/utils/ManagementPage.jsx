import React from 'react';
import Sidebar from '../components/sidebar_admin/Sidebar';
import { Helmet } from 'react-helmet';
import '../App.css';
import { Routes, Route, BrowserRouter } from 'react-router-dom'
import Brands from '../utils/widget_admin/Brands.jsx'
import Categories from '../utils/widget_admin/Categories.jsx'
import Create_new_products from '../utils/widget_admin/Create_new_products.jsx'
import Customers from '../utils/widget_admin/Customers.jsx'
import DashBoard from '../utils/widget_admin/DashBoard.jsx'
import Menu_setting from '../utils/widget_admin/Menu_setting.jsx'
import Orders from '../utils/widget_admin/Orders.jsx'
import Products from '../utils/widget_admin/Products.jsx'
import Shipping from '../utils/widget_admin/Shipping.jsx'
import Users from '../utils/widget_admin/Users.jsx'

const ManagementPage = () => {
    return (
        <div className="management-page">
            <Helmet>
                <title>Admin Management</title>
            </Helmet>
            
            <Sidebar>
                <Routes>
                    <Route path="/DashBoard" element={<DashBoard/>} />
                    <Route path="/Brands" element={<Brands/>} />
                    <Route path="/Categories" element={<Categories/>} />
                    <Route path="/Create_new_products" element={<Create_new_products/>} />
                    <Route path="/Customers" element={<Customers/>} />
                    <Route path="/Menu_setting" element={<Menu_setting/>} />
                    <Route path="/Orders" element={<Orders/>} />
                    <Route path="/Products" element={<Products/>} />
                    <Route path="/Shipping" element={<Shipping/>} />
                    <Route path="/Users" element={<Users/>} />
                </Routes>
            </Sidebar>
                    
        </div>
    );
};

export default ManagementPage;
