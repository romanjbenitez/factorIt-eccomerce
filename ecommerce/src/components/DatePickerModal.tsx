import * as React from 'react';
import { Button, Modal, Typography, Stack, styled } from '@mui/material';
import CalendarTodayIcon from '@mui/icons-material/CalendarToday';
import { setDate } from '../utils/fecthUtils';

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

function DatePickerModal() {
  const [open, setOpen] = React.useState(false);
  const [selectedDate, setSelectedDate] = React.useState<Date | null>(null);

  const handleOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const handleDateChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setSelectedDate(new Date(event.target.value));
  };
  const handleDateSelected = (date: Date | null) => {
    setDate(date);
  };

  const handleSaveClick = () => {
    handleDateSelected(selectedDate);
    handleClose();
    window.location.reload();
  };

  return (
    <div>
      <Button
        variant="contained"
        startIcon={<CalendarTodayIcon />}
        onClick={handleOpen}
      >
        Seleccionar fecha
      </Button>
      <StyledModal open={open} onClose={handleClose}>
        <StyledModalBody>
          <Stack direction="column" spacing={2}>
            <Typography variant="h5" align="center">
              Simular fecha
            </Typography>
            <input
              type="date"
              value={selectedDate?.toISOString().substr(0, 10) || ''}
              onChange={handleDateChange}
            />
            <Stack direction="row" spacing={2} justifyContent="flex-end">
              <Button variant="outlined" onClick={handleClose}>
                Cancelar
              </Button>
              <Button variant="contained" onClick={handleSaveClick}>
                Guardar
              </Button>
            </Stack>
          </Stack>
        </StyledModalBody>
      </StyledModal>
    </div>
  );
}

export default DatePickerModal;
