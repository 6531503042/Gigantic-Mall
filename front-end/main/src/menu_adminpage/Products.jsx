import React, { useState } from 'react';
import { DataGrid } from '@mui/x-data-grid';
import { FaEdit, FaTrashAlt } from 'react-icons/fa';
import '../css/Products.css'; 

const Products = () => {
  const [filterInput, setFilterInput] = useState('');

  const data = [
    { id: 1, name: 'Handmade Pouch', proId: '302012', category: 'Bag & Pouch', stock: 10, price: 121, status: 'Low Stock', added: '29 Dec 2022' },
    { id: 2, name: 'Smartwatch E2', proId: '302011', category: 'Watch', stock: 204, price: 590, status: 'Published', added: '24 Dec 2022' },
    { id: 3, name: 'Ipad Air 5', proId: '302013', category: 'Ipad', stock: 100, price: 1590, status: 'Published', added: '20 Sep 2022' },
  ];

  const columns = [
    { field: 'name', headerName: 'Product', width: 130 },
    { field: 'proId', headerName: 'Product ID', width: 130 },
    { field: 'category', headerName: 'Category', width: 130 },
    { field: 'stock', headerName: 'Stock', width: 90 },
    { field: 'price', headerName: 'Price', width: 90, renderCell: (params) => `$${params.value.toFixed(2)}` },
    { field: 'status', headerName: 'Status', width: 130, renderCell: (params) => <span className={`status ${params.value.toLowerCase().replace(' ', '-')}`}>{params.value}</span> },
    { field: 'added', headerName: 'Added', width: 130 },
    { field: 'actions', headerName: 'Actions', width: 130, renderCell: (params) => (
        <>
          <button className="edit-btn" onClick={() => handleEdit(params.row)}><FaEdit /></button>
          <button className="delete-btn" onClick={() => handleDelete(params.row)}><FaTrashAlt /></button>
        </>
      ) 
    }
  ];

  const handleFilterChange = (e) => {
    setFilterInput(e.target.value);
  };

  const handleEdit = (product) => {
    alert(`Editing product: ${product.name}`);
  };

  const handleDelete = (product) => {
    alert(`Deleting product: ${product.name}`);
  };

  const filteredData = data.filter(row => 
    row.name.toLowerCase().includes(filterInput.toLowerCase()) ||
    row.proId.toLowerCase().includes(filterInput.toLowerCase()) ||
    row.category.toLowerCase().includes(filterInput.toLowerCase()) ||
    row.status.toLowerCase().includes(filterInput.toLowerCase()) ||
    (row.price.toString().includes(filterInput) || row.stock.toString().includes(filterInput))
  );

  return (
    <div className='container-information'>
      <div className="table-controls">
        <input
          value={filterInput}
          onChange={handleFilterChange}
          placeholder="Search"
          className="search-input"
        />
        <button className="add-product-btn">Add Product</button>
        <div className="sort-by">
          <span>Sort by</span>
        </div>
      </div>
      <div className="table-container" style={{ height: 400, width: '100%' }}>
        <DataGrid
          rows={filteredData}
          columns={columns}
          pageSize={5}
          rowsPerPageOptions={[5, 10]}
          checkboxSelection
          disableSelectionOnClick
        />
      </div>
    </div>
  );
};

export default Products;
