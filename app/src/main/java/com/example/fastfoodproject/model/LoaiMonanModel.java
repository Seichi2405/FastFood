package com.example.fastfoodproject.model;

import java.util.List;

public class LoaiMonanModel {
    boolean success;
    String message;
    List<LoaiMonan> result;

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

    public List<LoaiMonan> getResult() {
        return result;
    }

    public void setResult(List<LoaiMonan> result) {
        this.result = result;
    }
}
