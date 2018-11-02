package com.mert.light.ui.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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
            Log.d("BaseFragment", "baseActivity is not null");
        } else {
            Log.d("BaseFragment", "baseActivity is null");
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
}
