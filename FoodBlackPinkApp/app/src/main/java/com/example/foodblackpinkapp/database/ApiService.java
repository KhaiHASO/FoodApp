package com.example.foodblackpinkapp.database;

import com.example.foodblackpinkapp.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;

public interface ApiService {

    @GET("products")
    Call<List<Product>> getAllProducts();
}
