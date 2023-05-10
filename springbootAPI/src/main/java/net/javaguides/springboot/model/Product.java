package net.javaguides.springboot.model;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name="products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private int productId;

	@Column(name = "name")
	private String name;

	@Column(name = "price")
	private double price;

	@Column(name = "description")
	private String description;

	@Column(name = "discount")
	private double discount;

	@Column(name = "image")
	private String image;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "category_id")
	private int categoryId;

	@Column(name = "supplier_id")
	private int supplierId;


}
