package com.example.foodblackpinkapp.model;

import com.google.gson.annotations.SerializedName;

public class ProductDetail {
    @SerializedName("productDetailId")
    private int productDetailId;
    @SerializedName("productId")
    private int productId;
    @SerializedName("image")
    private String image;

    public int getProductDetailId() {
        return productDetailId;
    }

    public void setProductDetailId(int productDetailId) {
        this.productDetailId = productDetailId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "ProductDetail{" +
                "productDetailId=" + productDetailId +
                ", productId=" + productId +
                ", image='" + image + '\'' +
                '}';
    }
}
