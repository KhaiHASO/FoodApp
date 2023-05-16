package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.BillViewDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BillViewRepository {

    private final JdbcTemplate jdbcTemplate;

    public BillViewRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<BillViewDTO> getBillView() {
        String sql = "SELECT order_id,customer_id, fullname, phone, address, product_list, order_date, total_price FROM bill_view";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            BillViewDTO billView = new BillViewDTO();
            billView.setOrderId(rs.getInt("order_id"));
            billView.setCustomerId(rs.getString("customer_id"));
            billView.setFullName(rs.getString("fullname"));
            billView.setPhone(rs.getString("phone"));
            billView.setAddress(rs.getString("address"));
            billView.setProductList(rs.getString("product_list"));
            billView.setOrderDate(rs.getString("order_date"));
            billView.setTotalPrice(rs.getInt("total_price"));
            return billView;
        });
    }

    public List<BillViewDTO> getBillViewByCustomerId(String customerId) {
        String sql = "SELECT order_id, customer_id, fullname, phone, address, product_list, order_date, total_price FROM bill_view WHERE customer_id = ? ORDER BY order_id DESC";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            BillViewDTO billView = new BillViewDTO();
            billView.setOrderId(rs.getInt("order_id"));
            billView.setCustomerId(rs.getString("customer_id"));
            billView.setFullName(rs.getString("fullname"));
            billView.setPhone(rs.getString("phone"));
            billView.setAddress(rs.getString("address"));
            billView.setProductList(rs.getString("product_list"));
            billView.setOrderDate(rs.getString("order_date"));
            billView.setTotalPrice(rs.getInt("total_price"));
            return billView;
        }, customerId);
    }
}
