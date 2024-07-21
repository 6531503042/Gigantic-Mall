import React, { useMemo, useState } from 'react';
import { useTable, useSortBy, useGlobalFilter } from 'react-table';
import { FaSort, FaSortUp, FaSortDown, FaEdit, FaTrashAlt } from 'react-icons/fa';
import '../css/Products.css'; 


const Products = () => {
  const [filterInput, setFilterInput] = useState('');

  const data = useMemo(() => [
    { id: 1, name: 'Handmade Pouch', proId: '302012', category: 'Bag & Pouch', stock: 10, price: 121, status: 'Low Stock', added: '29 Dec 2022' },
    { id: 2, name: 'Smartwatch E2', proId: '302011', category: 'Watch', stock: 204, price: 590, status: 'Published', added: '24 Dec 2022' },
    // Add more product data here...
  ], []);

  const columns = useMemo(() => [
    { Header: 'Product', accessor: 'name' },
    { Header: 'Product ID', accessor: 'proId' },
    { Header: 'Category', accessor: 'category' },
    { Header: 'Stock', accessor: 'stock' },
    { Header: 'Price', accessor: 'price', Cell: ({ value }) => `$${value.toFixed(2)}` },
    { Header: 'Status', accessor: 'status', Cell: ({ value }) => <span className={`status ${value.toLowerCase().replace(' ', '-')}`}>{value}</span> },
    { Header: 'Added', accessor: 'added' },
    { Header: 'Action', Cell: ({ row }) => (
      <>
        <button className="edit-btn" onClick={() => handleEdit(row.original)}><FaEdit /></button>
        <button className="delete-btn" onClick={() => handleDelete(row.original)}><FaTrashAlt /></button>
      </>
    ) }
  ], []);

  const {
    getTableProps,
    getTableBodyProps,
    headerGroups,
    rows,
    prepareRow,
    setGlobalFilter,
  } = useTable({ columns, data }, useGlobalFilter, useSortBy);

  const handleFilterChange = (e) => {
    const value = e.target.value || '';
    setGlobalFilter(value);
    setFilterInput(value);
  };

  const handleEdit = (product) => {
    alert(`Editing product: ${product.name}`);
    // Implement the edit functionality here
  };

  const handleDelete = (product) => {
    alert(`Deleting product: ${product.name}`);
    // Implement the delete functionality here
  };

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
          <FaSort />
        </div>
      </div>
      <div className="table-container">
        <table {...getTableProps()} className='product-table'>
          <thead>
            {headerGroups.map(headerGroup => (
              <tr {...headerGroup.getHeaderGroupProps()}>
                {headerGroup.headers.map(column => (
                  <th {...column.getHeaderProps(column.getSortByToggleProps())}>
                    {column.render('Header')}
                    <span>
                      {column.isSorted
                        ? column.isSortedDesc
                          ? <FaSortDown />
                          : <FaSortUp />
                        : <FaSort />}
                    </span>
                  </th>
                ))}
              </tr>
            ))}
          </thead>
          <tbody {...getTableBodyProps()}>
            {rows.map(row => {
              prepareRow(row);
              return (
                <tr {...row.getRowProps()}>
                  {row.cells.map(cell => (
                    <td {...cell.getCellProps()}>{cell.render('Cell')}</td>
                  ))}
                </tr>
              );
            })}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default Products;
