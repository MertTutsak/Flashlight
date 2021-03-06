package com.mert.light.ui.light;

import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.util.Log;

import com.mert.light.ui.base.BaseActivity;

public class LightPresenter<V extends Light.View> implements Light.Presenter<V> {
    public static int HOLDON = -1;
    public static int TURNON = 1;
    public static int TURNOFF = 0;
    public static int STATE;

    private boolean feature_camera_flash;
    private CameraManager cameraManager;


    private BaseActivity baseActivity;

    public LightPresenter(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
        STATE = TURNOFF;

        if (baseActivity == null) {
            Log.d("LightPresenter", "baseActivity == null");
        } else {
            Log.d("LightPresenter", "baseActivity != null");
        }
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
    public void TurnOn() {
        setState(TURNON);
        setCamera(true);
    }

    @Override
    public void HoldOn() {
        setState(HOLDON);
        setCamera(true);
    }

    @Override
    public void TurnOff() {
        setState(TURNOFF);
        setCamera(false);
    }

    private void setState(int state) {
        this.STATE = state;
        Log.d("LightPresenter", "setState is " + this.STATE);
    }

    private void setCamera(boolean enabled) {

        // your code using Camera2 API here - is api 21 or higher
        cameraManager = (CameraManager) baseActivity.getSystemService(Context.CAMERA_SERVICE);

        try {
            cameraManager.setTorchMode(cameraManager.getCameraIdList()[0], enabled);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }
}
