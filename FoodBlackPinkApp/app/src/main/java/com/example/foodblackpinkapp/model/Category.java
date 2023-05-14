package com.example.foodblackpinkapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Category implements Serializable {
    @SerializedName("categoryId")
    private int categoryId;
    @SerializedName("image")
    private String categoryImage;
    @SerializedName("name")
    private String categoryName;

    public Category(int categoryId, String categoryImage, String categoryName) {
        this.categoryId = categoryId;
        this.categoryImage = categoryImage;
        this.categoryName = categoryName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        this.categoryImage = categoryImage;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
