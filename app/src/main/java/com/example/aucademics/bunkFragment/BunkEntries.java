package com.example.aucademics.bunkFragment;

import android.provider.BaseColumns;

public class BunkEntries implements BaseColumns {
    //1)create private sql table with sub name, credits, total hours, bunked hours, hours left

    public static final String TABLE_NAME ="BM_TABLE";
    public static final String COLUMN_SUBJECT = "Subject_name";
    public static final String COLUMN_TOTAL_HOURS = "Total_hours";
    public static final String COLUMN_BUNKED_HOURS = "Bunked_hours";
    public static final String COLUMN_BUNKS_LEFT = "Bunks_left";
    public static final String COLUMN_ATTENDANCE_PERCENT = "Attendance_percent";


}
