import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.jsx'
import './index.css'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import HomePage from './utils/HomePage.jsx'
import UserManagementPage from './utils/UserManagementPage.jsx'
import AdminManagementPage from './utils/AdminManagementPage.jsx'
import 'bootstrap/dist/css/bootstrap.min.css';

const router = createBrowserRouter([
  {
    path: "/",
    element: <HomePage />,
  },
  {
  path: "/UserManagementPage",
  element: <UserManagementPage />,
  },
  {
  path: "/AdminPage",
  element: <AdminManagementPage />,
  }
])

ReactDOM.createRoot(document.getElementById('root')).render(
  <RouterProvider router={router} />)