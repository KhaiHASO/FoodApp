package com.example.foodblackpinkapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
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

public class RegisterActivity extends BaseActivity {


    private ApiService apiService;
    private EditText registerFullNameEditText;
    private EditText registerUsernameEditText;
    private EditText registerPasswordEditText;
    private EditText registerEmailEditText;
    private EditText registerMobileNumberEditText;
    private Button registerButton;
    private TextView textViewLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideTitleBar();
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        // Initialize views for register layout
        // Initialize views for register layout
        // Assume you have EditText and Button for registration
        registerFullNameEditText = findViewById(R.id.editTextRegisterFullName);
        registerUsernameEditText = findViewById(R.id.editTextRegisterUserName);
        registerPasswordEditText = findViewById(R.id.editTextRegisterPassword);
        registerEmailEditText = findViewById(R.id.editTextRegisterEmail);
        registerMobileNumberEditText = findViewById(R.id.editTextRegisterMobileNumber);
        registerButton = findViewById(R.id.btnRegister);
        textViewLogin = findViewById(R.id.textViewLogin);

        // Set onClickListener here...
        registerButton.setOnClickListener(v -> performRegistration());
        textViewLogin.setOnClickListener(v -> startLoginActivity());
    }

    private void performRegistration() {
        String fullName = registerFullNameEditText.getText().toString().trim();
        String username = registerUsernameEditText.getText().toString().trim();
        String password = registerPasswordEditText.getText().toString().trim();
        String email = registerEmailEditText.getText().toString().trim();
        String mobileNumber = registerMobileNumberEditText.getText().toString().trim();

        if (TextUtils.isEmpty(fullName) || TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(email) || TextUtils.isEmpty(mobileNumber)) {
            Toast.makeText(RegisterActivity.this, "Vui lòng điền đầy đủ thông tin đăng ký", Toast.LENGTH_SHORT).show();
            return;
        }

        Customer registrationRequest = new Customer(username,email,mobileNumber,fullName,password);
        apiService = RetrofitBase.getInstance();
        Call<Customer> call = apiService.register(registrationRequest);
        call.enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                if (response.isSuccessful()) {
                    Customer customer = response.body();
                    startLoginActivity();
                } else {
                    String errorBody = response.errorBody().toString();
                    Log.d("RegistrationError", "Đăng ký không thành công: " + errorBody);
                    Toast.makeText(RegisterActivity.this, "Đăng ký không thành công, username, email, hoặc sđt trùng", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Lỗi đăng ký: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        // You can also use an Intent to explicitly navigate to the previous Activity
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    private void startLoginActivity() {
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }, 0);
    }


}