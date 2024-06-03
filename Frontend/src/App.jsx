// src/App.jsx
import React from 'react';
import { BrowserRouter as Router } from 'react-router-dom';
import HomePage from './utils/HomePage.jsx';
import Layout from './utils/Layout.jsx';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/layout" element={<Layout />} />
      </Routes>
    </Router>
  );
}

export default App;
