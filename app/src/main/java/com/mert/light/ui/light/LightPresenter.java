package com.mert.light.ui.light;

import android.app.Activity;
import android.graphics.Camera;
import android.graphics.Color;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.util.Log;
import android.view.View;

import com.mert.light.R;
import com.mert.light.ui.base.BaseFragment;
import com.mert.light.ui.base.MvpPresenter;

public class LightPresenter<V extends Light.View> implements Light.Presenter<V> {
    public static int PRESSTATE = -1;
    public static int ONSTATE = 1;
    public static int OFFSTATE = 0;
    public static int STATE;

    private Activity activity;

    public LightPresenter(Activity activity) {
        this.activity = activity;
        STATE = OFFSTATE;
    }

    @Override
    public void onCreate(V mvpView) {
    }

    @Override
    public void omCreateView(V mvpView) {

    }

    @Override
    public void onResume(V mvpView) {
    }

    @Override
    public void onAttach(V mvpView) {

    }

    @Override
    public void onDestroy(V mvpView) {

    }

    @Override
    public int state() {
        Log.d("LightPresenter", "state is " + STATE);
        return STATE;
    }

    @Override
    public void On() {
        setState(ONSTATE);
        //setCamera(true);
    }

    @Override
    public void GoOn() {
        setState(PRESSTATE);
        //setCamera(true);
    }

    @Override
    public void Off() {
        setState(OFFSTATE);
        //setCamera(false);
    }

    private void setState(int state){
        this.STATE = state;
        Log.d("LightPresenter", "setState is " + this.STATE);
    }

    private void setCamera(boolean enabled) {
        CameraManager cameraManager = (CameraManager) activity.getSystemService(activity.CAMERA_SERVICE);
        String cameraId = null; // Usually front camera is at 0 position.
        try {
            cameraId = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraId, false);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }
}
