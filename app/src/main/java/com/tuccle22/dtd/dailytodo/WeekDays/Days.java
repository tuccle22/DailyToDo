package com.tuccle22.dtd.dailytodo.WeekDays;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Tucker
 * For Daily To Do
 */


public class Days implements Parcelable {

    private String day_of_week_long;
    private String day_of_week_short;
    private String day_of_month;

    public Days(String day_of_week_long, String day_of_week_short, String day_of_month) {
        this.day_of_week_long = day_of_week_long;
        this.day_of_week_short = day_of_week_short;
        this.day_of_month = day_of_month;
    }

    public String getDayOfWeekLong() {
        return this.day_of_week_long;
    }

    public String getDayOfWeekShort() {
        return this.day_of_week_short;
    }

    public String getDayOfMonth() {
        return this.day_of_month;
    }



    private Days(Parcel in) {
        day_of_week_long = in.readString();
        day_of_week_short = in.readString();
        day_of_month = in.readString();
    }

    public static final Creator<Days> CREATOR = new Creator<Days>() {
        @Override
        public Days createFromParcel(Parcel in) {
            return new Days(in);
        }

        @Override
        public Days[] newArray(int size) {
            return new Days[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(day_of_week_long);
        parcel.writeString(day_of_week_short);
        parcel.writeString(day_of_month);
    }
}
