package com.example.foodblackpinkapp.database;

import android.content.Intent;

import com.example.foodblackpinkapp.model.Cart;
import com.example.foodblackpinkapp.model.Customer;
import com.example.foodblackpinkapp.model.Order;
import com.example.foodblackpinkapp.model.OrderDetail;
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

    @POST("carts")
    Call<Cart> addToCart(@Body Cart cart);

    @POST("orders")
    Call<Order> placeOrder(@Body Order order);

    @POST("order-details")
    Call<OrderDetail> addOrderDetail(@Body OrderDetail orderDetail);

}
