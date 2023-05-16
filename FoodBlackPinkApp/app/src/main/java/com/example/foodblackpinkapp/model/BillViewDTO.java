package com.example.foodblackpinkapp.model;

public class BillViewDTO {
    private int orderId;


    private String customerId;
    private String fullName;
    private String phone;
    private String address;
    private String productList;
    private String orderDate;
    private int totalPrice;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProductList() {
        return productList;
    }

    public void setProductList(String productList) {
        this.productList = productList;
    }
    public String getOderDate() {
        return orderDate;
    }

    public void setOderDate(String oderDate) {
        this.orderDate = oderDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
    @Override
    public String toString() {
        return "BillViewDTO{" +
                "orderId=" + orderId +
                ", customerId='" + customerId + '\'' +
                ", fullName='" + fullName + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", productList='" + productList + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
