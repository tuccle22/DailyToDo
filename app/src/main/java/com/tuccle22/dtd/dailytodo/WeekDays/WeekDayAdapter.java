package com.tuccle22.dtd.dailytodo.WeekDays;

/**
 * Created by Tucker
 * For Daily To Do
 */

import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.tuccle22.dtd.dailytodo.R;

import java.util.ArrayList;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;

public class WeekDayAdapter extends RecyclerView.Adapter<WeekDayAdapter.MyViewHolder> {


    public static ArrayList<Days> daysList;


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.day_number) com.github.pavlospt.roundedletterview.RoundedLetterView day_of_month;
        @BindView(R.id.day_text) TextView day_of_week;
        @BindColor(R.color.colorPrimary) int color_primary;

        
        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            day_of_month.setOnClickListener(this);

        }
        @Override
        public void onClick(View v) {
            View view = v.getRootView();
            ViewPager view_pager = (ViewPager) view.findViewById(R.id.view_pager);
            view_pager.setCurrentItem(getAdapterPosition(), true);
        }
    }

    public WeekDayAdapter(ArrayList<Days> daysList) {
        WeekDayAdapter.daysList = daysList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.week_day, parent, false);
        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Days days = daysList.get(position);
        if (position == 0) {
            holder.day_of_month.setBackgroundColor(holder.color_primary);
        }
        holder.day_of_month.setTitleText(days.getDayOfMonth());
        holder.day_of_week.setText(days.getDayOfWeek());
        
    }

    @Override
    public int getItemCount() {
        return daysList.size();
    }

}