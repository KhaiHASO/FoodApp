package com.example.fooddr.Network;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.fooddr.Activites.LoginActivity;
import com.example.fooddr.Models.User;

public class SharePreferenceManager {

    private static final String KEY_ID = "keyid";
    private static final String SHARED_PREF_NAME = "retrofitregisterlogin";
    private static final String KEY_USERNAME = "keyusername";
    private static final String KEY_FNAME = "keyfname";
    private static final String KEY_EMAIL = "keyemail";
    private static final String KEY_GENDER = "keygender";
    private static final String KEY_IMAGES = "keyimages";
    private static SharePreferenceManager mInstance;
    private  Context context;

    public SharePreferenceManager(Context context) {
        this.context = context;
    }

    public static synchronized SharePreferenceManager getInstance(Context context)
    {
        if (mInstance==null)
        {
            mInstance = new SharePreferenceManager(context);
        }
        return mInstance;
    }

    public boolean isLoggedIn()
    {
        SharedPreferences sharePreferenceManager = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharePreferenceManager.getString(KEY_USERNAME,null) !=null;
    }

    public void userLogin(User user) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_ID, user.getId());
        editor.putString(KEY_USERNAME, user.getUserName());
        editor.putString(KEY_FNAME, user.getFullName());
        editor.putString(KEY_EMAIL, user.getEmail());
        editor.putString(KEY_GENDER, user.getGender());
        editor.putString(KEY_IMAGES, user.getImages());
        editor.apply();
    }

    public User getUser() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getInt(KEY_ID, -1),
                sharedPreferences.getString(KEY_USERNAME, null),
                sharedPreferences.getString(KEY_FNAME, null),
                sharedPreferences.getString(KEY_EMAIL, null),
                sharedPreferences.getString(KEY_GENDER, null),
                sharedPreferences.getString(KEY_IMAGES, null)
        );
    }

    public void logout() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        context.startActivity(new Intent(context, LoginActivity.class));
    }
}
