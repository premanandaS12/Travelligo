package com.example.tubesp3b;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

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
        // Inflate the layout for this fragment
        binding = FragmentGopayBinding.inflate(inflater);
        View view = binding.getRoot();

        return view;
    }

    @Override
    public void onClick(View v){
    }
}