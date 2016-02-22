package com.wenkchan.androidricheditor;

import android.support.annotation.ColorInt;

/**
 * Created by wenkchan on 16-2-15.
 */
public class ColorPanel {
    @ColorInt
    public static final int BLACK = 0xFF000000;
    @ColorInt
    public static final int RED = 0XFFFF4444;
    @ColorInt
    public static final int ORANGE = 0xFFFF8800;
    @ColorInt
    public static final int GREEN = 0xFF00FF00;
    @ColorInt
    public static final int BLUE = 0xFF0099CC;
    @ColorInt
    public static final int PURPLE = 0xFFAA66CC;
    private int color;

    public ColorPanel(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }


}
