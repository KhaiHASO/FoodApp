package com.example.foodblackpinkapp.model;

import java.util.List;

public class MessageVideo {
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<Video> getResult() {
        return result;
    }

    public void setResult(List<Video> result) {
        this.result = result;
    }

    boolean success;
    List<Video> result;
}