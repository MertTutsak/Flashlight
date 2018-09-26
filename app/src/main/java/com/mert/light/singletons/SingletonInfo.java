package com.mert.light.singletons;

import android.app.Activity;
import android.util.Log;

public class SingletonInfo {
    private static SingletonInfo ourInstance;

    private Activity activity;

    private SingletonInfo(Activity activity) {
        this.activity = activity;
    }

    public static SingletonInfo getInstance(Activity activity) {
        if (ourInstance == null) {
            ourInstance = new SingletonInfo(activity);
            Log.d(SingletonRealm.class.getClass().getSimpleName(), "SingletonInfo ayağa kaldırıldı.");
        }

        return ourInstance;
    }
}
