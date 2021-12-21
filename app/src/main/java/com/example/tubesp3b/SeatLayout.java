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

import com.example.tubesp3b.databinding.FragmentSelectSeatBinding;

public class SeatLayout extends Fragment implements View.OnTouchListener, IMainActivity, View.OnClickListener {
    private FragmentSelectSeatBinding binding;
    private CustomGestureListener customGestureListener;
    private boolean[] booked;
    private boolean[] dipencet;
    private Context context;
    private MainActivity activity;
    private MainPresenter mainPresenter;
    private String courseId="";
    private int hargaTiket;
    private String asal;
    private String tujuan;
    private String datetime;

    public SeatLayout(MainActivity activity, Context context) {
        this.activity = activity;
        this.context = context;
    }

    public static SeatLayout newInstance(MainActivity activity, Context context){
        SeatLayout fragment = new SeatLayout(activity, context);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.mainPresenter = new MainPresenter(this,this.activity,this.context);
        this.binding = FragmentSelectSeatBinding.inflate(inflater,container,false);
        this.getParentFragmentManager().setFragmentResultListener("courseSeatBesar", this, new FragmentResultListener() {
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
//                sendToBayar(booked,dipencet,courseId,source,destination,dateTime,fee);

            }
        });
        this.binding.seatLayout.post(new Runnable() {
            @Override
            public void run() {
                int w = binding.seatLayout.getWidth();
                int h = binding.seatLayout.getHeight();
                setSeat(w,h);
            }
        });
        this.binding.seatLayout.setOnTouchListener(this);
        this.binding.btnOrder.setOnClickListener(this);
        this.binding.seatLayout.invalidate();


        return this.binding.getRoot();
    }

    public void updateUi(boolean[] booked, boolean[] dipencet, String courseId, String source, String destination, String dateTime, int fee){
        this.booked=booked;
        this.dipencet=dipencet;
        this.courseId = courseId;
        this.asal = source;
        this.tujuan = destination;
        this.datetime = dateTime;
        this.hargaTiket = fee;
        this.binding.seatAsal.setText(this.asal);
        this.binding.seatTujuan.setText(this.tujuan);
        this.binding.textViewHari.setText(this.datetime);
        this.binding.hargaTiket.setText("Rp "+String.valueOf(this.hargaTiket));
    }



    public void setSeat(int w, int h){
        Bitmap bitmap = Bitmap.createBitmap(w,h, Bitmap.Config.ARGB_8888);
        this.binding.seatLayout.setImageBitmap(bitmap);
        Canvas canvas = new Canvas(bitmap);

        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#ebeaef"));
        Paint paintRed = new Paint();
        paintRed.setColor(Color.parseColor("#FF002D"));
        Paint p1 = new Paint();
        p1.setTextSize(50);
        p1.setColor(Color.GRAY);

        this.customGestureListener = new CustomGestureListener(canvas,w,h);


        int top = 50;
        int bottom = 200;
        int seatnum = 1;
        int toptext = 125;
        int counter=0;

        for(int i=0;i<5;i++){
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
            pembayaran.putString("vehicleType","Large");
            pembayaran.putString("seat",this.mainPresenter.getSeatNumber(this.booked,this.dipencet));
            pembayaran.putInt("jumlahSeat",this.mainPresenter.jumlahSeatDipesan(this.booked,this.dipencet));
            this.getParentFragmentManager().setFragmentResult("bayarSeatBesar",pembayaran);

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
            int top = 50;
            int bottom = 200;
            int counter=0;

            for(int i=0;i<5;i++){
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
            binding.seatLayout.invalidate();
            return super.onDown(e);
        }
    }
}
