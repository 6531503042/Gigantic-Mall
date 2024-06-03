// src/utils/AdminPageTest.jsx
import React from 'react';
import { Route, Routes } from 'react-router-dom';
import Sidebar from '../components/Sidebar';
import Brands from './widget_admin/Brands.jsx';
import Categories from './widget_admin/Categories.jsx';
import Create_new_products from './widget_admin/Create_new_products.jsx';
import Customers from './widget_admin/Customers.jsx';
import DashBoard from './widget_admin/DashBoard.jsx';
import Menu_setting from './widget_admin/Menu_setting.jsx';
import Orders from './widget_admin/Orders.jsx';
import Products from './widget_admin/Products.jsx';
import Shipping from './widget_admin/Shipping.jsx';
import Users from './widget_admin/Users.jsx';

const AdminPageTest = () => {
    return (
        <div className="App">
            <Sidebar />
            <div className="content">
                <Routes>
                    <Route path="/DashBoard" element={<DashBoard />} />
                    <Route path="/Categories" element={<Categories />} />
                    <Route path="/Shipping" element={<Shipping />} />
                    <Route path="/Users" element={<Users />} />
                    {/* Add other routes here */}
                </Routes>
            </div>
        </div>
    );
}

export default AdminPageTest;
