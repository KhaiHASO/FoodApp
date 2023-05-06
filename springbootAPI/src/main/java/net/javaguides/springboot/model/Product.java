package net.javaguides.springboot.model;

import javax.persistence.*;

import lombok.Data;

import java.sql.Date;

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

	@Column(name = "entered_date")
	private Date enteredDate;

	@Lob
	@Column(name = "image")
	private byte[] image;

	@Column(name = "quantity")
	private int quantity;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	@ManyToOne
	@JoinColumn(name = "supplier_id")
	private Supplier supplier;


}
