package com.example.tubesp3b;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.tubesp3b.databinding.FragmentShopeeBinding;

public class ShopeeFragment extends Fragment implements View.OnClickListener {

    private FragmentShopeeBinding binding;

    private Context context;
    private MainActivity activity;

    public ShopeeFragment(MainActivity activity, Context context) {
        this.activity = activity;
        this.context = context;
    }

    public static ShopeeFragment newInstance(MainActivity activity, Context context) {
        ShopeeFragment fragment = new ShopeeFragment(activity, context);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentShopeeBinding.inflate(inflater);
        View view = binding.getRoot();

        Glide.with(this.activity)
                .load("https://www.freepnglogos.com/uploads/shopee-logo-png/shopee-logo-digital-economy-forum-mdcc-1.png")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(this.binding.ivShopee);

        return view;
    }

    @Override
    public void onClick(View v){
    }
}