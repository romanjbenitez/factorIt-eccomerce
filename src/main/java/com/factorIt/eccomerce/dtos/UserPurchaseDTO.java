package com.factorIt.eccomerce.dtos;

import java.util.List;

public class UserPurchaseDTO {
    String userEmail;
    List<ProductDTO> products;
    boolean promotional;
    double totalAmount;
    double totalWithDiscount;

    public UserPurchaseDTO() {
    }

    public UserPurchaseDTO(String userEmail, List<ProductDTO> products, boolean promotional, double totalAmount, double totalWithDiscount) {
        this.userEmail = userEmail;
        this.products = products;
        this.promotional = promotional;
        this.totalAmount = totalAmount;
        this.totalWithDiscount = totalWithDiscount;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }

    public boolean isPromotional() {
        return promotional;
    }

    public void setPromotional(boolean promotional) {
        this.promotional = promotional;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getTotalWithDiscount() {
        return totalWithDiscount;
    }

    public void setTotalWithDiscount(float totalWithDiscount) {
        this.totalWithDiscount = totalWithDiscount;
    }
}
