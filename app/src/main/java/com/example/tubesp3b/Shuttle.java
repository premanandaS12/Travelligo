package com.example.tubesp3b;

public class Shuttle {
    private String city;
    private String address;

    public Shuttle(String address, String city){
        this.address = address;
        this.city = city;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
