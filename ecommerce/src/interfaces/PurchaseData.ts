import Product from './Product';

export default interface PurchaseData {
  userEmail: string;
  products: Product[];
  promotional: boolean;
  totalAmount: number;
  totalWithDiscount: number;
}
