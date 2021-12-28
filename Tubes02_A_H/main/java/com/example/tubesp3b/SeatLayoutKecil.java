package com.example.tubesp3b;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.example.tubesp3b.databinding.FragmentSelectSeatKecilBinding;

public class SeatLayoutKecil extends Fragment implements View.OnTouchListener, IMainActivity, View.OnClickListener {
    private FragmentSelectSeatKecilBinding binding;
    private CustomGestureListener customGestureListener;
    private boolean[] booked;
    private boolean[] dipencet;
    private Context context;
    private MainActivity activity;
    private MainPresenter mainPresenter;
    String courseId="";
    private String asal;
    private String tujuan;
    private String datetime;
    private int hargaTiket;

    public SeatLayoutKecil(MainActivity activity, Context context) {
        this.activity = activity;
        this.context = context;
    }

    public static SeatLayoutKecil newInstance(MainActivity activity, Context context){
        SeatLayoutKecil fragment = new SeatLayoutKecil(activity, context);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.binding = FragmentSelectSeatKecilBinding.inflate(inflater,container,false);
        this.mainPresenter = new MainPresenter(this,this.activity,this.context);
        this.getParentFragmentManager().setFragmentResultListener("courseSeatKecil", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                boolean[] booked = result.getBooleanArray("booked");
                boolean[] dipencet = result.getBooleanArray("dipencet");
                String courseId = result.getString("courseId");
                String source = result.getString("source");
                String destination = result.getString("destination");
                String dateTime = result.getString("dateTime");
                int fee = result.getInt("fee");

                updateUi(booked,dipencet,courseId,source,destination,dateTime,fee);

            }
        });
        this.binding.seatLayoutKecil.post(new Runnable() {
//            Dilakukan untuk mendapatkan width dan height dari imageview lalu dimasukkan ke bitmap untuk canvas
            @Override
            public void run() {
                int w = binding.seatLayoutKecil.getWidth();
                int h = binding.seatLayoutKecil.getHeight();
                setSeat(w,h);
            }
        });
        this.binding.seatLayoutKecil.setOnTouchListener(this);
        this.binding.btnOrder.setOnClickListener(this);
        this.binding.seatLayoutKecil.invalidate();

        return this.binding.getRoot();
    }

    public void setSeat(int w, int h){
        Bitmap bitmap = Bitmap.createBitmap(w,h, Bitmap.Config.ARGB_8888);
        this.binding.seatLayoutKecil.setImageBitmap(bitmap);
        Canvas canvas = new Canvas(bitmap);

        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#ebeaef"));
        Paint paintRed = new Paint();
        paintRed.setColor(Color.parseColor("#FF002D"));
        Paint p1 = new Paint();
        p1.setTextSize(50);
        p1.setColor(Color.GRAY);

        this.customGestureListener = new CustomGestureListener(canvas,w,h);

//        gambar seat pada canvas
//        jika status booked dan dipencet adalah true, menandakan seat tersebut telah diorder oleh pihak lain dan akan di gambar sebagai kotak berwarna merah
//        jika belum diorder atau masih available statusnya, warna seat putih
        int top = 50;
        int bottom = 200;
        int seatnum = 1;
        int toptext = 125;
        int counter=0;

        for(int i=0;i<3;i++){
            int left = (w/2)-175;
            int right = (w/2)-25;
            int leftText = (w/2)-250;
            for(int j = 0;j<2;j++){
                if(booked[counter]==true && dipencet[counter]==true){
                    Rect rect = new Rect(left,top,right,bottom);
                    canvas.drawRect(rect, paintRed);
                }else if(booked[counter]==false && dipencet[counter]==false){
                    Rect rect = new Rect(left,top,right,bottom);
                    canvas.drawRect(rect, paint);
                }
                if(j%2==0){
                    canvas.drawText(String.valueOf(seatnum),leftText,toptext,p1);
                }else{
                    canvas.drawText(String.valueOf(seatnum),leftText,toptext,p1);
                }
                left=right+50;
                right=left+150;
                leftText=right+75;
                seatnum++;
                counter++;
            }
            toptext+=200;
            top=bottom+50;
            bottom=top+150;
        }
    }

    public void updateUi(boolean[] booked, boolean[] dipencet, String courseId, String source, String destination, String dateTime, int fee){

        this.booked=booked;
        this.dipencet=dipencet;
        this.courseId = courseId;
        this.asal = source;
        this.tujuan = destination;
        this.datetime = dateTime;
        this.hargaTiket = fee;

        this.binding.tvDestinasiAwal.setText(this.asal);
        this.binding.tvDestinasiTujuan.setText(this.tujuan);
        this.binding.tanggalHari.setText(this.datetime);
        this.binding.tvHarga.setText("Rp "+String.valueOf(this.hargaTiket));
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return this.customGestureListener.onDown(motionEvent);
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
        if(view==this.binding.btnOrder){
            Bundle pembayaran = new Bundle();
            pembayaran.putString("username",this.mainPresenter.getUsernameFromDb());
            pembayaran.putString("courseId", this.courseId);
            pembayaran.putString("source",this.asal);
            pembayaran.putString("destination",this.tujuan);
            pembayaran.putString("dateTime",this.datetime);
            pembayaran.putInt("fee",this.hargaTiket);
            pembayaran.putString("vehicleType","Small");
            pembayaran.putString("seat",this.mainPresenter.getSeatNumber(this.booked,this.dipencet));
            pembayaran.putInt("jumlahSeat",this.mainPresenter.jumlahSeatDipesan(this.booked,this.dipencet));
            this.getParentFragmentManager().setFragmentResult("bayarSeatKecil",pembayaran);

            Bundle bun = new Bundle();
            bun.putInt("page", 12);
            this.getParentFragmentManager().setFragmentResult("changePage",bun);
        }
    }

    private class CustomGestureListener extends GestureDetector.SimpleOnGestureListener {
        private Canvas canvas;
        private Paint paintBlue;
        private Paint paintWhite;
        private Paint paintRed1;
        private int w;
        private int h;
        public CustomGestureListener(Canvas canvas, int w, int h){
            this.canvas = canvas;
            this.paintWhite = new Paint();
            this.paintBlue = new Paint();
            this.paintRed1 = new Paint();
            this.paintBlue.setColor(Color.parseColor("#012F50"));
            this.paintWhite.setColor(Color.parseColor("#ebeaef"));
            this.paintRed1.setColor(Color.parseColor("#FF002D"));
            this.w = w;
            this.h = h;
        }

        @Override
        public boolean onDown(MotionEvent e) {
//            Method untuk mengecek kursi mana yang dipilih user
//            Kursi yang dipilih user akan diubah menjadi biru
            int top = 50;
            int bottom = 200;
            int counter=0;
            for(int i=0;i<3;i++){
                int left = (this.w/2)-175;
                int right = (this.w/2)-25;
                for(int j = 0;j<2;j++){
                    if(e.getX()>=left && e.getX()<=right && e.getY()>=top && e.getY()<=bottom) {
                        if(booked[counter]!=true && dipencet[counter]==false){
                            Rect rect = new Rect(left, top, right, bottom);
                            canvas.drawRect(rect, paintBlue);
                            dipencet[counter]=true;
                        }else if(booked[counter]!=true && dipencet[counter]==true){
                            Rect rect = new Rect(left, top, right, bottom);
                            canvas.drawRect(rect, paintWhite);
                            dipencet[counter]=false;
                        }else if(booked[counter]==true && dipencet[counter]==true){
                            Rect rect = new Rect(left, top, right, bottom);
                            canvas.drawRect(rect, paintRed1);
                            dipencet[counter]=false;
                        }
                    }
                    left=right+50;
                    right=left+150;
                    counter++;
                }
                top=bottom+50;
                bottom=top+150;
            }
            binding.seatLayoutKecil.invalidate();
            return super.onDown(e);
        }
    }
}

