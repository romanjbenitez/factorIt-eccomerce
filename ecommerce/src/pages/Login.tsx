import React, { useRef, useState } from 'react';
import { Box, Container, TextField, Button, Typography } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import { login } from '../utils/fecthUtils';

export default function Login(): JSX.Element {
  const emailRef = useRef<HTMLInputElement | null>(null);
  const passwordRef = useRef<HTMLInputElement | null>(null);
  const [errorMessage, setErrorMessage] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    const email = emailRef.current?.value;
    const password = passwordRef.current?.value;
    try {
      const response = await login({ email, password });
      if (response?.token) {
        sessionStorage.setItem('token', response.token);
        navigate('/');
      }
    } catch (error) {
      setErrorMessage('Error al iniciar sesión. Intente de nuevo.');
      console.log(error)
    }
  };

  return (
    <Box
      sx={{
        paddingTop: 4,
        display: 'flex',
        justifyContent: 'center',
        height: '100vh',
      }}
    >
      <Container maxWidth="sm">
        <Box
          sx={{
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
            marginTop: 8,
          }}
        >
          <Typography component="h1" variant="h5">
            Iniciar sesión
          </Typography>
          {errorMessage && (
            <Typography component="p" color="error" sx={{ mt: 1 }}>
              {errorMessage}
            </Typography>
          )}
          <Box
            component="form"
            onSubmit={handleSubmit}
            sx={{ mt: 1, width: '100%' }}
          >
            <TextField
              margin="normal"
              required
              fullWidth
              id="email"
              label="Email"
              name="email"
              autoComplete="email"
              autoFocus
              inputRef={emailRef}
            />
            <TextField
              margin="normal"
              required
              fullWidth
              name="password"
              label="Contraseña"
              type="password"
              id="password"
              autoComplete="current-password"
              inputRef={passwordRef}
            />
            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
            >
              Ingresar
            </Button>
          </Box>
        </Box>
      </Container>
    </Box>
  );
}
