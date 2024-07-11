import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter as Router, Route } from 'react-router-dom';

import CreateUserComponent from './components/CreateUserConponent';

const App = () => {
    return (
        <Router>
            <div className="container">
                <Route path="/" exact component={CreateUserComponent} />
                {/* Add more routes as needed */}
            </div>
        </Router>
    );
}

ReactDOM.render(<App />, document.getElementById('root'));
