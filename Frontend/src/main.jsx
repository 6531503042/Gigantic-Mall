import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.jsx'
import './index.css'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import HomePage from './utils/HomePage.jsx'
import UserManagementPage from './utils/UserManagementPage.jsx'
import AdminManagementPage from './utils/AdminManagementPage.jsx'
import ManagementPage from './utils/ManagementPage.jsx'
import Brands from './utils/widget_admin/Brands.jsx'
import Categories from './utils/widget_admin/Categories.jsx'
import Create_new_products from './utils/widget_admin/Create_new_products.jsx'
import Customers from './utils/widget_admin/Customers.jsx'
import DashBoard from './utils/widget_admin/DashBoard.jsx'
import Menu_setting from './utils/widget_admin/Menu_setting.jsx'
import Orders from './utils/widget_admin/Orders.jsx'
import Products from './utils/widget_admin/Products.jsx'
import Shipping from './utils/widget_admin/Shipping.jsx'
import Users from './utils/widget_admin/Users.jsx'
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
  },
  {
  path: "/ManagementPage",
  element: <ManagementPage />,
  },
  {
  path: "/Brands",
  element: <Brands />,
  },
  {
  path: "/Categories",
  element: <Categories />,
  },
  {
  path: "/Create_new_products",
  element: <Create_new_products />,
  },
  {
  path: "/Customers",
  element: <Customers />,
  },
  {
  path: "/DashBoard",
  element: <DashBoard />,
  },
  {
  path: "/Menu_setting",
  element: <Menu_setting />,
  },
  {
  path: "/Orders",
  element: <Orders />,
  },
  {
  path: "/Products",
  element: <Products />,
  },
  {
  path: "/Shipping",
  element: <Shipping />,
  },
  {
  path: "/Users",
  element: <Users />,
  },
])

ReactDOM.createRoot(document.getElementById('root')).render(
  <RouterProvider router={router} />)