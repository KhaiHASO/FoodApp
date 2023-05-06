package net.javaguides.springboot.controller;

import java.util.List;

import net.javaguides.springboot.model.Product;
import net.javaguides.springboot.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	private ProductService productService;

	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}

	// build create product REST API
	@PostMapping()
	public ResponseEntity<Product> saveProduct(@RequestBody Product product){
		return new ResponseEntity<Product>(productService.saveProduct(product), HttpStatus.CREATED);
	}

	// build get all products REST API
	@GetMapping
	public List<Product> getAllProducts(){
		return productService.getAllProducts();
	}

	// build get product by id REST API
	// http://localhost:8080/api/products/1
	@GetMapping("{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") long productId){
		return new ResponseEntity<Product>(productService.getProductById(productId), HttpStatus.OK);
	}

	// build update product REST API
	// http://localhost:8080/api/products/1
	@PutMapping("{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable("id") long id, @RequestBody Product product){
		return new ResponseEntity<Product>(productService.updateProduct(product, id), HttpStatus.OK);
	}

	// build delete product REST API
	// http://localhost:8080/api/products/1
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable("id") long id){

		// delete product from DB
		productService.deleteProduct(id);

		return new ResponseEntity<String>("Product deleted successfully!.", HttpStatus.OK);
	}

}
