package com.example.fooddr.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
public class User implements Serializable {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("username")
    @Expose
    private String userName;

    @SerializedName("fname")
    @Expose
    private String fullName;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("gender")
    @Expose
    private String gender;

    @SerializedName("images")
    @Expose
    private String images;

    public User(int id, String userName, String fullName, String email, String gender, String images) {
        this.id = id;
        this.userName = userName;
        this.fullName = fullName;
        this.email = email;
        this.gender = gender;
        this.images = images;
    }

    public User(int id, String username, String email, String gender) {
        this.id=id;
        this.userName=username;
        this.email=email;
        this.gender=gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
}
