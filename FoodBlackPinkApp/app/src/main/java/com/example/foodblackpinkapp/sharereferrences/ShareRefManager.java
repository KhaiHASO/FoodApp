package com.example.foodblackpinkapp.sharereferrences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.foodblackpinkapp.activity.LoginActivity;
import com.example.foodblackpinkapp.model.Customer;

public class ShareRefManager {
    private static final String SHARED_PREF_NAME = "retrofitregisterlogin";
    private static final String KEY_CUSTOMERID = "keycustomerid";
    private static final String KEY_EMAIL = "keyemail";
    private static final String KEY_PHONE = "keyphone";
    private static final String KEY_FULLNAME = "keyfullname";
    private static final String KEY_PHOTO = "keyphoto";

    private static ShareRefManager mInstance;
    private Context mContext;

    private ShareRefManager(Context context) {
        mContext = context;
    }

    public static synchronized ShareRefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new ShareRefManager(context.getApplicationContext());
        }
        return mInstance;
    }

    public void customerLogin(Customer customer) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_CUSTOMERID, customer.getCustomerId());
        editor.putString(KEY_EMAIL, customer.getEmail());
        editor.putString(KEY_FULLNAME, customer.getFullname());
        editor.putString(KEY_PHONE, customer.getPhone());
        editor.putString(KEY_PHOTO, customer.getPhoto());
        editor.apply();
    }

    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_CUSTOMERID, null) != null;
    }

    public Customer getCustomer() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new Customer(
                sharedPreferences.getString(KEY_CUSTOMERID, null),
                sharedPreferences.getString(KEY_EMAIL, null),
                sharedPreferences.getString(KEY_PHONE, null),
                sharedPreferences.getString(KEY_FULLNAME, null),
                sharedPreferences.getString(KEY_PHOTO, null)
        );
    }

    public void logout() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        Intent intent = new Intent(mContext, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        mContext.startActivity(intent);
    }
}
