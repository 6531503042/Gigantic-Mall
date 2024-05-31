import { faAppleWhole } from "@fortawesome/free-solid-svg-icons/faAppleWhole"
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome"
import '../App.css'
import { faAngleLeft, faAngleRight, faDotCircle } from "@fortawesome/free-solid-svg-icons"



function WrapperHomePage() {
  return (
    <div className="wrapper-con">
      <div className="wrapper-home-page mx-auto my-4">
        <div className="wrapper-width-control ms-5">
          <div className="Row-1 ms-2 mt-3">
            <a ><FontAwesomeIcon className="mt-1" icon={faAppleWhole} style={{ color: 'white', fontSize: '3vw' }} /></a>
            <a className="ms-3" style={{ fontSize: '1.5vw' }}>iPhone 16 Series</a>
          </div>
          <div className="ms-2" style={{display:'block'}}>
            <h1 className="mt-1" style={{ color: 'white', fontSize: '4vw' }}>Up to 10%</h1>
            <h1 className="" style={{ color: 'white', fontSize: '4vw' }}>off Voucher</h1>
          </div>
          <div className="ms-2 mb-3" style={{display:'block'}}>
            <a className="" href='#' style={{ color: 'white', fontSize: '1.3vw' ,display:'inline-block'}}>Shop Now</a>
            <a className="ms-1" href='#'><FontAwesomeIcon icon={faAngleRight} style={{ color: 'white', fontSize: '1.2vw' }} /></a>
          </div>
          <div className="Dotter-ads mb-3" style={{ fontSize: '1.4vw' }}>
            <a className=""><FontAwesomeIcon icon={faDotCircle} style={{ color: 'white', }} /></a>
            <a className=""><FontAwesomeIcon icon={faDotCircle} style={{ color: 'white', }} /></a>
            <a className=""><FontAwesomeIcon icon={faDotCircle} style={{ color: 'white', }} /></a>
          </div>
        </div>
      </div>
      <div className="next-prev-wrapper">
        <a href="#" ><FontAwesomeIcon icon={faAngleLeft} style={{ color: 'black', background: 'orange', padding: '1vw', borderRadius: '50%', fontSize: '1.4vw' }} /></a>
        <a href="#" ><FontAwesomeIcon icon={faAngleRight} style={{ color: 'black', background: 'orange', padding: '1vw', borderRadius: '50%', fontSize: '1.4vw' }} /></a>
      </div>
    </div>

  )
}

export default WrapperHomePage