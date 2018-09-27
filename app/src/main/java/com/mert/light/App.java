package com.mert.light;

import android.os.Environment;

public class App extends android.app.Application {
    public static  String rootPath;

    @Override
    public void onCreate() {
        super.onCreate();

        //Root Path
        rootPath = Environment.getExternalStorageDirectory().getAbsolutePath();

    }
}
