import { faAngleDown, faSearch } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import '../App.css'

function SearchingBar() {
    return (
        <div className="searching-bar mt-4 w-50 me-4">
            <div className='searching-row'>
                <div className="all-cate-dropdown me-3 ms-2">
                    <a href='#' style={{ color: 'black', textDecoration: 'none', fontSize:'15px'}} className="me-2 ms-1" >All categories</a>
                    <a href="#"><FontAwesomeIcon icon={faAngleDown} style={{color:'black', fontSize:'14px'}}/></a>
                </div>
                <div className="search-input-fill">
                <input  type="search" placeholder="Search anything" name='search-input'/></div>
            </div>
            <a className='search-icon me-3' ><FontAwesomeIcon style={{ color: 'black', cursor:'pointer'}} icon={faSearch} /></a>
        </div>
    );
}

export default SearchingBar