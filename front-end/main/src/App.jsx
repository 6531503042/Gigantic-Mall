import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import HomePage from './utils/HomePage.jsx';
import AdminPage from './utils/AdminPage.jsx';
import Registration from './utils/registration.jsx';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/AdminPage/*" element={<AdminPage />} />
        <Route path="/Registration/*" element={<Registration />} />
      </Routes>
    </Router>
  );
}

export default App;
