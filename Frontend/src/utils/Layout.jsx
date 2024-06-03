import React from 'react';
import Sidebar from '../components/Sidebar';
import { Route, Routes } from 'react-router-dom';
import Dashboard from '../menu_adminpage/Dashboard';
import Orders from '../menu_adminpage/Orders';
import Users from '../menu_adminpage/Users';

const Layout = () => {
    return (
        <div className='main-con-manage'>
            <Sidebar>
                <Routes>
                    <Route path="/" element={<Dashboard />} />
                    <Route path="/Dashboard" element={<Dashboard />} />
                    <Route path="/Orders" element={<Orders />} />
                    <Route path="/Users" element={<Users />} />
                </Routes>
            </Sidebar>
        </div>
    );
};

export default Layout;
