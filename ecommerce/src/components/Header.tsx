import React, { useState, useEffect } from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import CssBaseline from '@mui/material/CssBaseline';
import Drawer from '@mui/material/Drawer';
import IconButton from '@mui/material/IconButton';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemText from '@mui/material/ListItemText';
import MenuIcon from '@mui/icons-material/Menu';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import Button from '@mui/material/Button';
import { Divider } from '@mui/material';
import { Link, useLocation } from 'react-router-dom';
import { getCurrentUserUtils } from '../utils/fecthUtils';
import DatePickerModal from './DatePickerModal';

const drawerWidth = 240;
const navItems = ['Inicio', 'Informacion de usuarios'];

export default function Header() {
  const [mobileOpen, setMobileOpen] = useState(false);
  const [loggedIn, setLoggedIn] = useState(false);
  const [userName, setUserName] = useState('');
  const location = useLocation();
  const [currentPath, setCurrentPath] = useState(location.pathname);

  const getCurrentUserName = async () => {
    const response = await getCurrentUserUtils();
    setUserName(`${response.data.firsName} ${response.data.lastName}`);
  };
  const checkLoggedIn = () => {
    const token = sessionStorage.getItem('token');
    if (token) {
      setLoggedIn(true);
    }
  };

  useEffect(() => {
    setCurrentPath(location.pathname);
    checkLoggedIn();
    if (loggedIn) {
      getCurrentUserName();
    }
  }, [loggedIn, location]);

  const handleLogout = () => {
    sessionStorage.removeItem('token');
    setLoggedIn(false);
  };

  const handleDrawerToggle = () => {
    setMobileOpen((prevState) => !prevState);
  };

  const drawer = (
    <Box onClick={handleDrawerToggle} sx={{ textAlign: 'center' }}>
      <Typography variant="h6" sx={{ my: 2 }}>
        MUI
      </Typography>
      <Divider />
      <List
        sx={{
          display: 'flex',
          flexDirection: 'column',
          justifyContent: 'center',
          height: '70vh',
        }}
      >
        {navItems.map((item) => (
          <Link
            to={
              item.toLowerCase() === 'inicio'
                ? '/'
                : item.toLowerCase() === 'informacion de usuarios'
                ? '/user-info'
                : `/${item.toLowerCase()}`
            }
            key={item}
          >
            <ListItem disablePadding>
              <ListItemButton>
                <ListItemText
                  primary={item}
                  sx={{
                    fontSize: '2rem',
                    textAlign: 'center',
                    width: '100%',
                    display: 'flex',
                    alignItems: 'center',
                    justifyContent: 'center',
                    height: '50%',
                  }}
                />
              </ListItemButton>
            </ListItem>
          </Link>
        ))}
      </List>
      {loggedIn ? (
        <Box>
          <Typography component="p" sx={{ mt: 1 }}>
            Hola, {userName}
          </Typography>
          <Box sx={{ p: 1, width: '100%' }}>
            <Button
              variant="contained"
              sx={{ width: '100%' }}
              onClick={handleLogout}
            >
              Cerrar sesion
            </Button>
          </Box>
          <Box sx={{ p: 1, width: '100%' }}>
            <DatePickerModal />
          </Box>
        </Box>
      ) : (
        <>
          <Box sx={{ p: 1, width: '100%' }}>
            <Button
              variant="contained"
              sx={{ width: '100%' }}
              component={Link}
              to="/login"
            >
              Iniciar sesión
            </Button>
          </Box>
          <Box sx={{ p: 1, width: '100%' }}>
            <Button
              variant="outlined"
              sx={{ width: '100%' }}
              component={Link}
              to="/register"
            >
              Registrarse
            </Button>
          </Box>
        </>
      )}
    </Box>
  );

  return (
    <Box sx={{ display: 'flex' }}>
      <CssBaseline />
      <AppBar
        position="fixed"
        sx={{ zIndex: (theme) => theme.zIndex.drawer + 1 }}
      >
        <Toolbar sx={{ justifyContent: 'space-between' }}>
          <Box sx={{ display: 'flex', alignItems: 'center' }}>
            <IconButton
              color="inherit"
              aria-label="open drawer"
              edge="start"
              onClick={handleDrawerToggle}
              sx={{ mr: 2, color: mobileOpen ? 'black' : 'inherit' }}
            >
              <MenuIcon />
            </IconButton>
            <Typography variant="h6" noWrap>
              Tienda de ropa
            </Typography>
          </Box>
          <Box sx={{ display: 'flex', alignItems: 'center' }}>
            <Button variant="outlined" sx={{ mr: 2 }}>
              Iniciar sesión
            </Button>
            <Link to="/checkout">
              <IconButton color="inherit" aria-label="shopping cart" edge="end">
                <ShoppingCartIcon />
              </IconButton>
            </Link>
          </Box>
        </Toolbar>
      </AppBar>
      <Box component="nav">
        <Drawer
          variant="temporary"
          open={mobileOpen}
          onClose={handleDrawerToggle}
          ModalProps={{
            keepMounted: true,
          }}
          sx={{
            '& .MuiDrawer-paper': {
              boxSizing: 'border-box',
              width: drawerWidth,
            },
          }}
        >
          {drawer}
        </Drawer>
      </Box>
    </Box>
  );
}
