import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import HomePage from './utils/HomePage.jsx';
import UserManagementPage from './utils/UserManagementPage.jsx';
import AdminManagementPage from './utils/AdminManagementPage.jsx';
import ManagementPage from './utils/ManagementPage.jsx';
import 'bootstrap/dist/css/bootstrap.min.css';

ReactDOM.createRoot(document.getElementById('root')).render(
    <BrowserRouter>
        <Routes>
            <Route path="/HomePage" element={<HomePage />} />
            <Route path="/UserManagementPage" element={<UserManagementPage />} />
            <Route path="/AdminPage" element={<AdminManagementPage />} />
            <Route path="/ManagementPage/*" element={<ManagementPage />} />
        </Routes>
    </BrowserRouter>
);
