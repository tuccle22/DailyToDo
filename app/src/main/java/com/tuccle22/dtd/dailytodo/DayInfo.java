package com.tuccle22.dtd.dailytodo;

import android.content.Context;

import com.tuccle22.dtd.dailytodo.WeekDays.Days;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import butterknife.BindArray;

/**
 * Created by Tucker
 * For Daily To Do
 */


public class DayInfo {


    public static final int TODAY = 0;
    public static final int TOMORROW = 1;
    private HashMap<String, ArrayList<Days>> days_map = new HashMap<String, ArrayList<Days>>();
    private ArrayList<Days> days_list = new ArrayList<>();

    private static DayInfo ourInstance = new DayInfo();

    public static DayInfo getInstance() {
        return ourInstance;
    }

    private DayInfo() { }

    public void initWeek(Context context) {
        days_list.clear();
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        String weekdays_long[] = context.getResources().getStringArray(R.array.weekdays_long);
        String weekdays_short[] = context.getResources().getStringArray(R.array.weekdays_short);

        for (int i = 0; i < 7; i++) {
            String day_of_week_long;
            if (i == TODAY) {
                day_of_week_long = "Today";
            } else if (i == TOMORROW) {
                day_of_week_long = "Tomorrow";
            } else {
                day_of_week_long = weekdays_long[cal.get(Calendar.DAY_OF_WEEK)%7];
            }

            String day_of_week_short = weekdays_short[cal.get(Calendar.DAY_OF_WEEK)%7];
            String day_of_month = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
            Days day = new Days(day_of_week_long, day_of_week_short, day_of_month);
            days_list.add(day);
            cal.add(Calendar.DAY_OF_YEAR, 1);
        }
    }

    public ArrayList<Days> getWeekDays() {
        return days_list;
    }


}
