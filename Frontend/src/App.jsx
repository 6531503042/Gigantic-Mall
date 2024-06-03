// src/App.jsx
import React from 'react';
import { Outlet } from 'react-router-dom';
import Sidebar from './components/Sidebar';

function App() {
  return (
    <div className="App">
      <Sidebar />
      <div className="content">
        <Outlet />
      </div>
    </div>
  );
}

export default App;
