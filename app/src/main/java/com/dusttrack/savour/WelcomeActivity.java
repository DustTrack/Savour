package com.dusttrack.savour;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.dusttrack.savour.core.BaseActivity;

public class WelcomeActivity extends BaseActivity {
    private final long delayedTiem = 3000L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(WelcomeActivity.this,HomeActivity.class);
            }
        }, delayedTiem);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //TODO
        }
    };
}
