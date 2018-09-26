package com.mert.light.tools;

import android.graphics.Color;

public class Random {

    private java.util.Random rnd = new java.util.Random();

    public long getRandomLong() {
        return -1;
    }

    public long getRandomId() {
        return 0;
    }

    public int getRandomColor() {
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }
}
