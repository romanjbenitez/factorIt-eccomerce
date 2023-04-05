import { List, ListItem, ListItemText } from '@mui/material';
import User from '../interfaces/User';

interface Props {
  users: User[];
}

function UserList({ users }: Props) {
  return (
    <div>
      <List sx={{ mt: 10 }}>
        {users.map((user) => (
          <ListItem key={user.name}>
            <ListItemText primary={`${user.name}`} secondary={user.role} />
          </ListItem>
        ))}
      </List>
    </div>
  );
}

export default UserList;
