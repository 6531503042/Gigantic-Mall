import React from 'react'
import '../css/Users.css';
import { FaAngleDown, FaContao, FaPlus, FaSearch } from 'react-icons/fa';

const Users = () => {
  return (
    <div className='container-information'>
      <div className='body-of-users'>
        <div className='body-con-row-1'>
          <a className='row-1-space'>
            <a className='Search-but'>
              <a><FaSearch style={{ fontSize: '12px' }} /></a>
              <input type="Search" placeholder='Search' style={{border:'none', width:'25rem'}}/>
            </a>
            <a className='add-user'>Add user <FaPlus style={{ fontSize: '12px' }}/></a>
            <a className='sort-by'>Sort by <FaAngleDown style={{ fontSize: '12px' }}/></a>
            <a className='saved-search'>Saved search <FaAngleDown style={{ fontSize: '12px' }}/></a>
            <a><FaContao /></a>
          </a>
        </div>
        <div>
          aaaa
        </div>
      </div>
    </div>
  )
}

export default Users