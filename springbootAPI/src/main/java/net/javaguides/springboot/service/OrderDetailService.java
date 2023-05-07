package net.javaguides.springboot.service;

import net.javaguides.springboot.model.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    OrderDetail saveOrderDetail(OrderDetail orderDetail);
    List<OrderDetail> getAllOrderDetails();
    OrderDetail getOrderDetailById(int id);
    OrderDetail updateOrderDetail(OrderDetail orderDetail, int id);
    void deleteOrderDetail(int id);
}
