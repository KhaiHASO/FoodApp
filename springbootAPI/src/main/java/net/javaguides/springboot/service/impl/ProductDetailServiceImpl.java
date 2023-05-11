package net.javaguides.springboot.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.ProductDetail;
import net.javaguides.springboot.repository.ProductDetailRepository;
import net.javaguides.springboot.service.ProductDetailService;

@Service
public class ProductDetailServiceImpl implements ProductDetailService {

    private ProductDetailRepository productDetailRepository;

    public ProductDetailServiceImpl(ProductDetailRepository productDetailRepository) {
        super();
        this.productDetailRepository = productDetailRepository;
    }

    @Override
    public ProductDetail saveProductDetail(ProductDetail productDetail) {
        return productDetailRepository.save(productDetail);
    }

    @Override
    public List<ProductDetail> getAllProductDetails() {
        return productDetailRepository.findAll();
    }

    @Override
    public ProductDetail getProductDetailById(int id) {
        return productDetailRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("ProductDetail", "Id", id));
    }

    @Override
    public ProductDetail updateProductDetail(ProductDetail productDetail, int id) {
        // we need to check whether product detail with given id is exist in DB or not
        ProductDetail existingProductDetail = productDetailRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("ProductDetail", "Id", id));

        existingProductDetail.setImage(productDetail.getImage());

        // save existing product detail to DB
        productDetailRepository.save(existingProductDetail);
        return existingProductDetail;
    }

    @Override
    public void deleteProductDetail(int id) {
        // check whether a product detail exist in a DB or not
        productDetailRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("ProductDetail", "Id", id));
        productDetailRepository.deleteById(id);
    }

    @Override
    public List<ProductDetail> getProductDetailByProductId(Integer productId) {
        return productDetailRepository.getProductDetailByProductId(productId);
    }


}
