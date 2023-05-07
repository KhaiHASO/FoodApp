package net.javaguides.springboot.service.impl;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.OrderDetail;
import net.javaguides.springboot.repository.OrderDetailRepository;
import net.javaguides.springboot.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public OrderDetail saveOrderDetail(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    @Override
    public List<OrderDetail> getAllOrderDetails() {
        return orderDetailRepository.findAll();
    }

    @Override
    public OrderDetail getOrderDetailById(int id) {
        return orderDetailRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order Detail", "id", id));
    }

    @Override
    public OrderDetail updateOrderDetail(OrderDetail orderDetail, int id) {
        OrderDetail existingOrderDetail = orderDetailRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order Detail", "id", id));

        existingOrderDetail.setPrice(orderDetail.getPrice());
        existingOrderDetail.setQuantity(orderDetail.getQuantity());

        return orderDetailRepository.save(existingOrderDetail);
    }

    @Override
    public void deleteOrderDetail(int id) {
        OrderDetail orderDetail = orderDetailRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order Detail", "id", id));

        orderDetailRepository.delete(orderDetail);
    }
}
