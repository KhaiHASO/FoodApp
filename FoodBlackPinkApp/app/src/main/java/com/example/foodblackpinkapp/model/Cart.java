package com.example.foodblackpinkapp.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Cart implements Serializable {
    @SerializedName("cartId")
    private int cartId;

    @SerializedName("customerId")
    private String customerId;

    @SerializedName("productId")
    private int productId;

    @SerializedName("quantity")
    private int quantity;
    @SerializedName("price")
    private int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }



    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @NonNull
    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", customerId='" + customerId + '\'' +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", price="+ price +
                '}';
    }
}
