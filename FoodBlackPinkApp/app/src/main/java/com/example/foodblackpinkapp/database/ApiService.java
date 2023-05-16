package com.example.foodblackpinkapp.database;

import com.example.foodblackpinkapp.model.Cart;
import com.example.foodblackpinkapp.model.CartProductViewDTO;
import com.example.foodblackpinkapp.model.Category;
import com.example.foodblackpinkapp.model.Customer;
import com.example.foodblackpinkapp.model.MessageVideo;
import com.example.foodblackpinkapp.model.Order;
import com.example.foodblackpinkapp.model.OrderDetail;
import com.example.foodblackpinkapp.model.Product;
import com.example.foodblackpinkapp.model.ProductDetail;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {

    @GET("products")
    Call<List<Product>> getAllProducts();
    @GET("categories")
    Call<List<Category>> getCategories();
    @GET("/api/products/category/{categoryId}")
    Call<List<Product>> getProductsByCategoryId(@Path("categoryId") int categoryId);

    @GET("product-details/product/{productId}")
    Call<List<ProductDetail>> getProductDetailByProductId(@Path("productId") int productId);

    @POST("customers/login")
    Call<Customer> login(@Body Customer loginRequest);

    @POST("customers")
    Call<Customer> register(@Body Customer registerRequest);

    @POST("carts")
    Call<Cart> addToCart(@Body Cart cart);

    @PUT("carts/{cartId}")
    Call<Cart> updateCart(@Path("cartId") int cartId, @Body Cart updatedCart);

    @POST("orders")
    Call<Order> placeOrder(@Body Order order);

    @POST("order-details")
    Call<OrderDetail> addOrderDetail(@Body OrderDetail orderDetail);

    @GET("cart-products/{customerId}")
    Call<List<CartProductViewDTO>> getCartProductsByCustomerId(@Path("customerId") String customerId);

    @PUT("carts/update")
    Call<Void> updateCart(@Body CartProductViewDTO cartProductViewDTO);


    @DELETE("carts/delete/{customerId}/{productId}")
    Call<Void> deleteCart(@Path("customerId") String customerId, @Path("productId") Integer productId);

    @POST("carts/checkout/{customerId}")
    Call<Void> checkoutCart(@Path("customerId")String customerId);

    Gson gson = new GsonBuilder().setDateFormat("yyyy MM dd HH:mm:ss").create();
    ApiService servieapi = new Retrofit.Builder()
            .baseUrl("http://app.iotstar.vn/appfoods/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(ApiService.class);

    @GET("getvideos.php")
    Call<MessageVideo> getVideos();
}




