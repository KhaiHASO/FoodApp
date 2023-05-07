package com.example.fooddr.Network;

public class LoginRetrofit extends BaseClient{
    private static final String BASE_URL = "http://app.iotstar.vn/shoppingapp/";
    private static ApiService apiService;
    public static ApiService getInstance() {
        if (apiService == null) return createService(ApiService.class, BASE_URL);
        return apiService;
    }
}
