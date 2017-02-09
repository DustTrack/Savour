package com.dusttrack.savour;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.dusttrack.savour.service.FloatWindowService;
import com.dusttrack.savour.widget.FloatWindow;

public class SettingActivity extends Activity {

    private Switch floatSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        floatSwitch = (Switch) findViewById(R.id.floatSwitch);

        floatSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    startService(new Intent(SettingActivity.this, FloatWindowService.class));
                } else {
                    stopService(new Intent(SettingActivity.this, FloatWindowService.class));
                }
            }
        });

    }

}
