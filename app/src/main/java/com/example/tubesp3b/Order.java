package com.example.tubesp3b;

public class Order {
    private String orderId;
    private int[] seats;

    public Order(String orderId, int[] seats) {
        this.orderId = orderId;
        this.seats = seats;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int[] getSeats() {
        return seats;
    }

    public void setSeats(int[] seats) {
        this.seats = seats;
    }
}
