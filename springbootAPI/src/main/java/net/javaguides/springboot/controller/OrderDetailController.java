package net.javaguides.springboot.controller;

import java.util.List;

import net.javaguides.springboot.model.OrderDetail;
import net.javaguides.springboot.service.OrderDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order-details")
public class OrderDetailController {

    private OrderDetailService orderDetailService;

    public OrderDetailController(OrderDetailService orderDetailService) {
        super();
        this.orderDetailService = orderDetailService;
    }

    // build create order detail REST API
    @PostMapping()
    public ResponseEntity<OrderDetail> saveOrderDetail(@RequestBody OrderDetail orderDetail){
        return new ResponseEntity<OrderDetail>(orderDetailService.saveOrderDetail(orderDetail), HttpStatus.CREATED);
    }

    // build get all order details REST API
    @GetMapping
    public List<OrderDetail> getAllOrderDetails(){
        return orderDetailService.getAllOrderDetails();
    }

    // build get order detail by id REST API
    // http://localhost:8080/api/order-details/1
    @GetMapping("{id}")
    public ResponseEntity<OrderDetail> getOrderDetailById(@PathVariable("id") int orderDetailId){
        return new ResponseEntity<OrderDetail>(orderDetailService.getOrderDetailById(orderDetailId), HttpStatus.OK);
    }

    // build update order detail REST API
    // http://localhost:8080/api/order-details/1
    @PutMapping("{id}")
    public ResponseEntity<OrderDetail> updateOrderDetail(@PathVariable("id") int id, @RequestBody OrderDetail orderDetail){
        return new ResponseEntity<OrderDetail>(orderDetailService.updateOrderDetail(orderDetail, id), HttpStatus.OK);
    }

    // build delete order detail REST API
    // http://localhost:8080/api/order-details/1
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteOrderDetail(@PathVariable("id") int id){

        // delete order detail from DB
        orderDetailService.deleteOrderDetail(id);

        return new ResponseEntity<String>("Order detail deleted successfully!.", HttpStatus.OK);
    }

}
