package com.student.student_order;

import android.text.format.DateFormat;
import android.util.Log;

import java.util.Calendar;
import java.util.Locale;

public class MyApi {
    private static final String TAG = "MyApi";
    public  static  String getDate(long time) {
        Log.d(TAG, "getDate: "+time);
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time);
        String date = DateFormat.format("yyyy-MM-dd HH:mm:ss", cal).toString();
        return date;
    }
}
