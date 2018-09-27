package com.mert.light.ui.base;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.mert.light.R;
import com.mert.light.ui.dialogs.CloseDialog;
import com.mert.light.ui.light.LightFragment;

public class BaseActivity extends FragmentActivity {
    private static final int CAMERA_REQUEST = 50;

    @Override
    protected void onStart() {
        super.onStart();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(BaseActivity.this, new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST);
        }
    }

    //Bundle
    private Bundle bundle;

    //FrameLayout
    private FrameLayout frameLayout;

    /* Dialog */
    //Close
    public CloseDialog closeDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        //FrameLAyout
        frameLayout = (FrameLayout) findViewById(R.id.base_framelayout);

        //Tam ekran yapmak için
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Bundle
        bundle = new Bundle();

        //Dialog
        closeDialog = new CloseDialog(BaseActivity.this);
    }

    //Frame Layout
    public void initView(final BaseFragment fragment) {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.base_framelayout, fragment).commitAllowingStateLoss();
            }
        }, 50);

    }

    public void changeFragment(final BaseFragment fragment) {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                //Animasyonlu geçiş
                //getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.to_right_animation, R.anim.from_left_animation, R.anim.from_right_animation, R.anim.to_left_animation).replace(R.id.frameLayout_baseActivity, fragment).addToBackStack(fragment.toString()).commit();
                getSupportFragmentManager().beginTransaction().replace(R.id.base_framelayout, fragment).addToBackStack(fragment.toString()).commit();
                Log.d(this.getClass().getSimpleName(), "STACK PUSH => " + fragment.toString());
            }
        }, 200);


    }

    //Arguments
    public Bundle getArguments() {
        Log.d("BUNDLE", "getArguments : " + this.bundle.toString());
        return this.bundle;
    }

    public void setArguments(Bundle bundle) {
        Log.d("BUNDLE", "setArguments : " + bundle.toString());
        this.bundle.putAll(bundle);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            /*Hangi Fragment'te olduğunu bulmak için.
            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.base_framelayout);
            if (currentFragment instanceof SelectionFragment) {
                Log.d(this.getClass().getSimpleName(), "STACK BACK PRESS => " + getSupportFragmentManager().findFragmentById(R.id.base_framelayout).toString());
                closeDialog.create();
            } else {
                Log.d(this.getClass().getSimpleName(), "STACK POP => " + getSupportFragmentManager().findFragmentById(R.id.base_framelayout).toString());
                super.onBackPressed();
            }
*/
        } else {
            closeDialog.create();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case CAMERA_REQUEST:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initView(new LightFragment());
                } else {
                    Toast.makeText(BaseActivity.this, "Permission Denied for the Camera",
                            Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}