package com.example.fooddr.Activites;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.fooddr.Models.User;
import com.example.fooddr.Network.ApiService;
import com.example.fooddr.Network.LoginRetrofit;
import com.example.fooddr.Network.SharePreferenceManager;
import com.example.fooddr.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {

    private EditText editTextName;
    private EditText editTextPassword;
    private ImageButton btnLogin;
    private TextView btnRegister;
    private ApiService apiService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideTitleBar();
        setContentView(R.layout.activity_login);

        anhXaAttribute();
        if (SharePreferenceManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
            return;
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });


    }

    private void anhXaAttribute() {
        editTextName = findViewById(R.id.editTextTextEmail);
        editTextPassword = findViewById(R.id.editTextTextPassword);
        btnLogin = findViewById(R.id.imageButtonRegistor);
        btnRegister = findViewById(R.id.textViewRegister);
    }

    private void login() {
        final String userName = editTextName.getText().toString();
        final String userPassword = editTextPassword.getText().toString();
        apiService = LoginRetrofit.getInstance();
        apiService.login(userName, userPassword).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject obj = new JSONObject(response.body().string());
                        if (!obj.getBoolean("error")) {
                            Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            JSONObject userJson = obj.getJSONObject("user");

                            User user = new User(
                                    userJson.getInt("id"),
                                    userJson.getString("username"),
                                    userJson.getString("fname"),
                                    userJson.getString("email"),
                                    userJson.getString("gender"),
                                    userJson.getString("images")
                            );

                            SharePreferenceManager.getInstance(getApplicationContext()).userLogin(user);
                            finish();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                        }

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Lỗi đăng nhập", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
