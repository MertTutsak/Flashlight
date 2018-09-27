package com.mert.light.ui.light;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mert.light.R;
import com.mert.light.ui.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;
import butterknife.OnTouch;

public class LightFragment extends BaseFragment implements Light.View {

    private LightPresenter lightPresenter;

    @BindView(R.id.light_button)
    Button buttonLight;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_light, container, false);

        if (baseActivity != null) {
            Log.d("LightFragment", "baseActivity is not null");
        } else {
            Log.d("LightFragment", "baseActivity is null");
        }
        ButterKnife.bind(this, view);

        lightPresenter = new LightPresenter(baseActivity);


        return view;
    }

    @OnTouch(R.id.light_button)
    public boolean OnTouch(android.view.View view, MotionEvent motionEvent) {
        Log.d("LightFragment", "buttonLight is on touch");
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            Log.d("LightFragment", "buttonLight actions down on touch");
            LightFragment.this.getView().setBackgroundResource(R.drawable.blub_background);
            //buttonLight.setBackgroundResource(R.color.blue);
            lightPresenter.HoldOn();
        } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            Log.d("LightFragment", "buttonLight actions up on touch");
            if (lightPresenter.STATE == -1) {
                lightPresenter.TurnOff();
                LightFragment.this.getView().setBackgroundResource(R.color.black);
                //buttonLight.setBackgroundResource(R.color.white);
            } else if (lightPresenter.STATE == 0) {
                lightPresenter.TurnOff();
                LightFragment.this.getView().setBackgroundResource(R.color.black);
                //buttonLight.setBackgroundResource(R.color.white);
            } else if (lightPresenter.STATE == 1) {
                lightPresenter.TurnOn();
                LightFragment.this.getView().setBackgroundResource(R.drawable.blub_background);
                //buttonLight.setBackgroundResource(R.color.green);
            }

        }
        return false;
    }

    @OnLongClick(R.id.light_button)
    public boolean OnLongClick(View view) {
        Log.d("LightFragment", "buttonLight is on longpress");
        if (lightPresenter.STATE != -1) {
            LightFragment.this.getView().setBackgroundResource(R.color.black);
            //buttonLight.setBackgroundResource(R.color.white);
            lightPresenter.TurnOff();
        } else {
            LightFragment.this.getView().setBackgroundResource(R.drawable.blub_background);
            //buttonLight.setBackgroundResource(R.color.green);
            lightPresenter.TurnOn();
        }
        return false;
    }
}
