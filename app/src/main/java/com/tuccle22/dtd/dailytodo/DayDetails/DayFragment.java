package com.tuccle22.dtd.dailytodo.DayDetails;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.tuccle22.dtd.dailytodo.DayInfo;
import com.tuccle22.dtd.dailytodo.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DayFragment extends Fragment {

    private Unbinder unbinder;
    private static ArrayList<DayDetail> day_detail_list = new ArrayList<>();
    public static DayDetailAdapter day_detail_adapter;
    private int position;

    @BindView(R.id.weekday_title) TextView title;
//    @BindView(R.id.detail_recycler_view) RecyclerView detail_recycler_view;

    // newInstance constructor for creating fragment with arguments
    public static DayFragment newInstance(int day) {

        Bundle args = new Bundle();
        args.putInt("POSITION", day);
        DayFragment day_fragment = new DayFragment();
        day_fragment.setArguments(args);
        return day_fragment;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_day, container, false);
        unbinder = ButterKnife.bind(this, view);
        if (savedInstanceState != null) {
            day_detail_list = savedInstanceState.getParcelableArrayList("DAY");
        }
        this.position = getArguments().getInt("POSITION");
        title.setText(getTitle());
//
//        this.position = getArguments().getInt("POSITION");
//
//        String day_week = getTitle();


//        day_detail_adapter = new DayDetailAdapter(day_detail_list);
//        RecyclerView.LayoutManager detailLayoutManager
//                = new LinearLayoutManager(inflater.getContext());
//
//        RecyclerView test = (RecyclerView) view.findViewById(R.id.detail_recycler_view);
//        detail_recycler_view.setLayoutManager(detailLayoutManager);
//        test.setAdapter(day_detail_adapter);
//        day_detail_list.add(new DayDetail("Here is text!"));

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private String getTitle() {
        if (position == 0)
            return "Today";
        else if (position == 1)
            return "Tomorrow";
        else
            return DayInfo.getInstance().getDayName(this.position, getContext(), R.array.weekdays_long);
    }
}