import React, { useState, useEffect } from 'react';
import {
  Button,
  Card,
  CardContent,
  CardMedia,
  Container,
  Grid,
  Typography,
  Box,
} from '@mui/material';
import Product from '../interfaces/Product';
import { getProducts, getDate } from '../utils/fecthUtils';
import useCart from '../hooks/useCart';
import CustomModal from '../components/CustomModal';

function Home(): JSX.Element {
  const [products, setProducts] = useState<Product[]>([]);
  const { inCartProducts, handleProductInCart } = useCart();
  const [date, setDate] = useState('');
  const [open, setOpen] = useState(false);

  const getDateTime = async () => {
    const response = await getDate();
    setDate(response.data);
  };

  function formatDate(isoString: string): string {
    const newDate = new Date(isoString);
    return newDate.toLocaleDateString('es-AR');
  }

  useEffect(() => {
    getDateTime();
    getProducts()
      .then((response) => {
        const { data } = response;
        setProducts(data);
      })
      .catch((error) => error);
  }, []);

  const addToCart = (product: Product) => {
    handleProductInCart(product);
    setOpen(true)
    setTimeout(() => setOpen(false), 500)
  };

  return (

    <Container sx={{ py: 4 }}>
      
      <Typography sx={{ mt: 5, fontSize: '2rem' }} align="center" gutterBottom>
        Bienvenido a la tienda
      </Typography>
      <Typography
        sx={{ fontSize: '1.5rem' }}
        variant="subtitle1"
        align="center"
        gutterBottom
      >
        Nuestros productos
      </Typography>
      <Typography
        sx={{ fontSize: '1.5rem' }}
        variant="subtitle1"
        align="center"
        gutterBottom
      >
        Hoy es {formatDate(date)}
      </Typography>
      <Grid container spacing={4} sx={{ py: 4 }}>
        {products.map((product: Product) => (
          <Grid key={product.id} item xs={12} sm={6} md={3}>
            <Card sx={{ position: 'relative' }}>
              <CardMedia
                component="img"
                height="240"
                image={product.image}
                alt={product.name}
              />
              <CardContent>
                <Typography gutterBottom variant="h5" component="div">
                  {product.name}
                </Typography>
                <Typography variant="h6">{product.amount}$</Typography>
                <Typography variant="caption">
                  Stock:{product.stock} unidades
                </Typography>
                <Box
                  sx={{
                    display: 'flex',
                    justifyContent: 'space-between',
                    alignItems: 'center',
                    padding: 0,
                  }}
                >
                  <Typography variant="body2" color="text.secondary">
                    {product.description}
                  </Typography>
                  <Button
                    variant="contained"
                    color="primary"
                    onClick={() => addToCart(product)}
                  >
                    +
                  </Button>
                </Box>
              </CardContent>
            </Card>
          </Grid>
        ))}
      </Grid>
      {open ? <CustomModal title='Item añadido al carrito con exito' open={open}/> : null}
    </Container>
    
  );
}

export default Home;
