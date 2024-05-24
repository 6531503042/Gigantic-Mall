import { faAngleRight } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import React from 'react'

function TopCategories() {
    return (
        <div className='shop-top-cate mt-1 '>
            <a style={{ marginLeft: '3rem', }}>
                <a style={{ marginRight: '5px', fontSize: '1.5vw', fontWeight: 'bold' }}>Shop From</a>
                <a style={{ fontSize: '1.5vw', fontWeight: 'bold', color: '#AD00FF' }}>Top Categories</a>
            </a>
            <a href="#" style={{marginRight: '3rem', textDecoration:'none'}}>
               <a style={{fontSize: '1vw', fontWeight: 'bold', color:'black' }}>View All</a> 
               <FontAwesomeIcon icon={faAngleRight} style={{marginLeft:'5px', fontSize: '1vw'}}/>
            </a>
        </div>
    )
}

export default TopCategories