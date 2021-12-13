package com.example.tubesp3b;

import java.lang.reflect.Array;

public class TravelCourses {
    private String courseId;
    private String source;
    private String destination;
    private String dateTime;
    private String vehicleType;
    private int num_Seats;
    private int[] seats;
    private int fee;

    public TravelCourses(String courseId, String source, String destination, String dateTime, String vehicleType, int num_Seats, int[] seats, int fee){
        this.courseId = courseId;
        this.source = source;
        this.destination = destination;
        this.dateTime = dateTime;
        this.vehicleType = vehicleType;
        this.num_Seats = num_Seats;
        this.seats = seats;
        this.fee = fee;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public int getNum_Seats() {
        return num_Seats;
    }

    public int[] getSeats() {
        return seats;
    }

    public int getFee() {
        return fee;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public void setNum_Seats(int num_Seats) {
        this.num_Seats = num_Seats;
    }

    public void setSeats(int[] seats) {
        this.seats = seats;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }
}
