import { faAngleRight } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import React from "react";
import phone from "../assets/phone.png";
import cosmetics from "../assets/cosmetics.png";
import electronics from "../assets/electronics.png";
import furniture from "../assets/furniture.png";
import watches from "../assets/watches.png";
import decor from "../assets/decor.png";
import accessories from "../assets/accessories.png";
import { text } from "@fortawesome/fontawesome-svg-core";
import Carousel from "react-multi-carousel";
import "react-multi-carousel/lib/styles.css";

function TopCategories() {
  const cate = [
    { image: phone, nameCate: "Mobile" },
    { image: cosmetics, nameCate: "Cosmetics" },
    { image: electronics, nameCate: "electronics" },
    { image: furniture, nameCate: "furniture" },
    { image: watches, nameCate: "watches" },
    { image: decor, nameCate: "decor" },
    { image: accessories, nameCate: "accessories" },
    { image: phone, nameCate: "Mobile" },
    { image: cosmetics, nameCate: "Cosmetics" },
    { image: electronics, nameCate: "electronics" },
    { image: furniture, nameCate: "furniture" },
    { image: watches, nameCate: "watches" },
    { image: decor, nameCate: "decor" },
    { image: accessories, nameCate: "accessories" },
  ];

  const responsive = {
    superLargeDesktop: {
      // the naming can be any, depends on you.
      breakpoint: { max: 3000, min: 1200 },
      items: 7
    },
    Largedesktop: {
      breakpoint: { max: 1800, min: 1024 },
      items: 5
    },
    desktop: {
      breakpoint: { max: 1200, min: 800 },
      items: 4
    },
    tablet: {
      breakpoint: { max: 800, min: 464 },
      items: 2
    },
    mobile: {
      breakpoint: { max: 464, min: 0 },
      items: 2
    }
  };
  return (
    <div>
      <div className="shop-top-cate">
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

          <div
            style={{ width: "17vw", height: "4px", backgroundColor: "#AD00FF" }}
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
     
        {cate.map((cate, cate2) => (
          <div className="container-cate">
            <div key={cate2} className="cate">
              <div className="cate-header">
                <img src={cate.image} alt="img cate" className="card-image" />
              </div>
            </div>
            <br />
            <div className="cate-body">
              <p className="NameCate">{cate.nameCate}</p>
            </div>
          </div>
        ))}
      </Carousel>
      ;
    </div>
  );
}

export default TopCategories;
