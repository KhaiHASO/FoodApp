package net.javaguides.springboot.model;


import lombok.Data;

@Data
public class CartProductViewDTO {
    private int cartId;
    private String customerId;
    private int productId;
    private int quantity;
    private String name;
    private int price;
    private String image;
    private int discount;
}
