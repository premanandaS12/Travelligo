package com.example.tubesp3b;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.example.tubesp3b.databinding.FragmentPembayaranBinding;

import org.json.JSONException;

import java.util.List;

public class PembayaranFragment extends Fragment implements View.OnClickListener, IMainActivity{
    private FragmentPembayaranBinding binding;
    private MainActivity activity;
    private Context context;
    private MainPresenter mainPresenter;
    private String username;
    private String courseId;
    private String asal;
    private String tujuan;
    private String dateTime;
    private int fee;
    private String vehicleType;
    private String seat;
    private int jumlahSeat;
    private int harga;

    public PembayaranFragment(MainActivity activity, Context context) {
        this.activity = activity;
        this.context = context;
    }

    public static PembayaranFragment newInstance(MainActivity activity, Context context) {
        PembayaranFragment fragment = new PembayaranFragment(activity,context);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.binding = FragmentPembayaranBinding.inflate(inflater, container, false);
        this.mainPresenter = new MainPresenter(this,this.activity,this.context);
        this.getParentFragmentManager().setFragmentResultListener("bayarSeatBesar", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String username = result.getString("username");
                String courseId = result.getString("courseId");
                String asal = result.getString("source");
                String tujuan = result.getString("destination");
                String dateTime = result.getString("dateTime");
                int fee = result.getInt("fee");
                String vehicleType = result.getString("vehicleType");
                String seat = result.getString("seat");
                int jumlahSeat = result.getInt("jumlahSeat");

                updateUi(username,courseId,asal,tujuan,dateTime,fee,vehicleType,seat,jumlahSeat);

            }
        });

        this.getParentFragmentManager().setFragmentResultListener("bayarSeatKecil", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String username = result.getString("username");
                String courseId = result.getString("courseId");
                String asal = result.getString("source");
                String tujuan = result.getString("destination");
                String dateTime = result.getString("dateTime");
                int fee = result.getInt("fee");
                String vehicleType = result.getString("vehicleType");
                String seat = result.getString("seat");
                int jumlahSeat = result.getInt("jumlahSeat");

                updateUi(username,courseId,asal,tujuan,dateTime,fee,vehicleType,seat,jumlahSeat);

            }
        });
        this.binding.btnOrder.setOnClickListener(this);
        this.binding.btnCancel.setOnClickListener(this);

        return this.binding.getRoot();
    }

    public void updateUi(String username, String courseId, String asal, String tujuan, String dateTime, int fee, String vehicleType, String seat, int jumlahSeat){
        this.username = username;
        this.courseId = courseId;
        this.asal = asal;
        this.tujuan = tujuan;
        this.dateTime = dateTime;
        this.fee = fee;
        this.vehicleType = vehicleType;
        this.seat = seat;
        this.jumlahSeat = jumlahSeat;
        this.harga = this.fee*this.jumlahSeat;

        this.binding.tvAsalTiket.setText(this.asal);
        this.binding.tvTujuanTiket.setText(this.tujuan);
        this.binding.tvTanggalTiket.setText(this.dateTime);
        this.binding.tvHargaTiket.setText("Rp "+String.valueOf(harga));
        this.binding.namaPemesanTiket.setText(this.username);
        this.binding.tvSeat.setText(this.seat);
        this.binding.tvVehicleType.setText(this.vehicleType);
    }

    @Override
    public void onClick(View v){
        if(v == binding.btnOrder){
            try {
                this.mainPresenter.order(this.courseId,this.seat);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else if(v==this.binding.btnCancel){
            Bundle bun = new Bundle();
            bun.putInt("page", 1);
            this.getParentFragmentManager().setFragmentResult("changePage",bun);
        }
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
        Bundle tiket = new Bundle();
        tiket.putString("source",this.asal);
        tiket.putString("destination",this.tujuan);
        tiket.putString("dateTime",this.dateTime);
        tiket.putInt("hargatotal",this.harga);
        tiket.putString("username",username);
        tiket.putString("seat",this.seat);
        tiket.putString("idtiket",order.getOrderId());
        tiket.putString("vehicleType",this.vehicleType);
        this.getParentFragmentManager().setFragmentResult("tiket",tiket);

        Bundle bun = new Bundle();
        bun.putInt("page", 4);
        this.getParentFragmentManager().setFragmentResult("changePage",bun);

    }
}

