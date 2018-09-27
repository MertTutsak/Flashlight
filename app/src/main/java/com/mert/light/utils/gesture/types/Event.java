package com.mert.light.utils.gesture.types;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;

import com.mert.light.utils.gesture.GestureListener;
import com.mert.light.utils.gesture.GesturePresenter;

public class Event extends GesturePresenter {

    private GestureListener.event event;

    public Event(Context context, GestureListener.event event) {
        super(context);
        this.event = event;
    }

    @Override
    public boolean onFling(MotionEvent downEvent, MotionEvent moveEvent, float velocityX, float velocityY) {
        boolean result = false;
        float diffY = moveEvent.getY() - downEvent.getY();
        float diffX = moveEvent.getX() - downEvent.getX();

        if (Math.abs(diffX) < Math.abs(diffY)) {
            if (Math.abs(diffY) > SWIPE_THRESHOLD_VELOCITY && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                if (diffY > 0) {
                    Log.d("GESTURE_EVENT", "onFling => Bottom");
                    event.BottomFling();
                } else {
                    Log.d("GESTURE_EVENT", "onFling => Top");
                    event.TopFling();
                }
                result = true;
            }
        }

        return result;
    }
}
