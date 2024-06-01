import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import UserManagementPage from './pages/UserManagementPage';
import HomePage from './pages/HomePage';

function App() {
  return (
    <Router>
      <Switch>
        <Route exact path="/" component={HomePage} />
        <Route path="/users" component={UserManagementPage} />
        {/* Add other routes here */}
      </Switch>
    </Router>
  );
}

export default App;
