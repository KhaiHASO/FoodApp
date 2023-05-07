package net.javaguides.springboot.controller;

import java.util.List;

import net.javaguides.springboot.model.Order;
import net.javaguides.springboot.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        super();
        this.orderService = orderService;
    }

    // create order REST API
    @PostMapping()
    public ResponseEntity<Order> saveOrder(@RequestBody Order order) {
        return new ResponseEntity<Order>(orderService.saveOrder(order), HttpStatus.CREATED);
    }

    // get all orders REST API
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    // get order by id REST API
    // http://localhost:8080/api/orders/1
    @GetMapping("{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable("id") int orderId) {
        return new ResponseEntity<Order>(orderService.getOrderById(orderId), HttpStatus.OK);
    }

    // update order REST API
    // http://localhost:8080/api/orders/1
    @PutMapping("{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable("id") int id, @RequestBody Order order) {
        return new ResponseEntity<Order>(orderService.updateOrder(order, id), HttpStatus.OK);
    }

    // delete order REST API
    // http://localhost:8080/api/orders/1
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable("id") int id) {

        // delete order from DB
        orderService.deleteOrder(id);

        return new ResponseEntity<String>("Order deleted successfully!.", HttpStatus.OK);
    }
}
