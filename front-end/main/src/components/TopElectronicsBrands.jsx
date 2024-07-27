import React from "react";
import { faAngleRight } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import PosterIphone from "../assets/poster-iphone.png";
import Carousel from "react-multi-carousel";
import "react-multi-carousel/lib/styles.css";

function TopElectronicsBrands() {
  const responsive = {
    superLargeDesktop: {
      // the naming can be any, depends on you.
      breakpoint: { max: 4000, min: 3000 },
      items: 5,
    },
    desktop: {
      breakpoint: { max: 3000, min: 1024 },
      items: 3,
    },
    tablet: {
      breakpoint: { max: 1024, min: 464 },
      items: 2,
    },
    mobile: {
      breakpoint: { max: 464, min: 0 },
      items: 1,
    },
  };
  return (
    <div>
      <div className="shop-top-cate" style={{ marginTop: "3vh" }}>
        <a style={{ marginLeft: "30px" }}>
          <a
            style={{
              marginRight: "5px",
              fontSize: "1.5vw",
              fontWeight: "bold",
            }}
          >
            Top
          </a>
          <a
            style={{ fontSize: "1.5vw", fontWeight: "bold", color: "#AD00FF" }}
          >
            Electronics Brands
          </a>

          <div
            style={{ width: "14vw", height: "4px", backgroundColor: "#AD00FF" }}
          ></div>

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

      <Carousel responsive={responsive}>
        <div className="card-electronics"><img src={PosterIphone} alt="poster" /></div>
        <div className="card-electronics"><img src={PosterIphone} alt="poster" /></div>
        <div className="card-electronics"><img src={PosterIphone} alt="poster" /></div>
        <div className="card-electronics"><img src={PosterIphone} alt="poster" /></div>
        <div className="card-electronics"><img src={PosterIphone} alt="poster" /></div>
      </Carousel>

      <div className="layout">
        <div className="playstation"></div>
        <div className="womanCollection"></div>
        <div className="speakers"></div>
        <div className="Gucci"></div>
      </div>
    </div>
  );
}

export default TopElectronicsBrands;
