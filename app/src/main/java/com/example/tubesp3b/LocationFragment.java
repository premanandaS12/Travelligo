package com.example.tubesp3b;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tubesp3b.databinding.FragmentLocationBinding;

import java.util.List;

public class LocationFragment extends Fragment implements IMainActivity {
    private MainActivity activity;
    private Context context;
    private MainPresenter presenter;
    private LocationAdapter adapter;
    private FragmentLocationBinding binding;


    public LocationFragment(MainActivity activity, Context context) {
        this.activity = activity;
        this.context = context;
    }


    public static LocationFragment newInstance(MainActivity activity, Context context) {
        LocationFragment fragment = new LocationFragment(activity, context);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.presenter = new MainPresenter(this,this.activity,this.context);
        this.binding = FragmentLocationBinding.inflate(inflater, container, false);
        this.adapter = LocationAdapter.getLocationAdapter(activity);
        this.presenter.getLocation();

        this.binding.listLocation.setAdapter(this.adapter);

        return this.binding.getRoot();
    }

    @Override
    public void toastMessage(String msg) {

    }

    @Override
    public void changePage(int page) {

    }

    @Override
    public void updateAsal(List<String> asal) {

    }

    @Override
    public void updateTujuan(List<String> tujuan) {

    }

    @Override
    public void updateJamBerangkat(List<String> jam) {

    }

    @Override
    public void updateVehicle(List<String> vehicleType) {

    }

    @Override
    public void ruteDipilih(Payload payload) {

    }

    @Override
    public void updatePoolLocation(List<Shuttle> poolLocation) {
        Log.d("poolLocation", poolLocation.toString());
        this.adapter.updateLocListAdapter(poolLocation);
    }

    @Override
    public void updateUname(String username) {

    }

    @Override
    public void updateHistory(List<TravelOrderHist> history) {

    }

    @Override
    public void updateCourse(TravelCourses travelCourses, boolean[] booked, boolean[] dipencet, int page) {

    }

    @Override
    public void updateTiket(String username, TravelOrderHist history) {

    }

    @Override
    public void displayTicket(Order order, String username) {

    }
}