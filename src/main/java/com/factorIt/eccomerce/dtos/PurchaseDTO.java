package com.factorIt.eccomerce.dtos;

import com.factorIt.eccomerce.models.Purchase;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class PurchaseDTO {
    private Long id;
    private LocalDateTime date;
    private double amount;

    private ShoppingCartDto shoppingCart;

    public PurchaseDTO() {
    }

    public PurchaseDTO(Purchase purchase) {
        this.id = purchase.getId();
        this.date = purchase.getDate();
        this.amount = purchase.getAmount();
        this.shoppingCart = new ShoppingCartDto(purchase.getShoppingCart());
    }

    public Long getId() {
        return id;
    }


    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public ShoppingCartDto getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCartDto shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}
