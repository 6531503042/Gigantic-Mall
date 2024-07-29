import { useState } from 'react';
import '../../css/registration.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faEnvelope, faKey, faCheckSquare, faSquare } from '@fortawesome/free-solid-svg-icons';
import { FaDiscord, FaGithub, FaGoogle } from 'react-icons/fa';

const login = () => {
    const [remember, setRemember] = useState(false);
    
    const toggleRemember = () => {
        setRemember(!remember);
    };

    return (
        <div>
            <div className='field-box'>
                <a><FontAwesomeIcon icon={faEnvelope} /></a>
                <input type="email" placeholder=" " />
                <label>Email Address</label>
            </div>
            <div className='field-box'>
                <a><FontAwesomeIcon icon={faKey} /></a>
                <input type="password" placeholder=" " />
                <label>Password</label>
            </div>
            <div className='remember-me'>
                <div onClick={toggleRemember} style={{ cursor: 'pointer', fontSize: '14px' }}>
                    <FontAwesomeIcon icon={remember ? faCheckSquare : faSquare} style={{ marginRight: '0.5rem', marginLeft: '0.5rem' }} />
                    <a>Remember for 30 Days</a>
                </div>
                <div style={{ marginRight: '0.5rem', fontSize: '14px', cursor: 'pointer' }}>
                    <a href="/">Forget Password</a>
                </div>
            </div>
            <div className='make-layor'>
                <a href='/' className='login-but'>
                    Login
                </a>
            </div>
            <div className='make-layor-line'>
                <a>or</a>
            </div>
            <div className='authenticate-icon-layor'>
                <a className='authenticate-icon-google' href='#'><FaGoogle /></a>
                <a className='authenticate-icon-git' href='#'><FaGithub /></a>
                <a className='authenticate-icon-discord' href='#'><FaDiscord /></a>
            </div>
        </div>
    )
}

export default login