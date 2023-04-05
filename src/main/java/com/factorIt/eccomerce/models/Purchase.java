package com.factorIt.eccomerce.models;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Purchase {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  LocalDateTime date;
    private double amount;

    @OneToOne(mappedBy = "purchase")
    private ShoppingCart shoppingCart;

    public Purchase() {
    }
    public Purchase(LocalDateTime date, double amount) {
        this.date = date;
        this.amount = amount;
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

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        shoppingCart.setPurchase(this);
        this.shoppingCart = shoppingCart;
    }

}
