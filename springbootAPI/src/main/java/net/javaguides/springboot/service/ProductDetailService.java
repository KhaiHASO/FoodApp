package net.javaguides.springboot.service;

import net.javaguides.springboot.model.ProductDetail;

import java.util.List;

public interface ProductDetailService {
    ProductDetail saveProductDetail(ProductDetail productDetail);
    List<ProductDetail> getAllProductDetails();
    ProductDetail getProductDetailById(int id);
    ProductDetail updateProductDetail(ProductDetail productDetail, int id);
    void deleteProductDetail(int id);
    List<ProductDetail> getProductDetailByProductId(Integer productId);
}

