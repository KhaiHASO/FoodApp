package net.javaguides.springboot.controller;

import net.javaguides.springboot.model.CartProductViewDTO;
import net.javaguides.springboot.repository.CartProductViewRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cart-products")
public class CartProductController {

    private final CartProductViewRepository cartProductViewRepository;

    public CartProductController(CartProductViewRepository cartProductViewRepository) {
        this.cartProductViewRepository = cartProductViewRepository;
    }

    @GetMapping
    public List<CartProductViewDTO> getCartProducts() {
        return cartProductViewRepository.getCartProductView();
    }

    @GetMapping("/{customerId}")
    public List<CartProductViewDTO> getCartProductsByCustomerId(@PathVariable String customerId) {
        return cartProductViewRepository.getCartProductViewByCustomerId(customerId);
    }
}