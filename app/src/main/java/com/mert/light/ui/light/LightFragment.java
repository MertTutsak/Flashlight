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

    @BindView(R.id.image_button)
    ImageButton imageButton;

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

        //GesturePresenter
        this.event = new Event(getContext(), this);

        return view;
    }

    @OnClick(R.id.image_button)
    void OnClick(View view) {
        int x = (view.getRight() + view.getLeft()) / 2;
        int y = (view.getTop() + view.getBottom()) / 2;

        int startRadius = 0;
        int endRadius = 0;

        if (linearLayoutInfo.getVisibility() == View.GONE) {
            startRadius = 0;
            endRadius = (int) Math.hypot(view.getWidth(), view.getHeight());
        } else {
            startRadius = (int) Math.hypot(view.getWidth(), view.getHeight());
            endRadius = 0;
        }

        circularReveal.setPosition(x, y);
        circularReveal.setRadius(startRadius, endRadius);

        circularReveal.create();

        if (circularReveal.isCreate()) {
            circularReveal.getAnimator().addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {
                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    if (circularReveal.isOpen()) {
                        imageButton.setImageResource(R.drawable.warning_white);
                    } else {
                        imageButton.setImageResource(R.drawable.warning_red);
                    }
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
            circularReveal.start();
        }

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
