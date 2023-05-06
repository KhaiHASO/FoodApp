package net.javaguides.springboot.service;

import java.util.List;

import net.javaguides.springboot.model.Product;

public interface ProductService {
	Product saveProduct(Product product);
	List<Product> getAllProducts();
	Product getProductById(long id);
	Product updateProduct(Product product, long id);
	void deleteProduct(long id);
}
