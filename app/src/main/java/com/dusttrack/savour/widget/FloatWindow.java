package com.dusttrack.savour.widget;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.dusttrack.savour.R;

/**
 * Created by DustTrack on 2016/10/22.
 */
public class FloatWindow extends FrameLayout implements View.OnTouchListener {

    private Context mContext;
    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mWmParams;
    private float mTouchStartX;
    private float mTouchStartY;
    private int mScreenWidth;
    private int mScreenHeight;
    private boolean mDraging;

    public FloatWindow(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        // 更新浮动窗口位置参数 靠边
        DisplayMetrics dm = new DisplayMetrics();
        // 获取屏幕信息
        mWindowManager.getDefaultDisplay().getMetrics(dm);
        mScreenWidth = dm.widthPixels;
        mScreenHeight = dm.heightPixels;
        this.mWmParams = new WindowManager.LayoutParams();
        // 设置window type
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mWmParams.type = WindowManager.LayoutParams.TYPE_TOAST;
        } else {
            mWmParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        }
        // 设置图片格式，效果为背景透明
        mWmParams.format = PixelFormat.RGBA_8888;
        // 设置浮动窗口不可聚焦（实现操作除浮动窗口外的其他可见窗口的操作）
        mWmParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        // 调整悬浮窗显示的停靠位置为左侧置
        mWmParams.gravity = Gravity.LEFT | Gravity.TOP;

        mScreenHeight = mWindowManager.getDefaultDisplay().getHeight();

        // 以屏幕左上角为原点，设置x、y初始值，相对于gravity
        mWmParams.x = 0;
        mWmParams.y = mScreenHeight / 2;

        // 设置悬浮窗口长宽数据
        mWmParams.width = FrameLayout.LayoutParams.WRAP_CONTENT;
        mWmParams.height = FrameLayout.LayoutParams.WRAP_CONTENT;
        addView(createView(mContext));
        mWindowManager.addView(this, mWmParams);
    }

    private View createView(final Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.window_float_mian, null);
        view.setOnTouchListener(this);
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"what?",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }


    public void show() {
        if (mWindowManager == null)
            return;
        setVisibility(View.VISIBLE);
        mWindowManager.updateViewLayout(this, mWmParams);
    }

    public void hide() {
        if (mWindowManager == null)
            return;
        setVisibility(View.VISIBLE);
        mWindowManager.updateViewLayout(this, mWmParams);
    }

    public void remove() {
        hide();
        try {
            mWindowManager.removeView(this);
        } catch (Exception ex) {

        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        // 获取相对屏幕的坐标，即以屏幕左上角为原点
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mTouchStartX = event.getX();
                mTouchStartY = event.getY();
                mWmParams.alpha = 1f;
                mWindowManager.updateViewLayout(this, mWmParams);
                mDraging = false;
                break;
            case MotionEvent.ACTION_MOVE:
                float mMoveStartX = event.getX();
                float mMoveStartY = event.getY();
                // 如果移动量大于3才移动
                if (Math.abs(mTouchStartX - mMoveStartX) > 3
                        && Math.abs(mTouchStartY - mMoveStartY) > 3) {
                    mDraging = true;
                    // 更新浮动窗口位置参数
                    mWmParams.x = (int) (x - mTouchStartX);
                    mWmParams.y = (int) (y - mTouchStartY);
                    mWindowManager.updateViewLayout(this, mWmParams);
                    return false;
                }

                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:

                if (mWmParams.x >= mScreenWidth / 2) {
                    mWmParams.x = mScreenWidth;
                } else if (mWmParams.x < mScreenWidth / 2) {
                    mWmParams.x = 0;
                }
                mWindowManager.updateViewLayout(this, mWmParams);
                // 初始化
                mTouchStartX = mTouchStartY = 0;
                break;
        }
        return false;
    }
}
