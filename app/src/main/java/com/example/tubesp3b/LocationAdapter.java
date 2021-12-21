package com.example.tubesp3b;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.tubesp3b.databinding.FragmentLocationItemBinding;

import java.util.List;

public class LocationAdapter extends BaseAdapter {
    private List<Shuttle> location;
    private MainActivity activity;
    protected static LocationAdapter singleton;

    public LocationAdapter(MainActivity activity){
        this.activity = activity;
    }

    public static LocationAdapter getLocationAdapter(MainActivity activity){
        if(LocationAdapter.singleton == null){
            LocationAdapter.singleton = new LocationAdapter(activity);
        }
        return LocationAdapter.singleton;
    }
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
        View convertView = view;
        ViewHolder viewHolder;
        if(convertView==null){
            FragmentLocationItemBinding binding = FragmentLocationItemBinding.inflate(this.activity.getLayoutInflater(), viewGroup, false);
            view = binding.getRoot();
            viewHolder = new ViewHolder(binding, (Shuttle)this.getItem(i));
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.updateView((Shuttle) this.getItem(i));
        return view;
    }

    public void updateLocListAdapter(List<Shuttle> getLocation){
        this.location = getLocation;
        notifyDataSetChanged();
    }

    private class ViewHolder{
        private FragmentLocationItemBinding binding;
        private Shuttle currPoolLocation;

        public ViewHolder(FragmentLocationItemBinding binding, Shuttle shuttle){
            this.binding = binding;
            this.currPoolLocation = shuttle;
        }

        public void updateView(Shuttle currPoolLocation){
            this.currPoolLocation = currPoolLocation;
            this.binding.cityLocation.setText(this.currPoolLocation.getCity());
            this.binding.addressLocation.setText(this.currPoolLocation.getAddress());
        }
    }
}
