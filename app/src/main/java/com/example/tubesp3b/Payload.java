package com.example.tubesp3b;

import android.text.format.Time;

import java.util.Date;

public class Payload {
    private String source;
    private String destination;
    private Integer fee;

    public Payload(String source, String destination, Integer fee){
        this.source = source;
        this.destination = destination;
        this.fee = fee;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDestination() {
        return destination;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }

    public Integer getFee() {
        return fee;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSource() {
        return source;
    }
}
