package com.factorIt.eccomerce.dtos;

import com.factorIt.eccomerce.models.ShoppingCartProducts;

public class ShoppingCartProductsDto {
    private Long shoppingCartId;
    private ProductDTO product;

    public ShoppingCartProductsDto(){

    }
    public ShoppingCartProductsDto(ShoppingCartProducts shoppingCartProducts){
        this.shoppingCartId = shoppingCartProducts.getShoppingCart().getId();
    }
}
