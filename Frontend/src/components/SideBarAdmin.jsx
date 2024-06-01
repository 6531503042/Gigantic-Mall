import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import '../css/AdminManagementPage.css';
import logo from '../assets/logo.png';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBox, faBraille, faCarCrash, faCircle, faCodePullRequest, faGroupArrowsRotate, faHandPaper, faHome, faHouse, faMapLocation, faMarsAndVenusBurst, faPaperPlane, faPlus, faShip, faUserCircle, faUserGroup } from '@fortawesome/free-solid-svg-icons';

const SideBarAdmin = () => {
    const [activeSubmenu, setActiveSubmenu] = useState(null);

    const toggleSubmenu = (menu) => {
        setActiveSubmenu(activeSubmenu === menu ? null : menu);
    };
    return (
      <div className="sidebar">
        <div className='sidebar-nav'>
            <div >
                <div>
                    <a href="/"><img src={logo} /></a>
                    
                </div>
                <div className='create-new-but'>
                    <button className='but-create'>
                    <a><FontAwesomeIcon icon={faPlus} style={{background:'orange', borderRadius:'100px', padding:'10px'}}/></a>
                    <a>Create new products</a>
                    </button>
                </div>
                <div className='col-sidebar-nav'>
                    <div className='menu-list'>
                        <div className='space-of-ui'>
                            <a><FontAwesomeIcon icon={faHouse} style={{borderRadius:'100px',}}/></a>
                            <a>Dashboard</a>
                        </div>
                    </div>
                </div>
                <div className='col-sidebar-nav'>
                    <div className='menu-list'>
                        <div className='space-of-ui'>
                            <a><FontAwesomeIcon icon={faUserGroup} style={{borderRadius:'100px',}}/></a>
                            <a>Users</a>
                        </div>
                    </div>
                </div>
                <div className='col-sidebar-nav'>
                    <div className='menu-list'>
                        <div className='space-of-ui'>
                            <a><FontAwesomeIcon icon={faMapLocation} style={{borderRadius:'100px',}}/></a>
                            <a>Categories</a>
                        </div>
                    </div>
                </div>
                <div className='col-sidebar-nav'>
                    <div className='menu-list'>
                        <div className='space-of-ui'>
                            <a><FontAwesomeIcon icon={faBraille} style={{borderRadius:'100px',}}/></a>
                            <a>Brands</a>
                        </div>
                    </div>
                </div>
                <div className='col-sidebar-nav'>
                    <div className='menu-list'>
                        <div className='space-of-ui'>
                            <a><FontAwesomeIcon icon={faBox} style={{borderRadius:'100px',}}/></a>
                            <a>Products</a>
                        </div>
                    </div>
                </div>
                <div className='col-sidebar-nav'>
                    <div className='menu-list'>
                        <div className='space-of-ui'>
                            <a><FontAwesomeIcon icon={faPaperPlane} style={{borderRadius:'100px',}}/></a>
                            <a>Shipping</a>
                        </div>
                    </div>
                </div>
                <div className='col-sidebar-nav'>
                    <div className='menu-list'>
                        <div className='space-of-ui'>
                            <a><FontAwesomeIcon icon={faHandPaper} style={{borderRadius:'100px',}}/></a>
                            <a>Orders</a>
                        </div>
                    </div>
                </div>
                <div className='col-sidebar-nav'>
                    <div className='menu-list'>
                        <div className='space-of-ui'>
                            <a><FontAwesomeIcon icon={faCodePullRequest} style={{borderRadius:'100px',}}/></a>
                            <a>Customers</a>
                        </div>
                    </div>
                </div>
                <div className='col-sidebar-nav'>
                    <div className='menu-list'>
                        <div className='space-of-ui'>
                            <a><FontAwesomeIcon icon={faHome} style={{borderRadius:'100px',}}/></a>
                            <a>Menu setting</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
      </div>
    );
  };

export default SideBarAdmin