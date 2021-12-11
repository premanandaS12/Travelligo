package com.example.tubesp3b;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public class LocationAdapter extends BaseAdapter {
    private List<Shuttle> location;
    private MainActivity activity;
    private MainPresenter presenter;
    protected static LocationAdapter singleton;
    @Override
    public int getCount() {
        return location.size();
    }

    @Override
    public Object getItem(int i) {
        return location.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }

    public void updateLocListAdapter(List<Shuttle> getLocation){
        this.location = getLocation;
        notifyDataSetChanged();
    }

    private class ViewHolder implements View.OnClickListener{
        private MainPresenter presenter;
        private Shuttle curLocation;


        @Override
        public void onClick(View view) {

        }
    }
}
