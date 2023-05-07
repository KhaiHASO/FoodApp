package com.example.fooddr.Activites;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.fooddr.Models.Product;
import com.example.fooddr.Network.ApiService;
import com.example.fooddr.Network.CategoryRetrofit;
import com.example.fooddr.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailActivity extends BaseActivity {

    ApiService apiService;
    TextView txtMealFood,txtPrice,txtDesc,txtAmount;
    ImageView imageMealDetail,minusMeal,plusMeal;
    Button btnAdd;
    int amount = 1;
    Product mealDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideTitleBar();
        setContentView(R.layout.activity_product_detail);
        Mapping();
        LoadData();
        eventClick();
    }

    private void LoadData() {
        String id = getIntent().getStringExtra("id");
        if(id != ""){
            LoadProductDetail(id);
        }
    }
    private  void Mapping(){
        txtMealFood = findViewById(R.id.txtmealfood);
        txtPrice = findViewById(R.id.txtprice);
        txtDesc = findViewById(R.id.txtDesc);
        imageMealDetail = findViewById(R.id.imageDetail);
        txtAmount = findViewById(R.id.txtAmount);
        minusMeal = findViewById(R.id.minusMeal);
        plusMeal = findViewById(R.id.plusMeal);
        btnAdd = findViewById(R.id.btnadd);
    }
    private void LoadProductDetail(String id) {

        apiService = CategoryRetrofit.getInstance();
        apiService.getMealDetail(id).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if(response.isSuccessful()){
                    Product mealDetailModel = response.body();
                    txtMealFood.setText(mealDetailModel.result.get(0).getNameMeal());
                    txtPrice.setText("$" + mealDetailModel.result.get(0).getPrice());
                    txtDesc.setText(mealDetailModel.result.get(0).getInstructions());
                    Glide.with(ProductDetailActivity.this).load(mealDetailModel.result.get(0).getNameMealThumbDetail()).into(imageMealDetail);
                }else {
                    Toast.makeText(getBaseContext(),"Lỗi",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {

            }
        });

    }
    private void eventClick() {
        plusMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount = Integer.parseInt(txtAmount.getText().toString()) + 1;
                txtAmount.setText(String.valueOf(amount));
            }
        });
        minusMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(txtAmount.getText().toString()) > 1) {
                    amount = Integer.parseInt(txtAmount.getText().toString()) - 1;
                    txtAmount.setText(String.valueOf(amount));
                }
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
            }
        });

    }

}