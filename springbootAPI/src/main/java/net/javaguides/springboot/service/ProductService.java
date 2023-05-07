package net.javaguides.springboot.service;

import java.util.List;

import net.javaguides.springboot.model.Product;

public interface ProductService {
	Product saveProduct(Product product);
	List<Product> getAllProducts();
	Product getProductById(int id);
	Product updateProduct(Product product, int id);
	void deleteProduct(int id);
	List<Product> findProductsByCategoryId(int id);
}
