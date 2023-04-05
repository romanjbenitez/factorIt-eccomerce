package com.factorIt.eccomerce.services;

import com.factorIt.eccomerce.dtos.ShoppingCartDto;

import com.factorIt.eccomerce.models.ShoppingCart;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShoppingCartService {

    List<ShoppingCartDto> getShoppingCarts();
    ShoppingCartDto getShoppingCart(Long id);

    public void saveShoppingCart(ShoppingCart shoppingCart);
}
