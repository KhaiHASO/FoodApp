package com.example.fooddr.Activites;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fooddr.Models.User;
import com.example.fooddr.Network.SharePreferenceManager;
import com.example.fooddr.R;

public class ProfileActivity extends BaseActivity implements View.OnClickListener {

    TextView id, userName, fname, userEmail, gender;
    Button btnLogout, btnBack;
    ImageView imageViewprofile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideTitleBar();
        setContentView(R.layout.activity_profile);

        if (SharePreferenceManager.getInstance(this).isLoggedIn()) {
            id = findViewById(R.id.textViewId);
            userName = findViewById(R.id.textViewUserNamee);
            fname = findViewById(R.id.textViewName);
            userEmail = findViewById(R.id.textViewEmail);
            gender = findViewById(R.id.textViewGender);
            btnLogout = findViewById(R.id.buttonLogout);
            imageViewprofile = findViewById(R.id.imageViewProfile);

            User user = SharePreferenceManager.getInstance(this).getUser();
            id.setText(String.valueOf(user.getId()));
            userName.setText(user.getUserName());
            userEmail.setText(user.getEmail());
            gender.setText(user.getGender());
            fname.setText(user.getUserName());

            Glide.with(getApplicationContext())
                    .load(user.getImages())
                    .into(imageViewprofile);

            btnLogout.setOnClickListener(this);
        } else {
            Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        btnBack = findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.equals(btnLogout)) {
            SharePreferenceManager.getInstance(getApplicationContext()).logout();
        }
    }
}
