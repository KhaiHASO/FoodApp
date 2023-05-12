package com.example.foodblackpinkapp.database;

import android.content.Intent;

import com.example.foodblackpinkapp.model.Customer;
import com.example.foodblackpinkapp.model.Product;
import com.example.foodblackpinkapp.model.ProductDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @GET("products")
    Call<List<Product>> getAllProducts();

    @GET("product-details/product/{productId}")
    Call<List<ProductDetail>> getProductDetailByProductId(@Path("productId") int productId);

    @POST("customers/login")
    Call<Customer> login(@Body Customer loginRequest);

    @POST("customers")
    Call<Customer> register(@Body Customer registerRequest);

}
