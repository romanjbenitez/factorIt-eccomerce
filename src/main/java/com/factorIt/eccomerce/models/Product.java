package com.factorIt.eccomerce.models;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String name;
    private String description;
    private double amount;
    private int stock;
    private String image;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ShoppingCart> shoppingCarts;

    @OneToMany(mappedBy="product", fetch=FetchType.EAGER)
    List<ShoppingCartProducts> shoppingCartProducts;

    public Product() {
    }

    public Product(String name, String description, double amount, String image, int stock) {
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.image = image;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void addShoppingCart(ShoppingCart shoppingCart) {
        shoppingCarts.add(shoppingCart);
    }
    public void AddShoppingCartProducts(ShoppingCartProducts shoppingCartProduct) {
        shoppingCartProduct.setProduct(this);
        shoppingCartProducts.add(shoppingCartProduct);
    }
}
