import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import '../css/UserManagement.css';

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
    <div className="container mx-auto px-4 py-8">
      <h1 className="text-2xl font-bold mb-4">User Management</h1>
      <div className="mb-4">
        <Link to="/users/add" className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
          Add User
        </Link>
      </div>
      <table className="min-w-full">
        <thead>
          <tr>
            <th className="text-left">Photo</th>
            <th className="text-left">Email</th>
            <th className="text-left">First Name</th>
            <th className="text-left">Last Name</th>
            <th className="text-left">Role</th>
            <th className="text-left">Enabled</th>
            <th className="text-left">Actions</th>
          </tr>
        </thead>
        <tbody>
          {users.map((user) => (
            <tr key={user.id}>
              <td><img src={user.photo || 'default-photo-url'} alt="User" className="w-10 h-10 rounded-full" /></td>
              <td>{user.email}</td>
              <td>{user.firstName}</td>
              <td>{user.lastName}</td>
              <td>{user.role}</td>
              <td>{user.enabled ? 'Yes' : 'No'}</td>
              <td>
                <button onClick={() => handleEdit(user)} className="mr-2 text-blue-500 hover:text-blue-700">Edit</button>
                <button onClick={() => handleDelete(user.id)} className="text-red-500 hover:text-red-700">Delete</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      {editingUser && (
        <div>
          <h2 className="text-lg font-bold mt-8 mb-4">Edit User</h2>
          <input
            type="text"
            placeholder="Photo URL"
            className="border border-gray-300 rounded-md p-2 mb-2"
            value={photo}
            onChange={(e) => setPhoto(e.target.value)}
          />
          <input
            type="email"
            placeholder="Email"
            className="border border-gray-300 rounded-md p-2 mb-2"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
          <input
            type="text"
            placeholder="First Name"
            className="border border-gray-300 rounded-md p-2 mb-2"
            value={firstName}
            onChange={(e) => setFirstName(e.target.value)}
          />
          <input
            type="text"
            placeholder="Last Name"
            className="border border-gray-300 rounded-md p-2 mb-2"
            value={lastName}
            onChange={(e) => setLastName(e.target.value)}
          />
          <input
            type="text"
            placeholder="Role"
            className="border border-gray-300 rounded-md p-2 mb-2"
            value={role}
            onChange={(e) => setRole(e.target.value)}
          />
          <label className="flex items-center mb-2">
            <input
              type="checkbox"
              checked={enabled}
              onChange={(e) => setEnabled(e.target.checked)}
              className="form-checkbox h-5 w-5 text-blue-500"
            />
            <span className="ml-2">Enabled</span>
          </label>
          <button onClick={handleSave} className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
            Save
          </button>
        </div>
      )}
    </div>
  );
};

export default UserManagementPage;
