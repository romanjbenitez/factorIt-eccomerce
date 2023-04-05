package com.factorIt.eccomerce.services.implement;

import com.factorIt.eccomerce.models.ShoppingCartProducts;
import com.factorIt.eccomerce.repositories.ShoppingCartProductsRepository;
import com.factorIt.eccomerce.services.ShoppingCartProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartProductServiceImpl implements ShoppingCartProductService {
    @Autowired
    private ShoppingCartProductsRepository shoppingCartProductsRepository;
    @Override
    public void saveShoppingCartProduct(ShoppingCartProducts shoppingCartProducts) {
        shoppingCartProductsRepository.save(shoppingCartProducts);
    }
}
