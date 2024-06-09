import React from 'react'
import '../css/Users.css';
import { FaAngleDown, FaContao, FaPlus, FaSearch } from 'react-icons/fa';

const Users = () => {

  const [users, setUsers] = useState([]);

  useEffect(() => {
    fetchUsers();
  }, []);

  const fetchUsers = async () => {
    try {
      const response = await fetch('/api/users/list');
      const data = await response.json();
      setUsers(data);
    } catch (error) {
      console.error('Error fetching users:', error);
    }
  };

  return (
    <div className='container-information'>
      <div className='body-of-users'>
        <div className='body-con-row-1'>
          <a className='row-1-space'>
            <a className='Search-but'>
              <a><FaSearch style={{ fontSize: '12px' }} /></a>
              <input className='search-hint' type="Search" placeholder='Search'/>
            </a>
            <a className='add-user'>Add user <FaPlus style={{ fontSize: '12px' }}/></a>
            <a className='sort-by'>Sort by <FaAngleDown style={{ fontSize: '12px' }}/></a>
            <a className='saved-search'>Saved search <FaAngleDown style={{ fontSize: '12px' }}/></a>
            <a><FaContao style={{ fontSize: '12px',  color:'black'}}/></a>
          </a>
        </div>
        <div className='information-users'>
          <div className='information-user-1'>
            <div className='list-style'>
              <a>List Users</a>
            </div>
            <div className='Topics-style'>
              <a>Photo</a>
              <a>Name</a>
              <a>Role</a>
              <a>Create Date</a>
              <a>UserID</a>
              <a>Role</a>
              <a>Action</a></div>
            <div className='data-style'>
            {users.length > 0 ? (
              users.map((user) => (
                <div key={user.id} className='user-item'>
                  <div>{user.firstName} {user.lastName}</div>
                  <div>{user.email}</div>
                  <div>{user.roles.map(role => role.name).join(', ')}</div>
                </div>
              ))
            ) : (
              <div>No users found</div>
            )}
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}

export default Users