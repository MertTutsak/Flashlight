package com.mert.light.ui.light;

import android.os.Bundle;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.mert.light.R;
import com.mert.light.ui.base.BaseFragment;
import com.mert.light.utils.gesture.GestureListener;
import com.mert.light.utils.gesture.types.Event;

import butterknife.ButterKnife;

public class LightFragment extends BaseFragment implements Light.View, GestureListener.event {

    private Event event;

    private LightPresenter lightPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_light, container, false);

        if (baseActivity != null) {
            Log.d("LightFragment", "baseActivity is not null");
        } else {
            Log.d("LightFragment", "baseActivity is null");
        }
        ButterKnife.bind(this, view);

        this.lightPresenter = new LightPresenter(baseActivity);
        //GesturePresenter
        this.event = new Event(getActivity(), this);

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    Log.d("LightFragment", "buttonLight actions down on touch");
                    LightFragment.this.getView().setBackgroundResource(R.drawable.blub_background);
                    //buttonLight.setBackgroundResource(R.color.blue);
                    lightPresenter.HoldOn();
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    if (lightPresenter.STATE == -1){
                        LightFragment.this.getView().setBackgroundResource(R.color.black);
                        //buttonLight.setBackgroundResource(R.color.white);
                        lightPresenter.TurnOff();
                    }
                }

                return event.getGestureDetector().onTouchEvent(motionEvent);
            }
        });
        return view;
    }

    @Override
    public void TopFling() {
        LightFragment.this.getView().setBackgroundResource(R.drawable.blub_background);
        //buttonLight.setBackgroundResource(R.color.green);
        lightPresenter.TurnOn();
    }

    @Override
    public void BottomFling() {
        LightFragment.this.getView().setBackgroundResource(R.color.black);
        //buttonLight.setBackgroundResource(R.color.white);
        lightPresenter.TurnOff();
    }
}
