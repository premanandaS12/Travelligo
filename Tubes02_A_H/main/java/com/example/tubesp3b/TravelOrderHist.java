package com.example.tubesp3b;

public class TravelOrderHist {
    private String order_id;
    private String course_id;
    private String source;
    private String destination;
    private String vehicle;
    private String order_datetime;
    private String course_datetime;
    private int[] seats;
    private int fee;

    public TravelOrderHist(String order_id, String course_id, String source, String destination, String vehicle, String order_datetime, String course_datetime, int[] seats, int fee) {
        this.order_id = order_id;
        this.course_id = course_id;
        this.source = source;
        this.destination = destination;
        this.vehicle = vehicle;
        this.order_datetime = order_datetime;
        this.course_datetime = course_datetime;
        this.seats = seats;
        this.fee = fee;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getOrder_datetime() {
        return order_datetime;
    }

    public void setOrder_datetime(String order_datetime) {
        this.order_datetime = order_datetime;
    }

    public String getCourse_datetime() {
        return course_datetime;
    }

    public void setCourse_datetime(String course_datetime) {
        this.course_datetime = course_datetime;
    }

    public int[] getSeats() {
        return seats;
    }

    public void setSeats(int[] seats) {
        this.seats = seats;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }
}
