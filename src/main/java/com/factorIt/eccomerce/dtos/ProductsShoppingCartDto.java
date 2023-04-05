package com.factorIt.eccomerce.dtos;

import com.factorIt.eccomerce.models.ShoppingCartProducts;

public class ProductsShoppingCartDto {
    private ProductDTO product;

    public ProductsShoppingCartDto(){

    }
    public ProductsShoppingCartDto(ShoppingCartProducts shoppingCartProducts){
        this.product = new ProductDTO(shoppingCartProducts.getProduct());
    }
    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }
}
