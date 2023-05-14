package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO order_details (order_id, product_id, quantity, price) " +
            "SELECT (SELECT order_id FROM orders ORDER BY order_id DESC LIMIT 1), product_id, quantity, price " +
            "FROM carts WHERE customer_id = :customerId", nativeQuery = true)
    void createOrderDetailsFromCart(@Param("customerId") String customerId);


}
