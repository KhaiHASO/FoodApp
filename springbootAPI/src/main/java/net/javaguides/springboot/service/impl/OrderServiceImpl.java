package net.javaguides.springboot.service.impl;

import java.util.List;

import net.javaguides.springboot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.model.Order;
import net.javaguides.springboot.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(int orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id " + orderId));
    }

    @Override
    public Order updateOrder(Order order, int orderId) {
        Order existingOrder = getOrderById(orderId);
        existingOrder.setOrderDate(order.getOrderDate());
        existingOrder.setTotalPrice(order.getTotalPrice());

        return orderRepository.save(existingOrder);
    }

    @Override
    public void deleteOrder(int orderId) {
        Order order = getOrderById(orderId);
        orderRepository.delete(order);
    }

    @Override
    public int createOrderFromCart(String customerId) {
        return orderRepository.createOrderFromCart(customerId);
    }

    @Override
    public Integer getLastInsertedOrderId() {
        return orderRepository.getLastInsertedOrderId();
    }

}
