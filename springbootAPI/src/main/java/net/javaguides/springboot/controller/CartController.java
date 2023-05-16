package net.javaguides.springboot.controller;
import net.javaguides.springboot.model.Cart;
import net.javaguides.springboot.model.CartProductViewDTO;
import net.javaguides.springboot.service.CartService;
import net.javaguides.springboot.service.OrderDetailService;
import net.javaguides.springboot.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final CartService cartService;
    private final OrderDetailService orderDetailService;
    private final OrderService orderService;


    public CartController(CartService cartService, OrderDetailService orderDetailService, OrderService orderService) {
        super();
        this.cartService = cartService;
        this.orderDetailService = orderDetailService;
        this.orderService = orderService;
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

    @PostMapping("/checkout/{customerId}")
    public ResponseEntity<String> createOrder(@PathVariable String customerId) {
        try {
            // Bước 2: Tạo đơn đặt hàng từ giỏ hàng
           orderService.createOrderFromCart(customerId);
            // Bước 3: Thêm chi tiết đơn hàng từ giỏ hàng
            orderDetailService.createOrderDetailsFromCart(customerId);

            // Bước 4: Xóa giỏ hàng sau khi hoàn tất đặt hàng
            cartService.emptyCart(customerId);
            int orderId= orderService.getLastInsertedOrderId();

            return ResponseEntity.ok(String.valueOf(orderId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to create order: " + e.getMessage());
        }
    }

}

