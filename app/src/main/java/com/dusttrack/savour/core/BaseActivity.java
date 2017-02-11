package com.dusttrack.savour.core;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

//import butterknife.ButterKnife;

/**
 * Created by admin on 2016/9/5.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected void startActivity(Context context, Class<?> ls) {
        Intent intent = new Intent(context, ls);
        startActivity(intent);
    }

    protected void startActivityForResult(Context context, Class<?> ls, int requestCode) {
        ((Activity) context).startActivityForResult(new Intent(context, ls), requestCode);
    }
}
