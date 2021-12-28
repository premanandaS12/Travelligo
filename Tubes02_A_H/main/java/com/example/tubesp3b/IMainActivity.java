package com.example.tubesp3b;

import java.util.List;

public interface IMainActivity {
    public void toastMessage(String msg);
    public void changePage(int page);
    public void updateAsal(List<String> asal);
    public void updateTujuan(List<String> tujuan);
    public void updateJamBerangkat(List<String> jam);
    public void updateVehicle(List<String> vehicleType);
    public void ruteDipilih(Payload payload);
    public void updatePoolLocation(List<Shuttle> poolLocation);
    public void updateUname(String username);
    public void updateHistory(List<TravelOrderHist> history);
    public void updateCourse(TravelCourses travelCourses, boolean[] booked, boolean[] dipencet, int page);
    public void updateTiket(String username, TravelOrderHist history);
    public void displayTicket(Order order, String username);
}
