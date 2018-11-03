package com.mert.light.ui.base;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.mert.light.R;
import com.mert.light.ui.dialogs.CloseDialog;
import com.mert.light.ui.light.LightFragment;

import java.util.List;

public class BaseActivity extends FragmentActivity {
    private static final int CAMERA_REQUEST = 50;

    //Bundle
    private Bundle bundle;

    //FrameLayout
    private FrameLayout frameLayout;

    //Ad Manager
    private AdView adViewTop;
    private AdView adViewBottom;

    /* Dialog */
    //Close
    public CloseDialog closeDialog;

    private boolean feature_camera_flash;
    private CameraManager cameraManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        if (this.checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            this.requestPermissions(new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST);
        }

        //FrameLAyout
        this.frameLayout = (FrameLayout) findViewById(R.id.base_framelayout);

        //Tam ekran yapmak için
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Bundle
        this.bundle = new Bundle();

        //Dialog
        this.closeDialog = new CloseDialog(BaseActivity.this);

        //AdMob
        MobileAds.initialize(this, getResources().getString(R.string.app_admob_id));

        adViewTop = findViewById(R.id.adView_top);
        adViewBottom = findViewById(R.id.adView_bottom);

        AdRequest adRequest = new AdRequest.Builder().addTestDevice("77379DF6B7DB9E34CC89112EB279D3F3").build();

        adViewTop.loadAd(adRequest);
        adViewBottom.loadAd(adRequest);

        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.CAMERA
                ).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {
                for (PermissionGrantedResponse response : report.getGrantedPermissionResponses()) {
                }

                for (PermissionDeniedResponse response : report.getDeniedPermissionResponses()) {
                    finish();
                }

            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

            }
        }).check();
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
