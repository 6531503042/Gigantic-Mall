import React from 'react';

function Footer() {
  return (
    <div className="footer-container">
      <footer className="footer">
        <div className="footer-content">
          <ul className="footer-list">
            <li><p>43111 Hai Trieu street,</p></li>
            <li><p>District 1, HCMC</p></li>
            <li><p>Vietnam</p></li>
            <li><div className="divider"></div></li>
            <li><p>84-756-3237</p></li>
          </ul>
          <ul className="footer-list">
            <li><a href="/about">About Us</a></li>
            <li><a href="/services">Services</a></li>
            <li><a href="/contact">Contact</a></li>
            <li><a href="/privacy">Privacy Policy</a></li>
          </ul>
          <ul className="footer-list">
            <li><a href="https://facebook.com" target="_blank" rel="noopener noreferrer">Facebook</a></li>
            <li><a href="https://twitter.com" target="_blank" rel="noopener noreferrer">Twitter</a></li>
            <li><a href="https://instagram.com" target="_blank" rel="noopener noreferrer">Instagram</a></li>
            <li><a href="https://linkedin.com" target="_blank" rel="noopener noreferrer">LinkedIn</a></li>
          </ul>
        </div>
      </footer>
    </div>
  );
}

export default Footer;
