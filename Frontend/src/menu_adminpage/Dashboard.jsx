import React from 'react';
import { Line } from 'react-chartjs-2';
import { Chart as ChartJS, CategoryScale, LinearScale, PointElement, LineElement, Title, Tooltip, Legend } from 'chart.js';
import { FaAngleDown, FaEllipsisH, FaMoneyBill, FaPlus, FaVectorSquare } from 'react-icons/fa';
import '../css/Dashboard.css';
import { FaCircleDot } from 'react-icons/fa6';

// Register necessary components
ChartJS.register(CategoryScale, LinearScale, PointElement, LineElement, Title, Tooltip, Legend);

const Dashboard = () => {
  // Chart data and options
  const data = {
    labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'], // X-axis labels
    datasets: [
      {
        label: 'Total Orders',
        data: [30, 45, 28, 56, 72, 65, 80], // Data points
        borderColor: '#4bc0c0',
        backgroundColor: 'rgba(75,192,192,0.2)',
        tension: 0.4, // Curve line
      },
      {
        label: 'Active Users',
        data: [50, 60, 70, 75, 85, 90, 100],
        borderColor: '#ff6384',
        backgroundColor: 'rgba(255,99,132,0.2)',
        tension: 0.4,
      },
    ],
  };

  const options = {
    responsive: true,
    plugins: {
      legend: {
        position: 'top',
      },
      title: {
        display: true,
        text: 'Monthly Business Overview',
      },
    },
  };

  return (
    <div className='container-information'>
      <div className='body-of-dashboard'>
        <div className='first-row'>
          <div className='block-each-first-row'>
            <div className='in-1-line'>
              <a style={{ fontWeight: '500' }}>Total Orders</a>
              <a><FaMoneyBill /></a>
            </div>
            <div className='in-2-line'>
              <a style={{ fontWeight: '500', fontSize: '25px' }}>$25k</a>
              <a style={{ fontWeight: '500', fontSize: '14px', color: 'green' }}>+11.01%<FaVectorSquare /></a>
            </div>
          </div>
          <div className='block-each-first-row'>
            <div className='in-1-line'>
              <a style={{ fontWeight: '500' }}>Visits</a>
              <a><FaMoneyBill /></a>
            </div>
            <div className='in-2-line'>
              <a style={{ fontWeight: '500', fontSize: '25px' }}>367k</a>
              <a style={{ fontWeight: '500', fontSize: '14px', color: 'green' }}>+9.15%<FaVectorSquare /></a>
            </div>
          </div>
          <div className='block-each-first-row'>
            <div className='in-1-line'>
              <a style={{ fontWeight: '500' }}>Orders</a>
              <a><FaMoneyBill /></a>
            </div>
            <div className='in-2-line'>
              <a style={{ fontWeight: '500', fontSize: '25px' }}>56</a>
              <a style={{ fontWeight: '500', fontSize: '14px', color: 'red' }}>-0.56%<FaVectorSquare /></a>
            </div>
          </div>
          <div className='block-each-first-row'>
            <div className='in-1-line'>
              <a style={{ fontWeight: '500' }}>Active Users</a>
              <a><FaMoneyBill /></a>
            </div>
            <div className='in-2-line'>
              <a style={{ fontWeight: '500', fontSize: '25px' }}>239k</a>
              <a style={{ fontWeight: '500', fontSize: '14px', color: 'red' }}>-1.48%<FaVectorSquare /></a>
            </div>
          </div>
        </div>
        <div className='second-row'>
          <div className='chart-container'>
            <Line data={data} options={options} />
          </div>
          <div className='info-chart'>
            <div className='line-1'>
              <a>Product</a>
              <a href='' style={{ color: 'black' }}><FaEllipsisH /></a>
            </div>
            <div className='info-chart-block'>
              <div className='in-1-line'>
                <a style={{ fontWeight: '500' }}>Products</a>
                <a><FaMoneyBill /></a>
              </div>
              <div className='in-2-line'>
                <a style={{ fontWeight: '500', fontSize: '25px' }}>104</a>
              </div>
            </div>
            <div className='in-3-line'>
              <div className='spacing1'>
                <a><FaPlus className='' /></a>
                <a>Add Product</a>
              </div>
              <div className='spacing1'>
                <a><FaPlus />In Stock </a>
                <a style={{ color: 'green' }}>104</a>
              </div>
              <div className='spacing1'>
                <a><FaPlus />Out of Stock:</a>
                <a style={{ color: 'red' }}>1</a>
              </div>
              <div className='spacing1'>
                <a><FaPlus />Enabled:</a>
                <a style={{ color: 'green' }}>104</a>
              </div>
              <div className='spacing1'>
                <a><FaPlus />Disabled:</a>
                <a style={{ color: 'red' }}>1</a>
              </div>
            </div>
          </div>
        </div>
        <div className='third-row'>
          <div className='top-selling'>
            <a>
              <a style={{ marginRight: '1rem', borderRight: '0.5px solid grey', paddingRight: '1rem' }}>Top Selling Products</a>
              <a>This week<FaAngleDown /></a>
            </a>
            <a><FaEllipsisH /></a>
          </div>
          <div className='cate-name-body'>
            <div className='cate-name'>
              <div className='rowing'>Product Name</div>
              <div className='rowing'>Price</div>
              <div className='rowing'>Sold</div>
              <div className='rowing'>Sales</div>
            </div>
          </div>
          <div className='product-info'>
            <div className='rowing-product'>
              <a className='aa'>aaaaaaaaaaaaa</a>
              <a className='aa'>aaaaaaaaaaaaa</a>
              <a className='aa'>aaaaaaaaaaaaa</a>
              <a className='aa'>aaaaaaaaaaaaa</a>
            </div>
            <div className='rowing-product'>
              <a className='aa'>aaaaaaaaaaaaa</a>
              <a className='aa'>aaaaaaaaaaaaa</a>
              <a className='aa'>aaaaaaaaaaaaa</a>
              <a className='aa'>aaaaaaaaaaaaa</a>
            </div>
            <div className='rowing-product'>
              <a className='aa'>aaaaaaaaaaaaa</a>
              <a className='aa'>aaaaaaaaaaaaa</a>
              <a className='aa'>aaaaaaaaaaaaa</a>
              <a className='aa'>aaaaaaaaaaaaa</a>
            </div>
            <div className='rowing-product'>
              <a className='aa'>aaaaaaaaaaaaa</a>
              <a className='aa'>aaaaaaaaaaaaa</a>
              <a className='aa'>aaaaaaaaaaaaa</a>
              <a className='aa'>aaaaaaaaaaaaa</a>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Dashboard;
