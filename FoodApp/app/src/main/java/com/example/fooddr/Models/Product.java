package com.example.fooddr.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Product {
    @SerializedName("idMeal")
    private int idMeal;
    @SerializedName("strMeal")
    private String nameMeal;
    @SerializedName("idCategory")
    private int idCate;
    @SerializedName("nameCategory")
    private String nameCategory;
    @SerializedName("area")
    private String area;
    @SerializedName("strmealthumb")
    private String nameMealThumbDetail;
    @SerializedName("strMealThumb")
    private String nameMealThumb;
    @SerializedName("instructions")
    private String instructions;
    @SerializedName("price")
    private double price;

    public boolean success;
    public String message;
    public List<Product> result;

    public Product(int idMeal, String nameMeal, int idCate, String nameCategory, String area, String nameMealThumbDetail, String nameMealThumb, String instructions, double price, boolean success, String message, List<Product> result) {
        this.idMeal = idMeal;
        this.nameMeal = nameMeal;
        this.idCate = idCate;
        this.nameCategory = nameCategory;
        this.area = area;
        this.nameMealThumbDetail = nameMealThumbDetail;
        this.nameMealThumb = nameMealThumb;
        this.instructions = instructions;
        this.price = price;
        this.success = success;
        this.message = message;
        this.result = result;
    }

    public int getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(int idMeal) {
        this.idMeal = idMeal;
    }

    public String getNameMeal() {
        return nameMeal;
    }

    public void setNameMeal(String nameMeal) {
        this.nameMeal = nameMeal;
    }

    public int getIdCate() {
        return idCate;
    }

    public void setIdCate(int idCate) {
        this.idCate = idCate;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getNameMealThumbDetail() {
        return nameMealThumbDetail;
    }

    public void setNameMealThumbDetail(String nameMealThumbDetail) {
        this.nameMealThumbDetail = nameMealThumbDetail;
    }

    public String getNameMealThumb() {
        return nameMealThumb;
    }

    public void setNameMealThumb(String nameMealThumb) {
        this.nameMealThumb = nameMealThumb;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Product> getResult() {
        return result;
    }

    public void setResult(List<Product> result) {
        this.result = result;
    }
}
