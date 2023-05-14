package com.example.foodblackpinkapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.example.foodblackpinkapp.databinding.ActivityProductByCategoryBinding;
import com.example.foodblackpinkapp.model.Product;
import com.example.foodblackpinkapp.database.ApiService;
import com.example.foodblackpinkapp.database.RetrofitBase;
import com.example.foodblackpinkapp.adapter.ProductGridAdapter;
import com.example.foodblackpinkapp.utils.StringUtil;
import com.example.foodblackpinkapp.constant.GlobalFunction;
import com.example.foodblackpinkapp.constant.Constant;
import com.example.foodblackpinkapp.R;
import com.example.foodblackpinkapp.activity.FoodDetailActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProductByCategoryActivity extends BaseActivity {
    private ActivityProductByCategoryBinding mActivityProductByCategoryBinding;
    private ApiService mApiService;
    private Product mProduct;
    private List<Product> mListProduct;
    private ProductGridAdapter mProductGridAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityProductByCategoryBinding= mActivityProductByCategoryBinding.inflate(getLayoutInflater());
        setContentView(mActivityProductByCategoryBinding.getRoot());
        getListProductByCategoryFromApi("");

    }
    private void getListProductByCategoryFromApi(String key) {
        mApiService = RetrofitBase.getInstance();
        int idcategory= getIntent().getIntExtra("categoryId",1);
        mApiService.getProductsByCategoryId(idcategory).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    mListProduct = new ArrayList<>();
                    for (Product product : response.body()) {
                        if (StringUtil.isEmpty(key)) {
                            mListProduct.add(0, product);
                        } else {
                            if (GlobalFunction.getTextSearch(product.getName()).toLowerCase().trim()
                                    .contains(GlobalFunction.getTextSearch(key).toLowerCase().trim())) {
                                mListProduct.add(0, product);
                            }
                        }
                    }

                    for (Product product : mListProduct) {
                        Log.d("HomeFragment", "Category: " + product.toString());
                    }
                    displayListProductByCategoryId();
                }
            }
            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                GlobalFunction.showToastMessage(getApplicationContext(), getString(R.string.msg_get_data_error));
            }
        });
    }
    private void displayListProductByCategoryId() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        mActivityProductByCategoryBinding.rcvProduct.setLayoutManager(gridLayoutManager);

        mProductGridAdapter = new ProductGridAdapter(mListProduct, this::goToFoodDetail);
        mActivityProductByCategoryBinding.rcvProduct.setAdapter(mProductGridAdapter);
    }
    private void goToFoodDetail(@NonNull Product product) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.KEY_INTENT_FOOD_OBJECT, product);
        GlobalFunction.startActivity(getApplicationContext(), FoodDetailActivity.class, bundle);
    }
}