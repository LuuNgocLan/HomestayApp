package com.lanltn.android_core_helper.utils;

import android.content.Context;
import android.util.DisplayMetrics;

public class DimenUtils {

    public static int dpToPx(Context context, int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT));
    }
}
