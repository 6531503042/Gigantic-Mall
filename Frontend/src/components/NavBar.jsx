
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import Logo from '../assets/logo_giant.png'
import SearchingBar from './SearchingBar'
import { faAngleDown, faCartShopping, faUser } from '@fortawesome/free-solid-svg-icons'

function NavBar() {
    return (
        <div>
            <div className='nav-bar-con'>
                <div >
                    <a href="/"><img className='container mt-2 mb-1 ms-3 w-75' src={Logo} /></a>
                </div>
                <SearchingBar />
                <div className='nav-bar-user mt-4 me-4 ms-4'>
                    <a href="#"><FontAwesomeIcon icon={faCartShopping} className='shopping-cart-style me-4 ' style={{fontSize:'15px'}}/></a>
                    <a className='user-account-name ms-3' href="#">
                        <a href="#"><FontAwesomeIcon className='user-profile-style' icon={faUser} style={{fontSize:'15px'}}/></a>
                        <a href="#">
                            <a className='account-name-show me-2 '>Account</a>
                            <FontAwesomeIcon icon={faAngleDown} className='me-4' style={{fontSize:'15px'}}/>
                        </a>
                    </a>
                </div>
            </div>
        </div>
    )
}

export default NavBar