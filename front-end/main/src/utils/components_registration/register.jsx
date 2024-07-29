import React from 'react'
import { useState } from 'react';
import '../../css/registration.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faEnvelope, faKey, faUnlockKeyhole, faUser } from '@fortawesome/free-solid-svg-icons';
import { FaDiscord, FaGithub, FaGoogle} from 'react-icons/fa';

const register = () => {
  return (
    <div>
                  <div className='field-box'>
                    <a><FontAwesomeIcon icon={faUser} /></a>
                    <input type="text" placeholder=" " />
                    <label>First Name</label>
                  </div>
                  <div className='field-box'>
                    <a><FontAwesomeIcon icon={faUser} /></a>
                    <input type="text" placeholder=" " />
                    <label>Last Name</label>
                  </div>
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
                  <div className='field-box'>
                    <a><FontAwesomeIcon icon={faUnlockKeyhole} /></a>    
                    <input type="password" placeholder=" " />
                    <label>Confirm Password</label>
                  </div>
                  <div className='make-layor'>
                    <a href='/' className='login-but'>
                      Register
                    </a>
                  </div>
                  <div className='make-layor-line'>
                    <a>or</a>
                  </div>
                  <div className='authenticate-icon-layor'>
                    <a className='authenticate-icon-google' href='#'><FaGoogle/></a>
                    <a className='authenticate-icon-git' href='#'><FaGithub/></a>
                    <a className='authenticate-icon-discord' href='#'><FaDiscord/></a>
                  </div>
                </div>
            
  )
}

export default register