package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface OrderRepository extends JpaRepository<Order, Integer>  {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO orders (customer_id, order_date, total_price) " +
            "SELECT customer_id, CURRENT_DATE, SUM(price) " +
            "FROM carts WHERE customer_id = :customerId " +
            "GROUP BY customer_id", nativeQuery = true)
    void createOrderFromCart(@Param("customerId") String customerId);

}
