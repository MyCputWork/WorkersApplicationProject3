package com.example.workersapplication;

import java.util.List;

public class OrderModel {

    List<String> product;
    String userEmail;
    String imgUrl;

    OrderModel(){

    }

    public OrderModel(List<String> product, String userEmail, String imgUrl) {
        this.product = product;
        this.userEmail = userEmail;
        this.imgUrl = imgUrl;
    }

    public List<String> getProduct() {
        return product;
    }

    public void setProduct(List<String> product) {
        this.product = product;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
