import React from "react";
import phone from "../assets/phone.png";

function Justforme() {
  const inputData = [
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
            <div className="card-header">
              <img src={item.img} alt="Product img" className="card-image" />
            </div>
            <div className="cart-buy">
              <input type="submit" name="add-to-cart" value="Add to cart" />
              <input type="submit" name="buy-now" value="Buy Now" />
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
