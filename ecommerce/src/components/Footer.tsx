import { styled } from '@mui/material/styles';
import Typography from '@mui/material/Typography';

const FooterContainer = styled('footer')({
  backgroundColor: '#f5f5f5',
  padding: '1rem',
  marginTop: 'auto',
  position: 'fixed',
  bottom: '1px',
  width: '100%',
});

export default function Footer() {
  return (
    <FooterContainer>
      <Typography variant="body2" align="center">
        Ecomerse.com &copy; {new Date().getFullYear()} Todos los derechos
        reservados.
      </Typography>
    </FooterContainer>
  );
}
