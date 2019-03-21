package com.lanltn.android_core_helper.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class TimeUtils {

    public static long getTime(int h, int m) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR, h);
        calendar.set(Calendar.MINUTE, m);
        return calendar.getTimeInMillis();
    }

    public static long getTime(long currentTime, int h, int m) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentTime);
        calendar.set(Calendar.HOUR, h);
        calendar.set(Calendar.MINUTE, m);
        return calendar.getTimeInMillis();
    }

    public static long getTimeInSec(long currentTime, int h, int m) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentTime);
        calendar.set(Calendar.HOUR, h);
        calendar.set(Calendar.MINUTE, m);
        return calendar.getTimeInMillis() / 1000;
    }

    public static long getTimeInMin(long currentTime, int h, int m) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentTime);
        calendar.set(Calendar.HOUR, h);
        calendar.set(Calendar.MINUTE, m);
        return calendar.getTimeInMillis() / 1000 / 60;
    }

    public static String getTimeHM(long timeInMillis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeInMillis);
        SimpleDateFormat fm = new SimpleDateFormat("HH:mm", Locale.US);
        return fm.format(calendar.getTime());
    }
}
