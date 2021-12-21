package com.example.tubesp3b;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.tubesp3b.databinding.FragmentGopayBinding;

public class GopayFragment extends Fragment implements View.OnClickListener {

    private FragmentGopayBinding binding;

    private Context context;
    private MainActivity activity;

    public GopayFragment(MainActivity activity, Context context) {
        this.activity = activity;
        this.context = context;
    }

    public static GopayFragment newInstance(MainActivity activity, Context context) {
        GopayFragment fragment = new GopayFragment(activity, context);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGopayBinding.inflate(inflater);
        View view = binding.getRoot();

        Glide.with(this.activity)
                .load("https://1000logos.net/wp-content/uploads/2021/05/GoPay-Logo.png")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(this.binding.ivGopay);

        return view;
    }

    @Override
    public void onClick(View v){
    }
}