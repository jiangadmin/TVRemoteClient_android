package com.samonkey.remote;

import android.app.Application;
import android.content.Context;

/**
 * Created on 2017/3/24
 *
 * @author saker
 */

public class SamApplication extends Application {

    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
    }

    public static Context getContext() {
        return sContext;
    }
}
