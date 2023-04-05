import axios from 'axios';
import PurchaseData from '../interfaces/PurchaseData';
import User from '../interfaces/User';

export async function getCurrentUserUtils() {
  const response = await axios.get(
    'http://localhost:8080/api/v1/users/current',
    {
      headers: { Authorization: `Bearer ${sessionStorage.getItem('token')}` },
    },
  );

  return response;
}

interface data {
  email: string | undefined;
  password: string | undefined;
}

export async function login(params: data) {
  const { email, password } = params;
  if (!email || !password) {
    throw new Error('Email and password are required.');
  }
  const response = await axios.post('http://localhost:8080/api/v1/auth/login', {
    email,
    password,
  });
  return response.data;
}

export async function getProducts() {
  const response = await axios.get('http://localhost:8080/api/v1/products/all');
  return response;
}

export async function newPurchase(purchaseData: PurchaseData) {
  const response = await axios.post(
    `http://localhost:8080/api/v1/purchase/new`,
    purchaseData,
    {
      headers: { Authorization: `Bearer ${sessionStorage.getItem('token')}` },
    },
  );
  return response;
}
export async function setDate(date: Date | null) {
  if (!date || Number.isNaN(date.getTime())) {
    throw new Error('La fecha proporcionada no es válida');
  }
  const response = await axios.post(
    `/api/v1/date/change?date=${date.toISOString()}`,
    null,
    {
      headers: { Authorization: `Bearer ${sessionStorage.getItem('token')}` },
    },
  );
  return response;
}

export async function getDate() {
  const response = await axios.get('http://localhost:8080/api/v1/date/get', {
    headers: { Authorization: `Bearer ${sessionStorage.getItem('token')}` },
  });
  return response;
}

export async function getPromotionalsDates() {
  const response = await axios.get(
    'http://localhost:8080/api/v1/promotional-dates/all',
    {
      headers: { Authorization: `Bearer ${sessionStorage.getItem('token')}` },
    },
  );
  return response.data;
}
export async function getUserInfo(): Promise<User[]> {
  const response = await axios.get(
    'http://localhost:8080/api/v1/users/users-info',
  );
  return response.data;
}
