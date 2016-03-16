package com.drivex;

import android.app.Application;
import android.content.Context;

/**
 * Created by sujeetkumarmehta on 11/27/15.
 */
public class DriveX extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        context = getBaseContext();
    }

    public static Context context;

    public static Context getContext() {
        return context;
    }

    ;
}
