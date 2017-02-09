package com.dusttrack.savour.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.dusttrack.savour.widget.FloatWindow;

public class FloatWindowService extends Service {
    private static final String TAG = FloatWindowService.class.getName();
    private FloatWindow floatWindow;

    public FloatWindowService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "FloatWindowService onCreate");
        floatWindow = new FloatWindow(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        return super.onStartCommand(intent, flags, startId);
        return START_STICKY_COMPATIBILITY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (floatWindow != null)
            floatWindow.remove();
    }
}
