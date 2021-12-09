package com.example.tubesp3b;

public class User {
    private String username;
    private String password;
    private String token;

    public User(String username, String password){
        this.username = username;
        this.password = password;
        this.token="";
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
