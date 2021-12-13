package com.example.tubesp3b;

public class Pesan {
    private String courseid;
    private String seats;

    public Pesan(String courseid, String seats) {
        this.courseid = courseid;
        this.seats = seats;
    }

    public String getCourseid() {
        return courseid;
    }

    public void setCourseid(String courseid) {
        this.courseid = courseid;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }
}
