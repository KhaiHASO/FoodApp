package net.javaguides.springboot.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Product;
import net.javaguides.springboot.repository.ProductRepository;
import net.javaguides.springboot.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;

	public ProductServiceImpl(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}

	@Override
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product getProductById(int id) {
		return productRepository.findById(id).orElseThrow(() ->
				new ResourceNotFoundException("Product", "Id", id));
	}

	@Override
	public Product updateProduct(Product product, int id) {
		// we need to check whether product with given id is exist in DB or not
		Product existingProduct = productRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Product", "Id", id));

		existingProduct.setName(product.getName());
		existingProduct.setDescription(product.getDescription());
		existingProduct.setPrice(product.getPrice());
		existingProduct.setQuantity(product.getQuantity());
		existingProduct.setCategoryId(product.getCategoryId());
		existingProduct.setSupplierId(product.getSupplierId());
		existingProduct.setDiscount(product.getDiscount());
		existingProduct.setImage(product.getImage());

		// save existing product to DB
		productRepository.save(existingProduct);
		return existingProduct;
	}

	@Override
	public void deleteProduct(int id) {
		// check whether a product exist in a DB or not
		productRepository.findById(id).orElseThrow(() ->
				new ResourceNotFoundException("Product", "Id", id));
		productRepository.deleteById(id);
	}

	@Override
	public List<Product> findProductsByCategoryId(int id) {
		return productRepository.findProductsByCategoryId(id);
	}

}
