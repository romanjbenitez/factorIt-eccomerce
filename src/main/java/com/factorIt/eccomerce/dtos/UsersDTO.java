package com.factorIt.eccomerce.dtos;

import com.factorIt.eccomerce.models.Role;
import com.factorIt.eccomerce.models.ShoppingCart;
import com.factorIt.eccomerce.models.Users;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UsersDTO {
    private Long id;
    private String firsName;
    private String lastName;
    private String email;
    private String password;
    private Role role;

    private List<PurchaseDTO> purchases;

    public UsersDTO(Users users) {
        this.id = users.getId();
        this.firsName= users.getFirsName();
        this.lastName = users.getLastName();
        this.email = users.getEmail();
        this.password = users.getPassword();
        this.role = users.getRole();
        this.purchases = users.getShoppingCarts().stream().map(ShoppingCart::getPurchase).map(PurchaseDTO::new).collect(Collectors.toList());

    }

    public Long getId() {
        return id;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<PurchaseDTO> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<PurchaseDTO> purchases) {
        this.purchases = purchases;
    }
}
