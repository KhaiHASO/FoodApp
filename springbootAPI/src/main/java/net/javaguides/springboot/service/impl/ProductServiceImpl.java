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
	public Product getProductById(long id) {
		return productRepository.findById(id).orElseThrow(() ->
				new ResourceNotFoundException("Product", "Id", id));
	}

	@Override
	public Product updateProduct(Product product, long id) {
		// we need to check whether product with given id is exist in DB or not
		Product existingProduct = productRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Product", "Id", id));

		existingProduct.setName(product.getName());
		existingProduct.setDescription(product.getDescription());
		existingProduct.setPrice(product.getPrice());
		existingProduct.setQuantity(product.getQuantity());
		existingProduct.setCategory(product.getCategory());
		existingProduct.setSupplier(product.getSupplier());
		existingProduct.setDiscount(product.getDiscount());
		existingProduct.setEnteredDate(product.getEnteredDate());
		existingProduct.setImage(product.getImage());

		// save existing product to DB
		productRepository.save(existingProduct);
		return existingProduct;
	}

	@Override
	public void deleteProduct(long id) {
		// check whether a product exist in a DB or not
		productRepository.findById(id).orElseThrow(() ->
				new ResourceNotFoundException("Product", "Id", id));
		productRepository.deleteById(id);
	}

}
