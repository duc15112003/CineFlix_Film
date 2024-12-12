package com.cineflix.vn.model.dto.response;

public class OauthResponse {
    private String token;
    private String email;

    // Constructor
    public OauthResponse(String token, String email) {
        this.token = token;
        this.email = email;
    }

    // Getters and Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
