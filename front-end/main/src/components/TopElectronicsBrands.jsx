import React from 'react'
import { faAngleRight } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

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
    </div>
  )
}

export default TopElectronicsBrands
