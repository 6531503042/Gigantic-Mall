import React from 'react'
import '../css/Header.css'
import { faArrowRight, faPercent} from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

function Header() {
  return (
    <div className='header'>
      <header>
        <p><FontAwesomeIcon icon={faPercent}
            style={{ marginRight: "10px", fontSize: "1vw" }}
          />30% off storewide - Limited time! <a href="#">Shop Now<FontAwesomeIcon
            icon={faArrowRight}
            style={{ marginLeft: "5px", fontSize: "0.8vw" }}
          /></a></p>
      </header>
    </div>
  )
}

export default Header