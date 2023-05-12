package com.example.foodblackpinkapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodblackpinkapp.R;
import com.example.foodblackpinkapp.database.ApiService;
import com.example.foodblackpinkapp.database.RetrofitBase;
import com.example.foodblackpinkapp.model.Customer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {
    private ApiService apiService;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private TextView textViewRegister;
    private boolean isLoginLayout = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideTitleBar();
        setContentView(R.layout.activity_login);
        initView();
    }


    private void initView() {
        // Initialize views for login layout
        usernameEditText = findViewById(R.id.editTextUsername);
        passwordEditText = findViewById(R.id.editTextPassword);
        loginButton = findViewById(R.id.loginButton);
        textViewRegister = findViewById(R.id.textViewRegister);

        // Set onClickListener here...
        loginButton.setOnClickListener(v -> performLogin());
        textViewRegister.setOnClickListener(v -> startRegisterActivity());
    }


    private void performLogin() {
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(LoginActivity.this, "Vui lòng điền đầy đủ thông tin đăng nhập", Toast.LENGTH_SHORT).show();
            return;
        }

        Customer loginRequest = new Customer(username, password);
        apiService = RetrofitBase.getInstance();
        Call<Customer> call = apiService.login(loginRequest);
        call.enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                if (response.isSuccessful()) {
                    Customer customer = response.body();
                    Log.d("CustomerInfo", "Customer ID: " + customer.getCustomerId());
                    Log.d("CustomerInfo", "Email: " + customer.getEmail());
                    Log.d("CustomerInfo", "Phone: " + customer.getPhone());
                    startMainActivity();
                } else {
                    String errorBody = response.errorBody().toString();
                    Log.d("LoginError", "Đăng nhập không thành công: " + errorBody);
                    Toast.makeText(LoginActivity.this, "Đăng nhập không thành công", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Lỗi đăng nhập: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void startMainActivity() {
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }, 2000);
    }

    private void startRegisterActivity() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
