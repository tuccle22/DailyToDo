package com.tuccle22.dtd.dailytodo.DayDetails;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Tucker
 * For Daily To Do
 */


public class DayDetail implements Parcelable {

    private String text;

    public DayDetail(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }


    private DayDetail(Parcel in) {
        text = in.readString();
    }

    public static final Creator<DayDetail> CREATOR = new Creator<DayDetail>() {
        @Override
        public DayDetail createFromParcel(Parcel in) {
            return new DayDetail(in);
        }

        @Override
        public DayDetail[] newArray(int size) {
            return new DayDetail[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(text);
    }
}
