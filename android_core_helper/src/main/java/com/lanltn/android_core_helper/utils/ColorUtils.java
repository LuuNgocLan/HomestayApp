package com.lanltn.android_core_helper.utils;

import android.graphics.Color;
import android.support.annotation.ColorInt;

public class ColorUtils {

    /**
     * This method return lighter or darker color
     *
     * @param color  origin color
     * @param factor 1 is original, <1 will return darker color, >1 will return lighter color
     * @return new color
     */
    @ColorInt
    public static int adjustAlpha(@ColorInt int color, float factor) {
        int alpha = Math.round(Color.alpha(color) * factor);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha, red, green, blue);
    }
}
