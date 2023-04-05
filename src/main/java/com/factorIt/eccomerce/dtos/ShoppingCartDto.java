package com.factorIt.eccomerce.dtos;

import com.factorIt.eccomerce.models.ShoppingCart;
import com.factorIt.eccomerce.models.ShopingCartType;

import java.util.List;
import java.util.stream.Collectors;

public class ShoppingCartDto {
    private Long id;
    private ShopingCartType type;

    private List<ProductDTO> products;

    public ShoppingCartDto() {
    }

    public ShoppingCartDto(ShoppingCart shoppingCart) {
        this.id = shoppingCart.getId();
        this.type = shoppingCart.getType();
        this.products = shoppingCart.getShoppingCartProducts().stream().map(ProductsShoppingCartDto::new).map(ProductsShoppingCartDto::getProduct).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }


    public ShopingCartType getType() {
        return type;
    }

    public void setType(ShopingCartType type) {
        this.type = type;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> shoppingCartProducts) {
        this.products = shoppingCartProducts;
    }
}
