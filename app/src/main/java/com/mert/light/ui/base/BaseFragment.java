package com.mert.light.ui.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.mert.light.data.db.sharedpreferences.SharedPreferences;
import com.mert.light.singletons.SingletonInfo;
import com.mert.light.singletons.SingletonRealm;
import com.mert.light.singletons.SingletonUser;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import io.realm.RealmObject;

public class BaseFragment extends Fragment {

    //Activity
    protected BaseActivity baseActivity;

    //Date
    private Date date;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseActivity = (BaseActivity) getActivity();

        if (baseActivity != null) {

        }
    }

    protected boolean replaceFragment(BaseFragment fragment) {
        if (fragment != null) {
            baseActivity.changeFragment(fragment);
            return true;
        } else {
            return false;
        }
    }

    protected boolean initFragment(BaseFragment fragment) {
        if (fragment != null) {
            baseActivity.initView(fragment);
            return true;
        } else {
            return false;
        }
    }

    protected boolean backPressButton() {
        baseActivity.onBackPressed();
        return true;
    }

    //Date
    protected Date getNow() {
        date = new Date();
        return date;
    }

    protected String getNowByString() {
        date = new Date();
        return new SimpleDateFormat("EEEE, dd MMMM yyyy kk:mm", Locale.getDefault()).format(date);
    }

    protected Calendar getCalendar() {
        Calendar.getInstance().clear();
        return Calendar.getInstance(Locale.getDefault());
    }

    protected Calendar parseCalendarByDate(Date date) {
        Calendar cal = getCalendar();
        cal.setTime(date);
        return cal;
    }

    protected Calendar parseCalendarByLong(Long val) {
        Calendar cal = getCalendar();
        cal.setTime(new Date(val));
        return cal;
    }

    //Realm
    protected long getNextId(RealmObject c) {
        Number currentIdNum = baseActivity.singletonRealm.getRealm().where(c.getClass()).max("id");
        int nextId = -1;
        if (currentIdNum == null) {
            nextId = 0;
        } else {
            nextId = currentIdNum.intValue() + 1;
        }
        return nextId;
    }

    protected SingletonRealm getSingletonRealm() {
        return baseActivity.singletonRealm;
    }

    //User
    protected SingletonUser getSingletonUser() {
        return baseActivity.singletonUser;
    }

    //Info
    protected SingletonInfo getSingletonInfo() {
        return baseActivity.singletonInfo;
    }

    //SharedPreferences
    protected SharedPreferences getSharedPreferences() {
        return baseActivity.sharedPreferences;
    }
}
