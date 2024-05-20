package com.example.ecommerceSpring.responses;

public class LoginResponse {
    private String token;
    private String fullName;

    public LoginResponse() {
    }

    public LoginResponse(String token, String fullName, long expiresIn) {
        this.token = token;
        this.fullName = fullName;
        this.expiresIn = expiresIn;
    }

    private long expiresIn;

    public String getToken() {
        return token;
    }

    public LoginResponse setToken(String token) {
        this.token = token;
        return this;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public LoginResponse setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "token='" + token + '\'' +
                ", expiresIn=" + expiresIn +
                '}';
    }
}