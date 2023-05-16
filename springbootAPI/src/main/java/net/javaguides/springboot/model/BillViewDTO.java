package net.javaguides.springboot.model;

import lombok.Data;

@Data
public class BillViewDTO {
    private int orderId;
    private String customerId;
    private String fullName;
    private String phone;
    private String address;
    private String productList;
    private String orderDate;
    private int totalPrice;
}

