package com.example.fooddr.Network;

import com.example.fooddr.Models.Category;
import com.example.fooddr.Models.LastProduct;
import com.example.fooddr.Models.Product;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @FormUrlEncoded
    @POST("registrationapi.php?apicall=login")
    Call<ResponseBody> login (@Field("username") String username, @Field("password") String password);
    @GET("categories.php")
    Call<List<Category>> getCategories();
    @GET("lastproduct.php")
    Call<List<LastProduct>> getLastProduct();
    @FormUrlEncoded
    @POST("getcategory.php")
    Call<List<Product>> getMeal(@Field("idcategory") int idcategory);

    @FormUrlEncoded
    @POST("newmealdetail.php")
    Call<Product> getMealDetail(@Field("id") String id);



}
