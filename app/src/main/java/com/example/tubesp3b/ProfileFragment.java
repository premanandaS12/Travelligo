package com.example.tubesp3b;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tubesp3b.databinding.FragmentBookNowBinding;
import com.example.tubesp3b.databinding.FragmentProfileBinding;


public class ProfileFragment extends Fragment {
    private Context context;
    private MainActivity activity;
    private MainPresenter mainPresenter;
    private FragmentProfileBinding binding;

    public ProfileFragment(MainActivity activity, Context context) {
        this.activity = activity;
        this.context = context;
    }


    public static ProfileFragment newInstance(MainActivity activity, Context context) {
        ProfileFragment fragment = new ProfileFragment(activity, context);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding = FragmentProfileBinding.inflate(inflater, container,false);
        return this.binding.getRoot();
    }
}