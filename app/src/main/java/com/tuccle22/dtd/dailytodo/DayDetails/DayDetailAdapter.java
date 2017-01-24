package com.tuccle22.dtd.dailytodo.DayDetails;

/**
 * Created by Tucker
 * For Daily To Do
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tuccle22.dtd.dailytodo.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DayDetailAdapter extends RecyclerView.Adapter<DayDetailAdapter.MyViewHolder> {


    public static ArrayList<DayDetail> day_detail_list;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item) TextView item;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public DayDetailAdapter(ArrayList<DayDetail> day_detail_list) {
        DayDetailAdapter.day_detail_list = day_detail_list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.day_detail, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        DayDetail day_detail = day_detail_list.get(position);
        holder.item.setText("test");

    }

    @Override
    public int getItemCount() {
        return day_detail_list.size();
    }

}