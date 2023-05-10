package com.example.fooddr.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LastProduct implements Serializable {
    @SerializedName("id")
    public  String id;
    @SerializedName("strMeal")
    public  String nameLastPD;
    @SerializedName("strMealThumb")
    public  String imageLastPD;
    @SerializedName("idMeal")
    public  String idMealPD;
    @SerializedName("idcategory")
    public  String idcategoryPD;

    public LastProduct(String id, String nameLastPD, String imageLastPD, String idMealPD, String idcategoryPD) {
        this.id = id;
        this.nameLastPD = nameLastPD;
        this.imageLastPD = imageLastPD;
        this.idMealPD = idMealPD;
        this.idcategoryPD = idcategoryPD;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameLastPD() {
        return nameLastPD;
    }

    public void setNameLastPD(String nameLastPD) {
        this.nameLastPD = nameLastPD;
    }

    public String getImageLastPD() {
        return imageLastPD;
    }

    public void setImageLastPD(String imageLastPD) {
        this.imageLastPD = imageLastPD;
    }

    public String getIdMealPD() {
        return idMealPD;
    }

    public void setIdMealPD(String idMealPD) {
        this.idMealPD = idMealPD;
    }

    public String getIdcategoryPD() {
        return idcategoryPD;
    }

    public void setIdcategoryPD(String idcategoryPD) {
        this.idcategoryPD = idcategoryPD;
    }
}
