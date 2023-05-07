package net.javaguides.springboot.service;

import net.javaguides.springboot.model.Cart;

import java.util.List;

public interface CartService {
    Cart saveCart(Cart cart);
    List<Cart> getAllCarts();
    Cart getCartById(int id);
    List<Cart> getCartsByCustomerId(String customerId);
    Cart updateCart(Cart cart, int id);
    void deleteCart(int id);
}
