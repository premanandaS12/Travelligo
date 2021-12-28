package com.example.tubesp3b;

public class Pesan {
    private String course_id;
    private String seats;

    public Pesan(String course_id, String seats) {
        this.course_id = course_id;
        this.seats = seats;
    }

    public String getCourseid() {
        return course_id;
    }

    public void setCourseid(String course_id) {
        this.course_id = course_id;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }
}
