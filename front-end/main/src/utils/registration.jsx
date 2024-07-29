import { useState } from 'react';
import { Helmet } from 'react-helmet';
import '../css/registration.css';
import Logo from '../assets/logo-mammoth.png';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faEnvelope, faKey, faCheckSquare, faUnlockKeyhole, faSquare, faUser } from '@fortawesome/free-solid-svg-icons';
import { FaDiscord, FaGithub, FaGoogle } from 'react-icons/fa';
import LoginForm from './components_registration/login'
import RegisterForm from './components_registration/register'

const Registration = () => {
  const [activeTab, setActiveTab] = useState('login');
  const [remember, setRemember] = useState(false);

  const toggleRemember = () => {
    setRemember(!remember);
  };

  return (
    <div className="body-whole">
      <Helmet>
        <title>Registration</title>
      </Helmet>
      <div className="set-position">
        <div className="logo-side">
          <div className='fix-line-logo'>
            <div className="logo-detail">
              <a style={{ fontSize: '2rem', marginBottom: '1.5rem', fontWeight: 'bold', color: 'white' }}>Gigantic</a>
              <a><img src={Logo} alt="Gigantic Mall Logo" /></a>
              <a style={{ fontSize: '2rem', marginBottom: '1.5rem', fontWeight: 'bold', color: 'white' }}>Mall</a>
            </div>
            <div className='logounder1'>
              The most popular mall for buying items online.
            </div>
            <div className='readmore'>
              <a href="/" style={{ color: 'white', textDecoration: 'none' }}>Read More</a>
            </div>
          </div>
        </div>
        <div className="registration-form">
          <div className="switch-login-regis">
            <a
              href="#"
              className={`login-section ${activeTab === 'login' ? 'active' : ''}`}
              onClick={() => setActiveTab('login')}
            >
              Log in
            </a>
            <a
              href="#"
              className={`register-section ${activeTab === 'register' ? 'active' : ''}`}
              onClick={() => setActiveTab('register')}
            >
              Register
            </a>
          </div>
          <div className="form-style">
            <h3 style={{ fontWeight: 'bolder' }}>
              {activeTab === 'login' ? 'Log in to your account.' : 'Create account for your own.'}
            </h3>
            <div style={{ color: 'grey', marginTop: '0.5rem', fontSize: '14px' }}>
              {activeTab === 'login' ? 'Welcome back! Please enter your details.' : 'Welcome for new user! Please enter your details.'}
            </div>
            <div className='body-field-box' style={{ marginTop: '1rem' }}>
              {activeTab === 'login' ?
                <LoginForm />
                :
                <RegisterForm />
              }
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Registration;
