package com.mert.light.ui.base;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.mert.light.R;
import com.mert.light.data.db.sharedpreferences.SharedPreferences;
import com.mert.light.singletons.SingletonInfo;
import com.mert.light.singletons.SingletonRealm;
import com.mert.light.singletons.SingletonUser;
import com.mert.light.ui.dialogs.CloseDialog;

public class BaseActivity extends FragmentActivity {

    //Bundle
    private Bundle bundle;

    //Realm
    public SingletonRealm singletonRealm;

    //User
    public SingletonUser singletonUser;

    //Info
    public SingletonInfo singletonInfo;

    //SharedPreferences
    public SharedPreferences sharedPreferences;

    //FrameLayout
    private FrameLayout frameLayout;

    /* Dialog */
    //Close
    public CloseDialog closeDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //FrameLAyout
        frameLayout = (FrameLayout) findViewById(R.id.base_framelayout);

        //SharedPreferences
        sharedPreferences = new SharedPreferences(this);

        //Realm
        singletonRealm = SingletonRealm.getInstance(this);

        //Info
        singletonInfo = SingletonInfo.getInstance(this);

        //User
        singletonUser = SingletonUser.getInstance();

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
                transaction.replace(R.id.base_framelayout, fragment).commit();
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
}
