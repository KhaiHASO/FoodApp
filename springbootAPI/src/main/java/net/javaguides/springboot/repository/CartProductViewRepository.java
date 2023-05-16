package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.CartProductViewDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartProductViewRepository {

    private final JdbcTemplate jdbcTemplate;

    public CartProductViewRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<CartProductViewDTO> getCartProductView() {
        String sql = "SELECT cart_id, customer_id, product_id, quantity, name, price, image, discount FROM cart_product_view";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            CartProductViewDTO cartProductView = new CartProductViewDTO();
            cartProductView.setCartId(rs.getInt("cart_id"));
            cartProductView.setCustomerId(rs.getString("customer_id"));
            cartProductView.setProductId(rs.getInt("product_id"));
            cartProductView.setQuantity(rs.getInt("quantity"));
            cartProductView.setName(rs.getString("name"));
            cartProductView.setPrice(rs.getInt("price"));
            cartProductView.setImage(rs.getString("image"));
            cartProductView.setDiscount(rs.getInt("discount"));
            return cartProductView;
        });
    }

    public List<CartProductViewDTO> getCartProductViewByCustomerId(String customerId) {
        String sql = "SELECT cart_id, customer_id, product_id, quantity, name, price, image, discount FROM cart_product_view WHERE customer_id = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            CartProductViewDTO cartProductView = new CartProductViewDTO();
            cartProductView.setCartId(rs.getInt("cart_id"));
            cartProductView.setCustomerId(rs.getString("customer_id"));
            cartProductView.setProductId(rs.getInt("product_id"));
            cartProductView.setQuantity(rs.getInt("quantity"));
            cartProductView.setName(rs.getString("name"));
            cartProductView.setPrice(rs.getInt("price"));
            cartProductView.setImage(rs.getString("image"));
            cartProductView.setDiscount(rs.getInt("discount"));
            return cartProductView;
        }, customerId);
    }
}
