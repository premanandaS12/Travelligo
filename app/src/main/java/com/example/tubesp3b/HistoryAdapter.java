package com.example.tubesp3b;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.tubesp3b.databinding.FragmentLocationItemBinding;
import com.example.tubesp3b.databinding.FragmentMyBookingHistoryItemBinding;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends BaseAdapter {
    private List<TravelOrderHist> history;
    protected static HistoryAdapter singleton;
    private MainActivity activity;
    private String username;

    public HistoryAdapter(MainActivity activity){
        this.activity = activity;
        this.history = new ArrayList<>();
    }

    public static HistoryAdapter getHistoryAdapter(MainActivity activity){
        if(HistoryAdapter.singleton == null){
            HistoryAdapter.singleton = new HistoryAdapter(activity);
        }
        return HistoryAdapter.singleton;
    }
    @Override
    public int getCount() {
        return history.size();
    }

    @Override
    public Object getItem(int i) {
        return history.get(i);
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
            FragmentMyBookingHistoryItemBinding binding = FragmentMyBookingHistoryItemBinding.inflate(this.activity.getLayoutInflater(), viewGroup, false);
            view = binding.getRoot();
            viewHolder = new ViewHolder(binding, (TravelOrderHist)this.getItem(i), this.username);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.updateView((TravelOrderHist) this.getItem(i),this.username);
        return view;
    }

    public void updateHistoryListAdapter(List<TravelOrderHist> getHist){
        this.history=getHist;
        notifyDataSetChanged();
    }

    public void updateUname(String username){
        this.username = username;
        notifyDataSetChanged();
    }

    private class ViewHolder{
        private FragmentMyBookingHistoryItemBinding binding;
        private TravelOrderHist currHistory;
        private String username;

        public ViewHolder(FragmentMyBookingHistoryItemBinding binding, TravelOrderHist history, String username){
            this.binding = binding;
            this.currHistory = history;
            this.username = username;
        }

        public void updateView(TravelOrderHist currHis, String username){
            this.currHistory = currHis;
            this.username = username;
            this.binding.bookHistAsal.setText(this.currHistory.getSource());
            this.binding.bookHistTujuan.setText(this.currHistory.getDestination());
            this.binding.tanggalBerangkatHist.setText(this.currHistory.getOrder_datetime());
            this.binding.namaPemesanHist.setText(this.username);
            String tempSeats="";
            for(int i=0;i<this.currHistory.getSeats().length;i++){
                if(i==0){
                    tempSeats+=String.valueOf(this.currHistory.getSeats()[i]);
                }else{
                    tempSeats+=", "+String.valueOf(this.currHistory.getSeats()[i]);
                }
            }
            this.binding.seatNumHist.setText(tempSeats);
            this.binding.idBookHist.setText(this.currHistory.getOrder_id());

        }
    }
}
