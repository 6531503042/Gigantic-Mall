import React from 'react';
import Sidebar from '../components/Sidebar';
import MainContent from '../components/MainContent';
import { Route, Routes } from 'react-router-dom';
import Dashboard from '../menu_adminpage/Dashboard.jsx';
import Orders from '../menu_adminpage/Orders.jsx';
import Users from '../menu_adminpage/Users.jsx';

const Layout = () => {
    return (
        <div className='main-con-manage'>
            <Sidebar />
            <MainContent>
                <Routes>
                    <Route path="/Dashboard" element={<Dashboard />} />
                    <Route path="/Orders" element={<Orders />} />
                    <Route path="/Users" element={<Users />} />
                </Routes>
            </MainContent>
        </div>
    );
};

export default Layout;
