package com.example.tubesp3b;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tubesp3b.databinding.FragmentProfileBinding;

import java.util.List;

public class ProfileFragment extends Fragment implements IMainActivity, View.OnClickListener{
    private Context context;
    private MainActivity activity;
    private MainPresenter mainPresenter;
    private FragmentProfileBinding binding;

    public ProfileFragment(MainActivity activity, Context context) {
        this.activity = activity;
        this.context = context;
    }


    public static ProfileFragment newInstance(MainActivity activity, Context context) {
        ProfileFragment fragment = new ProfileFragment(activity, context);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding = FragmentProfileBinding.inflate(inflater, container,false);
        this.mainPresenter = new MainPresenter(this,this.activity,this.context);
        this.binding.tvNama.setText(this.mainPresenter.getUsernameFromDb());
        this.binding.tvPoint.setText(String.valueOf(this.mainPresenter.getPoint()));
        this.binding.btnLogout.setOnClickListener(this);
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

    @Override
    public void onClick(View view) {
        this.mainPresenter.logout();
    }
}