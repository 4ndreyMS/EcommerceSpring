package com.example.ecommerceSpring.responses;

import com.example.ecommerceSpring.dtos.ExceptionDto;

import java.util.List;

public class ApiResponse<T> {
    private boolean success;
    private List<ExceptionDto> errors;
    private T data;

    public ApiResponse() {
    }

    public ApiResponse(boolean success, List<ExceptionDto> errors, T data) {
        this.success = success;
        this.errors = errors;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<ExceptionDto> getErrors() {
        return errors;
    }

    public void setErrors(List<ExceptionDto> errors) {
        this.errors = errors;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}