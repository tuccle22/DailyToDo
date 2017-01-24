package com.tuccle22.dtd.dailytodo.WeekDays;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Tucker
 * For Daily To Do
 */


public class Days implements Parcelable {

    private String day_of_week;
    private String day_of_month;

    public Days(String day_of_week, String day_of_month) {
        this.day_of_week = day_of_week;
        this.day_of_month = day_of_month;
    }

    public String getDayOfWeek() {
        return this.day_of_week;
    }

    public String getDayOfMonth() {
        return this.day_of_month;
    }



    private Days(Parcel in) {
        day_of_week = in.readString();
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
        parcel.writeString(day_of_week);
        parcel.writeString(day_of_month);
    }
}
