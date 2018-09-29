package com.mert.light.utils.circularreveal;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class CircularReveal {
    //View Object
    private View content;
    //Radius
    private int startRadius = -1;
    private int endRadius = -1;
    //Postion
    private int X = -1;
    private int Y = -1;
    //Duration
    private int duration = -1;
    //Animator
    private Animator animator = null;

    public CircularReveal(@NonNull View _content) {
        this.content = _content;
    }
    //RADIUS
    private int getStartRadius() {
        if (this.startRadius == -1) {
            this.startRadius = 0;
        }
        return this.startRadius;
    }

    private int getEndRadius() {
        if (this.endRadius == -1) {
            this.endRadius = 0;
        }
        return this.endRadius;
    }

    public CircularReveal setRadius(int startRadius, int endRadius) {
        this.startRadius = startRadius;
        this.endRadius = endRadius;
        Log.d("CIRCLE_REVEAL", "Radius => startRadius :" + this.startRadius + "endRadius :" + this.endRadius);
        return this;
    }

    //POSITION
    private int getX() {
        if (this.X == -1) {
            this.X = 0;
        }
        return this.X;
    }

    private int getY() {
        if (this.Y == -1) {
            this.Y = 0;
        }
        return this.Y;
    }

    public CircularReveal setPosition(int x, int y) {
        X = x;
        Y = y;
        Log.d("CIRCLE_REVEAL", "position => X :" + this.X + " Y :" + this.Y);
        return this;
    }

    //DURATION
    public long getDuration() {
        if (this.duration == -1) {
            this.duration = content.getResources().getInteger(android.R.integer.config_longAnimTime);
        }
        return this.duration;
    }

    public CircularReveal setDuration(int duration) {
        this.duration = duration;
        Log.d("CIRCLE_REVEAL", "duration :" + this.duration);
        return this;
    }

    //ANIMATION
    public Animator getAnimator() {
        return this.animator;
    }

    //Event
    public void create() {
        this.animator = ViewAnimationUtils.createCircularReveal(content, getX(), getY(), getStartRadius(), getEndRadius());
        this.animator.setDuration(getDuration());
        this.animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                if (getStartRadius() < getEndRadius()) {
                    content.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                if (getStartRadius() > getEndRadius()) {
                    content.setVisibility(View.GONE);
                }
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        Log.d("CIRCLE_REVEAL", "animator :" + this.toString());
    }

    public void start() {
        Log.d("CIRCLE_REVEAL", "started");
        getAnimator().start();
    }

    //Control
    public boolean isCreate() {
        if (getAnimator() == null) {
            Log.d("CIRCLE_REVEAL", "isCreate :false");
            return false;
        } else {
            Log.d("CIRCLE_REVEAL", "isCreate :true");
            return true;
        }
    }

    public boolean isOpen(){
        if (content.getVisibility() == View.GONE){
            return false;
        }else {
            return true;
        }
    }

}




