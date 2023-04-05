import { useState, useEffect } from 'react';
import { List, IconButton, Typography, Box, Button } from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';
import { useNavigate } from 'react-router-dom';
import useCart from '../hooks/useCart';
import useCartUtil from '../hooks/useCartUtil';
import { getCurrentUserUtils, newPurchase } from '../utils/fecthUtils';
import PurchaseData from '../interfaces/PurchaseData';
import CustomModal from '../components/CustomModal';

interface CartProps {
  typeOfCart: string;
}

export default function Cart({ typeOfCart }: CartProps): JSX.Element {
  const { inCartProducts, removeProductFromCart, clearCart } = useCart();
  const [cartProducts, setCartProducts] = useState(inCartProducts);
  const [role, setRole] = useState('');
  const [email, setEmail] = useState('');
  const [loggedIn, setLoggedIn] = useState(false);
  const [open, setOpen] = useState(false);
  const [openTo, setOpenTo] = useState(false);
  const navigate = useNavigate();

  const handleRemoveProduct = (id: number) => {
    const updatedProducts = cartProducts.filter((product) => product.id !== id);
    removeProductFromCart(id);
    setCartProducts(updatedProducts);
  };

  const calculateTotal = (): number =>
    cartProducts.reduce(
      (total, product) => total + product.amount * product.quantity,
      0,
    );

  const getCurrentUser = async () => {
    const response = await getCurrentUserUtils();
    const userRole = response.data.role;
    const userEmail = response.data.email;
    setRole(userRole);
    setEmail(userEmail);
  };

  const toLogin = () => {
    setOpenTo(true);
    setTimeout(() => {
      setOpenTo(false);
      navigate('/login');
    }, 1500);
  }
  const checkLoggedIn = () => {
    const token = sessionStorage.getItem('token');
    if (token) {
      setLoggedIn(true);
    }
  };

  useEffect(() => {
    checkLoggedIn();
    if (loggedIn) {
      getCurrentUser();
    }
    return () => {
      clearCart();
    };
  }, [loggedIn, clearCart]);

  const CartUtil = {
    numProducts: cartProducts.length,
    isPromotional: !!(typeOfCart === 'PROMOTIONALDATE' && role !== 'VIP'),
    isVip:
      role === 'VIP'
        ? true
        : !!(typeOfCart === 'PROMOTIONALDATE' && role === 'VIP'),
    clientTotalSpent: calculateTotal(),
  };

  const data = {
    userEmail: email,
    products: cartProducts,
    promotional: CartUtil.isPromotional,
    totalAmount: calculateTotal(),
    totalWithDiscount: useCartUtil(calculateTotal(), CartUtil),
  };

  const finishBuy = async (purchaseData: PurchaseData) => {
    await newPurchase(purchaseData);
    clearCart();
    setOpen(true);
    setTimeout(() => {
      setOpen(false);
      navigate('/');
    }, 1000);
  };
  return (
    <Box sx={{ p: 10 }}>
      <Typography variant="h5" gutterBottom>
        Mi carrito
      </Typography>
      <List>
        {cartProducts.map((product) => (
          <Box
            key={product.id}
            display="flex"
            alignItems="center"
            justifyContent="space-between"
            py={1}
          >
            <Box>
              <Typography variant="subtitle1">{product.name}</Typography>
              <Typography variant="subtitle2">
                Precio: {product.amount}
              </Typography>
              <Typography variant="subtitle2">
                Cantidad: {product.quantity}
              </Typography>
            </Box>
            <IconButton
              edge="end"
              aria-label="delete"
              onClick={() => handleRemoveProduct(product.id)}
            >
              <DeleteIcon />
            </IconButton>
          </Box>
        ))}
      </List>
      <Typography variant="h6" gutterBottom>
        Total: {calculateTotal()}
      </Typography>
      <Typography variant="h6" gutterBottom>
        Total con descuento {useCartUtil(calculateTotal(), CartUtil)}
      </Typography>
      <Box sx={{ width: '20%' }}>
        <Button
          variant="contained"
          sx={{ width: '100%' }}
          onClick={() => (loggedIn ? finishBuy(data) : toLogin())}
        >
          Terminar compra
        </Button>
      </Box>
      {open ? (
        <CustomModal title="Compra completada con exito" open={open} />
      ) : null}
      {openTo ? (
        <CustomModal title="Antes de poder hacer una compra debes de estar logueado" open={openTo} />
      ) : null}
    </Box>
  );
}
