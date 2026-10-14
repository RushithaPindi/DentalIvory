package com.dental.ivory.model.response;

import lombok.Data;

@Data
public class ClinicLoginUserResponse {

    private String token;
    private String message;

    // Getters and Setters

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}