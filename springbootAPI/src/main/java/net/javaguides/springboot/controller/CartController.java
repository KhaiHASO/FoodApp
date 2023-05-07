package net.javaguides.springboot.controller;
import net.javaguides.springboot.model.Cart;
import net.javaguides.springboot.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    private CartService cartService;

    public CartController(CartService cartService) {
        super();
        this.cartService = cartService;
    }

    // create cart REST API
    @PostMapping()
    public ResponseEntity<Cart> saveCart(@RequestBody Cart cart) {
        return new ResponseEntity<Cart>(cartService.saveCart(cart), HttpStatus.CREATED);
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
        return new ResponseEntity<Cart>(cartService.getCartById(cartId), HttpStatus.OK);
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
        return new ResponseEntity<Cart>(cartService.updateCart(cart, id), HttpStatus.OK);
    }

    // delete cart REST API
    // http://localhost:8080/api/carts/1
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCart(@PathVariable("id") int id) {

        // delete cart from DB
        cartService.deleteCart(id);

        return new ResponseEntity<String>("Cart deleted successfully!.", HttpStatus.OK);
    }

}

