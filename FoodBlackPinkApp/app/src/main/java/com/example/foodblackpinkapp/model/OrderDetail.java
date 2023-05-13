package com.example.foodblackpinkapp.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OrderDetail implements Serializable {
    @SerializedName("order_detail_id")
    private Integer orderDetailId;

    @SerializedName("product_id")
    private int productId;

    @SerializedName("quantity")
    private Integer quantity;

    @SerializedName("price")
    private int price;

    @SerializedName("order_id")
    private Integer orderId;

    public Integer getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Integer orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    @NonNull
    @Override
    public String toString() {
        return "OrderDetail{" +
                "orderDetailId=" + orderDetailId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", price=" + price +
                ", orderId=" + orderId +
                '}';
    }
}
