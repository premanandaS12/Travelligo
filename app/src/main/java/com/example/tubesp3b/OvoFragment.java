package com.example.tubesp3b;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.tubesp3b.databinding.FragmentOvoBinding;

public class OvoFragment extends Fragment implements View.OnClickListener {

    private FragmentOvoBinding binding;

    private Context context;
    private MainActivity activity;

    public OvoFragment(MainActivity activity, Context context) {
        this.activity = activity;
        this.context = context;
    }

    public static OvoFragment newInstance(MainActivity activity, Context context) {
        OvoFragment fragment = new OvoFragment(activity, context);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentOvoBinding.inflate(inflater);
        View view = binding.getRoot();

        Glide.with(this.activity)
                .load("https://commons.wikimedia.org/wiki/File:Logo_ovo_purple.svg#/media/File:Logo_ovo_purple.svg")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(this.binding.ivOvo);

        return view;
    }

    @Override
    public void onClick(View v){
    }
}
