package com.example.fooddr.Activites;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fooddr.Adapter.CategoryAdapter;
import com.example.fooddr.Adapter.LastProductAdapter;
import com.example.fooddr.Models.Category;
import com.example.fooddr.Models.LastProduct;
import com.example.fooddr.Models.User;
import com.example.fooddr.Network.ApiService;
import com.example.fooddr.Network.CategoryRetrofit;
import com.example.fooddr.Network.SharePreferenceManager;
import com.example.fooddr.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {
    //profile
    TextView userName, btnLogout;
    ImageView imageViewProfile;
    //list category, product
    RecyclerView recyclerViewCategoryList;
    RecyclerView recyclerViewProductList;
    CategoryAdapter adapterCategoryAdapter;
    LastProductAdapter adapterLastProduct;
    List<Category> listCategories;
    List<LastProduct> listLastProduct;
    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideTitleBar();
        setContentView(R.layout.activity_main);
        getProfile();
        getCategory();
        getLastProduct();
    }

    private void getProfile()
    {
        if (SharePreferenceManager.getInstance(this).isLoggedIn()) {
            userName = findViewById(R.id.textViewUserName);
            imageViewProfile = findViewById(R.id.imageView3);
            User user = SharePreferenceManager.getInstance(this).getUser();
            userName.setText(user.getUserName());
            Glide.with(getApplicationContext())
                    .load(user.getImages())
                    .circleCrop()
                    .into(imageViewProfile);
        }
        else {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        imageViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ProfileActivity.class);
                startActivity(intent);
            }
        });
    }
    private void getCategory() {
        apiService = CategoryRetrofit.getInstance();
        recyclerViewCategoryList = findViewById(R.id.recyclerViewCategory);
        apiService.getCategories().enqueue(new Callback<List<Category>>() {
            @Override   
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful()) {
                    listCategories = response.body();
                    adapterCategoryAdapter = new CategoryAdapter(MainActivity.this, listCategories);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
                    recyclerViewCategoryList.setLayoutManager(linearLayoutManager);
                    recyclerViewCategoryList.setAdapter(adapterCategoryAdapter);
                    adapterCategoryAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }
    private void getLastProduct()
    {
        apiService = CategoryRetrofit.getInstance();
        recyclerViewProductList = findViewById(R.id.recyclerViewProduct);
        apiService.getLastProduct().enqueue(new Callback<List<LastProduct>>() {
            @Override
            public void onResponse(Call<List<LastProduct>> call, Response<List<LastProduct>> response) {
                if(response.isSuccessful())
                {
                    listLastProduct = response.body();
                    adapterLastProduct = new LastProductAdapter(MainActivity.this, listLastProduct);
                    GridLayoutManager linearLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
                    recyclerViewProductList.setLayoutManager(linearLayoutManager);
                    recyclerViewProductList.setAdapter(adapterLastProduct);
                    adapterLastProduct.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<LastProduct>> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }


}