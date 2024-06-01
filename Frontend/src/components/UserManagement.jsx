// src/components/UserManagement.jsx
import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Button, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, IconButton } from '@mui/material';
import { Edit, Delete } from '@mui/icons-material';
import '../css/UserManagement.css'

const UserManagement = () => {
  const [users, setUsers] = useState([]);

  useEffect(() => {
    axios.get('http://localhost:8100/Admin/users')
      .then(response => setUsers(response.data))
      .catch(error => console.error('Error fetching users:', error));
  }, []);

  const handleEdit = (id) => {
    // Navigate to the edit page or open an edit modal
  };

  const handleDelete = (id) => {
    axios.delete(`http://localhost:8100/Admin/users/deleteById/${id}`)
      .then(() => setUsers(users.filter(user => user.id !== id)))
      .catch(error => console.error('Error deleting user:', error));
  };

  return (
    <div style={{ padding: '20px' }}>
      <h1>User Management</h1>
      <Button variant="contained" color="primary" style={{ marginBottom: '20px' }}>
        Add User
      </Button>
      <TableContainer component={Paper}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>Photo</TableCell>
              <TableCell>Name</TableCell>
              <TableCell>Role</TableCell>
              <TableCell>Create Date</TableCell>
              <TableCell>UserID</TableCell>
              <TableCell>Action</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {users.map((user) => (
              <TableRow key={user.id}>
                <TableCell><img src={user.photo || 'default-photo-url'} alt="User" style={{ width: '50px', height: '50px', borderRadius: '50%' }} /></TableCell>
                <TableCell>{user.name}</TableCell>
                <TableCell>{user.role}</TableCell>
                <TableCell>{new Date(user.createDate).toLocaleDateString()}</TableCell>
                <TableCell>{user.userId}</TableCell>
                <TableCell>
                  <IconButton onClick={() => handleEdit(user.id)}><Edit /></IconButton>
                  <IconButton onClick={() => handleDelete(user.id)}><Delete /></IconButton>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </div>
  );
};

export default UserManagement;
