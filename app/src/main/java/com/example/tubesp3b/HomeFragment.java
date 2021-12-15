package com.example.tubesp3b;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tubesp3b.databinding.FragmentHomeBinding;


public class HomeFragment extends Fragment implements View.OnClickListener {
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
        this.binding.btnBook.setOnClickListener(this);
        this.binding.btnPoint.setOnClickListener(this);
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
            Log.d("msk tombol","pencet");
            Bundle args = new Bundle();
            args.putInt("page",2);
            this.getParentFragmentManager().setFragmentResult("changePage",args);
        }else if(view==this.binding.btnPoint){
//            Bundle args = new Bundle();
//            args.putInt("page",2);
//            this.getParentFragmentManager().setFragmentResult("changePage",args);
        }else if(view==this.binding.btnPoint){
//            Bundle args = new Bundle();
//            args.putInt("page",2);
//            this.getParentFragmentManager().setFragmentResult("changePage",args);
        }else if(view==this.binding.gopayPromos){
//            Bundle args = new Bundle();
//            args.putInt("page",2);
//            this.getParentFragmentManager().setFragmentResult("changePage",args);
        }else if(view==this.binding.ovoPromos){
//            Bundle args = new Bundle();
//            args.putInt("page",2);
//            this.getParentFragmentManager().setFragmentResult("changePage",args);
        }else if(view==this.binding.shopeepayPromos){
//            Bundle args = new Bundle();
//            args.putInt("page",2);
//            this.getParentFragmentManager().setFragmentResult("changePage",args);
        }else if(view==this.binding.btnTelp){

        }else if(view==this.binding.btnWa){

        }
    }
}