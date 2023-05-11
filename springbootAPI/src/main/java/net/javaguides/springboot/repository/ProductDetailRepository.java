package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Cart;
import net.javaguides.springboot.model.Product;
import net.javaguides.springboot.model.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductDetailRepository extends JpaRepository<ProductDetail, Integer> {
    @Query("SELECT c FROM ProductDetail c WHERE c.productId = :productId")
    List<ProductDetail> getProductDetailByProductId(@Param("productId") Integer productId);
}
