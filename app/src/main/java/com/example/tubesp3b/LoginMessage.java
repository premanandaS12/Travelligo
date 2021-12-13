package com.example.tubesp3b;

public class LoginMessage {
    private String message;
    private String token;
    private String bearer;

    public LoginMessage(String message, String token){
        this.message = message;
        this.token = token;
        this.bearer = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjp7InVzZXJfaWQiOiI5ZTQ4MzIyNi00ZDhhLTExZWMtYjIzNC03MzZlY2E3OTFkMjYiLCJ1c2VybmFtZSI6InRlc3RpbmcxIn0sImlhdCI6MTYzODE3MjgzN30.5Rst_vM4yam9YVNuLG1NCdMqcA4Y78WhmvCzbGHrD7k";
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
