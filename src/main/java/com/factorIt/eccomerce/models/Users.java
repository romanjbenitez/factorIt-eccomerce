package com.factorIt.eccomerce.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private  Long Id;
    private String firsName;
    private String lastName;
    private String email;
    private String password;
    private LocalDateTime registrationDate;
    private Role role;

    private LocalDateTime lastPurchase;

    @OneToMany(mappedBy = "user")
    private List<ShoppingCart> shoppingCarts = new ArrayList<>();

    public Users() {
    }

    public Users(String firsName, String lastName, String email, String password, LocalDateTime registrationDate, Role role) {
        this.firsName = firsName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.registrationDate = registrationDate;
        this.role = role;
    }

    public String getFirsName() {
        return firsName;
    }

    public void setFirsName(String firsName) {
        this.firsName = firsName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getId() {
        return Id;
    }

    public void addShoppingCart(ShoppingCart cart) {
        cart.setUser(this);
        shoppingCarts.add(cart);
    }
    public List<ShoppingCart> getShoppingCarts() {
        return shoppingCarts;
    }

    public LocalDateTime getLastPurchase() {
        return lastPurchase;
    }

    public void setLastPurchase(LocalDateTime lastPurchase) {
        this.lastPurchase = lastPurchase;
    }
}
