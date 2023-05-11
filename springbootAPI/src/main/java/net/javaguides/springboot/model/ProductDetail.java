package net.javaguides.springboot.model;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name="product_details")
public class ProductDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_detail_id")
    private int productDetailId;
    @Column(name = "product_id")
    private int productId;
    @Column(name = "image")
    private String image;
}
