package com.dusttrack.savour.application;

import android.app.Application;
import android.content.res.Configuration;


/**
 * Created by DustTrack on 2016/10/22.
 */
public class SavourApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        startService(new Intent(this, FloatWindowService.class));
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
