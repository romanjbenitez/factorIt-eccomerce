package com.factorIt.eccomerce.services;

import com.factorIt.eccomerce.models.ShoppingCartProducts;
import org.springframework.stereotype.Service;

@Service
public interface ShoppingCartProductService {

    public void saveShoppingCartProduct(ShoppingCartProducts shoppingCartProducts);
}
