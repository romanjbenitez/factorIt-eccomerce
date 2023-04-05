import React, { useState, useEffect } from 'react';
import ShopingCart from '../components/ShopingCart';
import { getDate, getPromotionalsDates } from '../utils/fecthUtils';
import PromotionalDate from '../interfaces/PromotionalDate';

export default function Checkout() {
  const [date, setDate] = useState('');
  const [promotionalDate, setPromotionalDate] = useState<PromotionalDate[]>([]);
  const bearer = sessionStorage.getItem('token')

  const getDates = async () => {
    const dateNow = await getDate();
    const promotionalDates = await getPromotionalsDates();
    setDate(dateNow.data);
    setPromotionalDate(promotionalDates);
  };

  useEffect(() => {
    if(bearer != null){
      getDates();
    }
  }, []);

  const isDatePromotional = () =>
    promotionalDate.filter((promotionalDate) => {
      const startDate = new Date(promotionalDate.startOfPromotion);
      const endDate = new Date(promotionalDate.endOfPromotion);
      const dateNow = new Date(date);
      return dateNow >= startDate && dateNow <= endDate;
    }).length > 0;
  return (
    <div>
      <ShopingCart typeOfCart={isDatePromotional() ? 'PROMOTIONALDATE' : ''} />
    </div>
  );
}
