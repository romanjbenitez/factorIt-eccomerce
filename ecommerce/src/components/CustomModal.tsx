
import {  Modal, Typography, Stack, styled } from '@mui/material';

interface ModalProps {
  title: string;
  open: boolean;
}

const StyledModal = styled(Modal)({
  display: 'flex',
  alignItems: 'center',
  justifyContent: 'center',
  '& .MuiPaper-root': {
    width: '90%',
    maxHeight: '90%',
    overflow: 'scroll',
    maxWidth: '700px',
    backgroundColor: 'white',
  },
});

const StyledModalBody = styled('div')({
  backgroundColor: '#fff',
  borderRadius: '4px',
  boxShadow: '0 4px 12px rgba(0, 0, 0, 0.24)',
  padding: '16px',
  maxWidth: '30vw',
  width: '100%',
});


const CustomModal = (props: ModalProps) => {
  const { title, open } = props;
  return (
    <div>
    <StyledModal open={open}>
      <StyledModalBody>
        <Stack direction="column" spacing={2}>
          <Typography variant="h5" align="center">
          {title}
          </Typography>
        </Stack>
      </StyledModalBody>
    </StyledModal>
  </div>
  );
};

export default CustomModal;
