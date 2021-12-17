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

}
