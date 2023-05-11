package com.example.foodblackpinkapp.database;

import android.content.Intent;

import com.example.foodblackpinkapp.model.Product;
import com.example.foodblackpinkapp.model.ProductDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("products")
    Call<List<Product>> getAllProducts();

    @GET("/api/product-details/product/{productId}")
    Call<List<ProductDetail>> getProductDetailByProductId(@Path("productId") int productId);
}
