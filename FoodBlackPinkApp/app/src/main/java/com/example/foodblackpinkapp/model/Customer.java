package com.example.foodblackpinkapp.model;

import com.google.gson.annotations.SerializedName;

public class Customer {
    @SerializedName("customerId")
    private String customerId;
    @SerializedName("email")
    private String email;
    @SerializedName("phone")
    private String phone;
    @SerializedName("fullname")
    private String fullname;
    @SerializedName("password")
    private String password;
    @SerializedName("photo")
    private String photo;

    public Customer(String customerId, String password) {
        this.customerId=customerId;
        this.password=password;
    }

    public Customer(String customerId, String email, String phone, String fullname, String password) {
        this.customerId = customerId;
        this.email = email;
        this.phone = phone;
        this.fullname = fullname;
        this.password = password;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @SerializedName("roleId")
    private int roleId;

}
