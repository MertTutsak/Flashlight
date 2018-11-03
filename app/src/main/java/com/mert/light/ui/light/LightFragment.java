package com.mert.light.ui.light;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.mert.light.R;
import com.mert.light.ui.base.BaseFragment;
import com.mert.light.utils.circularreveal.CircularReveal;
import com.mert.light.utils.gesture.GestureListener;
import com.mert.light.utils.gesture.types.Event;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LightFragment extends BaseFragment implements Light.View, GestureListener.event {

    private Event event;

    //Presenter
    private LightPresenter lightPresenter;

    //Circular Reveal
    CircularReveal circularReveal;

    @BindView(R.id.linearLayout_info_light)
    LinearLayout linearLayoutInfo;

    //Layout
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_light, container, false);

        if (baseActivity != null) {
            Log.d("LightFragment", "baseActivity is not null");
        } else {
            Log.d("LightFragment", "baseActivity is null");
        }
        ButterKnife.bind(this, view);

        //Presenter
        this.lightPresenter = new LightPresenter(baseActivity);

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
