import React, { useState, useEffect } from 'react';
import axios from 'axios';
import '../css/UserManagementPage.css';
import { Helmet } from 'react-helmet';

const UserManagementPage = () => {
  const [users, setUsers] = useState([]);
  const [editingUser, setEditingUser] = useState(null);
  const [photo, setPhoto] = useState('');
  const [email, setEmail] = useState('');
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [role, setRole] = useState('');
  const [enabled, setEnabled] = useState(true);

  useEffect(() => {
    fetchUsers();
  }, []);

  const fetchUsers = async () => {
    try {
      const response = await axios.get('http://localhost:8100/Admin/users');
      setUsers(response.data);
    } catch (error) {
      console.error('Error fetching users:', error);
    }
  };

  const handleEdit = (user) => {
    setEditingUser(user);
    setPhoto(user.photo || '');
    setEmail(user.email);
    setFirstName(user.firstName);
    setLastName(user.lastName);
    setRole(user.role);
    setEnabled(user.enabled);
  };

  const handleDelete = async (id) => {
    try {
      await axios.delete(`http://localhost:8100/Admin/users/deleteById/${id}`);
      setUsers(users.filter(user => user.id !== id));
    } catch (error) {
      console.error('Error deleting user:', error);
    }
  };

  const handleSave = async () => {
    if (editingUser) {
      // Update existing user
      try {
        await axios.put(`http://localhost:8100/Admin/users/updateById/${editingUser.id}`, {
          photo,
          email,
          firstName,
          lastName,
          role,
          enabled,
        });
        fetchUsers();
        setEditingUser(null);
        setPhoto('');
        setEmail('');
        setFirstName('');
        setLastName('');
        setRole('');
        setEnabled(true);
      } catch (error) {
        console.error('Error updating user:', error);
      }
    } else {
      // Add new user
      try {
        await axios.post('http://localhost:8100/Admin/users/add', {
          photo,
          email,
          firstName,
          lastName,
          role,
          enabled,
        });
        fetchUsers();
        setPhoto('');
        setEmail('');
        setFirstName('');
        setLastName('');
        setRole('');
        setEnabled(true);
      } catch (error) {
        console.error('Error adding user:', error);
      }
    }
  };

  return (
    
    <div>
      <Helmet>
        <title>Admin Management</title>
      </Helmet>
      <SideBar/>
      <div></div>
    </div>
    
  );
  
};


export default UserManagementPage;
