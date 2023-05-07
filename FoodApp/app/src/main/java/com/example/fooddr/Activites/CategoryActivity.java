package com.example.fooddr.Activites;

import android.os.Bundle;
import android.util.Log;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddr.Adapter.ProductAdapter;
import com.example.fooddr.Models.Product;
import com.example.fooddr.Network.ApiService;
import com.example.fooddr.Network.CategoryRetrofit;
import com.example.fooddr.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryActivity extends BaseActivity {
    List<Product> listMeal;
    ProductAdapter adapterProduct;
    RecyclerView recyclerViewMealList;
    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideTitleBar();
        setContentView(R.layout.activity_category);
        getMeal();
    }
    private void getMeal() {
        int idcategory = getIntent().getIntExtra("idcategory", 1);
        apiService = CategoryRetrofit.getInstance();
        recyclerViewMealList = findViewById(R.id.rc_meals);
        apiService.getMeal(idcategory).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    listMeal = response.body();
                    adapterProduct = new ProductAdapter(CategoryActivity.this, listMeal);
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(CategoryActivity.this, 2);
                    recyclerViewMealList.setLayoutManager(layoutManager);
                    recyclerViewMealList.setAdapter(adapterProduct);
                    adapterProduct.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }
}