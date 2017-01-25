package com.tuccle22.dtd.dailytodo;

import android.content.Context;

import com.tuccle22.dtd.dailytodo.WeekDays.Days;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import butterknife.BindArray;

/**
 * Created by Tucker
 * For Daily To Do
 */


public class DayInfo {

    private Calendar cal = Calendar.getInstance();
    HashMap<String, ArrayList<Days>> days_map = new HashMap<String, ArrayList<Days>>();

    private static DayInfo ourInstance = new DayInfo();

    public static DayInfo getInstance() {
        return ourInstance;
    }

    private DayInfo() { }

    public String getDayName(int position, Context context, int resource_id) {
        String Weekdays[] = context.getResources().getStringArray(resource_id);
        int day_num = getTodayPosition();
        return Weekdays[((day_num+position+3)%7)];
    }

    public String getTodayName(Context context, int resource_id) {
        return getDayName(getTodayPosition(), context,  resource_id);
    }

    public int getTodayPosition() {
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    public ArrayList<Days> getBottomSheetDays(Context context) {
        ArrayList<Days> days_list = new ArrayList<>();
        String[] weekdays = context.getResources().getStringArray(R.array.weekdays_short);
        int today = getTodayPosition();
        for (int i = 0; i < 7; i++) {
            String day_of_week = weekdays[((today+i)%7)];
            String day_of_month = String.valueOf(cal.get(Calendar.DAY_OF_MONTH) + i);
            Days day = new Days(day_of_week, day_of_month);
            days_list.add(day);
        }
        return days_list;
    }

    public ArrayList<Days> getDaysList(String DAY) {
        return days_map.get(DAY);
    }


}
