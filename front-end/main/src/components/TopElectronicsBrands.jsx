import React from "react";
import { faAngleRight } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import PosterIphone from "../assets/poster-iphone.png";
import Carousel from "react-multi-carousel";
import "react-multi-carousel/lib/styles.css";
import PlaystationBrands from "../assets/playstationBrands.jpg";
import WomenInthedark from "../assets/womenDark.png";
import Speaker from "../assets/speaker.jpg";
import Perfume from "../assets/gucci.png";

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
        <div className="card-electronics">
          <img src={PosterIphone} alt="poster" />
        </div>
        <div className="card-electronics">
          <img src={PosterIphone} alt="poster" />
        </div>
        <div className="card-electronics">
          <img src={PosterIphone} alt="poster" />
        </div>
        <div className="card-electronics">
          <img src={PosterIphone} alt="poster" />
        </div>
        <div className="card-electronics">
          <img src={PosterIphone} alt="poster" />
        </div>
      </Carousel>

      <div className="layout">
        <div className="brands1">
          <a href="#">
            <img src={PlaystationBrands} alt="playstationBrands" />
            <div className="text-overlay">
              <h2 className="name1">PlayStation 5</h2>
              <p className="detail1">
                Experience the next generation of gaming
              </p>
              <p className="shop1">Shop Now</p>
            </div>
          </a>
        </div>

        <div className="brands2">
          <a href="#">
            <img src={WomenInthedark} alt="WomanDark" />
            <div className="text-overlay">
              <h2 className="name2">Women’s Collections</h2>
              <p className="detail2">
              Featured woman collections that <br /> give you another vibe.
              </p>
              <p className="shop2">Shop Now</p>
            </div>
          </a>
        </div>

        <div className="brands3">
          <a href="#">
            <img src={Speaker} alt="Speaker" />
            <div className="text-overlay">
              <h2 className="name3">Speakers</h2>
              <p className="detail3">
              Amazon wireless speakers
              </p>
              <p className="shop3">Shop Now</p>
            </div>
          </a>
        </div>

        <div className="brands4">
          <a href="#">
            <img src={Perfume} alt="Perfume" />
            <div className="text-overlay"></div>
          </a>
        </div>
      </div>
    </div>
  );
}

export default TopElectronicsBrands;
