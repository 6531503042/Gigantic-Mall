import React from 'react';
import { DataGrid } from '@mui/x-data-grid';
import { FaAngleDown, FaPlus, FaSearch, FaEllipsisH, FaEdit, FaTrashAlt } from 'react-icons/fa';
import '../css/Users.css';

const Users = () => {
  const columns = [
    { field: 'photo', headerName: 'Photo', width: 130, renderCell: (params) => <img src={params.value} alt="User" style={{ width: '100%' }} /> },
    { field: 'name', headerName: 'Name', width: 150 },
    { field: 'role', headerName: 'Role', width: 130 },
    { field: 'createDate', headerName: 'Create Date', width: 150 },
    { field: 'id', headerName: 'UserID', width: 130 },
    {
      field: 'action',
      headerName: 'Action',
      width: 150,
      renderCell: () => (
        <>
          <button className="edit-btn"><FaEdit /></button>
          <button className="delete-btn"><FaTrashAlt /></button>
        </>
      )
    },
  ];

  const rows = [
    { id: 1, photo: 'path/to/photo1.jpg', name: 'Jon Snow', role: 'Admin', createDate: '2022-12-29' },
    { id: 2, photo: 'path/to/photo2.jpg', name: 'Cersei Lannister', role: 'User', createDate: '2022-12-24' },
    { id: 3, photo: 'path/to/photo3.jpg', name: 'Jaime Lannister', role: 'User', createDate: '2022-09-20' },
    // Add more rows as needed
  ];

  return (
    <div className='container-information'>
      <div className='body-of-users'>
        <div className='body-con-row-1'>
          <div className='row-1-space'>
            <div className='Search-but'>
              <FaSearch style={{ fontSize: '12px' }} />
              <input className='search-hint' type="search" placeholder='Search' />
            </div>
            <a className='add-user'>Add user <FaPlus style={{ fontSize: '12px' }} /></a>
            <a className='sort-by'>Sort by <FaAngleDown style={{ fontSize: '12px' }} /></a>
            <a className='saved-search'>Saved search <FaAngleDown style={{ fontSize: '12px' }} /></a>
            <FaEllipsisH style={{ fontSize: '12px', color: 'black' }} />
          </div>
        </div>
        <div className='information-users'>
          <div className='list-style'>
            <a>List Users</a>
          </div>
          <div className='Topics-style'>
            <a>Photo</a>
            <a>Name</a>
            <a>Role</a>
            <a>Create Date</a>
            <a>UserID</a>
            <a>Action</a>
          </div>
          <div className='data-grid-container'>
            <DataGrid
              rows={rows}
              columns={columns}
              pageSizeOptions={[5, 10]}
              checkboxSelection
              hideFooter
              disableColumnMenu
              disableColumnSelector
              componentsProps={{
                header: {
                  className: 'custom-header'
                }
              }}
            />
          </div>
        </div>
      </div>
    </div>
  );
};

export default Users;
