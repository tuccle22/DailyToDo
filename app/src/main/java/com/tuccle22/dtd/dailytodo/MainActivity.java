package com.tuccle22.dtd.dailytodo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.db.chart.Tools;
import com.db.chart.animation.Animation;
import com.db.chart.model.BarSet;
import com.db.chart.model.LineSet;
import com.db.chart.renderer.XRenderer;
import com.db.chart.renderer.YRenderer;
import com.db.chart.view.BarChartView;
import com.db.chart.view.LineChartView;
import com.tuccle22.dtd.dailytodo.DayDetails.DayFragAdapter;
import com.tuccle22.dtd.dailytodo.DayDetails.ItemActivity;
import com.tuccle22.dtd.dailytodo.Utils.Logging;
import com.tuccle22.dtd.dailytodo.Utils.SpanningLinearLayoutManager;
import com.tuccle22.dtd.dailytodo.WeekDays.Days;
import com.tuccle22.dtd.dailytodo.WeekDays.WeekDayAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {


    // Bottom Sheet View
    private BottomSheetBehavior bottom_sheet_behavior;
    @BindView(R.id.bottom_sheet) NestedScrollView bottom_sheet;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.calendar_recycler_view) RecyclerView recycler_week;
    @BindView(R.id.fab_create) FloatingActionButton fab_create;

    private ArrayList<Days> days_list = new ArrayList<>();
    public static WeekDayAdapter day_adapter;
    FragmentPagerAdapter adapterViewPager;

    // Chart Information
    private LineChartView mChart;
    private final String[] mLabels = {"", "", "", "", "", "", ""};
    private final float[] mValues = {6f, 2f, 4f, 5f, 3f, 7f, 1f};


    @BindView(R.id.view_pager) ViewPager view_pager;

    @OnClick(R.id.fab_create)
    public void onFabClicked() {
        Intent intent = new Intent(getBaseContext(), ItemActivity.class);
        Bundle bundle = new Bundle();
        String day_title = DayInfo.getInstance().getWeekDays().get(view_pager.getCurrentItem()).getDayOfWeekLong();
        bundle.putString("DAY_TITLE", day_title);
        String day_of_month = DayInfo.getInstance().getWeekDays().get(view_pager.getCurrentItem()).getDayOfMonth();
        bundle.putString("DAY_OF_MONTH", day_of_month);
        intent.putExtras(bundle);
        startActivity(intent, bundle);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new Logging());
        }

        DayInfo.getInstance().initWeek(this);

        adapterViewPager = new DayFragAdapter(getSupportFragmentManager());
        view_pager.setAdapter(adapterViewPager);

        // Set Horizontal Bottom Sheet RecyclerView
        days_list.addAll(DayInfo.getInstance().getWeekDays());
        day_adapter = new WeekDayAdapter(days_list);
        final RecyclerView.LayoutManager daylayoutManager
                = new SpanningLinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recycler_week.setLayoutManager(daylayoutManager);
        recycler_week.setAdapter(day_adapter);


        mChart = (LineChartView) findViewById(R.id.barchart);

        // Data
//        BarSet barSet = new BarSet(mLabels, mValues[0]);
        LineSet barSet = new LineSet(mLabels, mValues);
        barSet.setColor(getResources().getColor(R.color.colorPrimary));
        barSet.setDotsColor(getResources().getColor(R.color.colorAccent));
        barSet.setDotsRadius(20f);
        mChart.addData(barSet);


        // Chart
        mChart.setXAxis(false)
                .setYAxis(false)
                .setXLabels(XRenderer.LabelPosition.OUTSIDE)
                .setYLabels(YRenderer.LabelPosition.NONE)
                .setLabelsColor(Color.parseColor("#86705c"))
                .setAxisColor(Color.parseColor("#86705c"));

        final int[] order = {1, 0, 2, 3, 5, 4, 6};

        mChart.show(new Animation().setOverlap(.7f, order));

        // Bottom Sheet
        bottom_sheet_behavior = BottomSheetBehavior.from(bottom_sheet);
        bottom_sheet_behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        bottom_sheet_behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    mChart.setVisibility(View.GONE);

                }

                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    mChart.setVisibility(View.VISIBLE);
                    mChart.show(new Animation().setOverlap(.7f, order));
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });




        view_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i < recycler_week.getLayoutManager().getChildCount(); i++) {
                    View v = recycler_week.getLayoutManager().getChildAt(i);
                    com.github.pavlospt.roundedletterview.RoundedLetterView day_circle = (com.github.pavlospt.roundedletterview.RoundedLetterView) v.findViewById(R.id.day_number);
                    if (position == i) {
                        day_circle.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        day_circle.setTitleText(day_circle.getTitleText());
                        day_circle.animate().rotationBy(360).setDuration(500);
                    } else {
                        day_circle.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                        day_circle.setTitleText(day_circle.getTitleText());
                    }
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) { }




        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}