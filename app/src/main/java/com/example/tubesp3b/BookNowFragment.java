package com.example.tubesp3b;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;

import com.example.tubesp3b.databinding.FragmentBookNowBinding;
import com.example.tubesp3b.databinding.FragmentHomeBinding;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class BookNowFragment extends Fragment implements View.OnClickListener, IMainActivity {
    private Context context;
    private MainActivity activity;
    private MainPresenter mainPresenter;
    private FragmentBookNowBinding binding;
    private DatePickerDialog.OnDateSetListener dateListener;
    private List<String> asal;
    private List<String> tujuan;
    private List<String> jam;
    private List<String> vehicleType;

    public BookNowFragment(MainActivity activity, Context context) {
        this.activity = activity;
        this.context = context;
        this.asal = new ArrayList<>();
        this.tujuan = new ArrayList<>();
        this.jam = new ArrayList<>();
        this.vehicleType = new ArrayList<>();
    }

    public static BookNowFragment newInstance(MainActivity activity, Context context) {
        BookNowFragment fragment = new BookNowFragment(activity, context);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding = FragmentBookNowBinding.inflate(inflater,container,false);
        this.mainPresenter = new MainPresenter(this, activity, context);

        this.mainPresenter.getRoutes();


        this.binding.tanggalBerangkat.setOnClickListener(this);

        dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int tahun, int bulan, int tanggal) {
                bulan+=1;
                Log.d("tanggal", String.valueOf(tanggal)+" - "+ String.valueOf(bulan)+" - "+String.valueOf(tahun));
                binding.tanggalBerangkat.setText(String.valueOf(tanggal)+" - "+ String.valueOf(bulan)+" - "+String.valueOf(tahun));
            }
        };

//        Spinner
//        Set adapter
        ArrayAdapter<String> adapterAsal= new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, this.asal);
        ArrayAdapter<String> adapterTujuan= new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, this.tujuan);
        ArrayAdapter<String> adapterJam = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, this.jam);
        ArrayAdapter<String> adapterVehicle = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, this.vehicleType);

        adapterAsal.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterTujuan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterJam.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterVehicle.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        this.binding.asal.setAdapter(adapterAsal);
        this.binding.tujuan.setAdapter(adapterTujuan);
        this.binding.waktuBerangkat.setAdapter(adapterJam);
        this.binding.vehicleType.setAdapter(adapterVehicle);

        this.binding.book.setOnClickListener(this);

        return this.binding.getRoot();
    }

    @Override
    public void onClick(View view) {
        if(view==this.binding.tanggalBerangkat){
            Calendar cal = Calendar.getInstance();
            int tahun = cal.get(Calendar.YEAR);
            int bulan = cal.get(Calendar.MONTH);
            int tanggal = cal.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog d = new DatePickerDialog(context, android.R.style.Theme_Holo_Light_Dialog_MinWidth, dateListener, tahun, bulan, tanggal);
            d.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            d.show();
        }else if(view==this.binding.book){

        }
    }

    @Override
    public void toastMessage(String msg) {

    }

    @Override
    public void changePage(int page) {
//        Masuk halaman pilih seat

//        Bundle args = new Bundle();
//        args.putInt("page",);
//        this.getParentFragmentManager().setFragmentResult("changePage",args);
    }

    @Override
    public void updateAsal(List<String> asal) {
        this.asal = asal;
    }

    @Override
    public void updateTujuan(List<String> tujuan) {
        this.tujuan = tujuan;
    }

    @Override
    public void updateJamBerangkat(List<String> jam) {
        this.jam = jam;
    }

    @Override
    public void updateVehicle(List<String> vehicleType) {
        this.vehicleType = vehicleType;
    }

    @Override
    public void ruteDipilih(Payload payload) {

    }

    @Override
    public void updatePoolLocation(List<Shuttle> poolLocation) {

    }

}