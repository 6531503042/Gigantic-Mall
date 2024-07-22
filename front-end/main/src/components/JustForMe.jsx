import React from "react";
import phone from "../assets/phone.png";
import SeeProduct from "../assets/SeeProduct.png";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faAngleDown,
  faCartShopping,
  faUser,
} from "@fortawesome/free-solid-svg-icons";
import cart1 from "../assets/Cart1.png";
import '../App.css'
import com from '../assets/com.png'

function Justforme() {
  const inputData = [
    {
      img: com,
      NameProduct: "Justforme 1",
      Price: "9,999 $",
      message:
        "Justforme 1 is a product that is used to make your hair look good.",
      rate: "⭐⭐⭐⭐⭐",
      review: 12,
    },
    {
      img: phone,
      NameProduct: "Justforme 1",
      Price: "9,999 $",
      message:
        "Justforme 1 is a product that is used to make your hair look good.",
      rate: "⭐⭐⭐⭐⭐",
      review: 12,
    },
    {
      img: phone,
      NameProduct: "Justforme 1",
      Price: "9,999 $",
      message:
        "Justforme 1 is a product that is used to make your hair look good.",
      rate: "⭐⭐⭐⭐⭐",
      review: 12,
    },
    {
      img: phone,
      NameProduct: "Justforme 1",
      Price: "9,999 $",
      message:
        "Justforme 1 is a product that is used to make your hair look good.",
      rate: "⭐⭐⭐⭐⭐",
      review: 12,
    },
    {
      img: phone,
      NameProduct: "Justforme 1",
      Price: "9,999 $",
      message:
        "Justforme 1 is a product that is used to make your hair look good.",
      rate: "⭐⭐⭐⭐⭐",
      review: 12,
    },
    {
      img: phone,
      NameProduct: "Justforme 1",
      Price: "9,999 $",
      message:
        "Justforme 1 is a product that is used to make your hair look good.",
      rate: "⭐⭐⭐⭐⭐",
      review: 12,
    },
    {
      img: phone,
      NameProduct: "Justforme 1",
      Price: "9,999 $",
      message:
        "Justforme 1 is a product that is used to make your hair look good.",
      rate: "⭐⭐⭐⭐⭐",
      review: 12,
    },
    {
      img: phone,
      NameProduct: "Justforme 1",
      Price: "9,999 $",
      message:
        "Justforme 1 is a product that is used to make your hair look good.",
      rate: "⭐⭐⭐⭐⭐",
      review: 12,
    },
    // Add more Product
  ];

  return (
    <div>
      <h1>Just For Me</h1>
      <br />
      <div className="cards">
        {inputData.map((item, index) => (
          <div key={index} className="card">
            <p style={{ marginLeft: "auto" }}>
              <img src={SeeProduct} alt="SeeProduct" />
            </p>
            <div className="card-header">
              <img src={item.img} alt="Product img" className="card-image" />
            </div>

            <div className="cart-buy">
              <div className="cart">
                <img src={cart1} alt="cart1" style={{ marginRight: "3px" }} />
                <input type="submit" name="add-to-cart" value="Add to cart" />
              </div>
              <div className="buy">
                <input type="submit" name="buy-now" value="Buy Now" />
              </div>
            </div>

            <div className="card-body">
              <p className="NameProduct">{item.NameProduct}</p>
              <p className="PriceProduct">{item.Price}</p>
              <p>{item.message}</p>
              <p>
                {item.rate} ({item.review})
              </p>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}

export default Justforme;
