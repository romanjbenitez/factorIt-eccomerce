import useCart from './useCart';

type CartInfo = {
  numProducts: number;
  isPromotional: boolean;
  isVip: boolean;
  clientTotalSpent: number;
};

export default function useCartUtil(total: number, cartInfo: CartInfo): number {
  const { inCartProducts } = useCart();
  let discount = 0;
  let newTotal = total;

  if (cartInfo.numProducts === 4) {
    discount = total * 0.25;
  } else if (cartInfo.numProducts >= 10) {
    if (cartInfo.isPromotional && !cartInfo.isVip) {
      discount = 300;
    } else if (cartInfo.isVip && !cartInfo.isPromotional) {
      let cheapestProduct = inCartProducts[0];
      for (let i = 1; i < inCartProducts.length; i++) {
        if (inCartProducts[i].amount < cheapestProduct.amount) {
          cheapestProduct = inCartProducts[i];
        }
      }
      newTotal -= cheapestProduct.amount;
      discount = 500;
    } else {
      discount = 100;
    }
  }

  return newTotal - discount;
}
