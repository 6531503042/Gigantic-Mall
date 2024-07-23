import React from 'react'
import { faAngleRight } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import PosterIphone from '../assets/poster-iphone.png'

function TopElectronicsBrands() {
  return (
    <div>
      <div className="shop-top-cate" style={{marginTop:"3vh"}}>
        <a style={{ marginLeft: "30px" }}>
          <a
            style={{
              marginRight: "5px",
              fontSize: "1.5vw",
              fontWeight: "bold",
            }}
          >
            Shop From
          </a>
          <a
            style={{ fontSize: "1.5vw", fontWeight: "bold", color: "#AD00FF" }}
          >
            Top Categories
          </a>

          <div style={{width:"17vw",height:"4px", backgroundColor:"#AD00FF"}}></div>

          <div className="top-cate-line"></div>
        </a>
        
        <a href="#" style={{ marginRight: "30px", textDecoration: "none" }}>
          <a style={{ fontSize: "1vw", fontWeight: "bold", color: "black" }}>
            View All
          </a>
          <FontAwesomeIcon
            icon={faAngleRight}
            style={{ marginLeft: "5px", fontSize: "1vw" }}
          />
        </a>
      </div>

      <div className='top-electronicsBrands'>
        <div className='poster-brands'>
          <a href="#"><img src={PosterIphone} alt="posterIphone" style={{width:"27vw",height:"32vh",borderRadius:"16px"}}/></a>
          <a href="#"><img src={PosterIphone} alt="posterIphone" style={{width:"27vw",height:"32vh",borderRadius:"16px"}}/></a>
          <a href="#"><img src={PosterIphone} alt="posterIphone" style={{width:"27vw",height:"32vh",borderRadius:"16px"}}/></a>
        </div>
      </div>
      <div className='layout'>
        <div className='playstation'></div>
        <div className='womanCollection'></div>
        <div className='speakers'></div>
        <div className='Gucci'></div>
      </div>
    </div>
  )
}

export default TopElectronicsBrands
