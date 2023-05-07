package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CartRepository extends JpaRepository<Cart, Integer> {

    @Query("SELECT c FROM Cart c WHERE c.customerId = :customerId")
    List<Cart> getCartsByCustomerId(@Param("customerId") String customerId);

}
