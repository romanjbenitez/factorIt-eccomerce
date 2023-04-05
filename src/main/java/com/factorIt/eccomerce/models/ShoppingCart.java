package com.factorIt.eccomerce.models;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private ShopingCartType type;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @OneToMany(mappedBy = "shoppingCart", fetch=FetchType.EAGER)
    List<ShoppingCartProducts> shoppingCartProducts = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "purchase_id", referencedColumnName = "id")
    private Purchase purchase;
    public ShoppingCart() {
    }

    public ShoppingCart( ShopingCartType type) {
        this.type = type;
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

    public void setUser(Users user) {
        this.user = user;
    }
    public Users getUser() {
        return user;
    }

    public List<ShoppingCartProducts> getShoppingCartProducts() {
        return shoppingCartProducts;
    }

    public void addShoppingCartProducts(ShoppingCartProducts shoppingCartProduct) {
        shoppingCartProduct.setShoppingCart(this);
        shoppingCartProducts.add(shoppingCartProduct);
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }
}
