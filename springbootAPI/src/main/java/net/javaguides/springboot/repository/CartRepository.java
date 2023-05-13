package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;


public interface CartRepository extends JpaRepository<Cart, Integer> {

    @Query("SELECT c FROM Cart c WHERE c.customerId = :customerId")
    List<Cart> getCartsByCustomerId(@Param("customerId") String customerId);

    @Modifying
    @Transactional
    @Query("UPDATE Cart c SET c.quantity = :quantity, c.price = :price WHERE c.customerId = :customerId AND c.productId = :productId")
    void updateCart(@Param("quantity") Integer quantity,
                    @Param("price") Integer price,
                    @Param("customerId") String customerId,
                    @Param("productId") Integer productId);


}
