package com.tuccle22.dtd.dailytodo.Data;

import android.provider.BaseColumns;

/**
 * Created by Tucker
 * For Daily To Do
 */


public final class DayShell {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private DayShell() {}

    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "week";
        public static final String COLUMN_MONDAY = "monday";
        public static final String COLUMN_TUESDAY = "tuesday";
        public static final String COLUMN_WEDNESDAY = "wednesday";
        public static final String COLUMN_THURSDAY = "thursday";
        public static final String COLUMN_FRIDAY = "friday";
        public static final String COLUMN_SATURDAY = "saturday";
        public static final String COLUMN_SUNDAY = "sunday";
    }

}
