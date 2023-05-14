package net.javaguides.springboot.controller;
import net.javaguides.springboot.model.Cart;
import net.javaguides.springboot.model.CartProductViewDTO;
import net.javaguides.springboot.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        super();
        this.cartService = cartService;
    }

    // create cart REST API
    @PostMapping()
    public ResponseEntity<Cart> saveCart(@RequestBody Cart cart) {
        return new ResponseEntity<>(cartService.saveCart(cart), HttpStatus.CREATED);
    }

    // get all carts REST API
    @GetMapping
    public List<Cart> getAllCarts() {
        return cartService.getAllCarts();
    }

    // get cart by id REST API
    // http://localhost:8080/api/carts/1
    @GetMapping("{id}")
    public ResponseEntity<Cart> getCartById(@PathVariable("id") int cartId) {
        return new ResponseEntity<>(cartService.getCartById(cartId), HttpStatus.OK);
    }

    // get cart by customer id REST API
    // http://localhost:8080/api/carts/customer/xxx
    @GetMapping("/customer/{customerId}")
    public List<Cart> getCartsByCustomerId(@PathVariable String customerId) {
        return cartService.getCartsByCustomerId(customerId);
    }

    // update cart REST API
    // http://localhost:8080/api/carts/1
    @PutMapping("{id}")
    public ResponseEntity<Cart> updateCart(@PathVariable("id") int id, @RequestBody Cart cart) {
        return new ResponseEntity<>(cartService.updateCart(cart, id), HttpStatus.OK);
    }

    // delete cart REST API
    // http://localhost:8080/api/carts/{customerId}/{productId}
    @DeleteMapping("/delete/{customerId}/{productId}")
    public ResponseEntity<String> deleteCart(@PathVariable String customerId, @PathVariable Integer productId) {
        try {
            cartService.deleteByCustomerIdAndProductId(customerId, productId);
            return ResponseEntity.ok("Cart deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete cart: " + e.getMessage());
        }
    }


    @PutMapping("/update")
    public ResponseEntity<String> updateCart(@RequestBody CartProductViewDTO cartProductViewDTO) {
        try {
            cartService.updateCart(cartProductViewDTO);

            return ResponseEntity.ok("Cart updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to update cart: " + e.getMessage());
        }
    }




}

