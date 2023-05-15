package net.javaguides.springboot.service;

import net.javaguides.springboot.model.Cart;
import net.javaguides.springboot.model.CartProductViewDTO;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartService {
    Cart saveCart(Cart cart);
    List<Cart> getAllCarts();
    Cart getCartById(int id);
    List<Cart> getCartsByCustomerId(String customerId);
    Cart updateCart(Cart cart, int id);
    void deleteByCustomerIdAndProductId(String customerId, Integer productId);
    void updateCart(CartProductViewDTO cartProductViewDTO);
    void emptyCart(String customerId);

}
