import { useState, useEffect } from 'react';
import Product from '../interfaces/Product';

const useCart = () => {
  const [inCartProducts, setInCartProducts] = useState<Product[]>(
    JSON.parse(sessionStorage.getItem('inCartProducts') || '[]'),
  );

  const handleProductInCart = (product: Product) => {
    const existingProductIndex = inCartProducts.findIndex(
      (cartProduct) => cartProduct.id === product.id,
    );
    if (existingProductIndex !== -1) {
      const updatedCartProducts = [...inCartProducts];
      updatedCartProducts[existingProductIndex].quantity += 1;
      setInCartProducts(updatedCartProducts);
    } else {
      const newCartProduct = { ...product, quantity: 1 };
      setInCartProducts([...inCartProducts, newCartProduct]);
    }
  };
  const removeProductFromCart = (productId: number) => {
    setInCartProducts((prevProducts) =>
      prevProducts.filter((product) => product.id !== productId),
    );
  };

  const clearCart = () => {
    sessionStorage.removeItem('inCartProducts');
  };

  useEffect(() => {
    sessionStorage.setItem('inCartProducts', JSON.stringify(inCartProducts));
  }, [inCartProducts]);

  return {
    inCartProducts,
    handleProductInCart,
    removeProductFromCart,
    clearCart,
  };
};

export default useCart;
