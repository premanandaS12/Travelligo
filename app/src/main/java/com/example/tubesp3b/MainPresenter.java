package com.example.tubesp3b;

import android.content.Context;

public class MainPresenter {
    protected IMainActivity ui;
    private Context context;
    private MainActivity activity;

    public MainPresenter(IMainActivity view, MainActivity activity){
        this.ui = view;
        this.activity = activity;
        this.context = activity.getBaseContext();
    }


}
