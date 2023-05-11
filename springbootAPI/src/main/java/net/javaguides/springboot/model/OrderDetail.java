package net.javaguides.springboot.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "order_details")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_id")
    private Integer orderDetailId;
    @Column(name = "product_id")
    private int productId;
    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private int price;

    @Column(name = "order_id")
    private Integer orderId;



}
