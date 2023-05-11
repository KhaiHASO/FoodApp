package net.javaguides.springboot.controller;

import java.util.List;

import net.javaguides.springboot.model.ProductDetail;
import net.javaguides.springboot.service.ProductDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product-details")
public class ProductDetailController {

    private ProductDetailService productDetailService;

    public ProductDetailController(ProductDetailService productDetailService) {
        super();
        this.productDetailService = productDetailService;
    }

    // build create product detail REST API
    @PostMapping()
    public ResponseEntity<ProductDetail> saveProductDetail(@RequestBody ProductDetail productDetail){
        return new ResponseEntity<ProductDetail>(productDetailService.saveProductDetail(productDetail), HttpStatus.CREATED);
    }

    // build get all product details REST API
    @GetMapping
    public List<ProductDetail> getAllProductDetails(){
        return productDetailService.getAllProductDetails();
    }

    // build get product detail by id REST API
    // http://localhost:8080/api/product-details/1
    @GetMapping("{id}")
    public ResponseEntity<ProductDetail> getProductDetailById(@PathVariable("id") int productDetailId){
        return new ResponseEntity<ProductDetail>(productDetailService.getProductDetailById(productDetailId), HttpStatus.OK);
    }

    // build update product detail REST API
    // http://localhost:8080/api/product-details/1
    @PutMapping("{id}")
    public ResponseEntity<ProductDetail> updateProductDetail(@PathVariable("id") int id, @RequestBody ProductDetail productDetail){
        return new ResponseEntity<ProductDetail>(productDetailService.updateProductDetail(productDetail, id), HttpStatus.OK);
    }

    // build delete product detail REST API
    // http://localhost:8080/api/product-details/1
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProductDetail(@PathVariable("id") int id){

        // delete product detail from DB
        productDetailService.deleteProductDetail(id);

        return new ResponseEntity<String>("Product detail deleted successfully!.", HttpStatus.OK);
    }

    // build get product detail by product id REST API
    // http://localhost:8080/api/product-details/product/1
    @GetMapping("/product/{productId}")
    public List<ProductDetail> getProductDetailByProductId(@PathVariable Integer productId){
        return productDetailService.getProductDetailByProductId(productId);
    }

}
