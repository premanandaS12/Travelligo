package com.example.tubesp3b;

public class Order {
    private String order_id;
    private int[] seats;

    public Order(String order_id, int[] seats) {
        this.order_id = order_id;
        this.seats = seats;
    }

    public String getOrderId() {
        return order_id;
    }

    public void setOrderId(String orderId) {
        this.order_id = order_id;
    }

    public int[] getSeats() {
        return seats;
    }

    public void setSeats(int[] seats) {
        this.seats = seats;
    }
}
