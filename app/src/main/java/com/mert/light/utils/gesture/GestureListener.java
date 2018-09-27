package com.mert.light.utils.gesture;

import android.view.GestureDetector;
import android.view.MotionEvent;

public interface GestureListener extends GestureDetector.OnGestureListener {

    interface event {
        void TopFling();

        void BottomFling();
    }
}
