package com.example.tubesp3b;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tubesp3b.databinding.FragmentHomeBinding;

import java.util.List;


public class HomeFragment extends Fragment implements View.OnClickListener, IMainActivity {
    private Context context;
    private MainActivity activity;
    private MainPresenter mainPresenter;
    private FragmentHomeBinding binding;

    public HomeFragment(MainActivity activity, Context context) {
        this.activity = activity;
        this.context = context;
    }

    public static HomeFragment newInstance(MainActivity activity, Context context) {
        HomeFragment fragment = new HomeFragment(activity, context);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding = FragmentHomeBinding.inflate(inflater,container,false);
        this.mainPresenter = new MainPresenter(this, this.activity, this.context);
        this.binding.tvUsername.setText(this.mainPresenter.getUsernameFromDb());
        this.binding.btnBook.setOnClickListener(this);
        this.binding.btnLocation.setOnClickListener(this);
        this.binding.btnTelp.setOnClickListener(this);
        this.binding.btnWa.setOnClickListener(this);
        this.binding.gopayPromos.setOnClickListener(this);
        this.binding.ovoPromos.setOnClickListener(this);
        this.binding.shopeepayPromos.setOnClickListener(this);
        return this.binding.getRoot();
    }

    @Override
    public void onClick(View view) {
        if(view==this.binding.btnBook){
            Bundle args = new Bundle();
            args.putInt("page",2);
            this.getParentFragmentManager().setFragmentResult("changePage",args);
        }else if(view==this.binding.btnLocation){
            Bundle args = new Bundle();
            args.putInt("page",6);
            this.getParentFragmentManager().setFragmentResult("changePage",args);
        }else if(view==this.binding.gopayPromos){
            Bundle args = new Bundle();
            args.putInt("page",7);
            this.getParentFragmentManager().setFragmentResult("changePage",args);
        }else if(view==this.binding.ovoPromos){
            Bundle args = new Bundle();
            args.putInt("page",8);
            this.getParentFragmentManager().setFragmentResult("changePage",args);
        }else if(view==this.binding.shopeepayPromos){
            Bundle args = new Bundle();
            args.putInt("page",9);
            this.getParentFragmentManager().setFragmentResult("changePage",args);
        }else if(view==this.binding.btnTelp){

        }else if(view==this.binding.btnWa){

        }
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
}