package com.example.tubesp3b;

public class TravelCourses {
    private String course_id;
    private String source;
    private String destination;
    private String datetime;
    private String vehicle;
    private int num_seats;
    private int[] seats;
    private int fee;

    public TravelCourses(String course_id, String source, String destination, String datetime, String vehicle, int num_seats, int[] seats, int fee){
        this.course_id = course_id;
        this.source = source;
        this.destination = destination;
        this.datetime = datetime;
        this.vehicle = vehicle;
        this.num_seats = num_seats;
        this.seats = seats;
        this.fee = fee;
    }

    public String getCourseId() {
        return course_id;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public String getDateTime() {
        return datetime;
    }

    public String getVehicleType() {
        return vehicle;
    }

    public int getNum_Seats() {
        return num_seats;
    }

    public int[] getSeats() {
        return seats;
    }

    public int getFee() {
        return fee;
    }

    public void setCourseId(String courseId) {
        this.course_id = courseId;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDateTime(String datetime) {
        this.datetime = datetime;
    }

    public void setVehicleType(String vehicle) {
        this.vehicle = vehicle;
    }

    public void setNum_Seats(int num_seats) {
        this.num_seats = num_seats;
    }

    public void setSeats(int[] seats) {
        this.seats = seats;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }
}
