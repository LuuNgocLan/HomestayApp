package com.lanltn.android_core_helper.network;


import android.util.Log;

import retrofit2.HttpException;

public class HandlingErrorAPI {

    public static String getString(Throwable throwable) {
        Log.e("throwable", throwable.toString());

        try {
            if (((HttpException) throwable).code() == 401) {

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
