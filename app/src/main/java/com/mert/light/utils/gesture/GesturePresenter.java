package com.mert.light.utils.gesture;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class GesturePresenter implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private GestureDetectorCompat gestureDetector;

    protected static final int SWIPE_THRESHOLD_VELOCITY = 200;

    public GesturePresenter(Context context) {
        this.gestureDetector = new GestureDetectorCompat(context, this);
    }

    public GestureDetectorCompat getGestureDetector() {
        return gestureDetector;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Log.d("GESTURE_EVENT", "onLongPress");
        //press.onLongPress(e);
    }


    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.d("GESTURE_EVENT", "onFling");
        //fling.BottomFling();
        return true;
    }


    @Override
    public boolean onDown(MotionEvent e) {
        Log.d("GESTURE_EVENT", "onDown");
        //press.onDown(e);
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Log.d("GESTURE_EVENT", "onShowPress");
        //press.onShowPress(e);
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.d("GESTURE_EVENT", "onSingleTapUp");
        //tap.onSingleTapUp(e);
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.d("GESTURE_EVENT", "onScroll");
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        Log.d("GESTURE_EVENT", "onSingleTapConfirmed");
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        Log.d("GESTURE_EVENT", "onDoubleTap");
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        Log.d("GESTURE_EVENT", "onDoubleTapEvent");
        return false;
    }
}
