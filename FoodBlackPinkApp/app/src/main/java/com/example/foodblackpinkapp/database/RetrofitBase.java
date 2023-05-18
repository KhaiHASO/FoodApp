package com.example.foodblackpinkapp.database;

public class RetrofitBase extends BaseClient{
    private static final String BASE_URL = "http://192.168.31.182:8080/api/";
    private static ApiService apiService;
    public static ApiService getInstance() {
        if (apiService == null) return createService(ApiService.class, BASE_URL);
        return apiService;
    }
}
