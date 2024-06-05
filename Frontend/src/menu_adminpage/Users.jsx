import React from 'react'
import '../css/Users.css';
import { FaContao } from 'react-icons/fa';

const Users = () => {
  return (
    <div className='container-information'>
      <div className='body-of-users'>
        <div className='body-con-row-1'>
          <a className='row-1-space'>
            <a>Search</a>
            <a>Add user +</a>
            <a>Sort by</a>
            <a>Saved search</a>
            <a><FaContao/></a>
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