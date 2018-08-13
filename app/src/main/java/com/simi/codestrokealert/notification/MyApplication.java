package com.simi.codestrokealert.notification;

import android.app.Application;
import android.content.Context;

import com.onesignal.OneSignal;
import com.simi.codestrokealert.SharedPref;


public class MyApplication extends Application {

    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        SharedPref.init(getApplicationContext());
        OneSignal.startInit(this)
                .setNotificationOpenedHandler(new MyNotificationOpenedHandler())
                .init();
    }
}
