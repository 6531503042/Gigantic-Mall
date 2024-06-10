import React from 'react';
import { Line } from 'react-chartjs-2';
import { Chart as ChartJS, CategoryScale, LinearScale, PointElement, LineElement, Title, Tooltip, Legend } from 'chart.js';
import { FaMoneyBill, FaVectorSquare } from 'react-icons/fa';
import '../css/Dashboard.css';

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
        <div className='chart-container'>
          <Line data={data} options={options} />
        </div>
      </div>
    </div>
  );
}

export default Dashboard;
