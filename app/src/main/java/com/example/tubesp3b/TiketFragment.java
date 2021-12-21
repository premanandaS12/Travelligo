package com.example.tubesp3b;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.example.tubesp3b.databinding.FragmentBookNowBinding;
import com.example.tubesp3b.databinding.FragmentMyBookingHistoryBinding;
import com.example.tubesp3b.databinding.FragmentTiketBinding;

public class TiketFragment extends Fragment {

    private FragmentTiketBinding binding;
    private MainActivity activity;
    private MainPresenter presenter;
    private Context context;
    private HistoryAdapter adapter;


    public TiketFragment(MainActivity activity, Context context) {
        this.activity = activity;
        this.context = context;
    }

    public static TiketFragment newInstance(MainActivity activity, Context context) {
        TiketFragment fragment = new TiketFragment(activity,context);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding = FragmentTiketBinding.inflate(inflater, container, false);
        this.getParentFragmentManager().setFragmentResultListener("tiket", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String username = result.getString("username");
                String orderId = result.getString("idtiket");
                String asal = result.getString("source");
                String tujuan = result.getString("destination");
                String dateTime = result.getString("dateTime");
                int fee = result.getInt("hargatotal");
                String seat = result.getString("seat");
                String vehicleType = result.getString("vehicleType");
                updateUi(username, orderId, asal, tujuan, dateTime, fee, seat, vehicleType);

            }
        });

        return this.binding.getRoot();
    }

    public void updateUi(String username, String orderId, String asal, String tujuan, String dateTime, int fee, String seat, String vehicleType){
        this.binding.tvAsalTiket.setText(asal);
        this.binding.tvTujuanTiket.setText(tujuan);
        this.binding.tvTanggalTiket.setText(dateTime);
        this.binding.tvHargaTiket.setText("Rp "+String.valueOf(fee));
        this.binding.namaPemesanTiket.setText(username);
        this.binding.tvSeat.setText(seat);
        this.binding.tvVehType.setText(vehicleType);
        this.binding.tvIdTiket.setText(orderId);
    }
}
