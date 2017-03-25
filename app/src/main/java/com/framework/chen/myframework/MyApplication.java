package com.framework.chen.myframework;

import android.app.Application;
import android.content.Context;

import com.framework.chen.myframework.api.RetrofitService;

/**
 * Created by chen on 17/3/25.
 */

public class MyApplication extends Application {
    private static Context sContext;
    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
        RetrofitService.init();
    }

    public static Context getContext() {
        return sContext;
    }
}
