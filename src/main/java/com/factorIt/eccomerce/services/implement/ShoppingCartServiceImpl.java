package com.factorIt.eccomerce.services.implement;


import com.factorIt.eccomerce.models.ShoppingCart;
import com.factorIt.eccomerce.services.ShoppingCartService;
import com.factorIt.eccomerce.dtos.ShoppingCartDto;
import com.factorIt.eccomerce.repositories.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Override
    public List<ShoppingCartDto> getShoppingCarts() {
        return  shoppingCartRepository.findAll().stream().map(ShoppingCartDto::new).collect(Collectors.toList());
    }

    @Override
    public ShoppingCartDto getShoppingCart(Long id) {
        return shoppingCartRepository.findById(id).map(ShoppingCartDto::new).orElse(null);
    }

    @Override
    public void saveShoppingCart(ShoppingCart shoppingCart) {
        shoppingCartRepository.save(shoppingCart);
    }
}
