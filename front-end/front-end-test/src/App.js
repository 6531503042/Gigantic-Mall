import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import CreateUserComponent from './components/CreateUserConponent';

function App() {
  return (
    <div>
      <Router>
        <div className="container">
          <Routes>
            <Route path="/add-user" element={<CreateUserComponent />} />
          </Routes>
        </div>
      </Router>
    </div>
  );
}

export default App;
