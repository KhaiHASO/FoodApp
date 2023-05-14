package net.javaguides.springboot.service;

import net.javaguides.springboot.model.Order;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderService {
    Order saveOrder(Order order);
    List<Order> getAllOrders();
    Order getOrderById(int id);
    Order updateOrder(Order order, int id);
    void deleteOrder(int id);
    void createOrderFromCart(String customerId);
}
