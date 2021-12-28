package com.example.tubesp3b;

public class LoginMessage {
    private String message;
    private String token;
    private String bearer;

    public LoginMessage(String message, String token){
        this.message = message;
        this.token = token;
        this.bearer = "Bearer ";
    }


    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setBearer(String bearer) {
        this.bearer = bearer;
    }

    public String getBearer() {
        return bearer;
    }
}
