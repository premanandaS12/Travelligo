package com.example.tubesp3b;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tubesp3b.databinding.FragmentLoginBinding;
import com.example.tubesp3b.databinding.FragmentMyBookingHistoryBinding;

import java.net.MalformedURLException;
import java.util.List;

public class MyBookingHistoryFragment extends Fragment implements IMainActivity {

    private FragmentMyBookingHistoryBinding binding;
    private MainActivity activity;
    private MainPresenter presenter;
    private Context context;
    private HistoryAdapter adapter;


    public MyBookingHistoryFragment(MainActivity activity, Context context) {
        this.activity = activity;
        this.context = context;
    }

    public static MyBookingHistoryFragment newInstance(MainActivity activity, Context context) {
        MyBookingHistoryFragment fragment = new MyBookingHistoryFragment(activity,context);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.presenter = new MainPresenter(this,this.activity, this.context);
        this.binding = FragmentMyBookingHistoryBinding.inflate(inflater,container,false);
        this.adapter = HistoryAdapter.getHistoryAdapter(this.activity);
        try {
            int orderCount = this.presenter.getOrderCount();
            Log.d("oc",String.valueOf(orderCount));
            int loop = (int) Math.ceil(orderCount*1.0/10);
            if(loop==0){
                loop=1;
            }
            int limit=10;
            int offset=1;
            for(int i=0;i<loop;i++){
                this.presenter.getTravelOrderHist(limit,offset);
                offset=limit+1;
                limit=limit+limit;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return this.binding.getRoot();
    }

    @Override
    public void toastMessage(String msg) {
        Toast.makeText(this.context, msg, Toast.LENGTH_SHORT).show();
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
        this.adapter.updateUname(username);

    }

    @Override
    public void updateHistory(List<TravelOrderHist> history) {
        Log.d("history",history.toString());
        this.adapter.updateHistoryListAdapter(history);
        this.binding.listBookHistory.setAdapter(this.adapter);
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