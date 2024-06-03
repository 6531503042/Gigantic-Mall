import React from 'react';
import Sidebar from '../components/Sidebar';
import { Route, Routes } from 'react-router-dom';
import Dashboard from '../menu_adminpage/Dashboard';
import Orders from '../menu_adminpage/Orders';
import Users from '../menu_adminpage/Users';
import Categories from '../menu_adminpage/Categories';
import Create_new_products from '../menu_adminpage/Create_new_products';
import Customers from '../menu_adminpage/Customers';
import Menu_setting from '../menu_adminpage/Menu_setting';
import Products from '../menu_adminpage/Products';
import Shipping from '../menu_adminpage/Shipping';
import Brands from '../menu_adminpage/Brands.jsx';

const Layout = () => {
    return (
        <div className='main-con-manage'>
            <Sidebar>
                <Routes>
                    <Route path="/" element={<Dashboard />} />
                    <Route path="/Dashboard" element={<Dashboard />} />
                    <Route path="/Orders" element={<Orders />} />
                    <Route path="/Users" element={<Users />} />
                    <Route path="/Categories" element={<Categories />} />
                    <Route path="/Create_new_products" element={<Create_new_products />} />
                    <Route path="/Customers" element={<Customers />} />
                    <Route path="/Menu_setting" element={<Menu_setting />} />
                    <Route path="/Products" element={<Products />} />
                    <Route path="/Shipping" element={<Shipping />} />
                    <Route path="/Brands" element={<Brands />} />
                </Routes>
            </Sidebar>
        </div>
    );
};

export default Layout;
