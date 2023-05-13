package net.javaguides.springboot.service.impl;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Cart;
import net.javaguides.springboot.repository.CartRepository;
import net.javaguides.springboot.service.CartService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        super();
        this.cartRepository = cartRepository;
    }

    @Override
    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    @Override
    public Cart getCartById(int id) {
        return cartRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cart", "Id", id));
    }

    @Override
    public List<Cart> getCartsByCustomerId(String customerId)
    {
        return cartRepository.getCartsByCustomerId(customerId);
    }

    @Override
    public Cart updateCart(Cart cart, int id) {
        Cart existingCart = getCartById(id);

        existingCart.setCustomerId(cart.getCustomerId());
        existingCart.setProductId(cart.getProductId());
        existingCart.setQuantity(cart.getQuantity());

        return cartRepository.save(existingCart);
    }

    @Override
    public void deleteCart(int id) {
        Cart existingCart = getCartById(id);
        cartRepository.delete(existingCart);
    }

    @Override
    public void updateCart(Integer quantity, Integer price, String customerId, Integer productId) {
        cartRepository.updateCart(quantity, price, customerId, productId);
    }


}
