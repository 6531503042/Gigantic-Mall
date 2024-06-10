import React from 'react'
import '../css/Dashboard.css';

const Dashboard = () => {
  return (
    <div className='container-information'>
      <div className='body-of-dashboard'>
          <div className='first-row'>
            <div className='block-each-first-row'>Total Orders</div>
            <div className='block-each-first-row'>Visits</div>
            <div className='block-each-first-row'>Orders</div>
            <div className='block-each-first-row'>Active Users</div>
          </div>
      </div>
    </div>
  )
}

export default Dashboard