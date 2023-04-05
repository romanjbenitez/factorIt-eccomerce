import React, { useEffect, useState } from 'react';
import UserList from '../components/UserList';
import { getUserInfo } from '../utils/fecthUtils';
import User from '../interfaces/User';

export default function UsersInfo() {
  const [users, setUsers] = useState<User[]>([]);
  const getUsersInfo = async () => {
    const data = await getUserInfo();
    setUsers(data);
  };
  useEffect(() => {
    getUsersInfo();
  }, []);
  return (
    <div>
      <UserList users={users} />
    </div>
  );
}
